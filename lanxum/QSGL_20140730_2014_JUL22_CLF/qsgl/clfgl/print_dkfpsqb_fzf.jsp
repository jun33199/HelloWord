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
<TITLE>�������۷��ݴ�����Ʊ�����</TITLE>

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
					��&nbsp;��&nbsp;��&nbsp;��&nbsp;��&nbsp;��&nbsp;��&nbsp;��&nbsp;��&nbsp;˰&nbsp;��&nbsp;��&nbsp;��&nbsp;��&nbsp;��&nbsp;<br>(��&nbsp;��&nbsp;��&nbsp;Ʊ&nbsp;��&nbsp;��&nbsp;��)
				</h3> ��ţ���ˮ�ţ���<bean:write name="ClfswjghdxxlrForm" property="sbbh" />
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�������ڣ�<bean:write
					name="ClfswjghdxxlrForm" property="slrq" /> <br>
					  ��ͬ��ţ�<bean:write name="ClfswjghdxxlrForm" property="htbh"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��λ��Ԫ���Ƿ֣�
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
																	rowspan="18">��<br>˰<br>��<br>��<br>д
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
																		<td class=2-td2-left colspan="6" style="border-left: 0px solid #9BB4CA;">��������Ϣ</td>
																	</tr>
																	<tr>
																		<td class=2-td2-noleft align="left" width="30%"><div align="left">�����ˣ�����������</div></td>
																		<td class=2-td2-left width="20%" colspan="2">&nbsp;��ϵ�绰</td>
																		<td class=2-td2-left width="20%" ><div align="left">�����ˣ��������Ϸ����֤��</div></td>
																		<td class=2-td2-center style="border-right: 0px solid;" width="30%"  colspan="2">֤������</td>
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
																		<td class=2-td2-left colspan="6" style="border-left: 0px solid #9BB4CA;">�����Ϣ</td>
																	</tr>
																	
																	<tr>
																		<td class=2-td2-noleft align="left" width="30%"><div align="left">�����ˣ��򷽣�����</div></td>
																		<td class=2-td2-left width="20%" colspan="2">��ϵ�绰</td>
																		<td class=2-td2-left width="20%"><div align="left">�����ˣ��򷽣��Ϸ����֤��</div></td>
																		<td class=2-td2-center style="border-right: 0px solid;" width="30%"  colspan="2">֤������</td>
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
																	nowrap="nowrap" ><div align="left">������ϸ��ַ</div></td>
																<td class=2-td2-center width="90%" id="fwzldzShow"
																	 colspan="5" >&nbsp;</td>

															</tr>
															<tr>
																<td class=2-td2-left width="10%" nowrap="nowrap"><div
																		align="left" >�Ƿ�Ϊ��ͥΨһ�����÷�</div></td>
																<td class=2-td2-center width="90%" nowrap="nowrap" colspan="5" >
																	<div align="left">
																		
																		<input type="checkbox" value="0" id="sfwyzf_Y"
																			onclick="return false;">��</input> &nbsp;&nbsp; <input
																			type="checkbox" value="1" id="sfwyzf_N"
																			onclick="return false;">��</input>
																	</div>
																</td>
															</tr>
															<tr>
																<td class=2-td2-left align="left" width="10%"
																	nowrap="nowrap"><div align="left">���ݲ�Ȩ֤��</div></td>
																<td class=2-td2-left width="35%" id="fwcqzhShow"
																	nowrap="nowrap" >&nbsp;</td>
																<td class=2-td2-left width="20%" nowrap="nowrap"><div
																		align="left">���ݲ�Ȩ֤ס������</div></td>
																<td class=2-td2-center width="15%" id="fwcqzbzzflxmcShow"
																	nowrap="nowrap" colspan="3">
																	&nbsp;
																</td>
															<tr>
																<td class=2-td2-left width="15%" nowrap="nowrap" colspan="2"><div
																		align="left">���뿪Ʊ�����ν��׽�</div></td>
																<td class=2-td2-center width="15%" id="sqkpjeShow" style="font-weight:bold;" colspan="4">&nbsp;</td>
															</tr>															</tr>

															<tr>
																<td class=2-td2-left width="10%" nowrap="nowrap"><div
																		align="left">��������</div></td>
																<td class=2-td2-center width="30%" id="fwlxShow"
																	colspan="5" nowrap="nowrap">
																	<div align="left">
																		
																		<input type="checkbox" value="0" id="fwlx_0"
																			onclick="return false;">¥��</input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
																			type="checkbox" value="1" id="fwlx_1"
																			onclick="return false;">ƽ��</input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
																			type="checkbox" value="2" id="fwlx_2"
																			onclick="return false;">������</input>
																	</div>
																</td>
															</tr>
															<tr>
																<td class=2-td2-left align="left" width="10%"
																	nowrap="nowrap"><div align="left">�������</div></td>
																<td class=2-td2-left width="35%" id="jcndShow"
																	nowrap="nowrap" style="font-weight:bold;">&nbsp;</td>
																<td class=2-td2-left width="10%" nowrap="nowrap"><div
																		align="left">��¥��</div></td>
																<td class=2-td2-left width="15%" id="zlcShow" style="font-weight:bold;">&nbsp;</td>
																<td class=2-td2-left width="10%" ><div
																		nowrap="nowrap" align="left">����¥��</div></td>
																<td class=2-td2-center width="20%" id="szlcShow" style="font-weight:bold;" nowrap="nowrap">&nbsp;</td>
															</tr>
															<tr>
																<td class=2-td2-left align="left"width="10%"
																	nowrap="nowrap"><div align="left">���ݽ������</div></td>
																<td class=2-td2-left width="35%" id="fwjzmjShow" style="font-weight:bold;" nowrap="nowrap">&nbsp;</td>
																<td class=2-td2-left width="15%" nowrap="nowrap"><div
																		align="left">ԭ������Ʊ���</div></td>
																<td class=2-td2-center width="30%" id="ygffpjeShow"
																	colspan="3" style="font-weight:bold;">&nbsp;</td>
															</tr>
															<tr>
																<td class=2-td2-left align="left" width="10%"
																	nowrap="nowrap"><div align="left">����֤������</div></td>
																<td class=2-td2-left width="35%" id="gfzmrqShow" style="font-weight:bold;">&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��</td>
																<td class=2-td2-left width="15%" nowrap="nowrap"><div
																		align="left">����˫����ͬǩ������</div></td>
																<td class=2-td2-center width="30%" id="htwsqyrqShow"
																	colspan="3" style="font-weight:bold;">&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��</td>
															</tr>
															<tr>
																<td class=2-td2-center width="10%" nowrap="nowrap"
																	colspan="6"><div align="center">�걨��ʽ�����ݸ�˰����ع涨�ɶ�ѡ��</div></td>
															</tr>
															<tr>
																<td class=2-td2-center width="15%" nowrap="nowrap"
																	colspan="6">
																	<div align="center">
																		
																		<input type="checkbox" value="0" id="tdzzssbfs_0"
																			onclick="return false;">�ṩ������Ʊ</input>&nbsp;&nbsp; <input
																			type="checkbox" value="1" id="tdzzssbfs_1"
																			onclick="return false;">�ṩ������˰Ʊ</input>&nbsp;&nbsp; <input
																			type="checkbox" value="2" id="tdzzssbfs_2"
																			onclick="return false;">�ṩԭ������ͬ</input>&nbsp;&nbsp;<br> <input
																			type="checkbox" value="3" id="tdzzssbfs_3"
																			onclick="return false;">�ṩ��������</input>&nbsp;&nbsp; <input
																			type="checkbox" value="4" id="tdzzssbfs_4"
																			onclick="return false;">���κ�Ʊ��</input>&nbsp;&nbsp; <input
																			type="checkbox" value="5" id="tdzzssbfs_5"
																			onclick="return false;">�ṩ��������</input>
																	</div>
																</td>
															</tr>
															<tr>
																<td class=2-td2-left align="left" width="10%"
																	nowrap="nowrap" colspan="2"><div align="center">�ṩ������Ʊ��д����</div></td>
																<td class=2-td2-center align="left" width="15%"
																	nowrap="nowrap" colspan="5"><div align="center">�ṩ����������д������</div></td>
															</tr>
															<tr>
																<td class=2-td2-left width="20%" 
																	rowspan="4"><div align="left">ȡ�÷��ز�ʱ�����ɵ���˰���</div></td>
																<td class=2-td2-left width="15%"
																	rowspan="4" id="qdfcqsjeShow" style="font-weight:bold;">&nbsp;</td>
															</tr>
															<tr>
																<td class=2-td2-left width="15%" nowrap="nowrap"><div
																		align="left">ȡ������ʹ��Ȩ��֧���Ľ��</div></td>
																<td class=2-td2-center width="30%" colspan="3"
																	id="qdtdsyqzfjeShow" style="font-weight:bold;">&nbsp;</td>
															</tr>
															<tr>
																<td class=2-td2-left width="15%" nowrap="nowrap"><div
																		align="left">�ɷ���������������۸�</div></td>
																<td class=2-td2-center width="30%" colspan="3"
																	id="jfpgjgShow" style="font-weight:bold;">&nbsp;</td>
															</tr>
															<tr>
																<td class=2-td2-left width="15%" nowrap="nowrap"><div
																		align="left">�۸���������</div></td>
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
																<td class=2-td1-left rowspan="10" width="1%">˰<br>��<br>��<br>��<br>ȷ<br>��
																</td>

															</tr>
															<tr>
																<td class=2-td2-t-left  width="25%" nowrap="nowrap" ><div
																		align="left">ÿƽ�׽��׵���</div></td>
																<td class=2-td2-t-left  width="30%" id="mpmjydjShow" style="font-weight:bold;">&nbsp;</td>
																<td class=2-td2-t-left  width="25%" nowrap="nowrap" ><div
																		align="left">������������</div></td>
																<td class=2-td2-t-left  width="30%" id="fwszqyShow" style="border-right:1px solid #9BB4CA;">&nbsp;</td>
															</tr>
															<tr>
																<td class=2-td2-center width="15%" colspan="6" nowrap="nowrap"><div
																		align="center">
																
																		<input type="checkbox" value="1" id="yyszsfs_1"
																			onclick="return false;">ȫ������Ӫҵ˰</input> <input
																			type="checkbox" value="2" id="yyszsfs_2"
																			onclick="return false;">�������Ӫҵ˰</input> <input
																			type="checkbox" value="1" id="grsdszsfs_1"
																			onclick="return false;">���ո�������˰</input> <input
																			type="checkbox" value="4" id="tdzsszsfs_4"
																			onclick="return false;">�ṩ������Ʊ����������ֵ˰</input><br> <input
																			type="checkbox" value="5" id="tdzsszsfs_5"
																			onclick="return false;">�˶�����������ֵ˰</input> <input
																			type="checkbox" value="6" id="tdzsszsfs_6"
																			onclick="return false;">�ṩ������������������ֵ˰</input> <input
																			type="checkbox" value="0" id="tdzsszsfs_0"
																			onclick="return false;">����������ֵ˰</input><input
																			type="checkbox" value="1" id="tdzsszsfs_1"
																			onclick="return false;">����������ֵ˰</input><br>
																			<input type="checkbox" value="0" id="yhs_0" onclick="return false;">����ӡ��˰</input>
																	<input type="checkbox" value="1" id="yhs_1" onclick="return false;">&nbsp;&nbsp;����ӡ��˰</input>

																	</div></td>
															</tr>
															<tr>
																<td class=2-td2-left width="15%" nowrap="nowrap"><div
																		align="left">��˰����ȷ�Ϸ�ʽ�����</div></td>
																<td class=2-td2-center width="15%" colspan="5"
																	id="jssrqrfsShow" style="font-weight:bold;">
																	<div align="left">
																		
																		<input type="checkbox" value="0" id="jssrqrfs_0"
																			onclick="return false;">��ͬ�۸�</input>
																		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																		<input type="checkbox" value="1" id="jssrqrfs_1"
																			onclick="return false;">�˶���˰�۸�</input>
																		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
																	</div>
																</td>
															</tr>

															<tr>
																<td colspan="6" class=2-td2-center>
																	<table border=0 cellSpacing=0>
																		<tr>
																			<td class=2-td2-center
																				style="border-left: 0px solid; border-right: 0px solid;"
																				width="15%" colspan="8">�۳����ȷ��</td>
																		</tr>
																		<tr>
																			<td class=2-td2-left style="border-left: 0px solid;"
																				align="left" width="15%" nowrap="nowrap"><div
																					align="left">ԭ������Ʊ�۸�</div></td>
																			<td class=2-td2-left width="20%" id="ygffpjeShow_1" style="font-weight:bold;">&nbsp;</td>
																			<td class=2-td2-left width="15%" nowrap="nowrap"><div
																					align="left">ס�������۸�</div></td>
																			<td class=2-td2-left width="20%" id="zfpgjgShow" style="font-weight:bold;">&nbsp;</td>
																			<td class=2-td2-left width="10%"><div
																					align="left">
																					���У�<br>�ɷ��������������۸�
																				</div></td>
																			<td class=2-td2-center
																				style="border-right: 0px solid;" width="20%"
																				colspan="3" id="jfpgjgShow1" style="font-weight:bold;">&nbsp;</td>
																		</tr>
																		<tr>
																			<td class=2-td2-left style="border-left: 0px solid;"
																				width="15%" nowrap="nowrap"><div align="left">Ӫҵ˰˰��</div></td>
																			<td class=2-td2-left width="15%" colspan="3"><div
																					align="left" id="yys_show" style="font-weight:bold;">��˰����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��5%&nbsp;&nbsp;&nbsp;=</div>
																			</td>
																			<td class=2-td2-left width="15%" nowrap="nowrap"><div
																					align="left">��˰ƾ֤��</div></td>
																			<td class=2-td2-center width="30%" colspan="3"
																				style="border-right: 0px solid;">&nbsp;</td>
																		</tr>
																		<tr>
																			<td class=2-td2-left style="border-left: 0px solid;"
																				width="15%" nowrap="nowrap"><div align="left">�ǽ�˰˰��</div></td>
																			<td class=2-td2-left width="15%" colspan="3"><div
																					align="left" id="cjs_show" style="font-weight:bold;">Ӫҵ˰˰��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��5%&nbsp;&nbsp;&nbsp;=</div>
																			</td>
																			<td class=2-td2-left width="15%" nowrap="nowrap"><div
																					align="left">��˰ƾ֤��</div></td>
																			<td class=2-td2-center width="30%" colspan="3"
																				style="border-right: 0px solid;">&nbsp;</td>
																		</tr>
																		<tr>
																			<td class=2-td2-left style="border-left: 0px solid;"
																				width="15%" nowrap="nowrap"><div align="left">�����Ѹ��ӽ��</div></td>
																			<td class=2-td2-left width="15%" colspan="3"><div
																					align="left" id="jyffj_show" style="font-weight:bold;">Ӫҵ˰˰��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��3%&nbsp;&nbsp;&nbsp;=</div>
																			</td>
																			<td class=2-td2-left width="15%" nowrap="nowrap"><div
																					align="left">��˰ƾ֤��</div></td>
																			<td class=2-td2-center width="30%" colspan="3"
																				style="border-right: 0px solid;">&nbsp;</td>
																		</tr>
																		<tr>
																			<td class=2-td2-left style="border-left: 0px solid;" width="15%" nowrap="nowrap"><div
																					align="left">�ط��������ӽ��</div></td>
																			<td class=2-td2-left width="15%" colspan="3"><div
																					align="left" id="dfjyfj_show" style="font-weight:bold;">Ӫҵ˰˰��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��2%&nbsp;&nbsp;&nbsp;=</div>
																			<td class=2-td2-left width="15%" nowrap="nowrap"><div
																					align="left">��˰ƾ֤��</div></td>
																			<td class=2-td2-center width="30%" colspan="3"
																				style="border-right: 0px solid;">&nbsp;</td>
																			</td>
																		</tr>
																		<tr>
																			<td class=2-td2-left style="border-left: 0px solid;" width="15%" nowrap="nowrap"><div
																					align="left">ӡ��˰���</div></td>
																			<td class=2-td2-left width="15%" colspan="3"><div
																					align="left" id="yhsse_show" style="font-weight:bold;">��ͬ�۸�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��0.05%&nbsp;&nbsp;&nbsp;=</div>
																			<td class=2-td2-left width="15%" nowrap="nowrap"><div
																					align="left">��˰ƾ֤��</div></td>
																			<td class=2-td2-center width="30%" colspan="3"
																				style="border-right: 0px solid;">&nbsp;</td>
																			</td>
																		</tr>
																		<tr>
																			<td class=2-td2-left style="border-left: 0px solid;"
																				width="15%" nowrap="nowrap"><div align="left">������ֵ˰<br>(����Ʊ��������������)</div></td>
																			<td class=2-td2-left width="15%" colspan="3"><div
																					align="left" id="tdzzs_FP_PGBG_show" style="font-weight:bold;">
																					��ֵ�������˰��-�۳���Ŀ���ϼơ�����۳�ϵ��=<br>&nbsp;
																				</div></td>
																			<td class=2-td2-left width="15%" nowrap="nowrap"><div
																					align="left">��˰ƾ֤��</div></td>
																			<td class=2-td2-center width="30%" colspan="3"
																				style="border-right: 0px solid;">&nbsp;</td>
																		</tr>
																		<tr>
																			<td class=2-td2-left style="border-left: 0px solid;"
																				width="15%" nowrap="nowrap"><div align="left">������ֵ˰���˶����գ�</div></td>
																			<td class=2-td2-left width="15%" colspan="3"><div
																					align="left" id="tdzzs_HD_show" style="font-weight:bold;">��˰����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��5%&nbsp;&nbsp;&nbsp;=</div>
																			</td>
																			<td class=2-td2-left width="15%" nowrap="nowrap"><div
																					align="left">��˰ƾ֤��</div></td>
																			<td class=2-td2-center width="30%" colspan="3"
																				style="border-right: 0px solid;">&nbsp;</td>
																		</tr>
																		
																		<tr>
																			<td class=2-td2-left style="border-left: 0px solid;"
																				width="15%" nowrap="nowrap"><div align="left">��������˰����ʵ���գ�</div></td>
																			<td class=2-td2-left width="15%" colspan="3"><div
																					align="left" id="grsds_AS_show" style="font-weight:bold;">������ת������-˰��-������ã�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��20%&nbsp;&nbsp;&nbsp;=</div>
																			</td>
																			<td class=2-td2-left width="15%" nowrap="nowrap"><div
																					align="left">��˰ƾ֤��</div></td>
																			<td class=2-td2-center width="30%" colspan="3"
																				style="border-right: 0px solid;">&nbsp;</td>
																		</tr>
																		
																		
																		<tr>
																			<td class=2-td2-left style="border-left: 0px solid;"
																				width="15%" nowrap="nowrap"><div align="left">����˰�Ѻϼ�</div></td>
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
																								align="left">��Ʊ����</div></td>
																						<td class=2-td2-center-noBottom width="25%">&nbsp;</td>
																						<td class=2-td2-center-noBottom nowrap="nowrap"><div
																								align="left">��Ʊ����</div></td>
																						<td class=2-td2-center-noBottom width="25%">&nbsp;</td>
																						<td class=2-td2-center-noBottom nowrap="nowrap"><div
																								align="left">��Ϣ��</div></td>
																						<td class=2-td2-center-noBottom width="25%">&nbsp;</td>
																						<td class=2-td2-center-noBottom nowrap="nowrap"><div
																								align="left">���߽��</div></td>
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
																<td class=2-td1-left width="1%" rowspan="2">��<br>��
																</td>
																<td class=2-td2-center width="15%" colspan="6"
																	style="border-top: 1px solid #9BB4CA;"><div
																		align="left">
																		<h3>�����ṩ����Ϣ��ʵ��׼ȷ��Ը�е���Ӧ�ķ������Ρ�</h3>
																		<h3>��˰��ǩ�£� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																	                          ������ǩ�£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>���������֤���룺&nbsp;&nbsp;&nbsp; 
																        </h3>
																	</div></td>
															</tr>
															<tr>
																<td class=2-td2-left align="left" width="15%"
																	nowrap="nowrap"><div align="left">������Աǩ��</div></td>
																<td class=2-td2-left width="30%">&nbsp;</td>
																<td class=2-td2-left width="15%" nowrap="nowrap"><div
																		align="left">������Աǩ��</div></td>
																<td class=2-td2-center width="30%" colspan="3">&nbsp;</td>
															</tr>
															<tr>
																<td colspan="7" class=2-td1-left
																	style="border-right: 1px solid #9BB4CA; border-top: 0px solid;"><div
																		align="right">����λ�£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div></td>
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
																					color="#0000FF">���˵��</font></strong></td>
																			<td>
																				<hr width="100%" size="1">
																			</td>
																		</tr>
																	</table>
																	<table border="1" align="center" cellpadding="1"
																		cellspacing="1" bordercolor="#FFFFFF" class="black9">
																		<tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
																			<td>
																				&nbsp;&nbsp;1.���������ڸ��������ѹ�ס��������˰�걨����Ӫҵ˰���ǽ�˰�������Ѹ��ӡ���������˰��������ֵ˰��˰�ּ�������Ʊ�������˰���ˣ�<br>
																				&nbsp;&nbsp;2.��˰�����ͬʱת���������������Ϸ��ز���Ӧ�ֱ������<br>
																				&nbsp;&nbsp;3.����һʽ���ݣ�һ�ݷ�����˰�ˣ�һ��˰��������棻<br>
																				&nbsp;&nbsp;4.��������ϸ��ַ����ָ�������۵��ѹ�ס���ľ����ַ��<br>
																				&nbsp;&nbsp;5.�����ݲ�Ȩ֤����ע��ס�����ࡱ���շ���֤�ϵ�������ݣ�����ǣ���д��һ����ָ���ķ�����������ס�������վ�������ס�������÷�����Ʒס���ȣ�<br>
																				&nbsp;&nbsp;6.�����ݲ�Ȩ֤�š���ָ�������۵���Ʒס���Ĳ�Ȩ֤����롣<br>
																				&nbsp;&nbsp;7.�����뿪Ʊ�����ν��׽�����ָ����˰���������Ŀ��߷�Ʊ��������ʵ�ʽ��׵�ʵ�ʽ���˰��Ӧ��ʵ����׽��Խ��׼۸�����ƫ�������������ɵģ���˰����غ˶����ա�;<br>
																				&nbsp;&nbsp;8.������֤�����ڡ���ָ�ܹ�֤������׼ȷ���ڵ�ʱ�䣬����˰��˰֤����˶�֤���������ڡ����ݲ�Ȩ֤������ڵ�֤����������;<br>
																				&nbsp;&nbsp;9.�������ˣ�������ί�з��ز������н���������ݽ��׵ĸ������ˣ���Ӧ��ʵ���з��ز������н���������ز������˵������Ϣ;<br>
																				&nbsp;&nbsp;10.����ͨס������޼ۡ��������ؼ��ε�ƽ���۸����1.2����Ľ���׼;<br>
																				&nbsp;&nbsp;11.����˰��ѡ���ṩ������Ʊ����ʽ�걨ʱ�����걨��д��ȡ�÷��ز�ʱ�����ɵ���˰��������ָ������ʱ�ܹ��ṩ��˰��˰ƾ֤���ؽ��ɵ���˰���;<br>
																				&nbsp;&nbsp;12.����˰��ѡ���ṩ�������桱��ʽ�걨ʱ���밴�ṩ����������Ӧ���������ݽ����걨��д����1��ȡ������ʹ��Ȩ��֧���Ľ��������ָ����˰��Ϊȡ������ʹ��Ȩʱ��ʵ��֧���ĵؼۿ�Ͱ�����ͳһ�涨���ɵ��йط��ã�2���ɷ���������������۸�������ָ���н�����������óɱ��������������ķ��ݼ�������۸񣻣�3���۸���������������ָ��˰�˶Է��ݼ���������Ҫ���м۸�����ʱ��֧�����������á�<br>
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
		<img alt="ִ�д�ӡ����" style="cursor: hand" onclick="printTab()"
			src="<%=static_file%>images/dayin1.jpg" width="79" height="22"
			id="dayin1" />&nbsp;&nbsp; <img alt="�˳���ӡҳ��" style="cursor: hand"
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

	//wb ��ӡ

	//����ֽ�ŷ���
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
	//������ҳ��ӡ��ҳüҳ��Ϊ��    
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

	//�ָ���ҳ��ӡ��ҳüҳ��   

	function PageSetup_default() {
		try {
			var Wsh = new ActiveXObject("WScript.Shell");
			HKEY_Key = "header";
			Wsh.RegWrite(HKEY_Root + HKEY_Path + HKEY_Key, "&w&bҳ��,&p/&P");
			HKEY_Key = "footer";
			Wsh.RegWrite(HKEY_Root + HKEY_Path + HKEY_Key, "&u&b&d");
		} catch (e) {
		}
	}

	function printsetup() {
		//��ӡҳ������ 
		wb.execwb(8, 1);
	}

	function printpreview() {
		//��ӡҳ��Ԥ�� 
		PageSetup_Null();
		wb.execwb(7, 1);
	}

	function printit() {
		if (confirm('ȷ����ӡ��')) {
			PageSetup_Null();
			wb.execwb(6, 6);
		}
	}
