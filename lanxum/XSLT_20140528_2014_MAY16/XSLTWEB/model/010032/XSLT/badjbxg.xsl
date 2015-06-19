<?xml version="1.0" encoding="gb2312"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" />
<xsl:template match="/">

	<input name="xsltVersion" type="hidden" id="xsltVersion">
		<xsl:attribute name="value">
			<xsl:value-of select="taxdoc/xsltVersion" />
		</xsl:attribute>
	</input>
	<input name="schemaVersion" type="hidden" id="schemaVersion">
		<xsl:attribute name="value">
			<xsl:value-of select="taxdoc/schemaVersion" />
		</xsl:attribute>
	</input>
	<input name="ywlx" type="hidden" id="ywlx">
		<xsl:attribute name="value">
			<xsl:value-of select="taxdoc/ywlx" />
		</xsl:attribute>
	</input>
	<input name="ywczlx" type="hidden" id="ywczlx">
		<xsl:attribute name="value">
			<xsl:value-of select="taxdoc/ywczlx" />
		</xsl:attribute>
	</input>
	<input name="tbrq" type="hidden" id="tbrq">
		<xsl:attribute name="value">
			<xsl:value-of select="taxdoc/tbrq" />
		</xsl:attribute>
	</input>
	<input name="tbrqShow" type="hidden" id="tbrqShow">
		<xsl:attribute name="value">
			<xsl:value-of select="taxdoc/tbrqShow" />
		</xsl:attribute>
	</input>
	<table width="1000" align="center" cellspacing="0">
		<tr>
			<td>
				<table class="table-99" align="center" border="1" cellspacing="0" cellpadding="0">
					<tr> 
						<td width="20%" class="2-td2-t-left">�۽������˼�������룺</td>
						<td width="30%" class="2-td2-t-left"> 
							<div align="left">&#160;
								<xsl:value-of select="taxdoc/kjywrxx/kjrjsjdm" />
								<input name="kjrjsjdm" type="hidden" id="kjrjsjdm">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/kjywrxx/kjrjsjdm" />
									</xsl:attribute>
								</input>
							</div>
						</td>
						<td width="20%" class="2-td2-t-left">�۽����������ƣ�</td>
						<td width="30%" class="2-td2-t-center">
							<div align="left">
								&#160;<xsl:value-of select="taxdoc/kjywrxx/kjrmc_cn" />
								<input name="kjrmc_cn" type="hidden" id="kjrmc_cn">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/kjywrxx/kjrmc_cn" />
									</xsl:attribute>
								</input>
							</div>
						</td>
					</tr>
				</table>	
			</td>
		</tr>
		<tr>
			<td>
				<br/>
				<table border="1" cellspacing="0" cellpadding="0" class="table-99" align="center" >
					<tr> 
						<td width="6%" align="center" class="2-td2-t-left">&#160;</td>
						<td width="22%" align="center" class="2-td2-t-left"><strong>��ͬ����</strong></td>
						<td width="22%" align="center" class="2-td2-t-left"><strong>��ͬ���</strong></td>
						<td width="10%" align="center" class="2-td2-t-left"><strong>��ͬǩԼ����</strong></td>
						<td width="10%" align="center" class="2-td2-t-left"><strong>��ͬ��Ч��</strong></td>
						<td width="10%" align="center" class="2-td2-t-left"><strong>��ͬ���</strong></td>
						<td width="10%" align="center" class="2-td2-t-left"><strong>�����</strong></td>
						<td width="10%" align="center" class="2-td2-t-center"><strong>���״̬</strong></td>
					</tr>
					<xsl:apply-templates select="taxdoc/badjbxx" />
				</table>
			</td>
		</tr>
	</table>
</xsl:template>
<xsl:template match="taxdoc/badjbxx/mxxx">
	<tr> 
		<td class="2-td2-left">
			<input type="radio" name="dshRadio">
				<xsl:attribute name="value">
					<xsl:value-of select="badjxh" />
				</xsl:attribute>
			</input>
		</td>
        <td class="2-td2-left" align="left">
			<xsl:value-of select="htmc" />
		</td>
        <td class="2-td2-left" align="left">
			<xsl:value-of select="htbh" />
		</td>
        <td class="2-td2-left" align="left">
			<xsl:value-of select="htqyrq" />
		</td>
        <td class="2-td2-left" align="left">
			<xsl:value-of select="htyxq" />
		</td>
        <td class="2-td2-left" align="left">
			<xsl:value-of select="htje" />
		</td>
        <td align="left" class="2-td2-left"> 
		  	<xsl:value-of select="tbrq" />
        </td>
        <td align="left" class="2-td2-center">
			<xsl:value-of select="shzt" />
		</td>
	</tr>
</xsl:template>
</xsl:stylesheet>
