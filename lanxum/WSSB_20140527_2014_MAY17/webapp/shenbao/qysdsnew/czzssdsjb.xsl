<?xml version="1.0" encoding="gb2312"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" />
	<xsl:template match="/">

		<table id="maintable" align="center" cellspacing="0" class="table-99">
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


					<input name="jmzg" type="hidden" id="jmzg">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/hdxx/jmzg" />
						</xsl:attribute>
					</input>
					<input name="ybjmsl" type="hidden" id="ybjmsl">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/hdxx/ybjmsl" />
						</xsl:attribute>
					</input>
					<input name="qyzslx" type="hidden" id="qyzslx">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/hdxx/qyzslx" />
						</xsl:attribute>
					</input>
					<input name="xzqy" type="hidden" id="xzqy">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/hdxx/xzqy" />
						</xsl:attribute>
					</input>
					<input name="cyl" type="hidden" id="cyl">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/hdxx/cyl" />
						</xsl:attribute>
					</input>
					<input name="dezsse" type="hidden" id="dezsse">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/hdxx/dezsse" />
						</xsl:attribute>
					</input>


					<input name="nd" type="hidden" id="nd">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/sbxx/nd" />
						</xsl:attribute>
					</input>
					<input name="sbrq" type="hidden" id="sbrq">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/sbxx/sbrq" />
						</xsl:attribute>
					</input>
					<input name="skssjsrq" type="hidden" id="skssjsrq">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/sbxx/skssjsrq" />
						</xsl:attribute>
					</input>
					<input name="skssksrq" type="hidden" id="skssksrq">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/sbxx/skssksrq" />
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
				</td>
			</tr>
			<tr>
				<td class="1-td1">查帐征收企业所得税季度纳税申报表</td>
			</tr>
			<xsl:apply-templates select="taxdoc/nsrxx" />
			<xsl:apply-templates select="taxdoc/sbsj" />
		</table>
		<br />
	</xsl:template>
	<xsl:template match="taxdoc/nsrxx">

		<tr>
			<td class="1-td2" colspan="7">
				<div align="left">
					&#160;&#160;&#160;&#160;申报日期:
					<xsl:value-of select="//sbrqshow" />
					&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;金额单位：元（列至角分）
				</div>
			</td>
		</tr>
		<tr>
			<td class="1-td2" colspan="7">
				<div align="left">
					&#160;&#160;&#160;&#160;计算机代码:
					<xsl:value-of select="jsjdm" />
					&#160;&#160;&#160;&#160;纳税人名称：
					<xsl:value-of select="nsrmc" />
					&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;税款所属日期：
					<xsl:value-of select="//skssksrq" />
					&#160;至&#160;
					<xsl:value-of select="//skssjsrq" />
				</div>
			</td>
		</tr>
	</xsl:template>


	<xsl:template match="taxdoc/sbsj">
		<tr>
			<td class="1-td2" colspan="7">
				<TABLE class="table-99" align="center">
					<TR>
						<TD>
							<table id="wrklistTable" border="1" cellspacing="0" class="table-99" align="center">
								<tr>
									<td class="2-td1-left" nowrap="nowrap">行次</td>
									<td class="2-td1-left" nowrap="nowrap">项目</td>
									<td class="2-td1-center" nowrap="nowrap">累计金额</td>
								</tr>
								<tr>
									<td class="2-td2-left" nowrap="nowrap">1</td>
									<td class="2-td2-left" nowrap="nowrap">利润总额</td>
									<td class="2-td2-center" nowrap="nowrap">
										<input type='text' name='lrze' id='lrze' size='13' tabindex='2' onchange="return formatData(this);">
											<xsl:attribute name="value">
												<xsl:value-of select="lrze" />
											</xsl:attribute>
										</input>
									</td>
								</tr>
								<tr>
									<td class="2-td2-left" nowrap="nowrap">2</td>
									<td class="2-td2-left" nowrap="nowrap">加：纳税调整增加额</td>
									<td class="2-td2-center" nowrap="nowrap">
										<input type='text' name='nstzzje' id='nstzzje' size='13' tabindex='2' onchange="return formatData(this);">
											<xsl:attribute name="value">
												<xsl:value-of select="nstzzje" />
											</xsl:attribute>
										</input>
									</td>
								</tr>

								<tr>
									<td class="2-td2-left" nowrap="nowrap">3</td>
									<td class="2-td2-left" nowrap="nowrap">减：纳税调整减少额</td>
									<td class="2-td2-center" nowrap="nowrap">
										<input type='text' name='nstzjse' id='nstzjse' size='13' tabindex='2' onchange="return formatData(this);">
											<xsl:attribute name="value">
												<xsl:value-of select="nstzjse" />
											</xsl:attribute>
										</input>
									</td>
								</tr>
								<tr>
									<td class="2-td2-left" nowrap="nowrap">4</td>
									<td class="2-td2-left" nowrap="nowrap">减：弥补以前年度亏损</td>
									<td class="2-td2-center" nowrap="nowrap">
										<input type='text' name='mbyqndks' id='mbyqndks' size='13' tabindex='2' onchange="return formatData(this);">
											<xsl:attribute name="value">
												<xsl:value-of select="mbyqndks" />
											</xsl:attribute>
										</input>
									</td>
								</tr>
								<tr>
									<td class="2-td2-left" nowrap="nowrap">5</td>
									<td class="2-td2-left" nowrap="nowrap">应纳税所得额（1+2-3-4）</td>
									<td class="2-td2-center" nowrap="nowrap">
										<input type='text' name='ynssde' id='ynssde' size='13' tabindex='2' onchange="return formatData2(this);">
											<xsl:attribute name="value">
												<xsl:value-of select="ynssde" />
											</xsl:attribute>
										</input>
									</td>
								</tr>
								<tr>
									<td class="2-td2-left" nowrap="nowrap">6</td>
									<td class="2-td2-left" nowrap="nowrap">适用税率</td>
									<td class="2-td2-center" nowrap="nowrap">
										<input type='text' name='sysl' id='sysl' size='13' tabindex='2' onchange="return formatData(this);">
											<xsl:attribute name="value">
												<xsl:value-of select="sysl" />
											</xsl:attribute>
										</input>
									</td>
								</tr>
								<tr>
									<td class="2-td2-left" nowrap="nowrap">7</td>
									<td class="2-td2-left" nowrap="nowrap">应纳所得税额（5×6 ）</td>
									<td class="2-td2-center" nowrap="nowrap">
										<input type='text' name='ynsdse' id='ynsdse' size='13' tabindex='2' onchange="return formatData(this);">
											<xsl:attribute name="value">
												<xsl:value-of select="ynsdse" />
											</xsl:attribute>
										</input>
									</td>
								</tr>
								<tr>
									<td class="2-td2-left" nowrap="nowrap">8</td>
									<td class="2-td2-left" nowrap="nowrap">减免所得税额</td>
									<td class="2-td2-center" nowrap="nowrap">
										<input type='text' name='jmsdse' id='jmsdse' size='13' tabindex='2' onchange="return formatData(this);">
											<xsl:attribute name="value">
												<xsl:value-of select="jmsdse" />
											</xsl:attribute>
										</input>
									</td>
								</tr>
								<tr>
									<td class="2-td2-left" nowrap="nowrap">9</td>
									<td class="2-td2-left" nowrap="nowrap">汇总纳税成员企业就地预缴比例</td>
									<td class="2-td2-center" nowrap="nowrap">
										<input type='text' name='hznscyqyjdyjbl' id='hznscyqyjdyjbl' size='13' tabindex='2' onchange="return formatData(this);">
											<xsl:attribute name="value">
												<xsl:value-of select="hznscyqyjdyjbl" />
											</xsl:attribute>
										</input>
									</td>
								</tr>
								<tr>
									<td class="2-td2-left" nowrap="nowrap">10</td>
									<td class="2-td2-left" nowrap="nowrap">实际已预缴的所得税额</td>
									<td class="2-td2-center" nowrap="nowrap">
										<input type='text' name='sjyyjdsdse' id='sjyyjdsdse' size='13' tabindex='2' onchange="return formatData(this);">
											<xsl:attribute name="value">
												<xsl:value-of select="sjyyjdsdse" />
											</xsl:attribute>
										</input>
									</td>
								</tr>
								<tr>
									<td class="2-td2-left" nowrap="nowrap">11</td>
									<td class="2-td2-left" nowrap="nowrap">应补（退）的所得税额[（7-8-10 ）或 （7-8）×9-10]</td>
									<td class="2-td2-center" nowrap="nowrap">
										<input type='text' name='ybdsdse' id='ybdsdse' size='13' tabindex='2' onchange="return formatData(this);">
											<xsl:attribute name="value">
												<xsl:value-of select="ybdsdse" />
											</xsl:attribute>
										</input>
									</td>
								</tr>
								<tr>
									<td class="2-td2-left" nowrap="nowrap">12</td>
									<td class="2-td2-left" nowrap="nowrap">上一年度实际缴纳的企业所得税额</td>
									<td class="2-td2-center" nowrap="nowrap">*</td>
								</tr>
								<tr>
									<td class="2-td2-left" nowrap="nowrap">13</td>
									<td class="2-td2-left" nowrap="nowrap">本季（月）应预缴所得税额（12行÷4或12行÷12）</td>
									<td class="2-td2-center" nowrap="nowrap">*</td>
								</tr>
								<tr>
									<td class="2-td2-left" nowrap="nowrap">14</td>
									<td class="2-td2-left" nowrap="nowrap">本年实际已预缴的所得税额</td>
									<td class="2-td2-center" nowrap="nowrap">*</td>
								</tr>

							</table>
						</TD>
					</TR>

				</TABLE>
			</td>
		</tr>
	</xsl:template>
</xsl:stylesheet>
