<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.Constants"%>
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
					<h3>��&nbsp;��&nbsp;��&nbsp;��&nbsp;��&nbsp;��&nbsp;ס&nbsp;��&nbsp;��&nbsp;˰&nbsp;��&nbsp;��&nbsp;��&nbsp;��&nbsp;��&nbsp;<br>(��&nbsp;��&nbsp;��&nbsp;Ʊ&nbsp;��&nbsp;��&nbsp;��)</h3>
				          ��ţ���ˮ�ţ���<bean:write name="ClfswjghdxxlrForm" property="sbbh"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�������ڣ�<bean:write name="ClfswjghdxxlrForm" property="slrq"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��λ��Ԫ���Ƿ֣�
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
															<td class=2-td1-left align="left" width="5%" rowspan="19">��<br>˰<br>��<br>��<br>д</td>
															<td colspan="6" class="2-td1-center">
																<table border=0 cellSpacing=0 width="100%">
																	<tr>
																		<td class=2-td2-noleft align="left" width="15%" nowrap="nowrap"><div align="left">�����ˣ�����������</div></td>
																		<td class=2-td2-left width="35%" id="sell_mcShow">&nbsp;</td>
																		<td class=2-td2-left width="10%" nowrap="nowrap"><div align="left">��ϵ�绰</div></td>
																		<td class=2-td2-center style="border-right: 0px solid;" width="40%" id="sell_lxdhShow"  colspan="3">&nbsp;</td>
																    </tr>
																	<tr>
																		<td class=2-td2-noleft align="left" width="15%" nowrap="nowrap"><div align="left">�����ˣ��������Ϸ����֤������</div></td>
																		<td class=2-td2-left width="30%" id="sell_zjlxShow" >&nbsp;</td>
																		<td class=2-td2-left width="10%" nowrap="nowrap"><div align="left">֤������</div></td>
																		<td class=2-td2-center style="border-right: 0px solid;" width="30%" id="sell_zjhmShow"  colspan="3">&nbsp;</td>
																	</tr>
																	<tr>
																		<td class=2-td2-noleft align="left" width="15%" nowrap="nowrap"><div align="left">�����ˣ��򷽣�����</div></td>
																		<td class=2-td2-left width="35%" id="buy_mcShow">&nbsp;</td>
																		<td class=2-td2-left width="10%"><div align="left">��ϵ�绰</div></td>
																		<td class=2-td2-center style="border-right: 0px solid;" width="40%" id="buy_lxdhShow" colspan="3">&nbsp;</td>
																	</tr>
																	<tr>
																		<td class=2-td2-center-noBottom style="border-left: 0px solid;" align="left" width="15%" nowrap="nowrap"><div align="left">�����ˣ��򷽣��Ϸ����֤������</div></td>
																		<td class=2-td2-center-noBottom width="30%" id="buy_zjlxShow">&nbsp;</td>
																		<td class=2-td2-center-noBottom width="10%"><div align="left">֤������</div></td>
																		<td class=2-td2-center-noBottom style="border-right: 0px solid;" width="40%" id="buy_zjhmShow" colspan="3">&nbsp;</td>
																	</tr>
																</table>
															</td>
														</tr>
															
															
															<tr>
																<td class=2-td2-left align="left" width="36%" nowrap="nowrap"><div align="left">��������סַ</div></td>
																<td class=2-td2-center width="30%" colspan="5" id="sqrxzdzShow" nowrap="nowrap">&nbsp;</td>
															</tr>												
															<tr>
																<td class=2-td2-left align="left" width="15%" nowrap="nowrap"><div align="left">������ϸ��ַ</div></td>
																<td class=2-td2-left width="60%" id="fwzldzShow" nowrap="nowrap">&nbsp;</td>
																<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">�Ƿ�Ϊ��ͥΨһ�����÷�</div></td>
																<td class=2-td2-center width="10%" nowrap="nowrap" colspan="3">
																<div align="left"><!-- �� �� &nbsp;&nbsp;&nbsp;&nbsp;�� �� -->  
																	<input type="checkbox" value="0" id="sfwyzf_Y" onclick="return false;">��</input> &nbsp;&nbsp;
																	<input type="checkbox" value="1" id="sfwyzf_N" onclick="return false;">��</input> 
																</div></td>													
															</tr>
															<tr>
																<td class=2-td2-left align="left" width="15%" nowrap="nowrap"><div align="left">���ݲ�Ȩ֤��</div></td>
																<td class=2-td2-left width="30%" id="fwcqzhShow" nowrap="nowrap">&nbsp;</td>
																<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">���ݲ�Ȩ֤ס������</div></td>
																<td class=2-td2-left width="30%" id="fwcqzbzzflxmcShow" nowrap="nowrap">
			<%-- 														<div align="center">
																		<bean:define id="dta" name="ClfswjghdxxlrForm" property="fwcqzbzzflxList"/>
			                  												<html:select property="fwcqzbzzflxdm" style='width:90%'>
			                    												<html:options collection="dta" labelProperty="fwcqzbzzflxmc" property="fwcqzbzzflxdm"/>
			                  												</html:select>
			                  										</div> --%>&nbsp;</td>
																<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">���뿪Ʊ�����ν��׽�</div></td>
																<td class=2-td2-center width="30%" id="sqkpjeShow">&nbsp;</td>													
															</tr>
															
															<tr>
														        <td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">��������</div></td>
																<td class=2-td2-center width="30%" id="fwlxShow" colspan="5">
																<div align="left">
																	<!-- ¥����&nbsp;&nbsp;ƽ����&nbsp;&nbsp;�����ҡ�&nbsp;&nbsp; -->
																	<input type="checkbox" value="0" id="fwlx_0" onclick="return false;">¥��</input>&nbsp;&nbsp;
																	<input type="checkbox" value="1" id="fwlx_1" onclick="return false;">ƽ��</input>&nbsp;&nbsp;
																	<input type="checkbox" value="2" id="fwlx_2" onclick="return false;">������</input>
																</div></td>
															</tr>
															<tr>
																<td class=2-td2-left align="left" width="15%" nowrap="nowrap"><div align="left">�������</div></td>
																<td class=2-td2-left width="30%" id="jcndShow" nowrap="nowrap">&nbsp;</td>
																<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">��¥��</div></td>
																<td class=2-td2-left width="30%" id="zlcShow">&nbsp;</td>
																<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">����¥��</div></td>
																<td class=2-td2-center width="30%" id="szlcShow">&nbsp;</td>													
															</tr>
															<tr>
																<td class=2-td2-left align="left" width="15%"nowrap="nowrap"><div align="left">���ݽ������</div></td>
																<td class=2-td2-left width="30%" id="fwjzmjShow">&nbsp;</td>
																<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">ԭ������Ʊ���</div></td>
																<td class=2-td2-center width="30%" id="ygffpjeShow" colspan="3">&nbsp;</td>
															</tr>
															<tr>
																<td class=2-td2-left align="left" width="15%"nowrap="nowrap"><div align="left">����֤������</div></td>
																<td class=2-td2-left width="30%" id="gfzmrqShow">&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��</td>
																<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">����˫����ͬǩ������</div></td>
																<td class=2-td2-center width="30%" id="htwsqyrqShow" colspan="3">&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��</td>
															</tr>
															<tr>
														        <td class=2-td2-center width="15%" nowrap="nowrap" colspan="6"><div align="center">������ֵ˰�걨��ʽ</div></td>
															</tr>	
															<tr>
														        <td class=2-td2-center width="15%" nowrap="nowrap" colspan="6">
															        <div align="center">
															        	<!-- �ṩ������Ʊ��&nbsp;&nbsp;�ṩ���������&nbsp;&nbsp;�˶����ա�&nbsp;&nbsp; -->
															        	<input type="checkbox" value="0" id="tdzzssbfs_0" onclick="return false;">�ṩ������Ʊ</input>&nbsp;&nbsp;
															        	<input type="checkbox" value="1" id="tdzzssbfs_1" onclick="return false;">�ṩ��������</input>&nbsp;&nbsp;
															        	<input type="checkbox" value="2" id="tdzzssbfs_2" onclick="return false;">�˶�����</input>
															        	
																	</div></td>
															</tr>														
															<tr>
																<td class=2-td2-left align="left" width="15%" nowrap="nowrap" colspan="2"><div align="center">�ṩ������Ʊ��д����</div></td>
																<td class=2-td2-center align="left" width="15%" nowrap="nowrap" colspan="5"><div align="center">�ṩ����������д������</div></td>
															</tr>												
															<tr>
														        <td class=2-td2-left width="15%" nowrap="nowrap" rowspan="4"><div align="left">ȡ�÷��ز�ʱ�����ɵ���˰���</div></td>
														        <td class=2-td2-left width="15%" nowrap="nowrap" rowspan="4" id="qdfcqsjeShow">&nbsp;</td>
															</tr>
															<tr>
																<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">ȡ������ʹ��Ȩ��֧���Ľ��</div></td>
																<td class=2-td2-center width="30%"  colspan="3" id="qdtdsyqzfjeShow">&nbsp;</td>													
															</tr>
															<tr>
																<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">�ɷ���������������۸�</div></td>
																<td class=2-td2-center width="30%"  colspan="3" id="jfpgjgShow">&nbsp;</td>													
															</tr>
															<tr>
																<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">�۸���������</div></td>
																<td class=2-td2-center width="30%" colspan="3" id="jgpgfyShow">&nbsp;</td>													
															</tr>																								
															<tr>
																<td class=2-td2-left align="left" width="15%"nowrap="nowrap"><div align="left">���ز��н��������</div></td>
																<td class=2-td2-left width="30%" id="fdczjjgmcShow">&nbsp;</td>
																<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">��˰���������</div></td>
																<td class=2-td2-center width="30%" id="fdczjjsjdmShow" colspan="3">&nbsp;</td>
															</tr>
															<tr>
																<td class=2-td2-left align="left" width="15%"nowrap="nowrap"><div align="left">��˰˰��ǼǺ���</div></td>
																<td class=2-td2-left width="30%" id="fdczjswdjzhShow">&nbsp;</td>
																<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">��ϵ�绰</div></td>
																<td class=2-td2-center width="30%" id="fdczjlxdhShow" colspan="3">&nbsp;</td>
															</tr>
															<tr>
																<td class=2-td2-left align="left" width="15%"nowrap="nowrap"><div align="left">���ز�����������</div></td>
																<td class=2-td2-left width="30%" id="fdczjjjrShow">&nbsp;</td>
																<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">��ϵ�绰</div></td>
																<td class=2-td2-center width="30%" id="fdczjjjrlxdhShow" colspan="3">&nbsp;</td>
															</tr>
															<tr>
																<td class=2-td2-left align="left" width="15%"nowrap="nowrap"><div align="left">���ز����������֤����</div></td>
																<td class=2-td2-left width="30%" id="fdczjjjrzjhmShow">&nbsp;</td>
																<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">�������ʸ�֤�����</div></td>
																<td class=2-td2-center width="30%" id="fdczjjjrzgzshShow" colspan="3">&nbsp;</td>
															</tr>
														
													</table>
												</td>
											</tr>
											
												
												<tr>
													<td colspan="6">
														<table cellSpacing=0 width="100%">
															<tr >
																<td class=2-td1-left rowspan="8" width="1%">˰<br>��<br>��<br>��<br>ȷ<br>��</td>
																<td class=2-td2-left style="border-top: 1px solid  #9BB4CA;" width="18%" nowrap="nowrap"><div align="left">��Ȩ֤��ע�������</div></td>
																<td class=2-td2-center  style="border-top: 1px solid  #9BB4CA;" width="80%" colspan="5">
																<div align="left">
																	<!-- 140ƽ�ף��������¡�&nbsp;&nbsp;140ƽ�����ϡ�&nbsp;&nbsp; -->
																	<input type="checkbox" value="0" id="cqzbzjzmjfl_0" onclick="return false;">140ƽ�ף���������</input>&nbsp;&nbsp;
																	<input type="checkbox" value="1" id="cqzbzjzmjfl_1" onclick="return false;">140ƽ������</input>
																</div></td>
															</tr>
															<tr>
																<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">ÿƽ�׽��׵���</div></td>
																<td class=2-td2-left width="30%" id="mpmjydjShow">&nbsp;</td>	
																<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">�������ڵ����ؼ���</div></td>
																<td class=2-td2-left width="30%" id="tdjcmcShow">&nbsp;</td>																								
																<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">��ͨס������޼�</div></td>
																<td class=2-td2-center width="30%" id="ptzfzgxjShow">&nbsp;</td>
															</tr>
															<tr>
																<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">�����ݻ���</div></td>
																<td class=2-td2-center width="15%" colspan="6">
																<div align="left">
																<!-- 1.0���¡�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1.0���������ϡ� -->
																
																	<input type="checkbox" value="0" id="fwrjl_0" onclick="return false;">1.0����</input>&nbsp;&nbsp;
																	<input type="checkbox" value="1" id="fwrjl_1" onclick="return false;">1.0����������</input>													
																	</div></td>
															</tr>
															<tr>
																<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">���ֱ�׼</div></td>
																<td class=2-td2-center width="15%" colspan="6"><div align="left">
																<!-- ��ͨסլ��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����ͨסլ�� -->
																	<input type="checkbox" value="0" id="hfbz_0" onclick="return false;">��ͨסլ</input>&nbsp;&nbsp;
																	<input type="checkbox" value="1" id="hfbz_1" onclick="return false;">����ͨסլ</input>	
																</div></td>
															</tr>
															<tr>
																<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">ס��ʹ��ʱ��</div></td>
																<td class=2-td2-center width="15%" colspan="6">
																<div align="left">
																<!-- 5�꣨�������ϡ�&nbsp;&nbsp;&nbsp;&nbsp;5������3�����ϡ�&nbsp;&nbsp;&nbsp;&nbsp;3�꣨�������� �� -->
																
																	<input type="checkbox" value="0" id="zfsjsjfl_0" onclick="return false;">5�꣨��������</input>&nbsp;&nbsp;
																	<input type="checkbox" value="1" id="zfsjsjfl_1" onclick="return false;">5������3������</input>&nbsp;&nbsp;
																	<input type="checkbox" value="2" id="zfsjsjfl_2" onclick="return false;">3�꣨��������</input>													
																</div></td>
															</tr>
															<tr>
																<td class=2-td2-center width="15%" colspan="6"><div align="center">
			<!-- 													����Ӫҵ˰��
																	&nbsp;&nbsp;&nbsp;&nbsp;ȫ������Ӫҵ˰��
																	&nbsp;&nbsp;&nbsp;&nbsp;�������Ӫҵ˰��
																	&nbsp;&nbsp;&nbsp;&nbsp;������������˰�� <br>���ո�������˰��&nbsp;&nbsp;&nbsp;&nbsp;����������ֵ˰��&nbsp;&nbsp;&nbsp;&nbsp;
																	ȫ������������ֵ˰��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
																	<input type="checkbox" value="0" id="yyszsfs_0" onclick="return false;">����Ӫҵ˰</input>
																	<input type="checkbox" value="1" id="yyszsfs_1" onclick="return false;">ȫ������Ӫҵ˰</input>
																	<input type="checkbox" value="2" id="yyszsfs_2" onclick="return false;">�������Ӫҵ˰</input>	
																	
																	<input type="checkbox" value="0" id="grsdszsfs_0" onclick="return false;">������������˰</input>
																	<input type="checkbox" value="1" id="grsdszsfs_1" onclick="return false;">���ո�������˰</input>
																	
																	<input type="checkbox" value="0" id="tdzsszsfs_0" onclick="return false;">������������ֵ˰</input>
																	<input type="checkbox" value="1" id="tdzsszsfs_1" onclick="return false;">��������������ֵ˰</input>
																	<input type="checkbox" value="2" id="tdzsszsfs_2" onclick="return false;">ȫ������������ֵ˰</input>	
																	
																	</div></td>
															</tr>
															<tr>
																<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">��˰����ȷ�Ϸ�ʽ�����</div></td>
																<td class=2-td2-center width="15%" colspan="5" id="jssrqrfsShow">
																<div align="left">
			<!-- 													��ͬ�۸��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�˶���˰�۸��
																	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
																	<input type="checkbox" value="0" id="jssrqrfs_0" onclick="return false;">��ͬ�۸�</input>
																	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																	<input type="checkbox" value="1" id="jssrqrfs_1" onclick="return false;">�˶���˰�۸�</input>
																	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
																	</div></td>
															</tr>	
															
															<tr>
																<td colspan="6" class=2-td2-center>
																	<table border=0 cellSpacing=0>
																		<tr>
																			<td class=2-td2-center style="border-left: 0px solid; border-right: 0px solid;" width="15%" colspan="8">�۳����ȷ��</td>
																		</tr>
																		<tr>
																			<td class=2-td2-left style="border-left: 0px solid;" align="left" width="15%" nowrap="nowrap"><div align="left">ԭ������Ʊ�۸�</div></td>
																			<td class=2-td2-left width="20%" id="ygffpjeShow_1">&nbsp;</td>
																			<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">ס�������۸�</div></td>
																			<td class=2-td2-left width="20%" id="zfpgjgShow">&nbsp;</td>
																			<td class=2-td2-left width="10%"><div align="left">���У�<br>�ɷ��������������۸�</div></td>
																			<td class=2-td2-center style="border-right: 0px solid;" width="20%" colspan="3" id="jfpgjgShow1">&nbsp;</td>													
																		</tr>
																		<tr>
																			<td class=2-td2-left style="border-left: 0px solid;" width="15%" nowrap="nowrap"><div align="left">Ӫҵ˰˰��</div></td>
																			<td class=2-td2-left width="15%" colspan="3"><div align="left" id="yys_show">��˰����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��5%&nbsp;&nbsp;&nbsp;=</div>
																			</td>
																			<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">��˰ƾ֤��</div></td>
																			<td class=2-td2-center width="30%"colspan="3" style="border-right: 0px solid;">&nbsp;</td>														
																		</tr>
																		<tr>
																			<td class=2-td2-left style="border-left: 0px solid;" width="15%" nowrap="nowrap"><div align="left">�ǽ�˰˰��</div></td>
																			<td class=2-td2-left width="15%" colspan="3"><div align="left" id="cjs_show">Ӫҵ˰˰��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��5%&nbsp;&nbsp;&nbsp;=</div>
																			</td>
																			<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">��˰ƾ֤��</div></td>
																			<td class=2-td2-center width="30%" colspan="3" style="border-right: 0px solid;">&nbsp;</td>	
																		</tr>
																		<tr>
																			<td class=2-td2-left style="border-left: 0px solid;" width="15%" nowrap="nowrap"><div align="left">�����Ѹ��ӽ��</div></td>
																			<td class=2-td2-left width="15%" colspan="3"><div align="left" id="jyffj_show">Ӫҵ˰˰��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��3%&nbsp;&nbsp;&nbsp;=</div>
																			</td>
																			<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">��˰ƾ֤��</div></td>
																			<td class=2-td2-center width="30%" colspan="3" style="border-right: 0px solid;">&nbsp;</td>														
																		</tr>
						 												<tr>
																			<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">�ط��������ӽ��</div></td>
																			<td class=2-td2-center width="15%" colspan="3"><div align="left" id="dfjyfj_show">Ӫҵ˰˰��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��2%&nbsp;&nbsp;&nbsp;=</div>
																			<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">��˰ƾ֤��</div></td>
																			<td class=2-td2-center width="30%" colspan="3" style="border-right: 0px solid;">&nbsp;</td>																				
																			</td>
																		</tr>
																		<!--<tr>
																			<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">Ӫ�ǽ̺ϼ�</div></td>
																			<td class=2-td2-center width="15%" colspan="3">&nbsp;</td>
																		</tr> -->
																		<tr>
																			<td class=2-td2-left style="border-left: 0px solid;" width="15%" nowrap="nowrap"><div align="left">������ֵ˰(����Ʊ��������������)</div></td>
																			<td class=2-td2-left width="15%" colspan="3"><div align="left" id="tdzzs_FP_PGBG_show">��ֵ�������˰��-�۳���Ŀ���ϼơ�����۳�ϵ��=<br>&nbsp;</div></td>
																			<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">��˰ƾ֤��</div></td>
																			<td class=2-td2-center width="30%" colspan="3" style="border-right: 0px solid;">&nbsp;</td>	
																		</tr>
																		<tr>
																			<td class=2-td2-left style="border-left: 0px solid;" width="15%" nowrap="nowrap"><div align="left">������ֵ˰���˶����գ�</div></td>
																			<td class=2-td2-left width="15%" colspan="3"><div align="left" id="tdzzs_HD_show">��˰����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��1%&nbsp;&nbsp;&nbsp;=</div>
																			</td>
																			<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">��˰ƾ֤��</div></td>
																			<td class=2-td2-center width="30%" colspan="3" style="border-right: 0px solid;">&nbsp;</td>
																		</tr>
																		<tr>
																			<td class=2-td2-left style="border-left: 0px solid;" width="15%" nowrap="nowrap"><div align="left">������ֵ˰���������գ�</div></td>
																			<td class=2-td2-left width="15%" colspan="3"><div align="left" id="tdzzs_JB_show">������ֵ˰Ӧ��˰��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ��2&nbsp;&nbsp;&nbsp;=</div>
																			</td>
																			<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">��˰ƾ֤��</div></td>
																			<td class=2-td2-center width="30%" colspan="3" style="border-right: 0px solid;">&nbsp;</td>
																		</tr>												
																		<tr>
																			<td class=2-td2-left style="border-left: 0px solid;" width="15%" nowrap="nowrap"><div align="left">��������˰���˶����գ�</div></td>
																			<td class=2-td2-left width="15%" colspan="3"><div align="left" id="grsds_HD_show">��˰����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��1%&nbsp;&nbsp;&nbsp;=</div>
																			</td>
																			<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">��˰ƾ֤��</div></td>
																			<td class=2-td2-center width="30%" colspan="3" style="border-right: 0px solid;">&nbsp;</td>
																		</tr>
																		<tr>
																			<td class=2-td2-left style="border-left: 0px solid;" width="15%" nowrap="nowrap"><div align="left">��������˰����ʵ���գ�</div></td>
																			<td class=2-td2-left width="15%" colspan="3"><div align="left" id="grsds_AS_show">������ת������-����ԭֵ-˰��-������ã�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��20%&nbsp;&nbsp;&nbsp;=</div>
																			</td>
																			<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">��˰ƾ֤��</div></td>
																			<td class=2-td2-center width="30%" colspan="3" style="border-right: 0px solid;">&nbsp;</td>
																		</tr>
			<!-- 															<tr>
																			<td class=2-td2-left width="15%" nowrap="nowrap">&nbsp;</td>
																			<td class=2-td2-left width="15%" colspan="3">&nbsp;</td>
																			<td class=2-td2-left width="15%" >&nbsp;</td>
																			<td class=2-td2-center width="15%" colspan="3">&nbsp;</td>
																		</tr> -->												
						<!-- 												<tr>
																			<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">ӡ��˰</div></td>
																			<td class=2-td2-center width="15%" colspan="3">&nbsp;</td>
																		</tr>
																		<tr>
																			<td class=2-td2-left width="15%" nowrap="nowrap"><div align="left">��˰</div></td>
																			<td class=2-td2-center width="15%" colspan="3">&nbsp;</td>
																		</tr> -->
																		<tr>
																			<td class=2-td2-left style="border-left: 0px solid;" width="15%" nowrap="nowrap"><div align="left">����˰�Ѻϼ�</div></td>
																			<td class=2-td2-center width="15%" colspan="7" style="border-right: 0px solid;"><div align="left" id="sjhjjeShow">&nbsp;</div></td>
																		</tr>
																		<tr>
																			<td  colspan="8" width="100%">
																				<table cellSpacing=0 width="100%">
																					<tr>
																						<td class=2-td2-center-noBottom style="border-left: 0px solid;" nowrap="nowrap"><div align="left">��Ʊ����</div></td>
																						<td class=2-td2-center-noBottom width="25%">&nbsp;</td>
																						<td class=2-td2-center-noBottom  nowrap="nowrap"><div align="left">��Ʊ����</div></td>
																						<td class=2-td2-center-noBottom width="25%" >&nbsp;</td>
																						<td class=2-td2-center-noBottom  nowrap="nowrap"><div align="left">��Ϣ��</div></td>
																						<td class=2-td2-center-noBottom width="25%" >&nbsp;</td>																																						
																						<td class=2-td2-center-noBottom  nowrap="nowrap"><div align="left">���߽��</div></td>
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
																<td class=2-td1-left width="1%" rowspan="2">��<br>��</td>
																<td class=2-td2-center width="15%" colspan="6" style="border-top: 1px solid #9BB4CA;"><div align="left"><h5>�����ṩ����Ϣ��ʵ��׼ȷ��Ը�е���Ӧ�ķ������Ρ�</h5>
																<h5>��˰��ǩ�£� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��
																	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																	������ǩ�£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;���������֤���룺&nbsp;&nbsp;&nbsp; 
																</h5></div></td>
															</tr>
															<tr>
																<td class=2-td2-left align="left" width="15%" nowrap="nowrap"><div align="left">������Աǩ��</div></td>
																<td class=2-td2-left width="30%">&nbsp;</td>
																<td class=2-td2-left width="15%" nowrap="nowrap" ><div align="left">������Աǩ��</div></td>
																<td class=2-td2-center width="30%" colspan="3">&nbsp;</td>
															</tr>
															<tr>
																<td colspan="7" class=2-td1-left style="border-right: 1px solid #9BB4CA; border-top: 0px solid;"><div align="right">����λ�£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div></td>
															</tr>
															<tr>
																<td colspan="7">
																	<table width="99%" border="0" cellpadding="0" cellspacing="0">
																		<tr>
																			<td>
																			<hr width="100%" size="1">
																			</td>
																			<td  align="center" class="black9"><strong><font
																				color="#0000FF">���˵��</font></strong></td>
																			<td>
																			<hr width="100%" size="1">
																			</td>
																		</tr>
																	</table>
																	<table   border="1" align="center" cellpadding="1"
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
		window.opener.document.forms[0].target = "_self";
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


	
	
