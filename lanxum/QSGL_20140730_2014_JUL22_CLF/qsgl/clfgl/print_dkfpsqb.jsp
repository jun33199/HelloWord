<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.Constants"%>
<HTML>
<HEAD>
<TITLE>个人销售房屋代开发票申请表</TITLE>

<style>
@media Print {
	.Noprn {
		DISPLAY: none
	}
}
</style>


<style type="text/css">
.2-td2-noleft {
	font-size: 9pt;
	line-height: 20px;
	color: #3E6071;
	background-color: #ECF2F4;
	text-align: center;
	border-top: 0px solid;
	border-right: 0px solid;
	border-bottom: 1px solid #9BB4CA;
	border-left: 0px solid;
}

.2-td2-center-noBottom {
	font-size: 9pt;
	line-height: 20px;
	color: #3E6071;
	background-color: #ECF2F4;
	text-align: center;
	border-top: 0px solid;
	border-right: 0px solid #9BB4CA;
	border-bottom: 0px solid;
	border-left: 1px solid #9BB4CA;
	vertical-align: center;

}

</style>

<object ID='wb' WIDTH=0 HEIGHT=0
	CLASSID='CLSID:8856F961-340A-11D0-A96B-00C04FD705A2'></object>
	
<META content="text/html; charset=gb2312" http-equiv=Content-Type>
<SCRIPT language=JavaScript src="<%=static_file%>js/inputchecker.js"
	type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