</script>

<script type="text/javascript">
	//����ʽ
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
	//�˺����Ǳ������֮��������ʾ�ѱ�����Ϣ�ĺ���
	function showSaveData() {
		//��������

		/* 		var res_slrq = '<bean:write name="ClfswjghdxxlrForm" property="slrq"/>';
		 putObjectValue("", 'slrqShow', transTime("��ͬ����ǩԼ����", slrq)); */
		//��ͬ����ǩԼ����
		var res_htwsqyrq = '<bean:write name="ClfswjghdxxlrForm" property="htwsqyrq"/>';
		putObjectValue("", 'htwsqyrqShow', transTime("��ͬ����ǩԼ����", res_htwsqyrq,
				"yyyy��mm��dd��"));
		//���׷��ݵ�ַ
		var res_fwzldz = '<bean:write name="ClfswjghdxxlrForm" property="fwzldz"/>';
		putObjectValue("", "fwzldzShow", res_fwzldz);
		//����Ȩ��ת������
		//var res_fwqszylxmc = "";
		//putObjectValue("", "fwqszylxmcShow", res_fwqszylxmc);
		//���ݲ�Ȩ֤��
		var res_fwcqzh = '<bean:write name="ClfswjghdxxlrForm" property="fwcqzh"/>';
		putObjectValue("", "fwcqzhShow", res_fwcqzh);

		//��������Ȩ֤�����
		/* 		var res_fwsyqztfrq = '<bean:write name="ClfswjghdxxlrForm" property="fwsyqztfrq"/>';
		 putObjectValue("", "fwsyqztfrqShow", transTime("��������Ȩ֤�����",
		 res_fwsyqztfrq,"yyyy��mm��dd��")); */
		//���ݽ������
		var res_fwjzmj = '<bean:write name="ClfswjghdxxlrForm" property="fwjzmj"/>';
		putObjectValue("", "fwjzmjShow", res_fwjzmj + "ƽ����");

		//������Ϣ
		//var sellerInfo = '<bean:write name="ClfswjghdxxlrForm" property="all_sellerInfo"/>';
		//setBuyorSellInfo(sellerInfo, "sell");
		//alert(sellerInfo);

		//����Ϣ
		//var buyyerInfo = '<bean:write name="ClfswjghdxxlrForm" property="all_buyerInfo"/>';
		//setBuyorSellInfo(buyyerInfo, "buy");
		//alert(buyyerInfo);

		//==============================�˶���Ϣ====================================

		//��������סַ
		//var sqrxzdz = '<bean:write name="ClfswjghdxxlrForm" property="sqrxzdz"/>';
		//putObjectValue("", "sqrxzdzShow", sqrxzdz);

		//�Ƿ�Ϊ��ͥΨһ�����÷�
		var jtwyshyhbz = '<bean:write name="ClfswjghdxxlrForm" property="jtwyshyhbz"/>';
		if (jtwyshyhbz != "" && jtwyshyhbz ==<%=Constants.ONLY_ROOM_YES%>) {
			setCheckbox("sfwyzf_Y", true);
		} else if (jtwyshyhbz != "" && jtwyshyhbz ==<%=Constants.ONLY_ROOM_NOT%>) {
			setCheckbox("sfwyzf_N", true);
		}

		//���ݲ�Ȩ֤ס������
		var fwcqzbzzflxmc = '<bean:write name="ClfswjghdxxlrForm" property="fwcqzbzzflxmc"/>';
		putObjectValue("", "fwcqzbzzflxmcShow", fwcqzbzzflxmc);

		//���뿪Ʊ�����ν��׽�

		var htzj = '<bean:write name="ClfswjghdxxlrForm" property="htzj"/>';
		putObjectValue("", "sqkpjeShow", changeJE(htzj));

		//��������
		var fwlxdm = '<bean:write name="ClfswjghdxxlrForm" property="fwlxdm"/>';
		if (fwlxdm != "" && (fwlxdm ==<%=Constants.FWLX_BUILDINGS%>)) {
			setCheckbox("fwlx_0", true);
		} else if (fwlxdm != "" && (fwlxdm ==<%=Constants.FWLX_BUNGALOW%>)) {
			setCheckbox("fwlx_1", true);
		} else if (fwlxdm != "" && (fwlxdm ==<%=Constants.FWLX_BASEMENT%>)) {
			setCheckbox("fwlx_2", true);
		}

		//�������
		var jcnd = '<bean:write name="ClfswjghdxxlrForm" property="jcnd"/>';
		putObjectValue("", "jcndShow", jcnd);
		//��¥��
		var zlc_show = '<bean:write name="ClfswjghdxxlrForm" property="zlc_show"/>';
		putObjectValue("", "zlcShow", zlc_show);

		//����¥��
		var szlc_show = '<bean:write name="ClfswjghdxxlrForm" property="szlc_show"/>';
		putObjectValue("", "szlcShow", szlc_show);
		//ԭ������Ʊ���
		var ygffpje = '<bean:write name="ClfswjghdxxlrForm" property="ygffpje"/>';
		putObjectValue("", "ygffpjeShow", ygffpje);
		//����֤������
		var gfzmrq = '<bean:write name="ClfswjghdxxlrForm" property="gfzmrq"/>';
		//putObjectValue("", "gfzmrqShow", gfzmrq);
		putObjectValue("", 'gfzmrqShow', transTime("����֤������", gfzmrq,
				"yyyy��mm��dd��"));
		//--modified by huohb,2014-07-18
		//��Ϊ��ס�����걨��ʽ���Զ�ѡ��������������ƴ�ӵ��ַ�������Ҫ���зָ�
		//������ֵ˰�걨��ʽ
		var tdzzssbfs_join = '<bean:write name="ClfswjghdxxlrForm" property="tdzzssbfs"/>';
		var tdzzssbfss = new Array();
		//�ָ��걨��ʽ-�ָ�ԭ��"|"                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
		tdzzssbfss = tdzzssbfs_join.split("|");
		//ѭ���жϣ�ѡ�ж�ѡ��
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
		
		//�ṩ������Ʊ��д����

		//�ṩ����������д������
		//@@ȡ�÷��ز�ʱ�����ɵ���˰���
		var qdfcqsje = '<bean:write name="ClfswjghdxxlrForm" property="qdfcqsje"/>';
		putObjectValue("", "qdfcqsjeShow", qdfcqsje);

		//�ṩ����������д������  
		//@@ȡ������ʹ��Ȩ��֧���Ľ��
		var qdtdsyqzfje = '<bean:write name="ClfswjghdxxlrForm" property="qdtdsyqzfje"/>';
		putObjectValue("", "qdtdsyqzfjeShow", qdtdsyqzfje);
		//@@�ɷ���������������۸�
		var jfpgjg = '<bean:write name="ClfswjghdxxlrForm" property="jfpgjg"/>';
		putObjectValue("", "jfpgjgShow", jfpgjg);
		putObjectValue("", "jfpgjgShow1", jfpgjg);
		//@@�۸���������
		var jgpgfy = '<bean:write name="ClfswjghdxxlrForm" property="jgpgfy"/>';
		putObjectValue("", "jgpgfyShow", jgpgfy);

		//��Ȩ֤��ע������� 
		//var cqzbzjzmjfl = '<bean:write name="ClfswjghdxxlrForm" property="cqzbzjzmjfl"/>';
		//if (cqzbzjzmjfl != "" && cqzbzjzmjfl ==<%=Constants.CQZBZ_JLMX_LOW%>) {
		//	setCheckbox("cqzbzjzmjfl_0", true);

		//} else if (cqzbzjzmjfl != ""&& cqzbzjzmjfl ==<%=Constants.CQZBZ_JLMX_HIGH%>) {
		//	setCheckbox("cqzbzjzmjfl_1", true);

		//}

		//ÿƽ�׽��׵���
		var mpmjydj = '<bean:write name="ClfswjghdxxlrForm" property="mpmjydj"/>';
		putObjectValue("", "mpmjydjShow", mpmjydj);

		//������������
		var fwszqy = '<bean:write name="ClfswjghdxxlrForm" property="fwszqymc"/>';
		putObjectValue("", "fwszqyShow", fwszqy);

		//��ͨס������޼�
	//	var ptzfzgxj = '<bean:write name="ClfswjghdxxlrForm" property="ptzfzgxj"/>';
	//	putObjectValue("", "ptzfzgxjShow", ptzfzgxj);

		//�����ݻ���
	//	var fwrjl = '<bean:write name="ClfswjghdxxlrForm" property="fwrjl"/>';
	//	if (fwrjl != "" && fwrjl ==<%=Constants.FWRJL_LOW%>) {
	//		setCheckbox("fwrjl_0", true);
	//	} else if (fwrjl != "" && fwrjl ==<%=Constants.FWRJL_HIGH%>) {
	//		setCheckbox("fwrjl_1", true);
	//	}

		//���ֱ�׼
	//	var hfbz = '<bean:write name="ClfswjghdxxlrForm" property="hfbz"/>';
	//	if (hfbz != "" && hfbz ==<%=Constants.HFBZ_PT%>) {
	//		setCheckbox("hfbz_0", true);
	//	} else if (hfbz != "" && hfbz ==<%=Constants.HFBZ_NOTPT%>) {
	//		setCheckbox("hfbz_1", true);
	//	}

		//ס��ʹ��ʱ��
	//	var zfsjsjfl = '<bean:write name="ClfswjghdxxlrForm" property="zfsjsjfl"/>';
	//	if (zfsjsjfl != "" && zfsjsjfl ==<%=Constants.ZFSYSJ_FIVE%>) {
	//		setCheckbox("zfsjsjfl_0", true);
	//	} else if (zfsjsjfl != ""&& zfsjsjfl ==<%=Constants.ZFSYSJ_THREETOFOIVE%>) {
	//		setCheckbox("zfsjsjfl_1", true);
	//	} else if (zfsjsjfl != "" && zfsjsjfl ==<%=Constants.ZFSYSJ_THREE%>) {
	//		setCheckbox("zfsjsjfl_2", true);
	//	}
		//Ӫҵ˰���շ�ʽ -- ����Ӫҵ˰��     ȫ������Ӫҵ˰��     �������Ӫҵ˰��    
		var yyszsfs = '<bean:write name="ClfswjghdxxlrForm" property="yyszsfs"/>';
		if (yyszsfs != "" && yyszsfs ==<%=Constants.YSSZSFS_ALL%>) {
			setCheckbox("yyszsfs_1", true);
		} else if (yyszsfs != "" && yyszsfs ==<%=Constants.YSSZSFS_MINUS%>) {
			setCheckbox("yyszsfs_2", true);
		}

		//��������˰���շ�ʽ -- ������������˰�� ���ո�������˰��   
		var grsdszsfs = '<bean:write name="ClfswjghdxxlrForm" property="grsdszsfs"/>';

		if (grsdszsfs != "" && grsdszsfs ==<%=Constants.GRSDSZSFS_ZS%>) {
			setCheckbox("grsdszsfs_1", true);
		}

		//ӡ��˰���շ�ʽ --����ӡ��˰ ����ӡ��˰
    	var yhszsfs = '<bean:write name="ClfswjghdxxlrForm" property="yhszsfs"/>';
    	if(yhszsfs!=""&&yhszsfs==<%=Constants.YHSZSFS_ZS%>){
    		//����ӡ��˰
			setCheckbox("yhs_0", true);
    	}else if(yhszsfs!=""&&yhszsfs==<%=Constants.YHSZSFS_FREE%>){
    		//����ӡ��˰
			setCheckbox("yhs_1", true);
    	}
		//������ֵ˰���շ�ʽ -- ������������ֵ˰    ����������ֵ˰��    ȫ������������ֵ˰�����ṩ������Ʊ����������ֵ˰���˶�����������ֵ˰���ṩ������������������ֵ˰
		var tdzsszsfs = '<bean:write name="ClfswjghdxxlrForm" property="tdzsszsfs"/>';
		if (tdzsszsfs != "" && tdzsszsfs ==<%=Constants.TDZZSZSFS_NOT%>) {
				//������������ֵ˰
				setCheckbox("tdzsszsfs_0", true);

		} else if (tdzsszsfs != "" && tdzsszsfs ==<%=Constants.TDZZSZSFS_FREE%>) {
				//����������ֵ˰
				setCheckbox("tdzsszsfs_1", true);

		}else if (tdzsszsfs != "" && tdzsszsfs ==<%=Constants.TDZZSZSFS_GFFPZS%>) {
				//�ṩ������Ʊ����������ֵ˰
				setCheckbox("tdzsszsfs_4", true);

		}else if(tdzsszsfs !=""&&tdzsszsfs ==<%=Constants.TDZZSZSFS_HDZS%>){
				//�˶�����������ֵ˰
				setCheckbox("tdzsszsfs_5",true);
		}else if(tdzsszsfs !=""&&tdzsszsfs ==<%=Constants.TDZZSZSFS_PGBGZS%>){
				//�ṩ������������������ֵ˰
				setCheckbox("tdzsszsfs_6",true);
		}

		//��˰����ȷ�Ϸ�ʽ�����
		var jssrqrfs = '<bean:write name="ClfswjghdxxlrForm" property="jssrqrfs"/>';
		var jsjeSubmit = '<bean:write name="ClfswjghdxxlrForm" property="jsje"/>';

		var blankHTML = "&nbsp;&nbsp;";
		var jeHTML = blankHTML + "��";

		var jssrqrfs_0_true = "<input type=\"checkbox\" value=\"0\" id=\"jssrqrfs_0\" onclick=\"return false\" checked=\"true\">��ͬ�۸�</input>"
				+ jeHTML;
		var jssrqrfs_0_false = "<input type=\"checkbox\" value=\"0\" id=\"jssrqrfs_0\" onclick=\"return false\">��ͬ�۸�</input>"
				+ jeHTML;

		var jssrqrfs_1_true = "<input type=\"checkbox\" value=\"1\" id=\"jssrqrfs_1\" onclick=\"return false\" checked=\"true\">�˶���˰�۸�</input>"
				+ jeHTML;
		var jssrqrfs_1_false = "<input type=\"checkbox\" value=\"1\" id=\"jssrqrfs_1\" onclick=\"return false\">�˶���˰�۸�</input>"
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
		//���ز��н��������
		var fdczjjgmc = '<bean:write name="ClfswjghdxxlrForm" property="fdczjjgmc"/>';
		putObjectValue("", "fdczjjgmcShow", fdczjjgmc);
		//��˰���������
		var fdczjjsjdm = '<bean:write name="ClfswjghdxxlrForm" property="fdczjjsjdm"/>';
		putObjectValue("", "fdczjjsjdmShow", fdczjjsjdm);
		//��˰˰��ǼǺ���
		var fdczjswdjzh = '<bean:write name="ClfswjghdxxlrForm" property="fdczjswdjzh"/>';
		putObjectValue("", "fdczjswdjzhShow", fdczjswdjzh);
		//��ϵ�绰
		var fdczjlxdh = '<bean:write name="ClfswjghdxxlrForm" property="fdczjlxdh"/>';
		putObjectValue("", "fdczjlxdhShow", fdczjlxdh);
		//----------------------------
		//���ز�����������
		var fdczjjjr = '<bean:write name="ClfswjghdxxlrForm" property="fdczjjjr"/>';
		putObjectValue("", "fdczjjjrShow", fdczjjjr);

		//��ϵ�绰
		var fdczjjjrlxdh = '<bean:write name="ClfswjghdxxlrForm" property="fdczjjjrlxdh"/>';
		putObjectValue("", "fdczjjjrlxdhShow", fdczjjjrlxdh);
		//--------------------
		//���ز����������֤����
		var fdczjjjrzjhm = '<bean:write name="ClfswjghdxxlrForm" property="fdczjjjrzjhm"/>';
		putObjectValue("", "fdczjjjrzjhmShow", fdczjjjrzjhm);
		//�������ʸ�֤�����
		var fdczjjjrzgzsh = '<bean:write name="ClfswjghdxxlrForm" property="fdczjjjrzgzsh"/>';
		putObjectValue("", "fdczjjjrzgzshShow", fdczjjjrzgzsh);
		*/
		//-------------------
		//ԭ������Ʊ���
		var ygffpje = '<bean:write name="ClfswjghdxxlrForm" property="ygffpje"/>';
		putObjectValue("", "ygffpjeShow_1", ygffpje);
		//ס�������۸�
		var zfpgjg = '<bean:write name="ClfswjghdxxlrForm" property="zfpgjg"/>';
		putObjectValue("", "zfpgjgShow", zfpgjg);

		//����:�ɷ��������������۸�
		var jfpgjg = '<bean:write name="ClfswjghdxxlrForm" property="jfpgjg"/>';
		putObjectValue("", "jfpgjgShow", jfpgjg);
		
		//�����걨��Ϣ
		setSbxx(tdzzssbfs, jssrqrfs);

		//����ʵ�ɽ��ϼ�
		var sjhjje = '<bean:write name="ClfswjghdxxlrForm" property="sjhjje"/>';
		putObjectValue("", "sjhjjeShow", sjhjje);

	}

	/*
	 *�����걨��Ϣ
	 *
	 *   tdzzssbfs ������ֵ˰�걨��ʽ
	 *   jssrqrfs  ��˰����ȷ�Ϸ�ʽ
	 */
	function setSbxx(tdzzssbfs, jssrqrfs) {

		//��������
		var blank_1 = "&nbsp;&nbsp;";
		var blank_2 = "&nbsp;&nbsp;";
		var jssr_ZH = "��˰����&nbsp;&nbsp;";
		var yysse_ZH = "Ӫҵ˰˰��";
		var tdzzs_FP_PGBG_ZH = "��ֵ�������˰��-�۳���Ŀ���ϼơ�����۳�ϵ��" + blank_2 + "=";
		var tdzzs_JB_ZH = "������ֵ˰Ӧ��˰��";
		var grsds_AS_ZH = "������ת������-˰��-������ã�";

		//��ø���DIV����
		var yys_show_OBJ = document.all.yys_show;//Ӫҵ˰˰��
		var cjs_show_OBJ = document.all.cjs_show;//�ǽ�˰˰��
		var jyffj_show_OBJ = document.all.jyffj_show;//�����Ѹ��ӽ��
		var dfjyfj_show_OBJ = document.all.dfjyfj_show;//�ط��������ӽ��
		var tdzzs_FP_PGBG_show_OBJ = document.all.tdzzs_FP_PGBG_show;//������ֵ˰(����Ʊ��������������)
		var tdzzs_HD_show_OBJ = document.all.tdzzs_HD_show;//������ֵ˰���˶����գ�
		//var tdzzs_JB_show_OBJ = document.all.tdzzs_JB_show;//������ֵ˰���������գ�
		//var grsds_HD_show_OBJ = document.all.grsds_HD_show;//��������˰���˶����գ�
		var grsds_AS_show_OBJ = document.all.grsds_AS_show;//��������˰����ʵ���գ�
		var yhs_show_OBJ = document.all.yhsse_show;//ӡ��˰

		var tdzzszsfs = '<bean:write name="ClfswjghdxxlrForm" property="tdzsszsfs" />';
		<logic:iterate id="sbitem" name="ClfswjghdxxlrForm"  property ="mfsbxxList" indexId="index">
		var szsmdm = '<bean:write name="sbitem" property="szsmdm"/>';//˰��˰Ŀ����
		var sl = '<bean:write name="sbitem" property="sl"/>';//˰��
		var jsje = '<bean:write name="sbitem" property="jsje"/>';//��˰���
		var sjje = '<bean:write name="sbitem" property="sjje"/>';//˰��˰��

		if (szsmdm != null && szsmdm != "") {
			var sbxxMsg = blank_1 + jsje + blank_2 + "��" + sl + "%" + blank_2
					+ "=" + blank_2 + sjje;
			switch (szsmdm) {
			case '02'://����Ӫҵ˰
				yys_show_OBJ.innerHTML = jssr_ZH + sbxxMsg;
				break;
			case '10'://�ǽ�˰
				cjs_show_OBJ.innerHTML = yysse_ZH + sbxxMsg;
				break;
			case '51'://�����Ѹ���
				jyffj_show_OBJ.innerHTML = yysse_ZH + sbxxMsg;
				break;
			case '54'://�ط������Ѹ���
				dfjyfj_show_OBJ.innerHTML = yysse_ZH + sbxxMsg;
				break;
			case '08'://������ֵ˰
				if (tdzzszsfs != null && tdzzszsfs != "") {
					//��1������Ʊ�������۸�
					if (tdzzszsfs ==<%=Constants.TDZZSZSFS_GFFPZS%>|| tdzzszsfs ==<%=Constants.TDZZSZSFS_PGBGZS%>) {
						//alert(tdzzs_FP_PGBG_ZH+","+blank_2+","+sjje)
						tdzzs_FP_PGBG_show_OBJ.innerHTML = tdzzs_FP_PGBG_ZH
								+ blank_2 + sjje;
					}

					//��2���˶�����
					if (tdzzszsfs ==<%=Constants.TDZZSZSFS_HDZS%>)//����Ʊ�������۸�
					{
						tdzzs_HD_show_OBJ.innerHTML = jssr_ZH + sbxxMsg;
					}
					//��3����������(����)								
				}
				break;
			case '05'://��������˰
				if (jssrqrfs != null && jssrqrfs != "") {
					//��1���˶�����
					//if (jssrqrfs ==<%=Constants.JSSRQRFS_HDJSJG%>) {
					//	grsds_HD_show_OBJ.innerHTML = jssr_ZH + sbxxMsg;
					//} else {
						//��2����ʵ����
						grsds_AS_show_OBJ.innerHTML = grsds_AS_ZH + sbxxMsg;
					//}
				}
				break;
			case '16'://ӡ��˰
				yhs_show_OBJ.innerHTML = "��ͬ�۸�"+sbxxMsg;
			default:
				break;
			}
		}
		</logic:iterate>

	}

	//ѡ�и�ѡѡ��
	function setCheckbox(checkBoxName, isChecked) {
		if (checkBoxName != null && checkBoxName != "" && isChecked != null
				&& isChecked != "") {
			document.getElementById(checkBoxName).checked = isChecked;

		}
	}

	//��ֵ����
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

		return "����";
	}
</script>
</HTML>
