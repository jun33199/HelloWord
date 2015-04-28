<?xml version="1.0" encoding="GB2312"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
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
		<input name="jmbasxdm" type="hidden" id="jmbasxdm">
			<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/jmbasxdm" />
			</xsl:attribute>
		</input>
		<input name="jmbasxmc" type="hidden" id="jmbasxmc">
			<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/jmbasxmc" />
			</xsl:attribute>
		</input>
		
		<input name="fhwjmc" type="hidden" id="fhwjmc">
		<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/fhwjmc" />
			</xsl:attribute>
		</input>
		<input name="qsrq" type="hidden" id="qsrq">
		<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/qsrq" />
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
		<input name="jzrq" type="hidden" id="jzrq">
		<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/jzrq" />
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
		<input name="band" type="hidden" id="band">
			<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/band" />
			</xsl:attribute>
		</input>
		<br />
		<table class="table-99" cellspacing="0">
		<tr>
                      <td width="50%" class="2-td2-t-left" colspan="1">
                        <div align="left">
                          备案申请文书号
                          &#160;
                        </div>
                      </td>
                      <td width="50%" class="2-td2-t-center" height="21"  >
                        <div align="left">
                          <input type="text" name="basqbh1" maxlength="11" size="30" tabindex="-1"
                           disabled="true">
                             		<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/basqbh" />
			</xsl:attribute></input>
                        </div>
                      </td>
                    </tr>
			<tr>
				<td width="20%" class="2-td2-left">
					<div align="left">技术转让类型代码<font color="#FF0000">*</font></div>
				</td>
				<td colspan="3" class="2-td2-center">
					<div id="jszrlxdiv" align="left">
					
					</div>
				</td>
			</tr>
			<tr>
				<td width="20%" class="2-td2-left">
					<div align="left">技术转让区域<font color="#FF0000">*</font></div>
				</td>
				<td colspan="3" class="2-td2-center">
					<div id="mx_zsfsdm_epx_visible_1" align="left">
						<xsl:choose>
							<xsl:when
								test="taxdoc/jmsbajl/qysdsjmba/jnjwbs = '1'">
								<input type="radio" name="jnjwbs"
									value="1" checked="true">
									<span id="zsfsdm_01">境外技术转让</span>
								</input>
								<input type="radio" name="jnjwbs"
									value="0" />
								<span id="zsfsdm_02">境内技术转让</span>
							</xsl:when>
							<xsl:otherwise>
								<input type="radio" name="jnjwbs"
									value="1" />
								<span id="zsfsdm_01">境外技术转让</span>
								<input type="radio" name="jnjwbs"
									value="0" checked="true" />
								<span id="zsfsdm_02">境内技术转让</span>
							</xsl:otherwise>
						</xsl:choose>
					</div>
				</td>
			</tr>
			
			
			
			
			<tr>
				<td width="20%" class="2-td2-left">
					<div align="left">取得技术转让所得<font color="#FF0000">*</font></div>
				</td>
				<td colspan="3" class="2-td2-center">
					<div id="mx_zsfsdm_epx_visible_1" align="left">
						<input type="text" name="jszrsd" size="20"
							tabindex="-1">
							 <xsl:attribute name="value">
							<xsl:value-of
								select="taxdoc/jmsbajl/qysdsjmba/jszrsd" />
								</xsl:attribute>
						</input>
						元
						
					</div>
				</td>
			</tr>
			<tr>
				<td width="20%" class="2-td2-left">
					<div align="left">经科学技术行政部门认定登记的技术转让合同名称<font color="#FF0000">*</font></div>
				</td>
				<td colspan="3" class="2-td2-center">
					<div id="mx_zsfsdm_epx_visible_1" align="left">
						<input type="text" name="jszrhtmc" size="20"
							tabindex="-1">
							 <xsl:attribute name="value">
							<xsl:value-of
								select="taxdoc/jmsbajl/qysdsjmba/jszrhtmc" />
								</xsl:attribute>
						</input>
						
					</div>
				</td>
			</tr>
		</table>
		<table class="table-99" cellspacing="0" id="FhtjjzzrsdTable">

			<tr>
			<xsl:variable name="rq" select="taxdoc/jmsbajl/lrrq"/>
			       <xsl:variable name="nd" select="taxdoc/jmsbajl/band"/>
			       <xsl:variable name="rr" select="taxdoc/nsrxx/nsrmc"/>
				<td width="10%" class="2-td2-left">
					<div align="right">录入日期</div>
				</td>
				<td width="30%" class="2-td2-left">
					<div align="left">
						<input type="text" name="lrrq" size="20"
							tabindex="-1" readonly="readonly" class="txtInput" value='{$rq}'>						
						</input>
					</div>
				</td>
				<td width="10%" class="2-td2-left">
					<div align="right">备案年度</div>
				</td>
				<td width="30%" class="2-td2-left">
					<div align="left">
						<input type="text" name="band" size="20"
							tabindex="-1" readonly="readonly" class="txtInput" value='{$nd}'>			
						</input>

					</div>
				</td>
				<td width="10%" class="2-td2-left">
					<div align="right">录入人</div>
				</td>
				<td width="30%" class="2-td2-center">
					<div align="left">
						<input type="text" name="lrr" size="20"
							tabindex="-1"  readonly="readonly" class="txtInput" value='{$rr}'>
						</input>

					</div>
				</td>
			</tr>

		</table>

	</xsl:template>
</xsl:stylesheet>