<script language=JavaScript src="../js/clfgl_util.js" type="text/javascript"></script>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
</HEAD>
<BODY bgColor=#ffffff leftMargin=0 style="MARGIN: 0px" topMargin=0
	marginheight="0" marginwidth="0">
	<table width="10" border="0" align="center" cellspacing="0" class="table-99">
		<tr>
			<td colspan="15">
					<h3>个&nbsp;人&nbsp;销&nbsp;售&nbsp;已&nbsp;购&nbsp;住&nbsp;房&nbsp;涉&nbsp;税&nbsp;申&nbsp;报&nbsp;核&nbsp;定&nbsp;表&nbsp;<br>(代&nbsp;开&nbsp;发&nbsp;票&nbsp;申&nbsp;请&nbsp;表)</h3>
				          编号（流水号）：<bean:write name="ClfswjghdxxlrForm" property="sbbh"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;受理日期：<bean:write name="ClfswjghdxxlrForm" property="slrq"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;金额单位：元（角分）
			</td>
		</tr>
	</table>

	<html:form action="/clfgl/clfxxcj.do" type="POST">
		<TABLE align=center border=0 cellPadding=0 cellSpacing=0 width=90%>
			<tr>
				<td vAlign=top>
					<TABLE align=center border=0 cellSpacing=0 height="100%" width=90%>
						<tr>
							<td vAlign=top>
								<TABLE align=center cellSpacing=0  height="90%" class=table-99>
									<tr>
										<td>
											<table cellSpacing=0 class=tabled-99 width="100%" height="100%">
											<tr>
												<td colspan="6"  class="td1-center">
													<table cellSpacing=0>
														<tr>
															<td class=2-td1-left align="left" width="5%" rowspan="19">纳<br>税<br>人<br>填<br>写</td>
															<td colspan="6" class="2-td1-center">
																<table border=0 cellSpacing=0 width="100%">
																	<tr>
																		<td class=2-td2-noleft align="left" width="15%" nowrap="nowrap"><div align="left">申请人（卖方）姓名</div></td>
																		<td class=2-td2-left width="35%" id="sell_mcShow">&nbsp;</td>
																		<td class=2-td2-left width="10%" nowrap="nowrap"><div align="left">联系电话</div></td>
																		<td class=2-td2-center style="border-right: 0px solid;" width="40%" id="sell_lxdhShow"  colspan="3">&nbsp;</td>
																    </tr>
																	<tr>
																		<td class=2-td2-noleft align="left" width="15%" nowrap="nowrap"><div align="left">申请人（卖方）合法身份证件名称</div></td>
																		<td class=2-td2-left width="30%" id="sell_zjlxShow" >&nbsp;</td>
																		<td class=2-td2-left width="10%" nowrap="nowrap"><div align="left">证件号码</div></td>
																		<td class=2-td2-center style="border-right: 0px solid;" width="30%" id="sell_zjhmShow"  colspan="3">&nbsp;</td>
																	</tr>
																	<tr>
																		<td class=2-td2-noleft align="left" width="15%" nowrap="nowrap"><div align="left">付款人（买方）姓名</div></td>
																		<td class=2-td2-left width="35%" id="buy_mcShow">&nbsp;</td>
																		<td class=2-td2-left width="10%"><div align="left">联系电话</div></td>
																		<td class=2-td2-center style="border-right: 0px solid;" width="40%" id="buy_lxdhShow" colspan="3">&nbsp;</td>
																	</tr>
																	<tr>
																		<td class=2-td2-center-noBottom style="border-left: 0px solid;" align="left" width="15%" nowrap="nowrap"><div align="left">付款人（买方）合法身份证件名称</div></td>
																		<td class=2-td2-center-noBottom width="30%" id="buy_zjlxShow">&nbsp;</td>
																		<td class=2-td2-center-noBottom width="10%"><div align="left">证件号码</div></td>
																		<td class=2-td2-center-noBottom style="border-right: 0px solid;" width="40%" id="buy_zjhmShow" colspan="3">&nbsp;</td>
																	</tr>
																</table>
															</td>
														</tr>
															
															
															<tr>
																<td class=2-td2-left align="left" width="36%" nowrap="nowrap"><div align="left">申请人现住址</div></td>
																<td class=2-td2-center width="30%" colspan="5" id="sqrxzdzShow" nowrap="nowrap">&nbsp;</td>
															</tr>												
															<tr>
																<td class=2-td2-left align="left" width="15%" nowrap="nowrap"><div align="left">房屋详细地址</div></td>
																<td class=2-td2-left width="60%" id="fwzldzShow" nowrap="nowrap">&nbsp;</td>
																<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">是否为家庭唯一生活用房</div></td>
																<td class=2-td2-center width="10%" nowrap="nowrap" colspan="3">
																<div align="left"><!-- 是 □ &nbsp;&nbsp;&nbsp;&nbsp;否 □ -->  
																	<input type="checkbox" value="0" id="sfwyzf_Y" onclick="return false;">是</input> &nbsp;&nbsp;
																	<input type="checkbox" value="1" id="sfwyzf_N" onclick="return false;">否</input> 
																</div></td>													
															</tr>
															<tr>
																<td class=2-td2-left align="left" width="15%" nowrap="nowrap"><div align="left">房屋产权证号</div></td>
																<td class=2-td2-left width="30%" id="fwcqzhShow" nowrap="nowrap">&nbsp;</td>
																<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">房屋产权证住房种类</div></td>
																<td class=2-td2-left width="30%" id="fwcqzbzzflxmcShow" nowrap="nowrap">
			<%-- 														<div align="center">
																		<bean:define id="dta" name="ClfswjghdxxlrForm" property="fwcqzbzzflxList"/>
			                  												<html:select property="fwcqzbzzflxdm" style='width:90%'>
			                    												<html:options collection="dta" labelProperty="fwcqzbzzflxmc" property="fwcqzbzzflxdm"/>
			                  												</html:select>
			                  										</div> --%>&nbsp;</td>
																<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">申请开票金额（本次交易金额）</div></td>
																<td class=2-td2-center width="30%" id="sqkpjeShow">&nbsp;</td>													
															</tr>
															
															<tr>
														        <td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">房屋类型</div></td>
																<td class=2-td2-center width="30%" id="fwlxShow" colspan="5">
																<div align="left">
																	<!-- 楼房□&nbsp;&nbsp;平房□&nbsp;&nbsp;地下室□&nbsp;&nbsp; -->
																	<input type="checkbox" value="0" id="fwlx_0" onclick="return false;">楼房</input>&nbsp;&nbsp;
																	<input type="checkbox" value="1" id="fwlx_1" onclick="return false;">平房</input>&nbsp;&nbsp;
																	<input type="checkbox" value="2" id="fwlx_2" onclick="return false;">地下室</input>
																</div></td>
															</tr>
															<tr>
																<td class=2-td2-left align="left" width="15%" nowrap="nowrap"><div align="left">建成年代</div></td>
																<td class=2-td2-left width="30%" id="jcndShow" nowrap="nowrap">&nbsp;</td>
																<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">总楼层</div></td>
																<td class=2-td2-left width="30%" id="zlcShow">&nbsp;</td>
																<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">所在楼层</div></td>
																<td class=2-td2-center width="30%" id="szlcShow">&nbsp;</td>													
															</tr>
															<tr>
																<td class=2-td2-left align="left" width="15%"nowrap="nowrap"><div align="left">房屋建筑面积</div></td>
																<td class=2-td2-left width="30%" id="fwjzmjShow">&nbsp;</td>
																<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">原购房发票金额</div></td>
																<td class=2-td2-center width="30%" id="ygffpjeShow" colspan="3">&nbsp;</td>
															</tr>
															<tr>
																<td class=2-td2-left align="left" width="15%"nowrap="nowrap"><div align="left">购房证明日期</div></td>
																<td class=2-td2-left width="30%" id="gfzmrqShow">&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日</td>
																<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">买卖双方合同签订日期</div></td>
																<td class=2-td2-center width="30%" id="htwsqyrqShow" colspan="3">&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日</td>
															</tr>
															<tr>
														        <td class=2-td2-center width="15%" nowrap="nowrap" colspan="6"><div align="center">土地增值税申报方式</div></td>
															</tr>	
															<tr>
														        <td class=2-td2-center width="15%" nowrap="nowrap" colspan="6">
															        <div align="center">
															        	<!-- 提供购房发票□&nbsp;&nbsp;提供评估报告□&nbsp;&nbsp;核定征收□&nbsp;&nbsp; -->
															        	<input type="checkbox" value="0" id="tdzzssbfs_0" onclick="return false;">提供购房发票</input>&nbsp;&nbsp;
															        	<input type="checkbox" value="1" id="tdzzssbfs_1" onclick="return false;">提供评估报告</input>&nbsp;&nbsp;
															        	<input type="checkbox" value="2" id="tdzzssbfs_2" onclick="return false;">核定征收</input>
															        	
																	</div></td>
															</tr>														
															<tr>
																<td class=2-td2-left align="left" width="15%" nowrap="nowrap" colspan="2"><div align="center">提供购房发票填写下栏</div></td>
																<td class=2-td2-center align="left" width="15%" nowrap="nowrap" colspan="5"><div align="center">提供评估报告填写下列栏</div></td>
															</tr>												
															<tr>
														        <td class=2-td2-left width="15%" nowrap="nowrap" rowspan="4"><div align="left">取得房地产时所缴纳的契税金额</div></td>
														        <td class=2-td2-left width="15%" nowrap="nowrap" rowspan="4" id="qdfcqsjeShow">&nbsp;</td>
															</tr>
															<tr>
																<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">取得土地使用权所支付的金额</div></td>
																<td class=2-td2-center width="30%"  colspan="3" id="qdtdsyqzfjeShow">&nbsp;</td>													
															</tr>
															<tr>
																<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">旧房及建筑物的评估价格</div></td>
																<td class=2-td2-center width="30%"  colspan="3" id="jfpgjgShow">&nbsp;</td>													
															</tr>
															<tr>
																<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">价格评估费用</div></td>
																<td class=2-td2-center width="30%" colspan="3" id="jgpgfyShow">&nbsp;</td>													
															</tr>																								
															<tr>
																<td class=2-td2-left align="left" width="15%"nowrap="nowrap"><div align="left">房地产中介机构名称</div></td>
																<td class=2-td2-left width="30%" id="fdczjjgmcShow">&nbsp;</td>
																<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">地税计算机代码</div></td>
																<td class=2-td2-center width="30%" id="fdczjjsjdmShow" colspan="3">&nbsp;</td>
															</tr>
															<tr>
																<td class=2-td2-left align="left" width="15%"nowrap="nowrap"><div align="left">地税税务登记号码</div></td>
																<td class=2-td2-left width="30%" id="fdczjswdjzhShow">&nbsp;</td>
																<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">联系电话</div></td>
																<td class=2-td2-center width="30%" id="fdczjlxdhShow" colspan="3">&nbsp;</td>
															</tr>
															<tr>
																<td class=2-td2-left align="left" width="15%"nowrap="nowrap"><div align="left">房地产经纪人姓名</div></td>
																<td class=2-td2-left width="30%" id="fdczjjjrShow">&nbsp;</td>
																<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">联系电话</div></td>
																<td class=2-td2-center width="30%" id="fdczjjjrlxdhShow" colspan="3">&nbsp;</td>
															</tr>
															<tr>
																<td class=2-td2-left align="left" width="15%"nowrap="nowrap"><div align="left">房地产经纪人身份证号码</div></td>
																<td class=2-td2-left width="30%" id="fdczjjjrzjhmShow">&nbsp;</td>
																<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">经纪人资格证书号码</div></td>
																<td class=2-td2-center width="30%" id="fdczjjjrzgzshShow" colspan="3">&nbsp;</td>
															</tr>
														
													</table>
												</td>
											</tr>
											
												
												<tr>
													<td colspan="6">
														<table cellSpacing=0 width="100%">
															<tr >
																<td class=2-td1-left rowspan="8" width="1%">税<br>务<br>机<br>关<br>确<br>认</td>
																<td class=2-td2-left style="border-top: 1px solid  #9BB4CA;" width="18%" nowrap="nowrap"><div align="left">产权证标注建筑面积</div></td>
																<td class=2-td2-center  style="border-top: 1px solid  #9BB4CA;" width="80%" colspan="5">
																<div align="left">
																	<!-- 140平米（含）以下□&nbsp;&nbsp;140平米以上□&nbsp;&nbsp; -->
																	<input type="checkbox" value="0" id="cqzbzjzmjfl_0" onclick="return false;">140平米（含）以下</input>&nbsp;&nbsp;
																	<input type="checkbox" value="1" id="cqzbzjzmjfl_1" onclick="return false;">140平米以上</input>
																</div></td>
															</tr>
															<tr>
																<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">每平米交易单价</div></td>
																<td class=2-td2-left width="30%" id="mpmjydjShow">&nbsp;</td>	
																<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">房屋所在地土地级次</div></td>
																<td class=2-td2-left width="30%" id="tdjcmcShow">&nbsp;</td>																								
																<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">普通住房最高限价</div></td>
																<td class=2-td2-center width="30%" id="ptzfzgxjShow">&nbsp;</td>
															</tr>
															<tr>
																<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">房屋容积率</div></td>
																<td class=2-td2-center width="15%" colspan="6">
																<div align="left">
																<!-- 1.0以下□&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1.0（含）以上□ -->
																
																	<input type="checkbox" value="0" id="fwrjl_0" onclick="return false;">1.0以下</input>&nbsp;&nbsp;
																	<input type="checkbox" value="1" id="fwrjl_1" onclick="return false;">1.0（含）以上</input>													
																	</div></td>
															</tr>
															<tr>
																<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">划分标准</div></td>
																<td class=2-td2-center width="15%" colspan="6"><div align="left">
																<!-- 普通住宅□&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;非普通住宅□ -->
																	<input type="checkbox" value="0" id="hfbz_0" onclick="return false;">普通住宅</input>&nbsp;&nbsp;
																	<input type="checkbox" value="1" id="hfbz_1" onclick="return false;">非普通住宅</input>	
																</div></td>
															</tr>
															<tr>
																<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">住房使用时间</div></td>
																<td class=2-td2-center width="15%" colspan="6">
																<div align="left">
																<!-- 5年（含）以上□&nbsp;&nbsp;&nbsp;&nbsp;5年以下3年以上□&nbsp;&nbsp;&nbsp;&nbsp;3年（含）以下 □ -->
																
																	<input type="checkbox" value="0" id="zfsjsjfl_0" onclick="return false;">5年（含）以上</input>&nbsp;&nbsp;
																	<input type="checkbox" value="1" id="zfsjsjfl_1" onclick="return false;">5年以下3年以上</input>&nbsp;&nbsp;
																	<input type="checkbox" value="2" id="zfsjsjfl_2" onclick="return false;">3年（含）以下</input>													
																</div></td>
															</tr>
															<tr>
																<td class=2-td2-center width="15%" colspan="6"><div align="center">
			<!-- 													免征营业税□
																	&nbsp;&nbsp;&nbsp;&nbsp;全额征收营业税□
																	&nbsp;&nbsp;&nbsp;&nbsp;差额征收营业税□
																	&nbsp;&nbsp;&nbsp;&nbsp;免征个人所得税□ <br>征收个人所得税□&nbsp;&nbsp;&nbsp;&nbsp;免征土地增值税□&nbsp;&nbsp;&nbsp;&nbsp;
																	全额征收土地增值税□&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
																	<input type="checkbox" value="0" id="yyszsfs_0" onclick="return false;">免征营业税</input>
																	<input type="checkbox" value="1" id="yyszsfs_1" onclick="return false;">全额征收营业税</input>
																	<input type="checkbox" value="2" id="yyszsfs_2" onclick="return false;">差额征收营业税</input>	
																	
																	<input type="checkbox" value="0" id="grsdszsfs_0" onclick="return false;">免征个人所得税</input>
																	<input type="checkbox" value="1" id="grsdszsfs_1" onclick="return false;">征收个人所得税</input>
																	
																	<input type="checkbox" value="0" id="tdzsszsfs_0" onclick="return false;">不征收土地增值税</input>
																	<input type="checkbox" value="1" id="tdzsszsfs_1" onclick="return false;">减半征收土地增值税</input>
																	<input type="checkbox" value="2" id="tdzsszsfs_2" onclick="return false;">全额征收土地增值税</input>	
																	
																	</div></td>
															</tr>
															<tr>
																<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">计税收入确认方式及金额</div></td>
																<td class=2-td2-center width="15%" colspan="5" id="jssrqrfsShow">
																<div align="left">
			<!-- 													合同价格□&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;金额：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;核定计税价格□
																	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;金额：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
																	<input type="checkbox" value="0" id="jssrqrfs_0" onclick="return false;">合同价格</input>
																	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;金额：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																	<input type="checkbox" value="1" id="jssrqrfs_1" onclick="return false;">核定计税价格</input>
																	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;金额：
																	</div></td>
															</tr>	
															
															<tr>
																<td colspan="6" class=2-td2-center>
																	<table border=0 cellSpacing=0>
																		<tr>
																			<td class=2-td2-center style="border-left: 0px solid; border-right: 0px solid;" width="15%" colspan="8">扣除金额确认</td>
																		</tr>
																		<tr>
																			<td class=2-td2-left style="border-left: 0px solid;" align="left" width="15%" nowrap="nowrap"><div align="left">原购房发票价格</div></td>
																			<td class=2-td2-left width="20%" id="ygffpjeShow_1">&nbsp;</td>
																			<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">住房评估价格</div></td>
																			<td class=2-td2-left width="20%" id="zfpgjgShow">&nbsp;</td>
																			<td class=2-td2-left width="10%"><div align="left">其中：<br>旧房及建筑物评估价格</div></td>
																			<td class=2-td2-center style="border-right: 0px solid;" width="20%" colspan="3" id="jfpgjgShow1">&nbsp;</td>													
																		</tr>
																		<tr>
																			<td class=2-td2-left style="border-left: 0px solid;" width="15%" nowrap="nowrap"><div align="left">营业税税额</div></td>
																			<td class=2-td2-left width="15%" colspan="3"><div align="left" id="yys_show">计税收入&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;×5%&nbsp;&nbsp;&nbsp;=</div>
																			</td>
																			<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">完税凭证号</div></td>
																			<td class=2-td2-center width="30%"colspan="3" style="border-right: 0px solid;">&nbsp;</td>														
																		</tr>
																		<tr>
																			<td class=2-td2-left style="border-left: 0px solid;" width="15%" nowrap="nowrap"><div align="left">城建税税额</div></td>
																			<td class=2-td2-left width="15%" colspan="3"><div align="left" id="cjs_show">营业税税额&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;×5%&nbsp;&nbsp;&nbsp;=</div>
																			</td>
																			<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">完税凭证号</div></td>
																			<td class=2-td2-center width="30%" colspan="3" style="border-right: 0px solid;">&nbsp;</td>	
																		</tr>
																		<tr>
																			<td class=2-td2-left style="border-left: 0px solid;" width="15%" nowrap="nowrap"><div align="left">教育费附加金额</div></td>
																			<td class=2-td2-left width="15%" colspan="3"><div align="left" id="jyffj_show">营业税税额&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;×3%&nbsp;&nbsp;&nbsp;=</div>
																			</td>
																			<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">完税凭证号</div></td>
																			<td class=2-td2-center width="30%" colspan="3" style="border-right: 0px solid;">&nbsp;</td>														
																		</tr>
						 												<tr>
																			<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">地方教育附加金额</div></td>
																			<td class=2-td2-center width="15%" colspan="3"><div align="left" id="dfjyfj_show">营业税税额&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;×2%&nbsp;&nbsp;&nbsp;=</div>
																			<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">完税凭证号</div></td>
																			<td class=2-td2-center width="30%" colspan="3" style="border-right: 0px solid;">&nbsp;</td>																				
																			</td>
																		</tr>
																		<!--<tr>
																			<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">营城教合计</div></td>
																			<td class=2-td2-center width="15%" colspan="3">&nbsp;</td>
																		</tr> -->
																		<tr>
																			<td class=2-td2-left style="border-left: 0px solid;" width="15%" nowrap="nowrap"><div align="left">土地增值税(按发票或评估报告征收)</div></td>
																			<td class=2-td2-left width="15%" colspan="3"><div align="left" id="tdzzs_FP_PGBG_show">增值额×适用税率-扣除项目金额合计×速算扣除系数=<br>&nbsp;</div></td>
																			<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">完税凭证号</div></td>
																			<td class=2-td2-center width="30%" colspan="3" style="border-right: 0px solid;">&nbsp;</td>	
																		</tr>
																		<tr>
																			<td class=2-td2-left style="border-left: 0px solid;" width="15%" nowrap="nowrap"><div align="left">土地增值税（核定征收）</div></td>
																			<td class=2-td2-left width="15%" colspan="3"><div align="left" id="tdzzs_HD_show">计税收入&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;×1%&nbsp;&nbsp;&nbsp;=</div>
																			</td>
																			<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">完税凭证号</div></td>
																			<td class=2-td2-center width="30%" colspan="3" style="border-right: 0px solid;">&nbsp;</td>
																		</tr>
																		<tr>
																			<td class=2-td2-left style="border-left: 0px solid;" width="15%" nowrap="nowrap"><div align="left">土地增值税（减半征收）</div></td>
																			<td class=2-td2-left width="15%" colspan="3"><div align="left" id="tdzzs_JB_show">土地增值税应纳税款&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ÷2&nbsp;&nbsp;&nbsp;=</div>
																			</td>
																			<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">完税凭证号</div></td>
																			<td class=2-td2-center width="30%" colspan="3" style="border-right: 0px solid;">&nbsp;</td>
																		</tr>												
																		<tr>
																			<td class=2-td2-left style="border-left: 0px solid;" width="15%" nowrap="nowrap"><div align="left">个人所得税（核定征收）</div></td>
																			<td class=2-td2-left width="15%" colspan="3"><div align="left" id="grsds_HD_show">计税收入&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;×1%&nbsp;&nbsp;&nbsp;=</div>
																			</td>
																			<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">完税凭证号</div></td>
																			<td class=2-td2-center width="30%" colspan="3" style="border-right: 0px solid;">&nbsp;</td>
																		</tr>
																		<tr>
																			<td class=2-td2-left style="border-left: 0px solid;" width="15%" nowrap="nowrap"><div align="left">个人所得税（按实征收）</div></td>
																			<td class=2-td2-left width="15%" colspan="3"><div align="left" id="grsds_AS_show">（房屋转让收入-房屋原值-税金-合理费用）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;×20%&nbsp;&nbsp;&nbsp;=</div>
																			</td>
																			<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">完税凭证号</div></td>
																			<td class=2-td2-center width="30%" colspan="3" style="border-right: 0px solid;">&nbsp;</td>
																		</tr>
			<!-- 															<tr>
																			<td class=2-td2-left width="15%" nowrap="nowrap">&nbsp;</td>
																			<td class=2-td2-left width="15%" colspan="3">&nbsp;</td>
																			<td class=2-td2-left width="15%" >&nbsp;</td>
																			<td class=2-td2-center width="15%" colspan="3">&nbsp;</td>
																		</tr> -->												
						<!-- 												<tr>
																			<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">印花税</div></td>
																			<td class=2-td2-center width="15%" colspan="3">&nbsp;</td>
																		</tr>
																		<tr>
																			<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">契税</div></td>
																			<td class=2-td2-center width="15%" colspan="3">&nbsp;</td>
																		</tr> -->
																		<tr>
																			<td class=2-td2-left style="border-left: 0px solid;" width="15%" nowrap="nowrap"><div align="left">各项税费合计</div></td>
																			<td class=2-td2-center width="15%" colspan="7" style="border-right: 0px solid;"><div align="left" id="sjhjjeShow">&nbsp;</div></td>
																		</tr>
																		<tr>
																			<td  colspan="8" width="100%">
																				<table cellSpacing=0 width="100%">
																					<tr>
																						<td class=2-td2-center-noBottom style="border-left: 0px solid;" nowrap="nowrap"><div align="left">发票代码</div></td>
																						<td class=2-td2-center-noBottom width="25%">&nbsp;</td>
																						<td class=2-td2-center-noBottom  nowrap="nowrap"><div align="left">发票号码</div></td>
																						<td class=2-td2-center-noBottom width="25%" >&nbsp;</td>
																						<td class=2-td2-center-noBottom  nowrap="nowrap"><div align="left">信息码</div></td>
																						<td class=2-td2-center-noBottom width="25%" >&nbsp;</td>																																						
																						<td class=2-td2-center-noBottom  nowrap="nowrap"><div align="left">开具金额</div></td>
																						<td class=2-td2-center-noBottom style="border-right: 0px solid;" width="100%">&nbsp;</td>
																					</tr>												
																				</table>
																			</td>
																		</tr>
																	</table>
															    </td>
															</tr>
															
														</table>
													</td>
												</tr>
												
												<tr>
													<td colspan="6" >
														<table cellSpacing=0 width="100%">
															<tr>
																<td class=2-td1-left width="1%" rowspan="2">申<br>明</td>
																<td class=2-td2-center width="15%" colspan="6" style="border-top: 1px solid #9BB4CA;"><div align="left"><h5>以上提供的信息真实、准确，愿承担相应的法律责任。</h5>
																<h5>纳税人签章： &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日
																	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																	经办人签章：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;经办人身份证号码：&nbsp;&nbsp;&nbsp; 
																</h5></div></td>
															</tr>
															<tr>
																<td class=2-td2-left align="left" width="15%" nowrap="nowrap"><div align="left">受理人员签字</div></td>
																<td class=2-td2-left width="30%">&nbsp;</td>
																<td class=2-td2-left width="15%" nowrap="nowrap" ><div align="left">复核人员签字</div></td>
																<td class=2-td2-center width="30%" colspan="3">&nbsp;</td>
															</tr>
															<tr>
																<td colspan="7" class=2-td1-left style="border-right: 1px solid #9BB4CA; border-top: 0px solid;"><div align="right">受理单位章：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div></td>
															</tr>
															<tr>
																<td colspan="7">
																	<table width="99%" border="0" cellpadding="0" cellspacing="0">
																		<tr>
																			<td>
																			<hr width="100%" size="1">
																			</td>
																			<td  align="center" class="black9"><strong><font
																				color="#0000FF">填表说明</font></strong></td>
																			<td>
																			<hr width="100%" size="1">
																			</td>
																		</tr>
																	</table>
																	<table   border="1" align="center" cellpadding="1"
																		cellspacing="1" bordercolor="#FFFFFF" class="black9">
																		<tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
																			<td>
																			&nbsp;&nbsp;1.本表适用于个人销售已购住房办理涉税申报缴纳营业税、城建税、教育费附加、个人所得税、土地增值税等税种及代开发票申请等涉税事宜；<br>
																			&nbsp;&nbsp;2.纳税人如果同时转让两处或两处以上房地产，应分别填报本表；<br>
																			&nbsp;&nbsp;3.本表一式两份，一份返还纳税人，一份税务机关留存；<br>
																			&nbsp;&nbsp;4.“房屋详细地址”是指本次销售的已购住房的具体地址；<br>
																			&nbsp;&nbsp;5.“房屋产权证所标注的住房种类”按照房产证上的相关内容（或戳记）填写，一般是指房改房、经济适用住房、比照经济适用住房管理用房、商品住房等；<br>
																			&nbsp;&nbsp;6.“房屋产权证号”是指本次销售的商品住房的产权证书号码。<br>
																			&nbsp;&nbsp;7.“申请开票金额（本次交易金额）”是指拟向税务机关申请的开具发票金额，即本次实际交易的实际金额；纳税人应如实填报交易金额，对交易价格明显偏低又无正当理由的，由税务机关核定征收。;<br>
																			&nbsp;&nbsp;8.“购房证明日期”是指能够证明购房准确日期的时间，如契税完税证明或核定证明开具日期、房屋产权证书填发日期等证明购房日期;<br>
																			&nbsp;&nbsp;9.如申请人（卖方）委托房地产交易中介机构办理房屋交易的各项事宜，则应如实填列房地产交易中介机构及房地产经纪人的相关信息;<br>
																			&nbsp;&nbsp;10.“普通住房最高限价”即该土地级次的平均价格乘以1.2倍后的金额标准;<br>
																			&nbsp;&nbsp;11.当纳税人选择“提供购房发票”方式申报时，请申报填写“取得房地产时所缴纳的契税”栏，是指：购房时能够提供契税完税凭证所载缴纳的契税金额;<br>
																			&nbsp;&nbsp;12.当纳税人选择“提供评估报告”方式申报时，请按提供评估报告相应各栏的内容进行申报填写：（1）取得土地使用权所支付的金额栏，是指：纳税人为取得土地使用权时所实际支付的地价款和按国家统一规定交纳的有关费用（2）旧房及建筑物的评估价格栏，是指：中介机构按照重置成本评估法，评定的房屋及建筑物价格；（3）价格评估费用栏，是指纳税人对房屋及建筑物需要进行价格评估时所支付的评估费用。<br>
																			</td>
																		</tr>
																	</table>	
																</td>
															</tr>															
														</table>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</TABLE></td>
						</tr>
					</TABLE></td>
			</tr>
		</TABLE>
		
	</html:form>
	<div class="Noprn">
		<br>
	</div>
	<div id="dayin" align="center" class="Noprn">
		<img alt="执行打印操作" style="cursor: hand" onclick="printTab()"
			src="<%=static_file%>images/dayin1.jpg" width="79" height="22"
			id="dayin1" />&nbsp;&nbsp; <img alt="退出打印页面" style="cursor: hand"
			onclick="window.close()" src="<%=static_file%>images/guanbi1.jpg"
			width="79" height="22" id="guanbi" />&nbsp;&nbsp;
	</div>
