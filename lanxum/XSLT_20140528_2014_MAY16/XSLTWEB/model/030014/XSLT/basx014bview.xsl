<?xml version="1.0" encoding="GB2312"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
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
		<input name="swjgzzjgdm" type="hidden" id="swjgzzjgdm">
			<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/nsrxx/swjgzzjgdm" />
			</xsl:attribute>
		</input>
		<input name="jsjdm" type="hidden" id="jsjdm">
			<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/nsrxx/jsjdm" />
			</xsl:attribute>
		</input>
		<input name="nsrmc" type="hidden" id="nsrmc">
			<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/nsrxx/nsrmc" />
			</xsl:attribute>
		</input>
		<input name="basqbh" type="hidden" id="basqbh">
			<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/basqbh" />
			</xsl:attribute>
		</input>
		<input name="basqwsh" type="hidden" id="basqwsh">
			<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/basqwsh" />
			</xsl:attribute>
		</input>
		<input name="band" type="hidden" id="band">
			<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/band" />
			</xsl:attribute>
		</input>
		<input name="jmbasxmc" type="hidden" id="jmbasxmc">
			<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/jmbasxmc" />
			</xsl:attribute>
		</input>
		<input name="lrrq" type="hidden" id="lrrq">
			<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/lrrq" />
			</xsl:attribute>
		</input>
		<input name="ztdm" type="hidden" id="ztdm">
			<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/ztdm" />
			</xsl:attribute>
		</input>
		<input name="ztmc" type="hidden" id="ztmc">
			<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/ztmc" />
			</xsl:attribute>
		</input>
		<input name="szdm" type="hidden" id="szdm">
			<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/szdm" />
			</xsl:attribute>
		</input>
		<input name="szmc" type="hidden" id="szmc">
			<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/szmc" />
			</xsl:attribute>
		</input>
		<input name="jmbasxdm" type="hidden" id="jmbasxdm">
			<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/jmbasxdm" />
			</xsl:attribute>
		</input>
		<input name="bsfsdm" type="hidden" id="bsfsdm">
			<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/bsfsdm" />
			</xsl:attribute>
		</input>
		<input name="bsfsmc" type="hidden" id="bsfsmc">
			<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/bsfsmc" />
			</xsl:attribute>
		</input>
		<input name="ywcbabs" type="hidden" id="ywcbabs">
			<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/qysdsjmba/ywcbabs" />
			</xsl:attribute>
		</input>



		<table class="table-99" cellspacing="0" id="TABLE_LIST1"
			style="display: block">
			<tr>
				<td class="2-td2-t-left" colspan="2" width="40%">
					<div align="left">备案申请文书号 &#160;</div>
				</td>
				<td class="2-td2-t-center" colspan="2">
					<div align="left">
						<input type="text" name="basqbh" maxlength="11"
							size="30" tabindex="-1" disabled="true">
							<xsl:attribute name="value">
								<xsl:value-of
									select="taxdoc/jmsbajl/basqbh" />
							</xsl:attribute>
						</input>
					</div>
				</td>
			</tr>
			<!--tr>
				<td width="50%" class="2-td2-left" height="21"
					colspan="2">
					<div align="left">往年备案申请编号 &#160;&#160;</div>
				</td>
				<td width="50%" class="2-td2-center" height="21"
					colspan="2">
					<div align="left">
						<input type="text" name="basqbh1" maxlength="25"
							size="30" tabindex="1" class="noborder-txtcenter" readonly="true">
						</input>
						&#160;
						<input type="button" name="cx" value="查询 "
							onclick="javascript:return doAjaxQueryWnsj();" class="noborder-txtcenter" readonly="true">
						</input>

					</div>
				</td>
			</tr-->
			<tr>
				<td class="2-td2-left" colspan="2">
					<div align="left">购置年度<font color="#FF0000">*</font></div>
				</td>
				<td class="2-td2-center" colspan="2">
					<div id="mx_zsfsdm_epx_visible_1" align="left">
						<input type="text" name="dmnd" maxlength="11"
							size="17" tabindex="2" value="" onchange="javascript:return doAjaxQueryDmnd();" class="noborder-txtcenter" readonly="true">
							<xsl:attribute name="value">
								<xsl:value-of
									select="taxdoc/jmsbajl/qysdsjmba/dmnd" />
							</xsl:attribute>
						</input>
					</div>
				</td>
			</tr>
			<tr>
				<td class="2-td2-left" colspan="2">
					<div align="left">
						购置专用设备名称
						<font color="#FF0000">*</font>
					</div>
				</td>
				<td class="2-td2-center" colspan="2">
					<div align="left">
						<input type="text" name="zysbmc" maxlength="11"
							size="17" tabindex="3" value="" class="noborder-txtcenter" readonly="true">
							<xsl:attribute name="value">
								<xsl:value-of
									select="taxdoc/jmsbajl/qysdsjmba/zysbmc" />
							</xsl:attribute>
						</input>
					</div>
				</td>
			</tr>
			<tr>
				<td class="2-td2-left" colspan="2">
					<div align="left">
						专用设备种类
						<font color="#FF0000">*</font>
					</div>
				</td>
				<td class="2-td2-center" colspan="2">
					<div id="zysblxdmdiv" align="left"></div>
				</td>
			</tr>

		</table>
		<br />
		<table cellspacing="0" border="0" class="table-99"
			style="TABLE-LAYOUT:fixed">
			<tr />
			<td class="2-td2-t-left" width="11%">投资年度</td>
			<td class="2-td2-t-left" width="11%">当年购置设备投资额</td>
			<td class="2-td2-t-left" width="11%">当年可抵免的应纳税额</td>
			<td class="2-td2-t-left" width="11%">当年实际抵免的应纳税额</td>
			<td class="2-td2-t-center" width="11%">结转以后年度抵免的应纳税额</td>
			<xsl:for-each select="taxdoc/jmsbajl/qysdsjmba">
				<xsl:variable name="selwsh" select="xh" />
				<xsl:variable name="crdmnd" select="dmnd" />
				<xsl:variable name="tabIndex" select="4" />
				<input name="xh{$crdmnd}" type="hidden" id="xh{$crdmnd}" value="{$selwsh}"/>
				<tr>
					<td width="10%" class="2-td2-left">
						<div align="center">
							<xsl:value-of select="dmnd" />
							年
						</div>
					</td>
					<td class="2-td2-left">
						<div id="mx_zsfsdm_epx_visible_1"
							align="center">
							<input name="tzezs{$crdmnd}" id="tzezs{$crdmnd}"
								tabindex="{$tabIndex+1}" type="text" style="text-align:right" size="17"
								onchange="change('1','{$crdmnd}')" class="noborder-txtcenter" readonly="true">
								<xsl:attribute name="value">
									<xsl:value-of select="tzezs" />
								</xsl:attribute>
							</input>
						</div>
					</td>
					<td class="2-td2-left">
						<div align="center">
							<input name="dnkdmse{$crdmnd}" id="dnkdmse{$crdmnd}"
								 type="text" style="text-align:right" size="17"
								 class="noborder-txtcenter" readonly="true">
								<xsl:attribute name="value">
									<xsl:value-of select="dnkdmse" />
								</xsl:attribute>
							</input>
						</div>
					</td>
					<td class="2-td2-left">
						<div align="center">
							<input name="dmynse{$crdmnd}" id="dmynse{$crdmnd}"
								tabindex="{$tabIndex+1}" type="text" style="text-align:right" size="17"
								onchange="change('2','{$crdmnd}')" class="noborder-txtcenter" readonly="true">
								<xsl:attribute name="value">
									<xsl:value-of select="dmynse" />
								</xsl:attribute>
							</input>
						</div>
					</td>
					<td class="2-td2-left">
						<div align="center">
							<input name="jze{$crdmnd}" id="jze{$crdmnd}"
								 type="text" style="text-align:right" size="17"
								onchange="return formatKsslJsje(this);"  class="noborder-txtcenter" readonly="true">
								<xsl:attribute name="value">
									<xsl:value-of select="jze" />
								</xsl:attribute>
							</input>
						</div>
					</td>
				</tr>
			</xsl:for-each>
		</table>



		<br></br>

		<table class="table-99" cellspacing="0">
			<tr>
				<td width="10%" class="2-td2-t-left">
					<div align="right">录入日期</div>
				</td>
				<td width="30%" class="2-td2-t-left">
					<div align="left">
						<input type="text" name="lrrq" size="15"
							readonly="readonly" class="txtInput">
							<xsl:attribute name="value">
								<xsl:value-of
									select="taxdoc/jmsbajl/lrrq" />
							</xsl:attribute>
						</input>
						<font color="#FF0000">*</font>
					</div>
				</td>
				<td width="10%" class="2-td2-t-left">
					<div align="right">备案年度</div>
				</td>
				<td width="30%" class="2-td2-t-left">
					<div align="left">
						<input type="text" name="band" size="15"
							readonly="readonly" class="txtInput">
							<xsl:attribute name="value">
								<xsl:value-of
									select="taxdoc/jmsbajl/band" />
							</xsl:attribute>
						</input>
						<font color="#FF0000">*</font>
					</div>
				</td>
				<td width="10%" class="2-td2-t-left">
					<div align="right">录入人</div>
				</td>
				<td width="30%" class="2-td2-t-center">
					<div align="left">
						<input type="text" name="lrr" size="15"
							readonly="readonly" class="txtInput">
							<xsl:attribute name="value">
								<xsl:value-of
									select="taxdoc/nsrxx/nsrmc" />
							</xsl:attribute>
						</input>
					</div>
				</td>
			</tr>
		</table>
	</xsl:template>
</xsl:stylesheet>
