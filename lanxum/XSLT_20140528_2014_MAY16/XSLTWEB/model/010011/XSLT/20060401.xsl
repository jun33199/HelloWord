<?xml version="1.0" encoding="GB2312"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
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
		<xsl:apply-templates select="taxdoc/sbxx"/>
		<br/>
		<xsl:apply-templates select="taxdoc/nsrxx"/>
		<br/>
		<xsl:apply-templates select="taxdoc/hdxx"/>
		<xsl:apply-templates select="taxdoc/sbsjlist"/>
	</xsl:template>
	<xsl:template match="taxdoc/sbxx">
		<table cellspacing="0" class="table-99" width="100%">
			<tr class="2-td2-t-left">
				<input name="fsdm" type="hidden" id="fsdm">
					<xsl:attribute name="value"><xsl:value-of select="fsdm"/></xsl:attribute>
				</input>
				<input name="nd" type="hidden" id="nd">
					<xsl:attribute name="value"><xsl:value-of select="nd"/></xsl:attribute>
				</input>
				<td colspan="2">
					<div align="left"> 申报日期：<xsl:value-of select="sbrq"/>
					</div>
					<input name="sbrq" type="hidden" id="sbrq" >
						<xsl:attribute name="value"><xsl:value-of select="sbrq"/></xsl:attribute>
					</input>					
				</td>
				<td colspan="2">
					<div align="left"> 申报所属日期：<xsl:value-of select="skssksrq"/> 至 <xsl:value-of select="skssjsrq"/>
					</div>
					<input name="skssksrq" type="hidden" id="skssksrq" >
						<xsl:attribute name="value"><xsl:value-of select="skssksrq"/></xsl:attribute>
					</input>
					<input name="skssjsrq" type="hidden" id="skssjsrq">
						<xsl:attribute name="value"><xsl:value-of select="skssjsrq"/></xsl:attribute>
					</input>					
				</td>
				<td colspan="2">
					<div align="right"> 金额单位：人民币元</div>
				</td>
			</tr>
		</table>
	</xsl:template>
	<xsl:template match="taxdoc/nsrxx">
		<table cellspacing="0" class="table-99" width="100%">
			<tr>
				<td nowrap="nowrap" class="2-td2-t-left">
					<div align="right">计算机代码&#160;&#160;</div>
				</td>
				<td nowrap="nowrap" class="2-td2-t-left">
					<div align="left">&#160;&#160;<xsl:value-of select="jsjdm"/></div>
						<input name="jsjdm" type="hidden" id="jsjdm">
							<xsl:attribute name="value"><xsl:value-of select="jsjdm"/></xsl:attribute>
						</input>
				</td>
				<td nowrap="nowrap" class="2-td2-t-left">
					<div align="right">申报单位&#160;&#160;</div>
				</td>
				<td nowrap="nowrap" class="2-td2-t-left">
					<div align="left">&#160;&#160;<xsl:value-of select="nsrmc"/></div>
						<input name="nsrmc" type="hidden" id="nsrmc">
							<xsl:attribute name="value"><xsl:value-of select="nsrmc"/></xsl:attribute>
						</input>
				</td>
				<td nowrap="nowrap" class="2-td2-t-left">
					<div align="right">联系电话&#160;&#160;</div>
				</td>
				<td nowrap="nowrap" class="2-td2-t-left">
					<div align="left">&#160;&#160;<xsl:value-of select="qylxdh"/></div>
					<input name="qylxdh" type="hidden" id="qylxdh">
						<xsl:attribute name="value"><xsl:value-of select="qylxdh"/></xsl:attribute>
					</input>
					<input name="swjgzzjgdm" type="hidden" id="swjgzzjgdm">
						<xsl:attribute name="value"><xsl:value-of select="swjgzzjgdm"/></xsl:attribute>
					</input>					
				</td>
			</tr>
		</table>
	</xsl:template>
	<xsl:template match="taxdoc/sbsjlist">
		<xsl:variable name="varSum" select="40"/>
		<xsl:variable name="varCol" select="2"/>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table-99">
			<tr>
				<td align="center" nowrap="nowrap" class="2-td1-left">项目</td>
				<td align="center" nowrap="nowrap" class="2-td1-left">行次</td>
				<td align="center" nowrap="nowrap" class="2-td1-left">年初数</td>
				<td align="center" nowrap="nowrap" class="2-td1-left">年末数</td>
				<td align="center" nowrap="nowrap" class="2-td1-left">项目</td>
				<td align="center" nowrap="nowrap" class="2-td1-left">行次</td>
				<td align="center" nowrap="nowrap" class="2-td1-left">年初数</td>
				<td align="center" nowrap="nowrap" class="2-td1-center">年末数</td>
			</tr>
			<tr>
				<xsl:for-each select="sbsj">
					<xsl:sort select="hc" data-type="number">
					</xsl:sort>
					<xsl:if test="position() &lt;= ($varSum div $varCol)">
						<xsl:variable name="varPosition" select="position()"/>
						<tr>
							<xsl:apply-templates select="../sbsj[hc = ($varPosition+($varSum div $varCol)*0)]"/>
							<xsl:apply-templates select="../sbsj[hc = ($varPosition+($varSum div $varCol)*1)]"/>
						</tr>
					</xsl:if>
				</xsl:for-each>
			</tr>
		</table>
	</xsl:template>
	<xsl:template match="taxdoc/sbsjlist/sbsj">
		<td align="center" class="2-td2-left">
			<div align="left">&#160;<xsl:value-of select="xmmc"/></div>
			<input type="hidden" name="qycwzbxmmc">
				<xsl:attribute name="value"><xsl:value-of select="xmmc"/></xsl:attribute>
				<xsl:attribute name="id">qycwzbxmmc_<xsl:value-of select="hc"/></xsl:attribute>
			</input>
		</td>
		<td align="center" nowrap="nowrap" class="2-td2-left">
			<input type="text" name="qycwzbhc" class="noborder-txtcenter" readonly="true" size="4">
				<xsl:attribute name="value"><xsl:value-of select="hc"/></xsl:attribute>
			</input>
		</td>
		<td align="center" nowrap="nowrap" class="2-td2-left">
			<input type='text' name='qycwzbncs' size="15" maxlength="15">
				<xsl:attribute name="value"><xsl:value-of select="ncs"/></xsl:attribute>
				<xsl:attribute name="id">cwzb_C_<xsl:value-of select="hc"/></xsl:attribute>
			</input>
		</td>
		<td align="center" nowrap="nowrap" class="2-td2-left">
			<input type="text" name="qycwzbnms" size="15" maxlength="15">
				<xsl:attribute name="value"><xsl:value-of select="nms"/></xsl:attribute>
				<xsl:attribute name="id">cwzb_M_<xsl:value-of select="hc"/></xsl:attribute>
			</input>
		</td>				
	</xsl:template>
</xsl:stylesheet>
