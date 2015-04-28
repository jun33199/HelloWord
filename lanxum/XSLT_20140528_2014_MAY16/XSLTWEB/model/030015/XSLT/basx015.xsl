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
		<input name="xh" type="hidden" id="xh">
			<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/qysdsjmba/xh" />
			</xsl:attribute>
		</input>
		<input name="tbblx" type="hidden" id="tbblx">
			<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/qysdsjmba/tbblx" />
			</xsl:attribute>
		</input>

		<table width="98%" border="0" cellspacing="0" class="table-99"
			id="TABLE_LIST1" style="display: block">

			<tr>
				<td width="50%" class="2-td2-t-left">
					<div align="left">备案申请文书号 &#160;</div>
				</td>
				<td width="50%" class="2-td2-t-center" height="21">
					<div align="left">
						<input type="text" name="basqbh1" maxlength="11"
							size="30" tabindex="-1" disabled="true">
							<xsl:attribute name="value">
								<xsl:value-of
									select="taxdoc/jmsbajl/basqbh" />
							</xsl:attribute>
						</input>
					</div>
				</td>
			</tr>

			<tr>
				<td class="2-td2-left" width="40%">
					<div align="left">
						固定资产名称
						<font color="#FF0000">*</font>
					</div>
				</td>
				<td colspan="3" class="2-td2-center">
					<div align="left">
						<input type="text" name="gdzcmc_sd"
							maxlength="200" size="15" value="" tabindex="1" >
							<xsl:attribute name="value">
								<xsl:value-of
									select="taxdoc/jmsbajl/qysdsjmba/gdzcmc_sd" />
							</xsl:attribute>
						</input>
					</div>
				</td>
			</tr>

			<tr>
				<td class="2-td2-left">
					<div align="left">
						固定资产原值
						<font color="#FF0000">*</font>
					</div>
				</td>
				<td colspan="3" class="2-td2-center">
					<div align="left">
						<input type="text" name="gdzcyz_sd"
							maxlength="18" size="15" tabindex="2" value=""
							onchange="return formatKsslJsje(this);">
							<xsl:attribute name="value">
								<xsl:value-of
									select="taxdoc/jmsbajl/qysdsjmba/gdzcyz_sd" />
							</xsl:attribute>
							元
						</input>
					</div>
				</td>
			</tr>
			<tr>
				<td class="2-td2-left">
					<div align="left">
						固定资产计税基础
						<font color="#FF0000">*</font>
					</div>
				</td>
				<td colspan="3" class="2-td2-center">
					<div align="left">
						<input type="text" name="gdzcjsjc_sd"
							maxlength="18" size="15" value=""
							onchange="return formatKsslJsje(this);" tabindex="3">
							<xsl:attribute name="value">
								<xsl:value-of
									select="taxdoc/jmsbajl/qysdsjmba/gdzcjsjc_sd" />
							</xsl:attribute>
							元
						</input>
					</div>
				</td>
			</tr>
			<tr>
				<td class="2-td2-left">
					<div align="left">
						税法规定的最低年限
						<font color="#FF0000">*</font>
					</div>
				</td>
				<td colspan="3" class="2-td2-center">
					<div align="left">
						<input type="text" name="sfgdzdnx_sd"
							maxlength="10" size="15"  value="" tabindex="4">
							<xsl:attribute name="value">
								<xsl:value-of
									select="taxdoc/jmsbajl/qysdsjmba/sfgdzdnx_sd" />
							</xsl:attribute>
							年
						</input>
					</div>
				</td>
			</tr>
			<tr>
				<td class="2-td2-left">
					<div align="left">
						加速折旧的最低年限
						<font color="#FF0000">*</font>
					</div>
				</td>
				<td colspan="3" class="2-td2-center">
					<div align="left">
						<input type="text" name="jszjzdnx_sd"
							maxlength="10" size="15"  value="" tabindex="5">
							<xsl:attribute name="value">
								<xsl:value-of
									select="taxdoc/jmsbajl/qysdsjmba/jszjzdnx_sd" />
							</xsl:attribute>
							年
						</input>
					</div>
				</td>
			</tr>
			<tr>
				<td class="2-td2-left">
					<div align="left">
						计提折旧起始年度
						<font color="#FF0000">*</font>
					</div>
				</td>
				<td class="2-td2-center">
					<div align="left">
						<input type="text" name="zjqsnd_sd"
							maxlength="4" size="15"  value="" tabindex="6">
							<xsl:attribute name="value">
								<xsl:value-of
									select="taxdoc/jmsbajl/qysdsjmba/zjqsnd_sd" />
							</xsl:attribute>
							年
						</input>
					</div>
				</td>
			</tr>
			<tr>
				<td class="2-td2-left">
					<div align="left">
						计提折旧截止年度
						<font color="#FF0000">*</font>
					</div>
				</td>
				<td class="2-td2-center">
					<div align="left">
						<input type="text" name="zjzznd_sd"
							maxlength="4" size="15" tabindex="7" value="">
							<xsl:attribute name="value">
								<xsl:value-of
									select="taxdoc/jmsbajl/qysdsjmba/zjzznd_sd" />
							</xsl:attribute>
							年
						</input>
					</div>
				</td>
			</tr>

			<tr>
				<td width="20%" class="2-td2-left">
					<div align="left">
						每年可扣除的折旧额
						<font color="#FF0000">*</font>
					</div>
				</td>
				<td colspan="3" class="2-td2-center">
					<div id="mx_zsfsdm_epx_visible_1" align="left">

						<input type="text" name="zje_sd" maxlength="18"
							size="15" tabindex="8" value=""
							onchange="return formatKsslJsje(this);">
							<xsl:attribute name="value">
								<xsl:value-of
									select="taxdoc/jmsbajl/qysdsjmba/zje_sd" />
							</xsl:attribute>
							元
						</input>
					</div>
				</td>
			</tr>

			<tr>
				<td width="20%" class="2-td2-left">
					<div align="left">
						已计提折旧的年限
						<font color="#FF0000">*</font>
					</div>
				</td>
				<td colspan="3" class="2-td2-center">
					<div id="mx_zsfsdm_epx_visible_1" align="left">

						<input type="text" name="yjtzjnx_sd"
							maxlength="10" size="15" tabindex="9" value="">
							<xsl:attribute name="value">
								<xsl:value-of
									select="taxdoc/jmsbajl/qysdsjmba/yjtzjnx_sd" />
							</xsl:attribute>
							年
						</input>
					</div>
				</td>
			</tr>
			<tr>
				<td width="20%" class="2-td2-left">
					<div align="left">
						已计提的折旧额
						<font color="#FF0000">*</font>
					</div>
				</td>
				<td colspan="3" class="2-td2-center">
					<div id="mx_zsfsdm_epx_visible_1" align="left">
						<input type="text" name="yjtzje_sd"
							maxlength="18" size="15" tabindex="10" value=""
							onchange="return formatKsslJsje(this);">
							<xsl:attribute name="value">
								<xsl:value-of
									select="taxdoc/jmsbajl/qysdsjmba/yjtzje_sd" />
							</xsl:attribute>
							元
						</input>
					</div>
				</td>
			</tr>
		</table>

		<table width="98%" border="0" cellspacing="0" class="table-99"
			id="TABLE_LIST2" style="display: none">
			<tr>
				<td class="2-td2-t-left">
					<div align="left">
						备案申请文书号
						
					</div>
				</td>
				<td colspan="3" class="2-td2-t-center">
					<div id="mx_zsfsdm_epx_visible_1" align="left">
						<input name="basqbh" id="basqbh" tabindex="1"
							type="text" style="text-align:right" value="" size="30"
							maxlength="15" disabled="true" >
							<xsl:attribute name="value">
								<xsl:value-of
									select="taxdoc/jmsbajl/basqbh" />
							</xsl:attribute>
						</input>
					</div>
				</td>
			</tr>
			<tr>
				<td class="2-td2-left" width="40%">
					<div align="left">
						固定资产名称
						<font color="#FF0000">*</font>
					</div>
				</td>
				<td colspan="3" class="2-td2-center">
					<div align="left">
						<input type="text" name="gdzcmc_js"
							maxlength="200" size="15" tabindex="1" value="">
							<xsl:attribute name="value">
								<xsl:value-of
									select="taxdoc/jmsbajl/qysdsjmba/gdzcmc_js" />
							</xsl:attribute>
						</input>
					</div>
				</td>
			</tr>
			<tr>
				<td class="2-td2-left">
					<div align="left">
						固定资产原值
						<font color="#FF0000">*</font>
					</div>
				</td>
				<td colspan="3" class="2-td2-center">
					<div align="left">
						<input type="text" name="gdzcyz_js"
							maxlength="18" size="15" tabindex="2" value=""
							onchange="return formatKsslJsje(this);">
							<xsl:attribute name="value">
								<xsl:value-of
									select="taxdoc/jmsbajl/qysdsjmba/gdzcyz_js" />
							</xsl:attribute>
							元
						</input>
					</div>
				</td>
			</tr>
			<tr>
				<td class="2-td2-left">
					<div align="left">
						固定资产计税基础
						<font color="#FF0000">*</font>
					</div>
				</td>
				<td colspan="3" class="2-td2-center">
					<div align="left">
						<input type="text" name="gdzcjsjc_js"
							maxlength="18" size="15" tabindex="3" value=""
							onchange="return formatKsslJsje(this);">
							<xsl:attribute name="value">
								<xsl:value-of
									select="taxdoc/jmsbajl/qysdsjmba/gdzcjsjc_js" />
							</xsl:attribute>
							元
						</input>
					</div>
				</td>
			</tr>
			<tr>
				<td class="2-td2-left">
					<div align="left">
						计算折旧的方法
						<font color="#FF0000">*</font>
					</div>
				</td>
				<td colspan="3" class="2-td2-center">
					<div align="left">
						<xsl:choose>
							<xsl:when
								test="taxdoc/jmsbajl/qysdsjmba/jszjffdm_js = '0'">
								<select name="jszjffdm_js"
									style="width:130px">
									<option value="0"
										selected='selected' tabindex="4">
										双倍余额递减法
									</option>
									<option value="1">年数总额法</option>
								</select>
							</xsl:when>
							<xsl:otherwise>
								<select name="jszjffdm_js"
									style="width:130px">
									<option value="0">双倍余额递减法</option>
									<option value="1"
										selected='selected'>
										年数总额法
									</option>
								</select>
							</xsl:otherwise>
						</xsl:choose>
					</div>
				</td>
			</tr>
			<tr>
				<td class="2-td2-left">
					<div align="left">
						年折旧额
						<font color="#FF0000">*</font>
					</div>
				</td>
				<td colspan="3" class="2-td2-center">
					<div align="left">
						<input type="text" name="zje_js" maxlength="18"
							size="15" tabindex="5" value=""
							onchange="return formatKsslJsje(this);">
							<xsl:attribute name="value">
								<xsl:value-of
									select="taxdoc/jmsbajl/qysdsjmba/zje_js" />
							</xsl:attribute>
							元
						</input>
					</div>
				</td>
			</tr>
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
