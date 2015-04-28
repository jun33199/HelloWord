<?xml version="1.0" encoding="gb2312"?>
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
		<table align="center" width="100%" cellspacing="0" class="table-99">
		   <xsl:apply-templates select="taxdoc/nsrxx"/>
		   <xsl:apply-templates select="taxdoc/sbxx"/>
		   <xsl:apply-templates select="taxdoc/sbsj"/>
		</table>
	</xsl:template>
	<xsl:template match="taxdoc/nsrxx">
		<tr class="black9">
		   <td nowrap="nowrap" class="2-td2-t-left" width="100"><div align="right">计算机代码&#160;&#160;</div></td>
		   <td nowrap="nowrap" class="2-td2-t-left"><div align="left">&#160;&#160; <xsl:value-of select="jsjdm"/>
					<input name="jsjdm" type="hidden" id="jsjdm">
						<xsl:attribute name="value"><xsl:value-of select="jsjdm"/></xsl:attribute>
					</input>
		   </div>
		   </td>
		   <td nowrap="nowrap" class="2-td2-t-left"><div align="right">单位名称&#160;&#160;</div></td>
		   <td nowrap="nowrap" class="2-td2-t-center"><div align="left">&#160;&#160;<xsl:value-of select="nsrmc"/></div>
					<input name="nsrmc" type="hidden" id="nsrmc">
						<xsl:attribute name="value"><xsl:value-of select="nsrmc"/></xsl:attribute>
					</input>
					<input name="swjgzzjgdm" type="hidden" id="swjgzzjgdm">
						<xsl:attribute name="value"><xsl:value-of select="swjgzzjgdm"/></xsl:attribute>
					</input>
		   </td>                     
		</tr>
	</xsl:template>
	<xsl:template match="taxdoc/hdxx">		
				<input name="bz" type="hidden" id="bz">
					<xsl:attribute name="value"><xsl:value-of select="bz"/></xsl:attribute>
				</input>
				<input name="canSave" type="hidden" id="canSave">
					<xsl:attribute name="value"><xsl:value-of select="canSave"/></xsl:attribute>
				</input>
				<input name="canDel" type="hidden" id="canDel">
					<xsl:attribute name="value"><xsl:value-of select="canDel"/></xsl:attribute>
				</input>
	</xsl:template>
	<xsl:template match="taxdoc/sbxx">
		<tr class="black9">
			<input name="fsdm" type="hidden" id="fsdm">
					<xsl:attribute name="value"><xsl:value-of select="fsdm"/></xsl:attribute>
			</input>
			<input name="sbrq" type="hidden" id="sbrq">
					<xsl:attribute name="value"><xsl:value-of select="sbrq"/></xsl:attribute>
			</input>
			 <td class="2-td2-left" width="100"><div align="right">申报所属日期&#160;&#160;</div></td>
			 <td class="2-td2-center" colspan="3"><div align="left">&#160;&#160;<xsl:value-of select="skssksrq"/>
				 &#160;&#160;至&#160;&#160; <xsl:value-of select="skssjsrq"/></div>
				<input name="skssksrq" type="hidden" id="skssksrq">
					<xsl:attribute name="value"><xsl:value-of select="skssksrq"/></xsl:attribute>
				</input>
				<input name="skssjsrq" type="hidden" id="skssjsrq">
					<xsl:attribute name="value"><xsl:value-of select="skssjsrq"/></xsl:attribute>
				</input>
			</td>
		</tr>
	</xsl:template> 
	<xsl:template match="taxdoc/sbsj">
		<tr class="black9">
			<td nowrap="nowrap" class="2-td2-left" width="100"><div align="right">申报原因&#160;&#160;</div></td>
		    <td nowrap="nowrap" class="2-td2-center" colspan="3"><div align="left">&#160;
				<xsl:variable name="wssbyy" select="wssbyydm"/>
				<select name="wssbyydm" id="wssbyydm" onchange="docheckSelected()">
					<option value="{$wssbyy}" selected="selected"><xsl:value-of select="wssbyymc"/>
						</option>
				</select>
				<input type="hidden" name="jmflmc" id="wssbyymc">
                     <xsl:attribute name="value"><xsl:value-of select="wssbyymc"/></xsl:attribute>
                </input></div>
		   </td>
		</tr>
		<tr id="id_bz" class="black9">
			<td nowrap="nowrap" class="2-td2-left" width="100"><div align="right">申报原因描述&#160;&#160;</div></td>
			<td nowrap="nowrap" class="2-td2-center" colspan="3"><div align="left">&#160;
				<textarea name="QtWssbyymc" id="QtWssbyymc" rows="4" cols="84%" onkeyup="countSl();">
					<xsl:value-of select="QtWssbyymc"/>
				</textarea><span id="spnId"> </span>
                </div>
			</td>
		</tr>
		<input name="sbcz" type="hidden" id="sbcz">
			<xsl:attribute name="value"><xsl:value-of select="sbcz"/></xsl:attribute>
		</input>
		<input name="sbbh" type="hidden" id="sbbh">
			<xsl:attribute name="value"><xsl:value-of select="sbbh"/></xsl:attribute>
		</input>
	</xsl:template>
</xsl:stylesheet>