</BODY>
<script type="text/javascript">
	function printTab() {
		//document.all.dayin.style.display = "none";
		window.print();
		//document.all.dayin.style.display = "";
	}
	function wirteback() {
		var father = window.opener.document
				.all("org.apache.struts.taglib.html.TOKEN");
		var child = document.all('org.apache.struts.taglib.html.TOKEN');
		father.value = child.value;
		window.opener.document.forms[0].target = "_self";
	}
	
	
	
	
	//wb 打印
	
	//设置纸张方向
/*  	 function window.onload()
 {
  shell = new ActiveXObject("WScript.Shell");  
  setTimeout("shell.sendKeys('%fu')",0); //works same as alt+F+U
  setTimeout("shell.sendKeys('%a')",0); // Orientation: Select "Landscape"
  setTimeout("shell.sendKeys('{ENTER}')",0);
  }  */


	
	
var HKEY_Root,HKEY_Path,HKEY_Key;    
HKEY_Root="HKEY_CURRENT_USER";    
HKEY_Path="\\Software\\Microsoft\\Internet Explorer\\PageSetup\\";    
    //设置网页打印的页眉页脚为空    
function PageSetup_Null()   
 {    
   try {    
   var Wsh=new ActiveXObject("WScript.Shell");    
   HKEY_Key="header";    
   Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"");    
   HKEY_Key="footer";    
   Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"");
   }  catch(e){
   }    
 }

 //恢复网页打印的页眉页脚   

 
  function PageSetup_default()   
 {    
   try {    
           var Wsh=new ActiveXObject("WScript.Shell");    
   HKEY_Key="header";    
   Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"&w&b页码,&p/&P");    
   HKEY_Key="footer";    
   Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"&u&b&d");    
   }  catch(e){}    
 }  

 function printsetup(){ 
		//打印页面设置 
		wb.execwb(8,1); 
	} 
	
 function printpreview(){ 
		//打印页面预览 
		PageSetup_Null(); 
		wb.execwb(7,1); 
	} 

 function printit() 
 { 
  if (confirm('确定打印吗？')) { 
      PageSetup_Null(); 
      wb.execwb(6,6);
  } 
 } 
