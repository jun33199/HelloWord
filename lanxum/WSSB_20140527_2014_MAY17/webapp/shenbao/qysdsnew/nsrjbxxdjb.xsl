<?xml version="1.0" encoding="gb2312"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" />
	<xsl:template match="/">
		<table width="96%" align="center" cellspacing="0" class="table-99">
			<tr>
				<td>
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
					
					<input name="jsjdm" type="hidden" id="jsjdm">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrjbxx/jsjdm" />
						</xsl:attribute>
					</input>
					<input name="nsrsbh" type="hidden" id="nsrsbh">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrjbxx/nsrsbh" />
						</xsl:attribute>
					</input>
					<input name="nsrmc" type="hidden" id="nsrmc">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrjbxx/nsrmc" />
						</xsl:attribute>
					</input>
					<input name="sbnd" type="hidden" id="sbnd">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrjbxx/sbnd" />
						</xsl:attribute>
					</input>
					<input name="sknd" type="hidden" id="sknd">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrjbxx/sknd" />
						</xsl:attribute>
					</input>
					<input name="ssjjlxdm" type="hidden" id="ssjjlxdm">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrjbxx/ssjjlxdm" />
						</xsl:attribute>
					</input>
					<input name="ssjjlxmc" type="hidden" id="ssjjlxmc">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrjbxx/ssjjlxmc" />
						</xsl:attribute>
					</input>
					<input name="jydz" type="hidden" id="jydz">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrjbxx/jydz" />
						</xsl:attribute>
					</input>
					<input name="sshydm" type="hidden" id="sshydm">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrjbxx/sshydm" />
						</xsl:attribute>
					</input>
					<input name="sshymc" type="hidden" id="sshymc">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrjbxx/sshymc" />
						</xsl:attribute>
					</input>
					<input name="zsfsdm" type="hidden" id="zsfsdm">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrjbxx/zsfsdm" />
						</xsl:attribute>
					</input>
					<input name="zsfsmc" type="hidden" id="zsfsmc">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrjbxx/zsfsmc" />
						</xsl:attribute>
					</input>
					<input name="cwkjzddm" type="hidden" id="cwkjzddm">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrjbxx/cwkjzddm" />
						</xsl:attribute>
					</input>
					<input name="cwkjzdmc" type="hidden" id="cwkjzdmc">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrjbxx/cwkjzdmc" />
						</xsl:attribute>
					</input>
					<input name="cwkjzddm_old" type="hidden" id="cwkjzddm_old">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrjbxx/cwkjzddm_old" />
						</xsl:attribute>
					</input>
					<input name="gzglxsdm" type="hidden" id="gzglxsdm">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrjbxx/gzglxsdm" />
						</xsl:attribute>
					</input>
					<input name="gzglxsmc" type="hidden" id="gzglxsmc">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrjbxx/gzglxsmc" />
						</xsl:attribute>
					</input>
					<input name="gzglxsdm_old" type="hidden" id="gzglxsdm_old">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrjbxx/gzglxsdm_old" />
						</xsl:attribute>
					</input>
					<input name="jmlxdm" type="hidden" id="jmlxdm">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrjbxx/jmlxdm" />
						</xsl:attribute>
					</input>
					<input name="jmlxmc" type="hidden" id="jmlxmc">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrjbxx/jmlxmc" />
						</xsl:attribute>
					</input>
					<input name="jmlxdm_old" type="hidden" id="jmlxdm_old">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrjbxx/jmlxdm_old" />
						</xsl:attribute>
					</input>
					<input name="swjgzzjgdm" type="hidden" id="swjgzzjgdm">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrjbxx/swjgzzjgdm" />
						</xsl:attribute>
					</input>
					<input name="swjgzzjgmc" type="hidden" id="swjgzzjgmc">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrjbxx/swjgzzjgmc" />
						</xsl:attribute>
					</input>
					<input name="cjr" type="hidden" id="cjr">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrjbxx/cjr" />
						</xsl:attribute>
					</input>
					<input name="cjrq" type="hidden" id="cjrq">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrjbxx/cjrq" />
						</xsl:attribute>
					</input>
					<input name="lrr" type="hidden" id="lrr">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrjbxx/lrr" />
						</xsl:attribute>
					</input>
					<input name="lrrq" type="hidden" id="lrrq">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrjbxx/lrrq" />
						</xsl:attribute>
					</input>
					<input name="sbrq" type="hidden" id="sbrq">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrjbxx/sbrq" />
						</xsl:attribute>
					</input>
					<input name="xtjb" type="hidden" id="xtjb">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrjbxx/xtjb" />
						</xsl:attribute>
					</input>
					<input name="bbmsf" type="hidden" id="bbmsf">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrjbxx/bbmsf" />
						</xsl:attribute>
					</input>
					<input name="skssksrq" type="hidden" id="skssksrq">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrjbxx/skssksrq" />
						</xsl:attribute>
					</input>
					<input name="skssjsrq" type="hidden" id="skssjsrq">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrjbxx/skssjsrq" />
						</xsl:attribute>
					</input>
					<input name="version" type="hidden" id="version">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrjbxx/version" />
						</xsl:attribute>
					</input>
					<input name="sqspbh" type="hidden" id="sqspbh">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrjbxx/sqspbh" />
						</xsl:attribute>
					</input>
				</td>
			</tr>
			<tr>
				<td class="1-td1" colspan="7">纳税人基本信息登记表</td>
			</tr>
			<xsl:apply-templates select="taxdoc/nsrjbxx" />
		</table>
		<br />
	</xsl:template>
	<xsl:template match="taxdoc/nsrjbxx">
		<tr>
			<td class="1-td2" colspan="7">
							<br/>
				<table class="table-99" align="center">
					<tr>
						<td>
							<div id="Layer2" style="position:static;">
								<table id="wrklistTable" border="1" cellspacing="0" class="table-99" align="center">
									<tr>
										<td class="2-td2-t-left" nowrap="nowrap">计算机代码</td>
										<td class="2-td2-t-left" nowrap="nowrap">
											<xsl:value-of select="jsjdm" />
										</td>
										<td class="2-td2-t-left" nowrap="nowrap">纳税人名称</td>
										<td class="2-td2-t-center" nowrap="nowrap">
											<xsl:value-of select="nsrmc" />
										</td>
									</tr>
									<tr>
										<td class="2-td2-left" nowrap="nowrap">所属经济类型</td>
										<td class="2-td2-left" nowrap="nowrap">
											<xsl:value-of select="ssjjlxmc" />
										</td>
										<td class="2-td2-left" nowrap="nowrap">联系电话</td>
										<td class="2-td2-center" nowrap="nowrap">
											<input type='text' name='lxdh' id='lxdh' value='lxdh' size='17' maxlength='16' tabindex='2'>
												<xsl:attribute name="value">
													<xsl:value-of select="lxdh" />
												</xsl:attribute>
											</input>
										</td>
									</tr>
									<tr>
										<td class="2-td2-left" nowrap="nowrap">所属行业</td>
										<td class="2-td2-left" nowrap="nowrap">
											<xsl:value-of select="sshymc" />
										</td>
										<td class="2-td2-left" nowrap="nowrap">企业所得税征收方式</td>
										<td class="2-td2-center" nowrap="nowrap">
											<xsl:value-of select="zsfsmc" />
										</td>
									</tr>
									<tr>
										<td class="2-td2-left" nowrap="nowrap">企业类别（必选）</td>
										<td colspan="3" class="2-td2-center" nowrap="nowrap">
											单项选择：
											<br />
												<input type="Radio" name="cwkjzddm_show" id="cwkjzddm12" value="12"/>一般企业；
												<input type="Radio" name="cwkjzddm_show" id="cwkjzddm14" value="14"/>金融企业；
												<input type="Radio" name="cwkjzddm_show" id="cwkjzddm15" value="15"/>事业单位、社会团体、民办非企业单位（对报表有控制）
										</td>
									</tr>
									
									
														<tr>
										<td class="2-td2-left" nowrap="nowrap">注册资本(万元，必填项)</td>
										<td class="2-td2-left" nowrap="nowrap">
													
											<input type='text' name='zczbje' id='zczbje' value='zczbje' size='13' maxlength='16' tabindex='2' onblur ='return formatData10(this,1);'>
						<xsl:attribute name="value">
							<xsl:value-of select="zczbje" />
						</xsl:attribute>
					</input>

										</td>
										<td class="2-td2-left" nowrap="nowrap">资产总额(万元，必填项)</td>
										<td class="2-td2-center" nowrap="nowrap">
											<input type='text' name='zcze' id='zcze' value='zcze' size='13' maxlength='16' tabindex='2' onblur ='return formatData10(this,0);'>
												<xsl:attribute name="value">
													<xsl:value-of select="zcze" />
												</xsl:attribute>
											</input>
										</td>
									</tr>
									
									
					<input name="gzglxsdm_show" type="hidden" id="gzglxsdm_show">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrjbxx/gzglxsdm" />
						</xsl:attribute>
					</input>
									
					<input name="jmlxdm_show" type="hidden" id="jmlxdm_show">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrjbxx/jmlxdm" />
						</xsl:attribute>
					</input>
					
					
									
								</table>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</xsl:template>
</xsl:stylesheet>