var HKEY_Root,HKEY_Path,HKEY_Key;    
HKEY_Root="HKEY_CURRENT_USER";    
HKEY_Path="\\Software\\Microsoft\\Internet Explorer\\PageSetup\\";    
    //������ҳ��ӡ��ҳüҳ��Ϊ��    
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

 //�ָ���ҳ��ӡ��ҳüҳ��   

 
  function PageSetup_default()   
 {    
   try {    
           var Wsh=new ActiveXObject("WScript.Shell");    
   HKEY_Key="header";    
   Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"&w&bҳ��,&p/&P");    
   HKEY_Key="footer";    
   Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"&u&b&d");    
   }  catch(e){}    
 }  

 function printsetup(){ 
		//��ӡҳ������ 
		wb.execwb(8,1); 
	} 
	
 function printpreview(){ 
		//��ӡҳ��Ԥ�� 
		PageSetup_Null(); 
		wb.execwb(7,1); 
	} 

 function printit() 
 { 
  if (confirm('ȷ����ӡ��')) { 
      PageSetup_Null(); 
      wb.execwb(6,6);
  } 
 } 
</script>

<script type="text/javascript">
//����ʽ
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
	//�˺����Ǳ������֮��������ʾ�ѱ�����Ϣ�ĺ���
	function showSaveData() {
		//��������
		
/* 		var res_slrq = '<bean:write name="ClfswjghdxxlrForm" property="slrq"/>';
		putObjectValue("", 'slrqShow', transTime("��ͬ����ǩԼ����", slrq)); */
		//��ͬ����ǩԼ����
		var res_htwsqyrq = '<bean:write name="ClfswjghdxxlrForm" property="htwsqyrq"/>';
		putObjectValue("", 'htwsqyrqShow', transTime("��ͬ����ǩԼ����", res_htwsqyrq,"yyyy��mm��dd��"));
		//���׷��ݵ�ַ
		var res_fwzldz = '<bean:write name="ClfswjghdxxlrForm" property="fwzldz"/>';
		putObjectValue("", "fwzldzShow", res_fwzldz);
		//����Ȩ��ת������
		var res_fwqszylxmc = "";
		putObjectValue("", "fwqszylxmcShow",
				res_fwqszylxmc);
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
    	var sellerInfo = '<bean:write name="ClfswjghdxxlrForm" property="all_sellerInfo"/>';
    	setBuyorSellInfo(sellerInfo,"sell");
    	//alert(sellerInfo);
    	
    	//����Ϣ
    	var buyyerInfo = '<bean:write name="ClfswjghdxxlrForm" property="all_buyerInfo"/>';
    	setBuyorSellInfo(buyyerInfo,"buy");
    	//alert(buyyerInfo);
    	
    	//==============================�˶���Ϣ====================================
    		
    	//��������סַ
    	var sqrxzdz = '<bean:write name="ClfswjghdxxlrForm" property="sqrxzdz"/>';	
    	putObjectValue("", "sqrxzdzShow", sqrxzdz);
    	
    	//�Ƿ�Ϊ��ͥΨһ�����÷�
    	var jtwyshyhbz = '<bean:write name="ClfswjghdxxlrForm" property="jtwyshyhbz"/>';	
    	if(jtwyshyhbz != "" && jtwyshyhbz ==<%=Constants.ONLY_ROOM_YES%>){
    		setCheckbox("sfwyzf_Y",true);
    	}else if (jtwyshyhbz != "" && jtwyshyhbz ==<%=Constants.ONLY_ROOM_NOT%>){
    		setCheckbox("sfwyzf_N",true);
    	}
    	
    	//���ݲ�Ȩ֤ס������
    	var fwcqzbzzflxmc = '<bean:write name="ClfswjghdxxlrForm" property="fwcqzbzzflxmc"/>';
    	putObjectValue("", "fwcqzbzzflxmcShow", fwcqzbzzflxmc);
    	
    	
    	
    	
    	//���뿪Ʊ�����ν��׽�
    	
    	var htzj = '<bean:write name="ClfswjghdxxlrForm" property="htzj"/>';
    	putObjectValue("", "sqkpjeShow", changeJE(htzj));
    	
    	//��������
    	var fwlxdm = '<bean:write name="ClfswjghdxxlrForm" property="fwlxdm"/>';
    	if(fwlxdm != "" &&(fwlxdm == <%=Constants.FWLX_BUILDINGS%>)){
    		setCheckbox("fwlx_0",true);
    	}else if (fwlxdm != "" &&(fwlxdm == <%=Constants.FWLX_BUNGALOW%>)){
    		setCheckbox("fwlx_1",true);
    	}else if (fwlxdm != "" &&(fwlxdm == <%=Constants.FWLX_BASEMENT%>)){
    		setCheckbox("fwlx_2",true);
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
    	putObjectValue("", 'gfzmrqShow', transTime("����֤������", gfzmrq,"yyyy��mm��dd��"));
    	
    	//������ֵ˰�걨��ʽ
    	var tdzzssbfs = '<bean:write name="ClfswjghdxxlrForm" property="tdzzssbfs"/>';
    	if(tdzzssbfs != "" && tdzzssbfs == <%=Constants.TDZSS_SB_GFFP%>){
    		setCheckbox("tdzzssbfs_0",true);
    	}else if (tdzzssbfs != "" && tdzzssbfs == <%=Constants.TDZSS_SB_PGBG%>){
    		setCheckbox("tdzzssbfs_1",true);	
    	}else if (tdzzssbfs != "" && tdzzssbfs == <%=Constants.TDZSS_SB_HDZS%>){
    		setCheckbox("tdzzssbfs_2",true);	
    	}
    	
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
    	var cqzbzjzmjfl = '<bean:write name="ClfswjghdxxlrForm" property="cqzbzjzmjfl"/>';
    	if(cqzbzjzmjfl != "" && cqzbzjzmjfl == <%=Constants.CQZBZ_JLMX_LOW%>){
    		setCheckbox("cqzbzjzmjfl_0",true);
    		
    	}else if (cqzbzjzmjfl != "" && cqzbzjzmjfl == <%=Constants.CQZBZ_JLMX_HIGH%>){
    		setCheckbox("cqzbzjzmjfl_1",true);
    		
    	}
    	
    	
    	//ÿƽ�׽��׵���
    	var mpmjydj = '<bean:write name="ClfswjghdxxlrForm" property="mpmjydj"/>';
    	putObjectValue("", "mpmjydjShow", mpmjydj);
    	
    	//�������ڵ����ؼ���
    	var tdjcmc = '<bean:write name="ClfswjghdxxlrForm" property="tdjcmc"/>';
    	putObjectValue("", "tdjcmcShow", tdjcmc);
    	
    	//��ͨס������޼�
    	var ptzfzgxj = '<bean:write name="ClfswjghdxxlrForm" property="ptzfzgxj"/>';
    	putObjectValue("", "ptzfzgxjShow", ptzfzgxj);
    	
    	//�����ݻ���
    	var fwrjl = '<bean:write name="ClfswjghdxxlrForm" property="fwrjl"/>';
    	if(fwrjl != "" && fwrjl == <%=Constants.FWRJL_LOW%>){
    		setCheckbox("fwrjl_0",true);
    	}else if  (fwrjl != "" &&  fwrjl == <%=Constants.FWRJL_HIGH%>){
    		setCheckbox("fwrjl_1",true);
    	}
    	
    	//���ֱ�׼
    	var hfbz = '<bean:write name="ClfswjghdxxlrForm" property="hfbz"/>';
    	if(hfbz != "" && hfbz == <%=Constants.HFBZ_PT%>){
    		setCheckbox("hfbz_0",true);
    	}else if (hfbz != "" && hfbz == <%=Constants.HFBZ_NOTPT%>){
    		setCheckbox("hfbz_1",true);
    	}
    	
    	//ס��ʹ��ʱ��
    	var zfsjsjfl = '<bean:write name="ClfswjghdxxlrForm" property="zfsjsjfl"/>';
    	if(zfsjsjfl != "" && zfsjsjfl == <%=Constants.ZFSYSJ_FIVE%>){
    		setCheckbox("zfsjsjfl_0",true);
    	}else if (zfsjsjfl != "" && zfsjsjfl == <%=Constants.ZFSYSJ_THREETOFOIVE%>){
    		setCheckbox("zfsjsjfl_1",true);
    	}else if (zfsjsjfl != "" && zfsjsjfl == <%=Constants.ZFSYSJ_THREE%>){
    		setCheckbox("zfsjsjfl_2",true);
    	}
    	
    	
    	//Ӫҵ˰���շ�ʽ -- ����Ӫҵ˰��     ȫ������Ӫҵ˰��     �������Ӫҵ˰��    
    	var yyszsfs = '<bean:write name="ClfswjghdxxlrForm" property="yyszsfs"/>';
    	
    	if(yyszsfs != "" && yyszsfs == <%=Constants.YSSZSFS_NOT%>){
    		setCheckbox("yyszsfs_0",true);
    	}else if (yyszsfs != "" && yyszsfs == <%=Constants.YSSZSFS_ALL%>){
    		setCheckbox("yyszsfs_1",true);
    	}else if (yyszsfs != "" && yyszsfs == <%=Constants.YSSZSFS_MINUS%>){
    		setCheckbox("yyszsfs_2",true);
    	}
    	
    	
    	
    	//��������˰���շ�ʽ -- ������������˰�� ���ո�������˰��   
    	var grsdszsfs = '<bean:write name="ClfswjghdxxlrForm" property="grsdszsfs"/>';
    	
    	if(grsdszsfs != "" && grsdszsfs == <%=Constants.GRSDSZSFS_FREE%>){
    		setCheckbox("grsdszsfs_0",true);
    	}else if (grsdszsfs != "" && grsdszsfs == <%=Constants.GRSDSZSFS_ZS%>){
    		setCheckbox("grsdszsfs_1",true);
    	}
    	
    	
    	//������ֵ˰���շ�ʽ -- ������������ֵ˰    ����������ֵ˰��    ȫ������������ֵ˰��
    	var tdzsszsfs = '<bean:write name="ClfswjghdxxlrForm" property="tdzsszsfs"/>';
    	if(tdzsszsfs != "" && tdzsszsfs == <%=Constants.TDZZSZSFS_NOT%>){//������������ֵ˰
    		setCheckbox("tdzsszsfs_0",true);
    		
    	}else if (tdzsszsfs != "" && tdzsszsfs == <%=Constants.TDZZSZSFS_ZS%>){//ȫ������������ֵ˰
    		setCheckbox("tdzsszsfs_2",true);
    		
    	}
    	
    	
    	//��˰����ȷ�Ϸ�ʽ�����
    	var jssrqrfs = '<bean:write name="ClfswjghdxxlrForm" property="jssrqrfs"/>';
    	var jsjeSubmit = '<bean:write name="ClfswjghdxxlrForm" property="jsje"/>';
    	
    	
    	var blankHTML="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
    	var jeHTML=blankHTML+"��";
    	
    	var jssrqrfs_0_true = "<input type=\"checkbox\" value=\"0\" id=\"jssrqrfs_0\" onclick=\"return false\" checked=\"true\">��ͬ�۸�</input>"+jeHTML;
    	var jssrqrfs_0_false = "<input type=\"checkbox\" value=\"0\" id=\"jssrqrfs_0\" onclick=\"return false\">��ͬ�۸�</input>"+jeHTML;
    	
    	var jssrqrfs_1_true = "<input type=\"checkbox\" value=\"1\" id=\"jssrqrfs_1\" onclick=\"return false\" checked=\"true\">�˶���˰�۸�</input>"+jeHTML;
        var jssrqrfs_1_false = "<input type=\"checkbox\" value=\"1\" id=\"jssrqrfs_1\" onclick=\"return false\">�˶���˰�۸�</input>"+jeHTML;
        
        
    	
    	if (jssrqrfs != "" && jssrqrfs == <%=Constants.JSSRQRFS_HTJG%>){
    		putObjectValue("", "jssrqrfsShow", jssrqrfs_0_true+""+jsjeSubmit+""+blankHTML+""+jssrqrfs_1_false+""+blankHTML);
    		//setCheckbox("jssrqrfs_0",true);
    	}else if (jssrqrfs != "" && jssrqrfs == <%=Constants.JSSRQRFS_HDJSJG%>){
    		//putObjectValue("", "jssrqrfsShow", jssrqrfs_1_true+""+jsjeSubmit+""+blankHTML+""+jssrqrfs_0_false+""+blankHTML);
    		putObjectValue("", "jssrqrfsShow", jssrqrfs_0_false+""+blankHTML+""+jssrqrfs_1_true+""+jsjeSubmit+""+blankHTML+"");
    		//setCheckbox("jssrqrfs_1",true);
    	}
		
		
		
    	
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
 setSbxx(tdzzssbfs,jssrqrfs);
 
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
	function setSbxx(tdzzssbfs,jssrqrfs){
		
		//��������
		var blank_1 = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		var blank_2 = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		var jssr_ZH = "��˰����&nbsp;&nbsp;";
		var yysse_ZH = "Ӫҵ˰˰��";
		var tdzzs_FP_PGBG_ZH="��ֵ�������˰��-�۳���Ŀ���ϼơ�����۳�ϵ��"+blank_2+"=";
		var tdzzs_JB_ZH = "������ֵ˰Ӧ��˰��";
		var grsds_AS_ZH ="������ת������-����ԭֵ-˰��-������ã�";
		
		//��ø���DIV����
		var yys_show_OBJ = document.all.yys_show;//Ӫҵ˰˰��
		var cjs_show_OBJ = document.all.cjs_show;//�ǽ�˰˰��
		var jyffj_show_OBJ = document.all.jyffj_show;//�����Ѹ��ӽ��
		var dfjyfj_show_OBJ = document.all.dfjyfj_show;//�ط��������ӽ��
		var tdzzs_FP_PGBG_show_OBJ = document.all.tdzzs_FP_PGBG_show;//������ֵ˰(����Ʊ��������������)
		var tdzzs_HD_show_OBJ = document.all.tdzzs_HD_show;//������ֵ˰���˶����գ�
		var tdzzs_JB_show_OBJ = document.all.tdzzs_JB_show;//������ֵ˰���������գ�
		var grsds_HD_show_OBJ = document.all.grsds_HD_show;//��������˰���˶����գ�
		var grsds_AS_show_OBJ = document.all.grsds_AS_show;//��������˰����ʵ���գ�
		
		
        <logic:iterate id="sbitem" name="ClfswjghdxxlrForm"  property ="mfsbxxList" indexId="index">
			var szsmdm = '<bean:write name="sbitem" property="szsmdm"/>';//˰��˰Ŀ����
			var sl = '<bean:write name="sbitem" property="sl"/>';//˰��
			var jsje = '<bean:write name="sbitem" property="jsje"/>';//��˰���
			var sjje = '<bean:write name="sbitem" property="sjje"/>';//˰��˰��
			
			if(szsmdm != null && szsmdm != ""){
				var sbxxMsg = blank_1 + jsje+blank_2+"��"+sl+"%"+blank_2+"="+blank_2+ sjje;
					switch(szsmdm) {
					    case '02'://����Ӫҵ˰
					    	yys_show_OBJ.innerHTML = jssr_ZH + sbxxMsg;
					     	break;
						case '10'://�ǽ�˰
							cjs_show_OBJ.innerHTML = yysse_ZH+ sbxxMsg; 
							break;
					    case '51'://�����Ѹ���
					    	jyffj_show_OBJ.innerHTML = yysse_ZH+ sbxxMsg; 
						 	break;
						case '54'://�ط������Ѹ���
							dfjyfj_show_OBJ.innerHTML = yysse_ZH+ sbxxMsg; 
						 	break;
						case '08'://������ֵ˰
							if(tdzzssbfs != null && tdzzssbfs != ""){
								//��1������Ʊ�������۸�
								if(tdzzssbfs == <%=Constants.TDZSS_SB_GFFP%> || 
								   tdzzssbfs == <%=Constants.TDZSS_SB_PGBG%>)
								{
									tdzzs_FP_PGBG_show_OBJ.innerHTML = tdzzs_FP_PGBG_ZH +blank_2+ sjje; 
								}
								
								//��2���˶�����
								if(tdzzssbfs == <%=Constants.TDZSS_SB_HDZS%>)//����Ʊ�������۸�
								{
									tdzzs_HD_show_OBJ.innerHTML = jssr_ZH + sbxxMsg;
								}								
								//��3����������(����)								
							}
						 	break;
						case '05'://��������˰
							if(jssrqrfs != null && jssrqrfs != ""){ 
								//��1���˶�����
								if(jssrqrfs == <%=Constants.JSSRQRFS_HDJSJG%>){
									grsds_HD_show_OBJ.innerHTML = jssr_ZH + sbxxMsg;
								}else{
								//��2����ʵ����
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
	
	
	//ѡ�и�ѡѡ��
	function setCheckbox(checkBoxName,isChecked){
		if(checkBoxName != null && checkBoxName !="" && isChecked != null && isChecked != ""){
			document.getElementById(checkBoxName).checked = isChecked;
			
		}
	}
	

	//��ֵ����
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

	//��������˫����Ϣ
	function setBuyorSellInfo(buyorSellInfo, type) {
		var isSuccess = false;

		if (buyorSellInfo == null || buyorSellInfo == "") {
			return isSuccess;
		}
		
		//��^����
		var after_split_all_group_arr = new Array();//����Ѿ�������ɵ�
		
		  if(buyorSellInfo.indexOf("^")>0){
			  //alert("�ж���");
			  var infoArr = buyorSellInfo.split("^");
			  
			  //alert("�ж��飬����::"+infoArr.length);
			  
			  for(var index =0; index < infoArr.length;index ++){
				  var tempInfo = infoArr[index];
				  
				  //���ý�������������ÿ������������Ϣ����ʾ
				  //setBuyorSellInfo(tempInfo,tableId,hidPropertyObj);
				  //alert("-----------22222222222");
				  after_split_all_group_arr.push(tempInfo);
			  }
		  }else{
			  //setBuyorSellInfo(buyorSellInfo,tableId,hidPropertyObj);
			  after_split_all_group_arr.push(buyorSellInfo);
		  }

			//����ÿո�ֿ�
			var xm_all ="";//���� 
			var lxdm_all ="";//��ϵ�绰
			var zjmc_all ="";//֤������
			var zjhm_all="";//֤������
			
		for(var index =0;index < after_split_all_group_arr.length;index ++){
			var info = after_split_all_group_arr[index];
			
			//�ֽⲢ��ʾ��Ϣ
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
						xm_all = value_0;//����
						lxdm_all = value_5;//��ϵ�绰
						zjmc_all = getZjmc(value_2);//֤������
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
			
		//���ò���ʾÿ����Ϣ
		
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
		
		return "����";
	}

	//��ÿ����Ϣ���õ�td��innerHTML����ʾ
	function setOneGroupInfo(arr, tableobj) {

		if (tableobj == null || tableobj == "") {
			return false;
		}
		var otr = tableobj.insertRow();
		//��һ�е�һ��  �����ƣ���������
		var mc_Cell = otr.insertCell();
		mc_Cell.innerHTML = "���ƣ�������";
		mc_Cell.className = "2-td2-left";
		mc_Cell.WIDTH = "20%";
		//��һ�еڶ��� 
		var mc_value_Cell = otr.insertCell();
		mc_value_Cell.innerHTML = arr[0];
		mc_value_Cell.className = "2-td2-left";
		mc_value_Cell.WIDTH = "30%";
		//��һ�е�����  �����
		var lb_Cell = otr.insertCell();
		lb_Cell.innerHTML = "���";
		lb_Cell.className = "2-td2-left";
		lb_Cell.WIDTH = "20%";
		//��һ�е�����  
		var lb_value_Cell = otr.insertCell();
		lb_value_Cell.innerHTML = nsrlb_mc(arr[1]);
		lb_value_Cell.className = "2-td2-center";
		lb_value_Cell.WIDTH = "30%";

		//�ڶ��е�һ�� ��֤�����͡�
		var otr2 = tableobj.insertRow();
		var zjlx_Cell = otr2.insertCell();
		zjlx_Cell.innerHTML = "֤������";
		zjlx_Cell.className = "2-td2-left";
		zjlx_Cell.WIDTH = "20%";
		//�ڶ��еڶ���
		var zjlx_value_Cell = otr2.insertCell();
		zjlx_value_Cell.innerHTML = nsrzjlb_mc(arr[2]);
		zjlx_value_Cell.className = "2-td2-left";
		zjlx_value_Cell.WIDTH = "30%";
		//�ڶ��е�һ�� ��֤�����롱
		var zjhm_Cell = otr2.insertCell();
		zjhm_Cell.innerHTML = "֤������";
		zjhm_Cell.className = "2-td2-left";
		zjhm_Cell.WIDTH = "20%";
		//�ڶ��е�һ�� 
		var zjhm_value_Cell = otr2.insertCell();
		zjhm_value_Cell.innerHTML = arr[3];
		zjhm_value_Cell.className = "2-td2-center";
		zjhm_value_Cell.WIDTH = "30%";

		//�����е�һ�� ��Ȩ���˷ݶ
		var otr3 = tableobj.insertRow();
		var fe_Cell = otr3.insertCell();
		fe_Cell.innerHTML = "Ȩ���˷ݶ�";
		fe_Cell.className = "2-td2-left";
		fe_Cell.WIDTH = "20%";
		//�����еڶ��� 
		var fe_value_Cell = otr3.insertCell();
		fe_value_Cell.innerHTML = arr[4];
		fe_value_Cell.className = "2-td2-left";
		fe_value_Cell.WIDTH = "30%";
		//�����е����� ����ϵ�˵绰��
		var lxdh_Cell = otr3.insertCell();
		lxdh_Cell.innerHTML = "��ϵ�˵绰";
		lxdh_Cell.className = "2-td2-left";
		lxdh_Cell.WIDTH = "20%";
		//�����е����� 
		var lxdh_value_Cell = otr3.insertCell();
		lxdh_value_Cell.innerHTML = arr[5];
		lxdh_value_Cell.className = "2-td2-center";
		lxdh_value_Cell.WIDTH = "30%";

		return true;
	}

</script>
</HTML>
