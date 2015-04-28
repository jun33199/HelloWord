<%@page import="com.creationstar.bjtax.qsgl.util.CommonUtil"%>
<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.Constants"%>
<%@ page import="java.util.*" %>
<%@ page import="com.creationstar.bjtax.qsgl.VisionLogic.form.clfgl.ClfswjghdxxlrForm" %>
<%@ page import="com.creationstar.bjtax.qsgl.util.*" %>
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

</style>

<object ID='wb' WIDTH=0 HEIGHT=0
	CLASSID='CLSID:8856F961-340A-11D0-A96B-00C04FD705A2'></object>

<META content="text/html; charset=gb2312" http-equiv=Content-Type>
<SCRIPT language=JavaScript src="<%=static_file%>js/inputchecker.js"
	type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js"
	type=text/JavaScript></SCRIPT>
<script language=JavaScript src="../js/clfgl_util.js"
	type="text/javascript"></script>
<!-- <LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css> -->
<LINK href="../css/view.css" rel=stylesheet type=text/css media="screen">
<LINK href="../css/print.css" rel=stylesheet type=text/css media="print">
</HEAD>
<BODY bgColor=#ffffff leftMargin=0 style="MARGIN: 0px" topMargin=0
	marginheight="0" marginwidth="0">
	<table width="80%" border="0" align="center" cellspacing="0"
		class="table-99">
		<tr>
			<td colspan="15">
				<h3>
					个&nbsp;人&nbsp;销&nbsp;售&nbsp;已&nbsp;购&nbsp;房&nbsp;屋&nbsp;涉&nbsp;税&nbsp;申&nbsp;报&nbsp;核&nbsp;定&nbsp;表&nbsp;<br>(代&nbsp;开&nbsp;发&nbsp;票&nbsp;申&nbsp;请&nbsp;表)
				</h3> 编号（流水号）：<bean:write name="ClfswjghdxxlrForm" property="sbbh" />
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;受理日期：<bean:write
					name="ClfswjghdxxlrForm" property="slrq" /> <br>
					  合同编号：<bean:write name="ClfswjghdxxlrForm" property="htbh"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;金额单位：元（角分）
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
								<TABLE align=center cellSpacing=0 height="90%" class=table-99>
									<tr>
													<td colspan="6" class="td1-center">
														<table cellSpacing=0 class=tabled-99 width="100%" height="100%">
															<tr>
																<td class=2-td1-left align="left" width="1%"
																	rowspan="18">纳<br>税<br>人<br>填<br>写
																</td>
																<%
																	ClfswjghdxxlrForm form =(ClfswjghdxxlrForm)request.getAttribute("ClfswjghdxxlrForm");
																	String sellerInfo =form.getAll_sellerInfo();
																	String buyerInfo =form.getAll_buyerInfo();
																	String[] sellerArr = sellerInfo.split("\\^");
																	String[] buyerArr = buyerInfo.split("\\^");
																%>
																<td colspan="6" class="2-td1-center" width="99%">
																	<table border=0 cellSpacing=0 width="100%" id="sellAndBuyTable">
																	<tr>
																		<td class=2-td2-left colspan="6" style="border-left: 0px solid #9BB4CA;">申请人信息</td>
																	</tr>
																	<tr>
																		<td class=2-td2-noleft align="left" width="30%"><div align="left">申请人（卖方）姓名</div></td>
																		<td class=2-td2-left width="20%" colspan="2">&nbsp;联系电话</td>
																		<td class=2-td2-left width="20%" ><div align="left">申请人（卖方）合法身份证件</div></td>
																		<td class=2-td2-center style="border-right: 0px solid;" width="30%"  colspan="2">证件号码</td>
																    </tr>
																    <%
																    	for(int i = 0;i<sellerArr.length;i++){
																    		String str = sellerArr[i];
																    		String[] detailArr = str.split("~");
																    		String zjmc = CommonUtil.getZjmc(form, detailArr[2]);
																    %>
																	<tr>
																		<td class=2-td2-noleft align="left" width="30%"><div align="left"><%=detailArr[0]%></div></td>
																		<td class=2-td2-left width="20%"  colspan="2">&nbsp;<%=detailArr[5] %></td>
																		<td class=2-td2-left width="20%" ><div align="left"><%= zjmc%></div></td>
																		<td class=2-td2-center style="border-right: 0px solid;" width="30%"   colspan="2"><%= detailArr[3]%></td>
																	</tr>
																	<% }%>
																	<tr>
																		<td class=2-td2-left colspan="6" style="border-left: 0px solid #9BB4CA;">付款方信息</td>
																	</tr>
																	
																	<tr>
																		<td class=2-td2-noleft align="left" width="30%"><div align="left">付款人（买方）姓名</div></td>
																		<td class=2-td2-left width="20%" colspan="2">联系电话</td>
																		<td class=2-td2-left width="20%"><div align="left">付款人（买方）合法身份证件</div></td>
																		<td class=2-td2-center style="border-right: 0px solid;" width="30%"  colspan="2">证件号码</td>
																	</tr>
																	<%
																		for(int j = 0 ;j<buyerArr.length;j++){
																			String temp = buyerArr[j];
																			String[] tempArr = temp.split("~");
																			String fkfZjmc = CommonUtil.getZjmc(form, tempArr[2]);
																			if(j==buyerArr.length-1){
																				%>
																				<tr>
																				<td class=2-td2-center-noBottom style="border-left: 0px solid;" align="left" width="30%"><div align="left"><%= tempArr[0]%></div></td>
																				<td class=2-td2-center-noBottom width="20%" colspan="2">&nbsp;<%=tempArr[5] %></td>
																				<td class=2-td2-center-noBottom width="20%"><div align="left"><%=fkfZjmc%></div></td>
																				<td class=2-td2-center-noBottom style="border-right: 0px solid;" width="30%"  colspan="2"><%=tempArr[3] %></td>
																			</tr>
																			<%
																			}else{
																	%>
																	<tr>
																		<td class=2-td2-noleft style="border-left: 0px solid;" align="left" width="30%"><div align="left"><%= tempArr[0]%></div></td>
																		<td class=2-td2-left width="20%" colspan="2">&nbsp;<%=tempArr[5] %></td>
																		<td class=2-td2-left width="20%"><div align="left"><%=fkfZjmc%></div></td>
																		<td class=2-td2-center style="border-right: 0px solid;" width="30%"  colspan="2"><%=tempArr[3] %></td>
																	</tr>
																	<%}
																	} 
																	%>
																	</table>
																</td>
															</tr>
															<tr>
																<td class=2-td2-left align="left" width="10%"
																	nowrap="nowrap" ><div align="left">房屋详细地址</div></td>
																<td class=2-td2-center width="90%" id="fwzldzShow"
																	 colspan="5" >&nbsp;</td>

															</tr>
															<tr>
																<td class=2-td2-left width="10%" nowrap="nowrap"><div
																		align="left" >是否为家庭唯一生活用房</div></td>
																<td class=2-td2-center width="90%" nowrap="nowrap" colspan="5" >
																	<div align="left">
																		
																		<input type="checkbox" value="0" id="sfwyzf_Y"
																			onclick="return false;">是</input> &nbsp;&nbsp; <input
																			type="checkbox" value="1" id="sfwyzf_N"
																			onclick="return false;">否</input>
																	</div>
																</td>
															</tr>
															<tr>
																<td class=2-td2-left align="left" width="10%"
																	nowrap="nowrap"><div align="left">房屋产权证号</div></td>
																<td class=2-td2-left width="35%" id="fwcqzhShow"
																	nowrap="nowrap" >&nbsp;</td>
																<td class=2-td2-left width="20%" nowrap="nowrap"><div
																		align="left">房屋产权证住房种类</div></td>
																<td class=2-td2-center width="15%" id="fwcqzbzzflxmcShow"
																	nowrap="nowrap" colspan="3">
																	&nbsp;
																</td>
															<tr>
																<td class=2-td2-left width="15%" nowrap="nowrap" colspan="2"><div
																		align="left">申请开票金额（本次交易金额）</div></td>
																<td class=2-td2-center width="15%" id="sqkpjeShow" style="font-weight:bold;" colspan="4">&nbsp;</td>
															</tr>															</tr>

															<tr>
																<td class=2-td2-left width="10%" nowrap="nowrap"><div
																		align="left">房屋类型</div></td>
																<td class=2-td2-center width="30%" id="fwlxShow"
																	colspan="5" nowrap="nowrap">
																	<div align="left">
																		
																		<input type="checkbox" value="0" id="fwlx_0"
																			onclick="return false;">楼房</input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
																			type="checkbox" value="1" id="fwlx_1"
																			onclick="return false;">平房</input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
																			type="checkbox" value="2" id="fwlx_2"
																			onclick="return false;">地下室</input>
																	</div>
																</td>
															</tr>
															<tr>
																<td class=2-td2-left align="left" width="10%"
																	nowrap="nowrap"><div align="left">建成年代</div></td>
																<td class=2-td2-left width="35%" id="jcndShow"
																	nowrap="nowrap" style="font-weight:bold;">&nbsp;</td>
																<td class=2-td2-left width="10%" nowrap="nowrap"><div
																		align="left">总楼层</div></td>
																<td class=2-td2-left width="15%" id="zlcShow" style="font-weight:bold;">&nbsp;</td>
																<td class=2-td2-left width="10%" ><div
																		nowrap="nowrap" align="left">所在楼层</div></td>
																<td class=2-td2-center width="20%" id="szlcShow" style="font-weight:bold;" nowrap="nowrap">&nbsp;</td>
															</tr>
															<tr>
																<td class=2-td2-left align="left"width="10%"
																	nowrap="nowrap"><div align="left">房屋建筑面积</div></td>
																<td class=2-td2-left width="35%" id="fwjzmjShow" style="font-weight:bold;" nowrap="nowrap">&nbsp;</td>
																<td class=2-td2-left width="15%" nowrap="nowrap"><div
																		align="left">原购房发票金额</div></td>
																<td class=2-td2-center width="30%" id="ygffpjeShow"
																	colspan="3" style="font-weight:bold;">&nbsp;</td>
															</tr>
															<tr>
																<td class=2-td2-left align="left" width="10%"
																	nowrap="nowrap"><div align="left">购房证明日期</div></td>
																<td class=2-td2-left width="35%" id="gfzmrqShow" style="font-weight:bold;">&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日</td>
																<td class=2-td2-left width="15%" nowrap="nowrap"><div
																		align="left">买卖双方合同签订日期</div></td>
																<td class=2-td2-center width="30%" id="htwsqyrqShow"
																	colspan="3" style="font-weight:bold;">&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日</td>
															</tr>
															<tr>
																<td class=2-td2-center width="10%" nowrap="nowrap"
																	colspan="6"><div align="center">申报方式（根据各税种相关规定可多选）</div></td>
															</tr>
															<tr>
																<td class=2-td2-center width="15%" nowrap="nowrap"
																	colspan="6">
																	<div align="center">
																		
																		<input type="checkbox" value="0" id="tdzzssbfs_0"
																			onclick="return false;">提供购房发票</input>&nbsp;&nbsp; <input
																			type="checkbox" value="1" id="tdzzssbfs_1"
																			onclick="return false;">提供购房契税票</input>&nbsp;&nbsp; <input
																			type="checkbox" value="2" id="tdzzssbfs_2"
																			onclick="return false;">提供原购房合同</input>&nbsp;&nbsp;<br> <input
																			type="checkbox" value="3" id="tdzzssbfs_3"
																			onclick="return false;">提供评估报告</input>&nbsp;&nbsp; <input
																			type="checkbox" value="4" id="tdzzssbfs_4"
																			onclick="return false;">无任何票据</input>&nbsp;&nbsp; <input
																			type="checkbox" value="5" id="tdzzssbfs_5"
																			onclick="return false;">提供法律文书</input>
																	</div>
																</td>
															</tr>
															<tr>
																<td class=2-td2-left align="left" width="10%"
																	nowrap="nowrap" colspan="2"><div align="center">提供购房发票填写下栏</div></td>
																<td class=2-td2-center align="left" width="15%"
																	nowrap="nowrap" colspan="5"><div align="center">提供评估报告填写下列栏</div></td>
															</tr>
															<tr>
																<td class=2-td2-left width="20%" 
																	rowspan="4"><div align="left">取得房地产时所缴纳的契税金额</div></td>
																<td class=2-td2-left width="15%"
																	rowspan="4" id="qdfcqsjeShow" style="font-weight:bold;">&nbsp;</td>
															</tr>
															<tr>
																<td class=2-td2-left width="15%" nowrap="nowrap"><div
																		align="left">取得土地使用权所支付的金额</div></td>
																<td class=2-td2-center width="30%" colspan="3"
																	id="qdtdsyqzfjeShow" style="font-weight:bold;">&nbsp;</td>
															</tr>
															<tr>
																<td class=2-td2-left width="15%" nowrap="nowrap"><div
																		align="left">旧房及建筑物的评估价格</div></td>
																<td class=2-td2-center width="30%" colspan="3"
																	id="jfpgjgShow" style="font-weight:bold;">&nbsp;</td>
															</tr>
															<tr>
																<td class=2-td2-left width="15%" nowrap="nowrap"><div
																		align="left">价格评估费用</div></td>
																<td class=2-td2-center width="30%" colspan="3"
																	id="jgpgfyShow" style="font-weight:bold;">&nbsp;</td>
															</tr>
															
														</table>
													</td>
												</tr>

												
											 <tr style="page-break-after: always;"></tr>
												<tr>
													<td colspan="6" class="td1-center">
														<table cellSpacing=0 width="100%">
															<tr>
																<td class=2-td1-left rowspan="10" width="1%">税<br>务<br>机<br>关<br>确<br>认
																</td>

															</tr>
															<tr>
																<td class=2-td2-t-left  width="25%" nowrap="nowrap" ><div
																		align="left">每平米交易单价</div></td>
																<td class=2-td2-t-left  width="30%" id="mpmjydjShow" style="font-weight:bold;">&nbsp;</td>
																<td class=2-td2-t-left  width="25%" nowrap="nowrap" ><div
																		align="left">房屋所在区域</div></td>
																<td class=2-td2-t-left  width="30%" id="fwszqyShow" style="border-right:1px solid #9BB4CA;">&nbsp;</td>
															</tr>
															<tr>
																<td class=2-td2-center width="15%" colspan="6" nowrap="nowrap"><div
																		align="center">
																
																		<input type="checkbox" value="1" id="yyszsfs_1"
																			onclick="return false;">全额征收营业税</input> <input
																			type="checkbox" value="2" id="yyszsfs_2"
																			onclick="return false;">差额征收营业税</input> <input
																			type="checkbox" value="1" id="grsdszsfs_1"
																			onclick="return false;">征收个人所得税</input> <input
																			type="checkbox" value="4" id="tdzsszsfs_4"
																			onclick="return false;">提供购房发票征收土地增值税</input><br> <input
																			type="checkbox" value="5" id="tdzsszsfs_5"
																			onclick="return false;">核定征收土地增值税</input> <input
																			type="checkbox" value="6" id="tdzsszsfs_6"
																			onclick="return false;">提供评估报告征收土地增值税</input> <input
																			type="checkbox" value="0" id="tdzsszsfs_0"
																			onclick="return false;">不征土地增值税</input><input
																			type="checkbox" value="1" id="tdzsszsfs_1"
																			onclick="return false;">免征土地增值税</input><br>
																			<input type="checkbox" value="0" id="yhs_0" onclick="return false;">征收印花税</input>
																	<input type="checkbox" value="1" id="yhs_1" onclick="return false;">&nbsp;&nbsp;免征印花税</input>

																	</div></td>
															</tr>
															<tr>
																<td class=2-td2-left width="15%" nowrap="nowrap"><div
																		align="left">计税收入确认方式及金额</div></td>
																<td class=2-td2-center width="15%" colspan="5"
																	id="jssrqrfsShow" style="font-weight:bold;">
																	<div align="left">
																		
																		<input type="checkbox" value="0" id="jssrqrfs_0"
																			onclick="return false;">合同价格</input>
																		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;金额：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																		<input type="checkbox" value="1" id="jssrqrfs_1"
																			onclick="return false;">核定计税价格</input>
																		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;金额：
																	</div>
																</td>
															</tr>

															<tr>
																<td colspan="6" class=2-td2-center>
																	<table border=0 cellSpacing=0>
																		<tr>
																			<td class=2-td2-center
																				style="border-left: 0px solid; border-right: 0px solid;"
																				width="15%" colspan="8">扣除金额确认</td>
																		</tr>
																		<tr>
																			<td class=2-td2-left style="border-left: 0px solid;"
																				align="left" width="15%" nowrap="nowrap"><div
																					align="left">原购房发票价格</div></td>
																			<td class=2-td2-left width="20%" id="ygffpjeShow_1" style="font-weight:bold;">&nbsp;</td>
																			<td class=2-td2-left width="15%" nowrap="nowrap"><div
																					align="left">住房评估价格</div></td>
																			<td class=2-td2-left width="20%" id="zfpgjgShow" style="font-weight:bold;">&nbsp;</td>
																			<td class=2-td2-left width="10%"><div
																					align="left">
																					其中：<br>旧房及建筑物评估价格
																				</div></td>
																			<td class=2-td2-center
																				style="border-right: 0px solid;" width="20%"
																				colspan="3" id="jfpgjgShow1" style="font-weight:bold;">&nbsp;</td>
																		</tr>
																		<tr>
																			<td class=2-td2-left style="border-left: 0px solid;"
																				width="15%" nowrap="nowrap"><div align="left">营业税税额</div></td>
																			<td class=2-td2-left width="15%" colspan="3"><div
																					align="left" id="yys_show" style="font-weight:bold;">计税收入&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;×5%&nbsp;&nbsp;&nbsp;=</div>
																			</td>
																			<td class=2-td2-left width="15%" nowrap="nowrap"><div
																					align="left">完税凭证号</div></td>
																			<td class=2-td2-center width="30%" colspan="3"
																				style="border-right: 0px solid;">&nbsp;</td>
																		</tr>
																		<tr>
																			<td class=2-td2-left style="border-left: 0px solid;"
																				width="15%" nowrap="nowrap"><div align="left">城建税税额</div></td>
																			<td class=2-td2-left width="15%" colspan="3"><div
																					align="left" id="cjs_show" style="font-weight:bold;">营业税税额&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;×5%&nbsp;&nbsp;&nbsp;=</div>
																			</td>
																			<td class=2-td2-left width="15%" nowrap="nowrap"><div
																					align="left">完税凭证号</div></td>
																			<td class=2-td2-center width="30%" colspan="3"
																				style="border-right: 0px solid;">&nbsp;</td>
																		</tr>
																		<tr>
																			<td class=2-td2-left style="border-left: 0px solid;"
																				width="15%" nowrap="nowrap"><div align="left">教育费附加金额</div></td>
																			<td class=2-td2-left width="15%" colspan="3"><div
																					align="left" id="jyffj_show" style="font-weight:bold;">营业税税额&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;×3%&nbsp;&nbsp;&nbsp;=</div>
																			</td>
																			<td class=2-td2-left width="15%" nowrap="nowrap"><div
																					align="left">完税凭证号</div></td>
																			<td class=2-td2-center width="30%" colspan="3"
																				style="border-right: 0px solid;">&nbsp;</td>
																		</tr>
																		<tr>
																			<td class=2-td2-left style="border-left: 0px solid;" width="15%" nowrap="nowrap"><div
																					align="left">地方教育附加金额</div></td>
																			<td class=2-td2-left width="15%" colspan="3"><div
																					align="left" id="dfjyfj_show" style="font-weight:bold;">营业税税额&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;×2%&nbsp;&nbsp;&nbsp;=</div>
																			<td class=2-td2-left width="15%" nowrap="nowrap"><div
																					align="left">完税凭证号</div></td>
																			<td class=2-td2-center width="30%" colspan="3"
																				style="border-right: 0px solid;">&nbsp;</td>
																			</td>
																		</tr>
																		<tr>
																			<td class=2-td2-left style="border-left: 0px solid;" width="15%" nowrap="nowrap"><div
																					align="left">印花税金额</div></td>
																			<td class=2-td2-left width="15%" colspan="3"><div
																					align="left" id="yhsse_show" style="font-weight:bold;">合同价格&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;×0.05%&nbsp;&nbsp;&nbsp;=</div>
																			<td class=2-td2-left width="15%" nowrap="nowrap"><div
																					align="left">完税凭证号</div></td>
																			<td class=2-td2-center width="30%" colspan="3"
																				style="border-right: 0px solid;">&nbsp;</td>
																			</td>
																		</tr>
																		<tr>
																			<td class=2-td2-left style="border-left: 0px solid;"
																				width="15%" nowrap="nowrap"><div align="left">土地增值税<br>(按发票或评估报告征收)</div></td>
																			<td class=2-td2-left width="15%" colspan="3"><div
																					align="left" id="tdzzs_FP_PGBG_show" style="font-weight:bold;">
																					增值额×适用税率-扣除项目金额合计×速算扣除系数=<br>&nbsp;
																				</div></td>
																			<td class=2-td2-left width="15%" nowrap="nowrap"><div
																					align="left">完税凭证号</div></td>
																			<td class=2-td2-center width="30%" colspan="3"
																				style="border-right: 0px solid;">&nbsp;</td>
																		</tr>
																		<tr>
																			<td class=2-td2-left style="border-left: 0px solid;"
																				width="15%" nowrap="nowrap"><div align="left">土地增值税（核定征收）</div></td>
																			<td class=2-td2-left width="15%" colspan="3"><div
																					align="left" id="tdzzs_HD_show" style="font-weight:bold;">计税收入&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;×5%&nbsp;&nbsp;&nbsp;=</div>
																			</td>
																			<td class=2-td2-left width="15%" nowrap="nowrap"><div
																					align="left">完税凭证号</div></td>
																			<td class=2-td2-center width="30%" colspan="3"
																				style="border-right: 0px solid;">&nbsp;</td>
																		</tr>
																		
																		<tr>
																			<td class=2-td2-left style="border-left: 0px solid;"
																				width="15%" nowrap="nowrap"><div align="left">个人所得税（按实征收）</div></td>
																			<td class=2-td2-left width="15%" colspan="3"><div
																					align="left" id="grsds_AS_show" style="font-weight:bold;">（房屋转让收入-税金-合理费用）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;×20%&nbsp;&nbsp;&nbsp;=</div>
																			</td>
																			<td class=2-td2-left width="15%" nowrap="nowrap"><div
																					align="left">完税凭证号</div></td>
																			<td class=2-td2-center width="30%" colspan="3"
																				style="border-right: 0px solid;">&nbsp;</td>
																		</tr>
																		
																		
																		<tr>
																			<td class=2-td2-left style="border-left: 0px solid;"
																				width="15%" nowrap="nowrap"><div align="left">各项税费合计</div></td>
																			<td class=2-td2-center width="15%" colspan="7"
																				style="border-right: 0px solid;font-weight:bold;"><div
																					align="left" id="sjhjjeShow" >&nbsp;</div></td>
																		</tr>
																		<tr>
																			<td colspan="8" width="100%">
																				<table cellSpacing=0 width="100%">
																					<tr>
																						<td class=2-td2-center-noBottom
																							style="border-left: 0px solid;" nowrap="nowrap"><div
																								align="left">发票代码</div></td>
																						<td class=2-td2-center-noBottom width="25%">&nbsp;</td>
																						<td class=2-td2-center-noBottom nowrap="nowrap"><div
																								align="left">发票号码</div></td>
																						<td class=2-td2-center-noBottom width="25%">&nbsp;</td>
																						<td class=2-td2-center-noBottom nowrap="nowrap"><div
																								align="left">信息码</div></td>
																						<td class=2-td2-center-noBottom width="25%">&nbsp;</td>
																						<td class=2-td2-center-noBottom nowrap="nowrap"><div
																								align="left">开具金额</div></td>
																						<td class=2-td2-center-noBottom
																							style="border-right: 0px solid;" width="100%" style="font-weight:bold;">&nbsp;</td>
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
													<td colspan="6">
														<table cellSpacing=0 width="100%">
															<tr>
																<td class=2-td1-left width="1%" rowspan="2">申<br>明
																</td>
																<td class=2-td2-center width="15%" colspan="6"
																	style="border-top: 1px solid #9BB4CA;"><div
																		align="left">
																		<h3>以上提供的信息真实、准确，愿承担相应的法律责任。</h3>
																		<h3>纳税人签章： &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																	                          经办人签章：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>经办人身份证号码：&nbsp;&nbsp;&nbsp; 
																        </h3>
																	</div></td>
															</tr>
															<tr>
																<td class=2-td2-left align="left" width="15%"
																	nowrap="nowrap"><div align="left">受理人员签字</div></td>
																<td class=2-td2-left width="30%">&nbsp;</td>
																<td class=2-td2-left width="15%" nowrap="nowrap"><div
																		align="left">复核人员签字</div></td>
																<td class=2-td2-center width="30%" colspan="3">&nbsp;</td>
															</tr>
															<tr>
																<td colspan="7" class=2-td1-left
																	style="border-right: 1px solid #9BB4CA; border-top: 0px solid;"><div
																		align="right">受理单位章：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div></td>
															</tr>
															<tr class="Noprn">
																<td colspan="7">
																	<table width="99%" border="0" cellpadding="0"
																		cellspacing="0">
																		<tr>
																			<td>
																				<hr width="100%" size="1">
																			</td>
																			<td align="center" class="black9"><strong><font
																					color="#0000FF">填表说明</font></strong></td>
																			<td>
																				<hr width="100%" size="1">
																			</td>
																		</tr>
																	</table>
																	<table border="1" align="center" cellpadding="1"
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
								</TABLE>
							</td>
						</tr>
					</TABLE>
				</td>
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
		window.opener.document.forms[0].target = "_blank";
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

	var HKEY_Root, HKEY_Path, HKEY_Key;
	HKEY_Root = "HKEY_CURRENT_USER";
	HKEY_Path = "\\Software\\Microsoft\\Internet Explorer\\PageSetup\\";
	//设置网页打印的页眉页脚为空    
	function PageSetup_Null() {
		try {
			var Wsh = new ActiveXObject("WScript.Shell");
			HKEY_Key = "header";
			Wsh.RegWrite(HKEY_Root + HKEY_Path + HKEY_Key, "");
			HKEY_Key = "footer";
			Wsh.RegWrite(HKEY_Root + HKEY_Path + HKEY_Key, "");
		} catch (e) {
		}
	}

	//恢复网页打印的页眉页脚   

	function PageSetup_default() {
		try {
			var Wsh = new ActiveXObject("WScript.Shell");
			HKEY_Key = "header";
			Wsh.RegWrite(HKEY_Root + HKEY_Path + HKEY_Key, "&w&b页码,&p/&P");
			HKEY_Key = "footer";
			Wsh.RegWrite(HKEY_Root + HKEY_Path + HKEY_Key, "&u&b&d");
		} catch (e) {
		}
	}

	function printsetup() {
		//打印页面设置 
		wb.execwb(8, 1);
	}

	function printpreview() {
		//打印页面预览 
		PageSetup_Null();
		wb.execwb(7, 1);
	}

	function printit() {
		if (confirm('确定打印吗？')) {
			PageSetup_Null();
			wb.execwb(6, 6);
		}
	}