</script>

<script type="text/javascript">
//金额格式
function  changeJE(je)
{
	var strJe = je.toString();
    var rs = strJe.indexOf('.');  
     
    if (rs < 0) {  
         rs = strJe.length;  
         strJe += '.';  
    }  
    while (strJe.length <= rs + 2) {  
         strJe += '0';  
    }  
    return strJe;  
}
	showSaveData();
	//此函数是保存完成之后用于显示已保存信息的函数
	function showSaveData() {
		//受理日期
		
/* 		var res_slrq = '<bean:write name="ClfswjghdxxlrForm" property="slrq"/>';
		putObjectValue("", 'slrqShow', transTime("合同网上签约日期", slrq)); */
		//合同网上签约日期
		var res_htwsqyrq = '<bean:write name="ClfswjghdxxlrForm" property="htwsqyrq"/>';
		putObjectValue("", 'htwsqyrqShow', transTime("合同网上签约日期", res_htwsqyrq,"yyyy年mm月dd日"));
		//交易房屋地址
		var res_fwzldz = '<bean:write name="ClfswjghdxxlrForm" property="fwzldz"/>';
		putObjectValue("", "fwzldzShow", res_fwzldz);
		//房屋权属转移类型
		var res_fwqszylxmc = "";
		putObjectValue("", "fwqszylxmcShow",
				res_fwqszylxmc);
		//房屋产权证号
		var res_fwcqzh = '<bean:write name="ClfswjghdxxlrForm" property="fwcqzh"/>';
		putObjectValue("", "fwcqzhShow", res_fwcqzh);

		//房屋所有权证填发日期
/* 		var res_fwsyqztfrq = '<bean:write name="ClfswjghdxxlrForm" property="fwsyqztfrq"/>';
		putObjectValue("", "fwsyqztfrqShow", transTime("房屋所有权证填发日期",
				res_fwsyqztfrq,"yyyy年mm月dd日")); */
		//房屋建筑面积
		var res_fwjzmj = '<bean:write name="ClfswjghdxxlrForm" property="fwjzmj"/>';
		putObjectValue("", "fwjzmjShow", res_fwjzmj + "平方米");
		
    	//卖方信息
    	var sellerInfo = '<bean:write name="ClfswjghdxxlrForm" property="all_sellerInfo"/>';
    	setBuyorSellInfo(sellerInfo,"sell");
    	//alert(sellerInfo);
    	
    	//买方信息
    	var buyyerInfo = '<bean:write name="ClfswjghdxxlrForm" property="all_buyerInfo"/>';
    	setBuyorSellInfo(buyyerInfo,"buy");
    	//alert(buyyerInfo);
    	
    	//==============================核定信息====================================
    		
    	//申请人现住址
    	var sqrxzdz = '<bean:write name="ClfswjghdxxlrForm" property="sqrxzdz"/>';	
    	putObjectValue("", "sqrxzdzShow", sqrxzdz);
    	
    	//是否为家庭唯一生活用房
    	var jtwyshyhbz = '<bean:write name="ClfswjghdxxlrForm" property="jtwyshyhbz"/>';	
    	if(jtwyshyhbz != "" && jtwyshyhbz ==<%=Constants.ONLY_ROOM_YES%>){
    		setCheckbox("sfwyzf_Y",true);
    	}else if (jtwyshyhbz != "" && jtwyshyhbz ==<%=Constants.ONLY_ROOM_NOT%>){
    		setCheckbox("sfwyzf_N",true);
    	}
    	
    	//房屋产权证住房种类
    	var fwcqzbzzflxmc = '<bean:write name="ClfswjghdxxlrForm" property="fwcqzbzzflxmc"/>';
    	putObjectValue("", "fwcqzbzzflxmcShow", fwcqzbzzflxmc);
    	
    	
    	
    	
    	//申请开票金额（本次交易金额）
    	
    	var htzj = '<bean:write name="ClfswjghdxxlrForm" property="htzj"/>';
    	putObjectValue("", "sqkpjeShow", changeJE(htzj));
    	
    	//房屋类型
    	var fwlxdm = '<bean:write name="ClfswjghdxxlrForm" property="fwlxdm"/>';
    	if(fwlxdm != "" &&(fwlxdm == <%=Constants.FWLX_BUILDINGS%>)){
    		setCheckbox("fwlx_0",true);
    	}else if (fwlxdm != "" &&(fwlxdm == <%=Constants.FWLX_BUNGALOW%>)){
    		setCheckbox("fwlx_1",true);
    	}else if (fwlxdm != "" &&(fwlxdm == <%=Constants.FWLX_BASEMENT%>)){
    		setCheckbox("fwlx_2",true);
    	}
    	
    	
    	//建成年代
    	var jcnd = '<bean:write name="ClfswjghdxxlrForm" property="jcnd"/>';
    	putObjectValue("", "jcndShow", jcnd);
    	//总楼层
    	 var zlc_show = '<bean:write name="ClfswjghdxxlrForm" property="zlc_show"/>';
    	 putObjectValue("", "zlcShow", zlc_show);
    	
    	//所在楼层
    	 var szlc_show = '<bean:write name="ClfswjghdxxlrForm" property="szlc_show"/>';
    	 putObjectValue("", "szlcShow", szlc_show);
    	//原购房发票金额
    	var ygffpje = '<bean:write name="ClfswjghdxxlrForm" property="ygffpje"/>';
    	putObjectValue("", "ygffpjeShow", ygffpje);
    	//购房证明日期
    	var gfzmrq = '<bean:write name="ClfswjghdxxlrForm" property="gfzmrq"/>';
    	//putObjectValue("", "gfzmrqShow", gfzmrq);
    	putObjectValue("", 'gfzmrqShow', transTime("购房证明日期", gfzmrq,"yyyy年mm月dd日"));
    	
    	//土地增值税申报方式
    	var tdzzssbfs = '<bean:write name="ClfswjghdxxlrForm" property="tdzzssbfs"/>';
    	if(tdzzssbfs != "" && tdzzssbfs == <%=Constants.TDZSS_SB_GFFP%>){
    		setCheckbox("tdzzssbfs_0",true);
    	}else if (tdzzssbfs != "" && tdzzssbfs == <%=Constants.TDZSS_SB_PGBG%>){
    		setCheckbox("tdzzssbfs_1",true);	
    	}else if (tdzzssbfs != "" && tdzzssbfs == <%=Constants.TDZSS_SB_HDZS%>){
    		setCheckbox("tdzzssbfs_2",true);	
    	}
    	
    	//提供购房发票填写下栏
    	
    	//提供评估报告填写下列栏
    	  //@@取得房地产时所缴纳的契税金额
    	  var qdfcqsje = '<bean:write name="ClfswjghdxxlrForm" property="qdfcqsje"/>';
    	  putObjectValue("", "qdfcqsjeShow", qdfcqsje);
    	  
    	//提供评估报告填写下列栏  
    	  //@@取得土地使用权所支付的金额
    	  var qdtdsyqzfje = '<bean:write name="ClfswjghdxxlrForm" property="qdtdsyqzfje"/>';
    	  putObjectValue("", "qdtdsyqzfjeShow", qdtdsyqzfje);
    	  //@@旧房及建筑物的评估价格
    	  var jfpgjg = '<bean:write name="ClfswjghdxxlrForm" property="jfpgjg"/>';
    	  putObjectValue("", "jfpgjgShow", jfpgjg);
    	  putObjectValue("", "jfpgjgShow1", jfpgjg);
    	  //@@价格评估费用
    	  var jgpgfy = '<bean:write name="ClfswjghdxxlrForm" property="jgpgfy"/>';
    	  putObjectValue("", "jgpgfyShow", jgpgfy);
    	  
    	  
    	  
    	
    	//产权证标注建筑面积 
    	var cqzbzjzmjfl = '<bean:write name="ClfswjghdxxlrForm" property="cqzbzjzmjfl"/>';
    	if(cqzbzjzmjfl != "" && cqzbzjzmjfl == <%=Constants.CQZBZ_JLMX_LOW%>){
    		setCheckbox("cqzbzjzmjfl_0",true);
    		
    	}else if (cqzbzjzmjfl != "" && cqzbzjzmjfl == <%=Constants.CQZBZ_JLMX_HIGH%>){
    		setCheckbox("cqzbzjzmjfl_1",true);
    		
    	}
    	
    	
    	//每平米交易单价
    	var mpmjydj = '<bean:write name="ClfswjghdxxlrForm" property="mpmjydj"/>';
    	putObjectValue("", "mpmjydjShow", mpmjydj);
    	
    	//房屋所在地土地级次
    	var tdjcmc = '<bean:write name="ClfswjghdxxlrForm" property="tdjcmc"/>';
    	putObjectValue("", "tdjcmcShow", tdjcmc);
    	
    	//普通住房最高限价
    	var ptzfzgxj = '<bean:write name="ClfswjghdxxlrForm" property="ptzfzgxj"/>';
    	putObjectValue("", "ptzfzgxjShow", ptzfzgxj);
    	
    	//房屋容积率
    	var fwrjl = '<bean:write name="ClfswjghdxxlrForm" property="fwrjl"/>';
    	if(fwrjl != "" && fwrjl == <%=Constants.FWRJL_LOW%>){
    		setCheckbox("fwrjl_0",true);
    	}else if  (fwrjl != "" &&  fwrjl == <%=Constants.FWRJL_HIGH%>){
    		setCheckbox("fwrjl_1",true);
    	}
    	
    	//划分标准
    	var hfbz = '<bean:write name="ClfswjghdxxlrForm" property="hfbz"/>';
    	if(hfbz != "" && hfbz == <%=Constants.HFBZ_PT%>){
    		setCheckbox("hfbz_0",true);
    	}else if (hfbz != "" && hfbz == <%=Constants.HFBZ_NOTPT%>){
    		setCheckbox("hfbz_1",true);
    	}
    	
    	//住房使用时间
    	var zfsjsjfl = '<bean:write name="ClfswjghdxxlrForm" property="zfsjsjfl"/>';
    	if(zfsjsjfl != "" && zfsjsjfl == <%=Constants.ZFSYSJ_FIVE%>){
    		setCheckbox("zfsjsjfl_0",true);
    	}else if (zfsjsjfl != "" && zfsjsjfl == <%=Constants.ZFSYSJ_THREETOFOIVE%>){
    		setCheckbox("zfsjsjfl_1",true);
    	}else if (zfsjsjfl != "" && zfsjsjfl == <%=Constants.ZFSYSJ_THREE%>){
    		setCheckbox("zfsjsjfl_2",true);
    	}
    	
    	
    	//营业税征收方式 -- 免征营业税□     全额征收营业税□     差额征收营业税□    
    	var yyszsfs = '<bean:write name="ClfswjghdxxlrForm" property="yyszsfs"/>';
    	
    	if(yyszsfs != "" && yyszsfs == <%=Constants.YSSZSFS_NOT%>){
    		setCheckbox("yyszsfs_0",true);
    	}else if (yyszsfs != "" && yyszsfs == <%=Constants.YSSZSFS_ALL%>){
    		setCheckbox("yyszsfs_1",true);
    	}else if (yyszsfs != "" && yyszsfs == <%=Constants.YSSZSFS_MINUS%>){
    		setCheckbox("yyszsfs_2",true);
    	}
    	
    	
    	
    	//个人所得税征收方式 -- 免征个人所得税□ 征收个人所得税□   
    	var grsdszsfs = '<bean:write name="ClfswjghdxxlrForm" property="grsdszsfs"/>';
    	
    	if(grsdszsfs != "" && grsdszsfs == <%=Constants.GRSDSZSFS_FREE%>){
    		setCheckbox("grsdszsfs_0",true);
    	}else if (grsdszsfs != "" && grsdszsfs == <%=Constants.GRSDSZSFS_ZS%>){
    		setCheckbox("grsdszsfs_1",true);
    	}
    	
    	
    	//土地增值税征收方式 -- 不征收土地增值税    免征土地增值税□    全额征收土地增值税□
    	var tdzsszsfs = '<bean:write name="ClfswjghdxxlrForm" property="tdzsszsfs"/>';
    	if(tdzsszsfs != "" && tdzsszsfs == <%=Constants.TDZZSZSFS_NOT%>){//不征收土地增值税
    		setCheckbox("tdzsszsfs_0",true);
    		
    	}else if (tdzsszsfs != "" && tdzsszsfs == <%=Constants.TDZZSZSFS_ZS%>){//全额征收土地增值税
    		setCheckbox("tdzsszsfs_2",true);
    		
    	}
    	
    	
    	//计税收入确认方式及金额
    	var jssrqrfs = '<bean:write name="ClfswjghdxxlrForm" property="jssrqrfs"/>';
    	var jsjeSubmit = '<bean:write name="ClfswjghdxxlrForm" property="jsje"/>';
    	
    	
    	var blankHTML="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
    	var jeHTML=blankHTML+"金额：";
    	
    	var jssrqrfs_0_true = "<input type=\"checkbox\" value=\"0\" id=\"jssrqrfs_0\" onclick=\"return false\" checked=\"true\">合同价格</input>"+jeHTML;
    	var jssrqrfs_0_false = "<input type=\"checkbox\" value=\"0\" id=\"jssrqrfs_0\" onclick=\"return false\">合同价格</input>"+jeHTML;
    	
    	var jssrqrfs_1_true = "<input type=\"checkbox\" value=\"1\" id=\"jssrqrfs_1\" onclick=\"return false\" checked=\"true\">核定计税价格</input>"+jeHTML;
        var jssrqrfs_1_false = "<input type=\"checkbox\" value=\"1\" id=\"jssrqrfs_1\" onclick=\"return false\">核定计税价格</input>"+jeHTML;
        
        
    	
    	if (jssrqrfs != "" && jssrqrfs == <%=Constants.JSSRQRFS_HTJG%>){
    		putObjectValue("", "jssrqrfsShow", jssrqrfs_0_true+""+jsjeSubmit+""+blankHTML+""+jssrqrfs_1_false+""+blankHTML);
    		//setCheckbox("jssrqrfs_0",true);
    	}else if (jssrqrfs != "" && jssrqrfs == <%=Constants.JSSRQRFS_HDJSJG%>){
    		//putObjectValue("", "jssrqrfsShow", jssrqrfs_1_true+""+jsjeSubmit+""+blankHTML+""+jssrqrfs_0_false+""+blankHTML);
    		putObjectValue("", "jssrqrfsShow", jssrqrfs_0_false+""+blankHTML+""+jssrqrfs_1_true+""+jsjeSubmit+""+blankHTML+"");
    		//setCheckbox("jssrqrfs_1",true);
    	}
		
		
		
    	
   //--------------------------------- 	
    	//房地产中介机构名称
    	var fdczjjgmc = '<bean:write name="ClfswjghdxxlrForm" property="fdczjjgmc"/>';
    	putObjectValue("", "fdczjjgmcShow", fdczjjgmc);
    	//地税计算机代码
    	var fdczjjsjdm = '<bean:write name="ClfswjghdxxlrForm" property="fdczjjsjdm"/>';
    	putObjectValue("", "fdczjjsjdmShow", fdczjjsjdm);
    	//地税税务登记号码
    	var fdczjswdjzh = '<bean:write name="ClfswjghdxxlrForm" property="fdczjswdjzh"/>';
    	putObjectValue("", "fdczjswdjzhShow", fdczjswdjzh);
    	//联系电话
    	var fdczjlxdh = '<bean:write name="ClfswjghdxxlrForm" property="fdczjlxdh"/>';
    	putObjectValue("", "fdczjlxdhShow", fdczjlxdh);
 //----------------------------
 //房地产经纪人姓名
 var fdczjjjr = '<bean:write name="ClfswjghdxxlrForm" property="fdczjjjr"/>';
 putObjectValue("", "fdczjjjrShow", fdczjjjr);
 
 //联系电话
 var fdczjjjrlxdh = '<bean:write name="ClfswjghdxxlrForm" property="fdczjjjrlxdh"/>';
 putObjectValue("", "fdczjjjrlxdhShow", fdczjjjrlxdh);
 //--------------------
 //房地产经纪人身份证号码
 var fdczjjjrzjhm = '<bean:write name="ClfswjghdxxlrForm" property="fdczjjjrzjhm"/>';
 putObjectValue("", "fdczjjjrzjhmShow", fdczjjjrzjhm);
 //经纪人资格证书号码
 var fdczjjjrzgzsh = '<bean:write name="ClfswjghdxxlrForm" property="fdczjjjrzgzsh"/>';
 putObjectValue("", "fdczjjjrzgzshShow", fdczjjjrzgzsh);
 
 
 //-------------------
 //原购房发票金额
  var ygffpje = '<bean:write name="ClfswjghdxxlrForm" property="ygffpje"/>';
  putObjectValue("", "ygffpjeShow_1", ygffpje);
 //住房评估价格
 var zfpgjg = '<bean:write name="ClfswjghdxxlrForm" property="zfpgjg"/>';
 putObjectValue("", "zfpgjgShow", zfpgjg);
 
 
 //其中:旧房及建筑物评估价格
 var jfpgjg = '<bean:write name="ClfswjghdxxlrForm" property="jfpgjg"/>';
 putObjectValue("", "jfpgjgShow", jfpgjg);
 
 //设置申报信息
 setSbxx(tdzzssbfs,jssrqrfs);
 
 //设置实缴金额合计
  var sjhjje = '<bean:write name="ClfswjghdxxlrForm" property="sjhjje"/>';
  putObjectValue("", "sjhjjeShow", sjhjje);
  
 
	}
	
	
	/*
	*设置申报信息
	*
	*   tdzzssbfs 土地增值税申报方式
	*   jssrqrfs  计税收入确认方式
	*/
	function setSbxx(tdzzssbfs,jssrqrfs){
		
		//中文描述
		var blank_1 = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		var blank_2 = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		var jssr_ZH = "计税收入&nbsp;&nbsp;";
		var yysse_ZH = "营业税税额";
		var tdzzs_FP_PGBG_ZH="增值额×适用税率-扣除项目金额合计×速算扣除系数"+blank_2+"=";
		var tdzzs_JB_ZH = "土地增值税应纳税款";
		var grsds_AS_ZH ="（房屋转让收入-房屋原值-税金-合理费用）";
		
		//获得各个DIV对象
		var yys_show_OBJ = document.all.yys_show;//营业税税额
		var cjs_show_OBJ = document.all.cjs_show;//城建税税额
		var jyffj_show_OBJ = document.all.jyffj_show;//教育费附加金额
		var dfjyfj_show_OBJ = document.all.dfjyfj_show;//地方教育附加金额
		var tdzzs_FP_PGBG_show_OBJ = document.all.tdzzs_FP_PGBG_show;//土地增值税(按发票或评估报告征收)
		var tdzzs_HD_show_OBJ = document.all.tdzzs_HD_show;//土地增值税（核定征收）
		var tdzzs_JB_show_OBJ = document.all.tdzzs_JB_show;//土地增值税（减半征收）
		var grsds_HD_show_OBJ = document.all.grsds_HD_show;//个人所得税（核定征收）
		var grsds_AS_show_OBJ = document.all.grsds_AS_show;//个人所得税（按实征收）
		
		
        <logic:iterate id="sbitem" name="ClfswjghdxxlrForm"  property ="mfsbxxList" indexId="index">
			var szsmdm = '<bean:write name="sbitem" property="szsmdm"/>';//税种税目代码
			var sl = '<bean:write name="sbitem" property="sl"/>';//税率
			var jsje = '<bean:write name="sbitem" property="jsje"/>';//计税金额
			var sjje = '<bean:write name="sbitem" property="sjje"/>';//税金税额
			
			if(szsmdm != null && szsmdm != ""){
				var sbxxMsg = blank_1 + jsje+blank_2+"×"+sl+"%"+blank_2+"="+blank_2+ sjje;
					switch(szsmdm) {
					    case '02'://设置营业税
					    	yys_show_OBJ.innerHTML = jssr_ZH + sbxxMsg;
					     	break;
						case '10'://城建税
							cjs_show_OBJ.innerHTML = yysse_ZH+ sbxxMsg; 
							break;
					    case '51'://教育费附加
					    	jyffj_show_OBJ.innerHTML = yysse_ZH+ sbxxMsg; 
						 	break;
						case '54'://地方教育费附加
							dfjyfj_show_OBJ.innerHTML = yysse_ZH+ sbxxMsg; 
						 	break;
						case '08'://土地增值税
							if(tdzzssbfs != null && tdzzssbfs != ""){
								//（1）按发票或评估价格
								if(tdzzssbfs == <%=Constants.TDZSS_SB_GFFP%> || 
								   tdzzssbfs == <%=Constants.TDZSS_SB_PGBG%>)
								{
									tdzzs_FP_PGBG_show_OBJ.innerHTML = tdzzs_FP_PGBG_ZH +blank_2+ sjje; 
								}
								
								//（2）核定征收
								if(tdzzssbfs == <%=Constants.TDZSS_SB_HDZS%>)//按发票或评估价格
								{
									tdzzs_HD_show_OBJ.innerHTML = jssr_ZH + sbxxMsg;
								}								
								//（3）减半征收(暂无)								
							}
						 	break;
						case '05'://个人所得税
							if(jssrqrfs != null && jssrqrfs != ""){ 
								//（1）核定征收
								if(jssrqrfs == <%=Constants.JSSRQRFS_HDJSJG%>){
									grsds_HD_show_OBJ.innerHTML = jssr_ZH + sbxxMsg;
								}else{
								//（2）按实征收
									grsds_AS_show_OBJ.innerHTML = grsds_AS_ZH+ sbxxMsg;
								}
							}
						 	break;							
						default:
							break;
				}
			}
	   </logic:iterate> 
	   
	}
	
	
	//选中复选选框
	function setCheckbox(checkBoxName,isChecked){
		if(checkBoxName != null && checkBoxName !="" && isChecked != null && isChecked != ""){
			document.getElementById(checkBoxName).checked = isChecked;
			
		}
	}
	

	//置值方法
	function putObjectValue(hiddProperty, td_id, obj_value) {
		if (hiddProperty != null && hiddProperty != "") {
			hiddProperty.value = obj_value;
		}
		
		//alert(td_id +"-----------------"+obj_value);
		if (td_id != null && td_id != "" && obj_value != null && obj_value != ""&& obj_value != "0.00"&& obj_value != "0.0000") {
			document.getElementById(td_id).innerHTML = "<div align='left'>"+obj_value+"&nbsp;</div>";
		}
		return true;
	}

	//设置买卖双方信息
	function setBuyorSellInfo(buyorSellInfo, type) {
		var isSuccess = false;

		if (buyorSellInfo == null || buyorSellInfo == "") {
			return isSuccess;
		}
		
		//用^分组
		var after_split_all_group_arr = new Array();//存放已经分组完成的
		
		  if(buyorSellInfo.indexOf("^")>0){
			  //alert("有多组");
			  var infoArr = buyorSellInfo.split("^");
			  
			  //alert("有多组，长度::"+infoArr.length);
			  
			  for(var index =0; index < infoArr.length;index ++){
				  var tempInfo = infoArr[index];
				  
				  //调用解析方法，解析每个买卖方的信息并显示
				  //setBuyorSellInfo(tempInfo,tableId,hidPropertyObj);
				  //alert("-----------22222222222");
				  after_split_all_group_arr.push(tempInfo);
			  }
		  }else{
			  //setBuyorSellInfo(buyorSellInfo,tableId,hidPropertyObj);
			  after_split_all_group_arr.push(buyorSellInfo);
		  }

			//多个用空格分开
			var xm_all ="";//姓名 
			var lxdm_all ="";//联系电话
			var zjmc_all ="";//证件名称
			var zjhm_all="";//证件号码
			
		for(var index =0;index < after_split_all_group_arr.length;index ++){
			var info = after_split_all_group_arr[index];
			
			//分解并显示信息
		var AllGroupInfoArr = info.split("~");
		if (AllGroupInfoArr != null && AllGroupInfoArr != "") {
			var count = AllGroupInfoArr.length / 6;
			if (count != null && count != 0) {
				var oneGroupInfo = new Array();
				for (zsIndex = 0; zsIndex < count; zsIndex++) {
					var value_0 = AllGroupInfoArr[0 + (zsIndex * 6)];
					var value_1 = AllGroupInfoArr[1 + (zsIndex * 6)];
					var value_2 = AllGroupInfoArr[2 + (zsIndex * 6)];
					var value_3 = AllGroupInfoArr[3 + (zsIndex * 6)];
					var value_4 = AllGroupInfoArr[4 + (zsIndex * 6)];
					var value_5 = AllGroupInfoArr[5 + (zsIndex * 6)];

					if(index == 0){
						xm_all = value_0;//姓名
						lxdm_all = value_5;//联系电话
						zjmc_all = getZjmc(value_2);//证件名称
						zjhm_all = value_3;
					}else{
						xm_all = xm_all +" "+value_0;
						lxdm_all = lxdm_all +" "+value_5;
						zjmc_all = zjmc_all+" "+getZjmc(value_2);
						zjhm_all = zjhm_all +" "+value_3;
					}
				}
			}
		}
		}
			
		//设置并显示每组信息
		
		if(type == "buy"){
			
/* 			document.getElementById("buy_mcShow").innerHTML=xm_all;
			document.getElementById("buy_lxdhShow").innerHTML=lxdm_all;
			document.getElementById("buy_zjlxShow").innerHTML=zjmc_all;
			document.getElementById("buy_zjhmShow").innerHTML=zjhm_all; */
			putObjectValue("", "buy_mcShow", xm_all);
			putObjectValue("", "buy_lxdhShow", lxdm_all);
			putObjectValue("", "buy_zjlxShow", zjmc_all);
			putObjectValue("", "buy_zjhmShow", zjhm_all);
			
		}else{
/* 			document.getElementById("sell_mcShow").innerHTML=xm_all;
			document.getElementById("sell_lxdhShow").innerHTML=lxdm_all;
			document.getElementById("sell_zjlxShow").innerHTML=zjmc_all;
			document.getElementById("sell_zjhmShow").innerHTML=zjhm_all; */
			putObjectValue("", "sell_mcShow", xm_all);
			putObjectValue("", "sell_lxdhShow", lxdm_all);
			putObjectValue("", "sell_zjlxShow", zjmc_all);
			putObjectValue("", "sell_zjhmShow", zjhm_all);
			
		}
		


		return true;
	}
	
	function getZjmc(zjdm){
		//alert(1111111111);
         <logic:iterate id="item" name="ClfswjghdxxlrForm"  property ="zjList" indexId="index">
			var zjdm_1 = '<bean:write name="item" property="zjlxdm"/>';
			var zjmc_1 = '<bean:write name="item" property="zjlxmc"/>';
			
			if(zjdm == zjdm_1){
				
			return zjmc_1;
			
			}
		</logic:iterate> 
		
		return "其他";
	}

	//把每组信息设置到td的innerHTML中显示
	function setOneGroupInfo(arr, tableobj) {

		if (tableobj == null || tableobj == "") {
			return false;
		}
		var otr = tableobj.insertRow();
		//第一行第一列  ”名称（姓名）“
		var mc_Cell = otr.insertCell();
		mc_Cell.innerHTML = "名称（姓名）";
		mc_Cell.className = "2-td2-left";
		mc_Cell.WIDTH = "20%";
		//第一行第二列 
		var mc_value_Cell = otr.insertCell();
		mc_value_Cell.innerHTML = arr[0];
		mc_value_Cell.className = "2-td2-left";
		mc_value_Cell.WIDTH = "30%";
		//第一行第三列  “类别”
		var lb_Cell = otr.insertCell();
		lb_Cell.innerHTML = "类别";
		lb_Cell.className = "2-td2-left";
		lb_Cell.WIDTH = "20%";
		//第一行第四列  
		var lb_value_Cell = otr.insertCell();
		lb_value_Cell.innerHTML = nsrlb_mc(arr[1]);
		lb_value_Cell.className = "2-td2-center";
		lb_value_Cell.WIDTH = "30%";

		//第二行第一列 “证件类型”
		var otr2 = tableobj.insertRow();
		var zjlx_Cell = otr2.insertCell();
		zjlx_Cell.innerHTML = "证件类型";
		zjlx_Cell.className = "2-td2-left";
		zjlx_Cell.WIDTH = "20%";
		//第二行第二列
		var zjlx_value_Cell = otr2.insertCell();
		zjlx_value_Cell.innerHTML = nsrzjlb_mc(arr[2]);
		zjlx_value_Cell.className = "2-td2-left";
		zjlx_value_Cell.WIDTH = "30%";
		//第二行第一列 “证件号码”
		var zjhm_Cell = otr2.insertCell();
		zjhm_Cell.innerHTML = "证件号码";
		zjhm_Cell.className = "2-td2-left";
		zjhm_Cell.WIDTH = "20%";
		//第二行第一列 
		var zjhm_value_Cell = otr2.insertCell();
		zjhm_value_Cell.innerHTML = arr[3];
		zjhm_value_Cell.className = "2-td2-center";
		zjhm_value_Cell.WIDTH = "30%";

		//第三行第一列 “权利人份额”
		var otr3 = tableobj.insertRow();
		var fe_Cell = otr3.insertCell();
		fe_Cell.innerHTML = "权利人份额";
		fe_Cell.className = "2-td2-left";
		fe_Cell.WIDTH = "20%";
		//第三行第二列 
		var fe_value_Cell = otr3.insertCell();
		fe_value_Cell.innerHTML = arr[4];
		fe_value_Cell.className = "2-td2-left";
		fe_value_Cell.WIDTH = "30%";
		//第三行第三列 “联系人电话”
		var lxdh_Cell = otr3.insertCell();
		lxdh_Cell.innerHTML = "联系人电话";
		lxdh_Cell.className = "2-td2-left";
		lxdh_Cell.WIDTH = "20%";
		//第三行第四列 
		var lxdh_value_Cell = otr3.insertCell();
		lxdh_value_Cell.innerHTML = arr[5];
		lxdh_value_Cell.className = "2-td2-center";
		lxdh_value_Cell.WIDTH = "30%";

		return true;
	}

</script>
</HTML>
