<?xml version="1.0" encoding="GB2312"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html"/>
	<xsl:template match="/">
		<input name="xsltVersion" type="hidden" id="xsltVersion">
			<xsl:attribute name="value"><xsl:value-of select="taxdoc/xsltVersion"/></xsl:attribute>
		</input>
		<input name="schemaVersion" type="hidden" id="schemaVersion">
			<xsl:attribute name="value"><xsl:value-of select="taxdoc/schemaVersion"/></xsl:attribute>
		</input>
		<input name="ywlx" type="hidden" id="ywlx">
			<xsl:attribute name="value"><xsl:value-of select="taxdoc/ywlx"/></xsl:attribute>
		</input>
		<input name="ywczlx" type="hidden" id="ywczlx">
			<xsl:attribute name="value"><xsl:value-of select="taxdoc/ywczlx"/></xsl:attribute>
		</input>
		<!--�������趨ֵ -->
		<input name="swjgzzjgdm" type="hidden" id="swjgzzjgdm">
			<xsl:attribute name="value"><xsl:value-of select="taxdoc/nsrxx/swjgzzjgdm"/></xsl:attribute>
		</input>
		<input name="lrr" type="hidden" id="lrr">
			<xsl:attribute name="value"><xsl:value-of select="taxdoc/sbxx/lrr"/></xsl:attribute>
		</input>
		<input name="signFlagVal" type="hidden" id="signFlagVal">
			<xsl:attribute name="value"><xsl:value-of select="taxdoc/sbxx/signFlagVal"/></xsl:attribute>
		</input>
		<input name="sqspbh" type="hidden" id="sqspbh">
			<xsl:attribute name="value"><xsl:value-of select="taxdoc/sbxx/sqspbh"/></xsl:attribute>
		</input>
		<input name="success" type="hidden" id="success">
			<xsl:attribute name="value"><xsl:value-of select="taxdoc/sbxx/success"/></xsl:attribute>
		</input>
		<input name="isError" type="hidden" id="isError">
			<xsl:attribute name="value"><xsl:value-of select="taxdoc/sbxx/isError"/></xsl:attribute>
		</input>
		<input name="fsdm" type="hidden" id="fsdm">
			<xsl:attribute name="value"><xsl:value-of select="taxdoc/sbxx/fsdm"/></xsl:attribute>
		</input>
		<div id="div1">
			<table class="table-99" cellspacing="0">
				<tr class="black9">
					<td class="2-td2-t-left" width="35%" height="20" nowrap="nowrap">
						<div align="right">
						��ȷ�ϼ��������
					</div>
					</td>
					<td class="2-td2-t-left" colspan="3" width="30%" nowrap="nowrap">
					<div align="left">
							<input name="jsjdm" type="text" id="jsjdm" size="20" maxlength="8" readonly="readonly">
								<xsl:attribute name="value"><xsl:value-of select="taxdoc/nsrxx/jsjdm"/></xsl:attribute>
							</input>
							<font color="#FF0000">&#160;*</font>
						</div>
					</td>
					<td class="2-td2-t-center" width="35%" nowrap="nowrap">
						<div align="left">
							<!--//div1������ʾͼƬ�����div -->
							<div id="div1PicShow"> 
						</div>
						</div>
					</td>
				</tr>
			</table>
		</div>
		<div id="div2" style="display:none">
			<table class="table-99" cellspacing="0">
				<tr class="black9">
					<td align="left">
						<div>
							˰������ʱ�䣺
							<input name="skssrq" type="text" maxlength="6" size="12" readonly="readonly" id="skssrq">
								<xsl:attribute name="value"><xsl:value-of select="taxdoc/sbxx/skssrq"/></xsl:attribute>
							</input>
						</div>
						<br/>
					</td>
					<td align="center">
						<div align="right">
							��λ:Ԫ���Ƿ�
						</div>
					</td>
				</tr>
			</table>
			<br/>
			<table class="table-99" cellspacing="0">
				<tr class="black9">
					<td class="2-td2-t-left" width="20%" height="20" nowrap="nowrap">
						<div align="right">
							��λ����(����)
						</div>
					</td>
					<td class="2-td2-t-left" colspan="3" width="35%">
						<div align="left">
						&#160;<xsl:value-of select="taxdoc/nsrxx/nsrmc"/>
						</div>
					</td>
					<td class="2-td2-t-left" width="20%" nowrap="nowrap">
						<div align="right">
							��˰��ʶ���(˰��Ǽ�֤��)
						</div>
					</td>
					<td class="2-td2-t-center" width="160">
						<div align="left">
							<input name="swdjzh" size="30" onblur="30" readonly="readonly" id="swdjzh">
								<xsl:attribute name="value"><xsl:value-of select="taxdoc/nsrxx/swdjzh"/></xsl:attribute>
							</input>
						</div>
					</td>
				</tr>
				<tr>
					<td class="2-td2-left">
						<div align="right">
						ע���ַ
					</div>
					</td>
					<td class="2-td2-left" colspan="3">
						<div align="left">
 							&#160;<xsl:value-of select="taxdoc/nsrxx/zcdz"/>
						</div>
					</td>
					<td class="2-td2-left">
						<div align="right">
						��λ����
					</div>
					</td>
					<td class="2-td2-center">
						<div align="left">
							<xsl:variable name="dwxzdmz" select="taxdoc/sbxx/dwxz"/>
							<select name="dwxz" id="dwxz">
							</select>
						</div>
					</td>
				</tr>
				<tr>
					<td class="2-td2-left" align="right">
						<div align="right">
						ְ��������
					</div>
					</td>
					<td class="2-td2-left">
						<div align="left">
							<input name="zgzrs" type="text" id="zgzrs" size="23" maxlength="7">
								<xsl:attribute name="value"><xsl:value-of select="taxdoc/sbxx/zgzrs"/></xsl:attribute>
							</input>
						</div>
					</td>
					<td class="2-td2-left" width="100" height="20">
						<div align="center">
						���òм�ְ������
					</div>
					</td>
					<td class="2-td2-left">
						<div align="left">
							<input name="cjrzgrs" type="text" id="cjrzgrs" size="15" maxlength="7">
								<xsl:attribute name="value"><xsl:value-of select="taxdoc/sbxx/cjrzgrs"/></xsl:attribute>
							</input>
						</div>
					</td>
					<td class="2-td2-left" width="100" height="20">
						<div align="center">
						�м���ְ��ռְ���������ı���
					</div>
					</td>
					<td class="2-td2-center">
						<div align="left">
							<input name="cjrybl" type="text" id="cjrybl" size="15" maxlength="7">
								<xsl:attribute name="value"><xsl:value-of select="taxdoc/sbxx/cjrybl"/></xsl:attribute>
							</input>
						</div>
					</td>
				</tr>
				<tr>
					<td class="2-td2-left" height="70">
						<div align="right">
						��Ӫ��Χ
					</div>
					</td>
					<td class="2-td2-center" colspan="5">
						<div align="left">
						&#160;<xsl:value-of select="taxdoc/nsrxx/jyfw"/>
						</div>
					</td>
				</tr>
			</table>
			<table class="table-99" cellspacing="0">
				<tr>
					<td width="10%" class="2-td2-left" colspan="2">
					��Ŀ
				</td>
					<td width="20%" class="2-td2-left">
					����
				</td>
					<td width="20%" class="2-td2-center">
					���
				</td>
				</tr>
				<tr>
					<td width="20%" class="2-td2-left" colspan="2">
					����Ӫҵ˰Ӧ˰����
				</td>
					<td width="20%" class="2-td2-left">
					1
				</td>
					<td width="20%" class="2-td2-center">
						<div align="center">
							<input name="ynyyssr" type="text" id="ynyyssr" size="25" maxlength="15">
								<xsl:attribute name="value"><xsl:value-of select="taxdoc/sbxx/ynyyssr"/></xsl:attribute>
							</input>
						</div>
					</td>
				</tr>
				<tr>
					<td width="20%" class="2-td2-left" colspan="2">
					����Ӧ��Ӫҵ˰˰��
				</td>
					<td width="20%" class="2-td2-left">
					2
				</td>
					<td width="20%" class="2-td2-center">
						<div align="center">
							<input name="yjyysse" type="text" id="yjyysse" size="25" maxlength="15">
								<xsl:attribute name="value"><xsl:value-of select="taxdoc/sbxx/yjyysse"/></xsl:attribute>
							</input>
						</div>
					</td>
				</tr>
				<tr>
					<td width="20%" class="2-td2-left" colspan="2">
					���У������ܱ��Ż����ߵ�˰��
				</td>
					<td width="20%" class="2-td2-left">
					3
				</td>
					<td width="20%" class="2-td2-center">
						<div align="center">
							<input name="xsyhzzse" type="text" id="xsyhzzse" size="25" maxlength="15">
								<xsl:attribute name="value"><xsl:value-of select="taxdoc/sbxx/xsyhzzse"/></xsl:attribute>
							</input>
						</div>
					</td>
				</tr>
				<tr>
					<td width="20%" class="2-td2-left" colspan="2">
					����Ӧ����Ӫҵ˰�޶�
				</td>
					<td width="20%" class="2-td2-left">
					4=35000/12*���òм�ְ������
				</td>
					<td width="20%" class="2-td2-center">
						<div align="center">
							<input name="byyjzyysxe" type="text" id="byyjzyysxe" size="25" maxlength="15">
								<xsl:attribute name="value"><xsl:value-of select="taxdoc/sbxx/byyjzyysxe"/></xsl:attribute>
							</input>
						</div>
					</td>
				</tr>
				<tr>
					<td width="20%" class="2-td2-left" colspan="2">
					����δ����Ӫҵ˰�޶�
				</td>
					<td width="20%" class="2-td2-left">
					5=����9
				</td>
					<td width="20%" class="2-td2-center">
						<div align="center">
							<input name="syyjzyysxe" type="text" id="syyjzyysxe" size="25" maxlength="15">
								<xsl:attribute name="value"><xsl:value-of select="taxdoc/sbxx/syyjzyysxe"/></xsl:attribute>
							</input>
						</div>
					</td>
				</tr>
				<tr>
					<td width="20%" class="2-td2-left" colspan="2">
					���¿ɼ���Ӫҵ˰�޶�
				</td>
					<td width="20%" class="2-td2-left">
					6=4+5
				</td>
					<td width="20%" class="2-td2-center">
						<div align="center">
							<input name="bykjzyysxe" type="text" id="bykjzyysxe" size="25" maxlength="15">
								<xsl:attribute name="value"><xsl:value-of select="taxdoc/sbxx/bykjzyysxe"/></xsl:attribute>
							</input>
						</div>
					</td>
				</tr>
				<tr>
					<td width="20%" class="2-td2-left" colspan="2">
					����ʵ�ʼ���Ӫҵ˰˰��
				</td>
					<td width="20%" class="2-td2-left">
					7(��3>=6��Ϊ6������Ϊ3)
				</td>
					<td width="20%" class="2-td2-center">
						<div align="center">
							<input name="bysjjzyysye" type="text" id="bysjjzyysye" size="25" maxlength="15">
								<xsl:attribute name="value"><xsl:value-of select="taxdoc/sbxx/bysjjzyysye"/></xsl:attribute>
							</input>
						</div>
					</td>
				</tr>
				<tr>
					<td width="20%" class="2-td2-left" colspan="2">
					���¼���Ӫҵ˰��Ӧ��Ӫҵ˰˰��
				</td>
					<td width="20%" class="2-td2-left">
					8=2-7
				</td>
					<td width="20%" class="2-td2-center">
						<div align="center">
							<input name="bysjyesse" type="text" id="bysjyesse" size="25" maxlength="15">
								<xsl:attribute name="value"><xsl:value-of select="taxdoc/sbxx/bysjyesse"/></xsl:attribute>
							</input>
						</div>
					</td>
				</tr>
				<tr>
					<td width="20%" class="2-td2-left" colspan="2">
					����δ����Ӫҵ˰�޶�
				</td>
					<td width="20%" class="2-td2-left">
					9 = 6-7
				</td>
					<td width="20%" class="2-td2-center">
						<div align="center">
							<input name="bymjzyysxe" type="text" id="bymjzyysxe" size="25" maxlength="15">
								<xsl:attribute name="value"><xsl:value-of select="taxdoc/sbxx/bymjzyysxe"/></xsl:attribute>
							</input>
						</div>
					</td>
				</tr>
			</table>
			<br/>
			<table class="table-99" cellspacing="0">
				<tr>
					<td width="7%" class="2-td2-t-left">
						<div align="right">
						¼������
					</div>
					</td>
					<td width="30%" class="2-td2-t-center" colspan="2">
						<div align="left">
							<input name="lrrq" type="text" id="lrrq" size="20" maxlength="8" readonly="readonly">
								<xsl:attribute name="value"><xsl:value-of select="taxdoc/sbxx/lrrq"/></xsl:attribute>
							</input>
						</div>
					</td>
				</tr>
			</table>
			<br/>
			<!--//div2������ʾͼƬ�����div -->
			<div id="div2PicShow">
			</div>
		</div>
	</xsl:template>
</xsl:stylesheet>