</script>

<script type="text/javascript">
	//金额格式
	function changeJE(je) {
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
		putObjectValue("", 'htwsqyrqShow', transTime("合同网上签约日期", res_htwsqyrq,
				"yyyy年mm月dd日"));
		//交易房屋地址
		var res_fwzldz = '<bean:write name="ClfswjghdxxlrForm" property="fwzldz"/>';
		putObjectValue("", "fwzldzShow", res_fwzldz);
		//房屋权属转移类型
		//var res_fwqszylxmc = "";
		//putObjectValue("", "fwqszylxmcShow", res_fwqszylxmc);
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
		//var sellerInfo = '<bean:write name="ClfswjghdxxlrForm" property="all_sellerInfo"/>';
		//setBuyorSellInfo(sellerInfo, "sell");
		//alert(sellerInfo);

		//买方信息
		//var buyyerInfo = '<bean:write name="ClfswjghdxxlrForm" property="all_buyerInfo"/>';
		//setBuyorSellInfo(buyyerInfo, "buy");
		//alert(buyyerInfo);

		//==============================核定信息====================================

		//申请人现住址
		//var sqrxzdz = '<bean:write name="ClfswjghdxxlrForm" property="sqrxzdz"/>';
		//putObjectValue("", "sqrxzdzShow", sqrxzdz);

		//是否为家庭唯一生活用房
		var jtwyshyhbz = '<bean:write name="ClfswjghdxxlrForm" property="jtwyshyhbz"/>';
		if (jtwyshyhbz != "" && jtwyshyhbz ==<%=Constants.ONLY_ROOM_YES%>) {
			setCheckbox("sfwyzf_Y", true);
		} else if (jtwyshyhbz != "" && jtwyshyhbz ==<%=Constants.ONLY_ROOM_NOT%>) {
			setCheckbox("sfwyzf_N", true);
		}

		//房屋产权证住房种类
		var fwcqzbzzflxmc = '<bean:write name="ClfswjghdxxlrForm" property="fwcqzbzzflxmc"/>';
		putObjectValue("", "fwcqzbzzflxmcShow", fwcqzbzzflxmc);

		//申请开票金额（本次交易金额）

		var htzj = '<bean:write name="ClfswjghdxxlrForm" property="htzj"/>';
		putObjectValue("", "sqkpjeShow", changeJE(htzj));

		//房屋类型
		var fwlxdm = '<bean:write name="ClfswjghdxxlrForm" property="fwlxdm"/>';
		if (fwlxdm != "" && (fwlxdm ==<%=Constants.FWLX_BUILDINGS%>)) {
			setCheckbox("fwlx_0", true);
		} else if (fwlxdm != "" && (fwlxdm ==<%=Constants.FWLX_BUNGALOW%>)) {
			setCheckbox("fwlx_1", true);
		} else if (fwlxdm != "" && (fwlxdm ==<%=Constants.FWLX_BASEMENT%>)) {
			setCheckbox("fwlx_2", true);
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
		putObjectValue("", 'gfzmrqShow', transTime("购房证明日期", gfzmrq,
				"yyyy年mm月dd日"));
		//--modified by huohb,2014-07-18
		//因为非住房的申报方式可以多选，但传过来的是拼接的字符串，需要进行分割
		//土地增值税申报方式
		var tdzzssbfs_join = '<bean:write name="ClfswjghdxxlrForm" property="tdzzssbfs"/>';
		var tdzzssbfss = new Array();
		//分割申报方式-分割原则"|"                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
		tdzzssbfss = tdzzssbfs_join.split("|");
		//循环判断，选中多选框
		for(i=0;i<tdzzssbfss.length;i++){
			var tdzzssbfs = tdzzssbfss[i];
			if (tdzzssbfs != "" && tdzzssbfs ==<%=Constants.TDZSS_SB_GFFP%>) {
							setCheckbox("tdzzssbfs_0", true);
			} else if (tdzzssbfs != "" && tdzzssbfs ==<%=Constants.TDZSS_SB_GFQSP%>) {
							setCheckbox("tdzzssbfs_1", true);
			} else if (tdzzssbfs != "" && tdzzssbfs ==<%=Constants.TDZSS_SB_GFHT%>) {
							setCheckbox("tdzzssbfs_2", true);
			}else if (tdzzssbfs != "" && tdzzssbfs ==<%=Constants.TDZSS_SB_PGBG%>) {
							setCheckbox("tdzzssbfs_3", true);
			} else if (tdzzssbfs != "" && tdzzssbfs ==<%=Constants.TDZSS_SB_WPJ%>) {
							setCheckbox("tdzzssbfs_4", true);
			} else if (tdzzssbfs != "" && tdzzssbfs ==<%=Constants.TDZSS_SB_FLWS%>) {
							setCheckbox("tdzzssbfs_5", true);
			}
		}
		//--modified end
		
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
		//var cqzbzjzmjfl = '<bean:write name="ClfswjghdxxlrForm" property="cqzbzjzmjfl"/>';
		//if (cqzbzjzmjfl != "" && cqzbzjzmjfl ==<%=Constants.CQZBZ_JLMX_LOW%>) {
		//	setCheckbox("cqzbzjzmjfl_0", true);

		//} else if (cqzbzjzmjfl != ""&& cqzbzjzmjfl ==<%=Constants.CQZBZ_JLMX_HIGH%>) {
		//	setCheckbox("cqzbzjzmjfl_1", true);

		//}

		//每平米交易单价
		var mpmjydj = '<bean:write name="ClfswjghdxxlrForm" property="mpmjydj"/>';
		putObjectValue("", "mpmjydjShow", mpmjydj);

		//房屋所在区域
		var fwszqy = '<bean:write name="ClfswjghdxxlrForm" property="fwszqymc"/>';
		putObjectValue("", "fwszqyShow", fwszqy);

		//普通住房最高限价
	//	var ptzfzgxj = '<bean:write name="ClfswjghdxxlrForm" property="ptzfzgxj"/>';
	//	putObjectValue("", "ptzfzgxjShow", ptzfzgxj);

		//房屋容积率
	//	var fwrjl = '<bean:write name="ClfswjghdxxlrForm" property="fwrjl"/>';
	//	if (fwrjl != "" && fwrjl ==<%=Constants.FWRJL_LOW%>) {
	//		setCheckbox("fwrjl_0", true);
	//	} else if (fwrjl != "" && fwrjl ==<%=Constants.FWRJL_HIGH%>) {
	//		setCheckbox("fwrjl_1", true);
	//	}

		//划分标准
	//	var hfbz = '<bean:write name="ClfswjghdxxlrForm" property="hfbz"/>';
	//	if (hfbz != "" && hfbz ==<%=Constants.HFBZ_PT%>) {
	//		setCheckbox("hfbz_0", true);
	//	} else if (hfbz != "" && hfbz ==<%=Constants.HFBZ_NOTPT%>) {
	//		setCheckbox("hfbz_1", true);
	//	}

		//住房使用时间
	//	var zfsjsjfl = '<bean:write name="ClfswjghdxxlrForm" property="zfsjsjfl"/>';
	//	if (zfsjsjfl != "" && zfsjsjfl ==<%=Constants.ZFSYSJ_FIVE%>) {
	//		setCheckbox("zfsjsjfl_0", true);
	//	} else if (zfsjsjfl != ""&& zfsjsjfl ==<%=Constants.ZFSYSJ_THREETOFOIVE%>) {
	//		setCheckbox("zfsjsjfl_1", true);
	//	} else if (zfsjsjfl != "" && zfsjsjfl ==<%=Constants.ZFSYSJ_THREE%>) {
	//		setCheckbox("zfsjsjfl_2", true);
	//	}
		//营业税征收方式 -- 免征营业税□     全额征收营业税□     差额征收营业税□    
		var yyszsfs = '<bean:write name="ClfswjghdxxlrForm" property="yyszsfs"/>';
		if (yyszsfs != "" && yyszsfs ==<%=Constants.YSSZSFS_ALL%>) {
			setCheckbox("yyszsfs_1", true);
		} else if (yyszsfs != "" && yyszsfs ==<%=Constants.YSSZSFS_MINUS%>) {
			setCheckbox("yyszsfs_2", true);
		}

		//个人所得税征收方式 -- 免征个人所得税□ 征收个人所得税□   
		var grsdszsfs = '<bean:write name="ClfswjghdxxlrForm" property="grsdszsfs"/>';

		if (grsdszsfs != "" && grsdszsfs ==<%=Constants.GRSDSZSFS_ZS%>) {
			setCheckbox("grsdszsfs_1", true);
		}

		//印花税征收方式 --征收印花税 免征印花税
    	var yhszsfs = '<bean:write name="ClfswjghdxxlrForm" property="yhszsfs"/>';
    	if(yhszsfs!=""&&yhszsfs==<%=Constants.YHSZSFS_ZS%>){
    		//征收印花税
			setCheckbox("yhs_0", true);
    	}else if(yhszsfs!=""&&yhszsfs==<%=Constants.YHSZSFS_FREE%>){
    		//免征印花税
			setCheckbox("yhs_1", true);
    	}
		//土地增值税征收方式 -- 不征收土地增值税    免征土地增值税□    全额征收土地增值税□、提供购房发票征收土地增值税、核定征收土地增值税、提供评估报告征收土地增值税
		var tdzsszsfs = '<bean:write name="ClfswjghdxxlrForm" property="tdzsszsfs"/>';
		if (tdzsszsfs != "" && tdzsszsfs ==<%=Constants.TDZZSZSFS_NOT%>) {
				//不征收土地增值税
				setCheckbox("tdzsszsfs_0", true);

		} else if (tdzsszsfs != "" && tdzsszsfs ==<%=Constants.TDZZSZSFS_FREE%>) {
				//免征土地增值税
				setCheckbox("tdzsszsfs_1", true);

		}else if (tdzsszsfs != "" && tdzsszsfs ==<%=Constants.TDZZSZSFS_GFFPZS%>) {
				//提供购房发票征收土地增值税
				setCheckbox("tdzsszsfs_4", true);

		}else if(tdzsszsfs !=""&&tdzsszsfs ==<%=Constants.TDZZSZSFS_HDZS%>){
				//核定征收土地增值税
				setCheckbox("tdzsszsfs_5",true);
		}else if(tdzsszsfs !=""&&tdzsszsfs ==<%=Constants.TDZZSZSFS_PGBGZS%>){
				//提供评估报告征收土地增值税
				setCheckbox("tdzsszsfs_6",true);
		}

		//计税收入确认方式及金额
		var jssrqrfs = '<bean:write name="ClfswjghdxxlrForm" property="jssrqrfs"/>';
		var jsjeSubmit = '<bean:write name="ClfswjghdxxlrForm" property="jsje"/>';

		var blankHTML = "&nbsp;&nbsp;";
		var jeHTML = blankHTML + "金额：";

		var jssrqrfs_0_true = "<input type=\"checkbox\" value=\"0\" id=\"jssrqrfs_0\" onclick=\"return false\" checked=\"true\">合同价格</input>"
				+ jeHTML;
		var jssrqrfs_0_false = "<input type=\"checkbox\" value=\"0\" id=\"jssrqrfs_0\" onclick=\"return false\">合同价格</input>"
				+ jeHTML;

		var jssrqrfs_1_true = "<input type=\"checkbox\" value=\"1\" id=\"jssrqrfs_1\" onclick=\"return false\" checked=\"true\">核定计税价格</input>"
				+ jeHTML;
		var jssrqrfs_1_false = "<input type=\"checkbox\" value=\"1\" id=\"jssrqrfs_1\" onclick=\"return false\">核定计税价格</input>"
				+ jeHTML;

		if (jssrqrfs != "" && jssrqrfs ==<%=Constants.JSSRQRFS_HTJG%>) {
			putObjectValue("", "jssrqrfsShow", jssrqrfs_0_true + ""
					+ jsjeSubmit + "" + blankHTML + "<br>" + jssrqrfs_1_false + ""
					+ blankHTML);
			//setCheckbox("jssrqrfs_0",true);
		} else if (jssrqrfs != "" && jssrqrfs ==<%=Constants.JSSRQRFS_HDJSJG%>) {
			//putObjectValue("", "jssrqrfsShow", jssrqrfs_1_true+""+jsjeSubmit+""+blankHTML+""+jssrqrfs_0_false+""+blankHTML);
			putObjectValue("", "jssrqrfsShow", jssrqrfs_0_false + ""
					+ blankHTML + "<br>" + jssrqrfs_1_true + "" + jsjeSubmit + ""
					+ blankHTML + "");
			//setCheckbox("jssrqrfs_1",true);
		}
		/*
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
		*/
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
		setSbxx(tdzzssbfs, jssrqrfs);

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
	function setSbxx(tdzzssbfs, jssrqrfs) {

		//中文描述
		var blank_1 = "&nbsp;&nbsp;";
		var blank_2 = "&nbsp;&nbsp;";
		var jssr_ZH = "计税收入&nbsp;&nbsp;";
		var yysse_ZH = "营业税税额";
		var tdzzs_FP_PGBG_ZH = "增值额×适用税率-扣除项目金额合计×速算扣除系数" + blank_2 + "=";
		var tdzzs_JB_ZH = "土地增值税应纳税款";
		var grsds_AS_ZH = "（房屋转让收入-税金-合理费用）";

		//获得各个DIV对象
		var yys_show_OBJ = document.all.yys_show;//营业税税额
		var cjs_show_OBJ = document.all.cjs_show;//城建税税额
		var jyffj_show_OBJ = document.all.jyffj_show;//教育费附加金额
		var dfjyfj_show_OBJ = document.all.dfjyfj_show;//地方教育附加金额
		var tdzzs_FP_PGBG_show_OBJ = document.all.tdzzs_FP_PGBG_show;//土地增值税(按发票或评估报告征收)
		var tdzzs_HD_show_OBJ = document.all.tdzzs_HD_show;//土地增值税（核定征收）
		//var tdzzs_JB_show_OBJ = document.all.tdzzs_JB_show;//土地增值税（减半征收）
		//var grsds_HD_show_OBJ = document.all.grsds_HD_show;//个人所得税（核定征收）
		var grsds_AS_show_OBJ = document.all.grsds_AS_show;//个人所得税（按实征收）
		var yhs_show_OBJ = document.all.yhsse_show;//印花税

		var tdzzszsfs = '<bean:write name="ClfswjghdxxlrForm" property="tdzsszsfs" />';
		<logic:iterate id="sbitem" name="ClfswjghdxxlrForm"  property ="mfsbxxList" indexId="index">
		var szsmdm = '<bean:write name="sbitem" property="szsmdm"/>';//税种税目代码
		var sl = '<bean:write name="sbitem" property="sl"/>';//税率
		var jsje = '<bean:write name="sbitem" property="jsje"/>';//计税金额
		var sjje = '<bean:write name="sbitem" property="sjje"/>';//税金税额

		if (szsmdm != null && szsmdm != "") {
			var sbxxMsg = blank_1 + jsje + blank_2 + "×" + sl + "%" + blank_2
					+ "=" + blank_2 + sjje;
			switch (szsmdm) {
			case '02'://设置营业税
				yys_show_OBJ.innerHTML = jssr_ZH + sbxxMsg;
				break;
			case '10'://城建税
				cjs_show_OBJ.innerHTML = yysse_ZH + sbxxMsg;
				break;
			case '51'://教育费附加
				jyffj_show_OBJ.innerHTML = yysse_ZH + sbxxMsg;
				break;
			case '54'://地方教育费附加
				dfjyfj_show_OBJ.innerHTML = yysse_ZH + sbxxMsg;
				break;
			case '08'://土地增值税
				if (tdzzszsfs != null && tdzzszsfs != "") {
					//（1）按发票或评估价格
					if (tdzzszsfs ==<%=Constants.TDZZSZSFS_GFFPZS%>|| tdzzszsfs ==<%=Constants.TDZZSZSFS_PGBGZS%>) {
						//alert(tdzzs_FP_PGBG_ZH+","+blank_2+","+sjje)
						tdzzs_FP_PGBG_show_OBJ.innerHTML = tdzzs_FP_PGBG_ZH
								+ blank_2 + sjje;
					}

					//（2）核定征收
					if (tdzzszsfs ==<%=Constants.TDZZSZSFS_HDZS%>)//按发票或评估价格
					{
						tdzzs_HD_show_OBJ.innerHTML = jssr_ZH + sbxxMsg;
					}
					//（3）减半征收(暂无)								
				}
				break;
			case '05'://个人所得税
				if (jssrqrfs != null && jssrqrfs != "") {
					//（1）核定征收
					//if (jssrqrfs ==<%=Constants.JSSRQRFS_HDJSJG%>) {
					//	grsds_HD_show_OBJ.innerHTML = jssr_ZH + sbxxMsg;
					//} else {
						//（2）按实征收
						grsds_AS_show_OBJ.innerHTML = grsds_AS_ZH + sbxxMsg;
					//}
				}
				break;
			case '16'://印花税
				yhs_show_OBJ.innerHTML = "合同价格"+sbxxMsg;
			default:
				break;
			}
		}
		</logic:iterate>

	}

	//选中复选选框
	function setCheckbox(checkBoxName, isChecked) {
		if (checkBoxName != null && checkBoxName != "" && isChecked != null
				&& isChecked != "") {
			document.getElementById(checkBoxName).checked = isChecked;

		}
	}

	//置值方法
	function putObjectValue(hiddProperty, td_id, obj_value) {
		if (hiddProperty != null && hiddProperty != "") {
			hiddProperty.value = obj_value;
		}

		//alert(td_id +"-----------------"+obj_value);
		if (td_id != null && td_id != "" && obj_value != null
				&& obj_value != "" && obj_value != "0.00"
				&& obj_value != "0.0000") {
			document.getElementById(td_id).innerHTML = "<div align='left'>"
					+ obj_value + "&nbsp;</div>";
		}
		return true;
	}

	function getZjmc(zjdm) {
		//alert(1111111111);
		<logic:iterate id="item" name="ClfswjghdxxlrForm"  property ="zjList" indexId="index">
		var zjdm_1 = '<bean:write name="item" property="zjlxdm"/>';
		var zjmc_1 = '<bean:write name="item" property="zjlxmc"/>';

		if (zjdm == zjdm_1) {

			return zjmc_1;

		}
		</logic:iterate>

		return "其他";
	}
</script>
</HTML>
