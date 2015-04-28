<?xml version="1.0" encoding="GB2312"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:variable name="varStep" select="1"/>
	<xsl:template match="taxdoc">
		<input name="xsltVersion" type="hidden" id="xsltVersion">
			<xsl:attribute name="value"><xsl:value-of select="xsltVersion"/></xsl:attribute>
		</input>
		<input name="schemaVersion" type="hidden" id="schemaVersion">
			<xsl:attribute name="value"><xsl:value-of select="schemaVersion"/></xsl:attribute>
		</input>
		<input name="ywlx" type="hidden" id="ywlx">
			<xsl:attribute name="value"><xsl:value-of select="ywlx"/></xsl:attribute>
		</input>
		<input name="ywczlx" type="hidden" id="ywczlx">
			<xsl:attribute name="value"><xsl:value-of select="ywczlx"/></xsl:attribute>
		</input>
		<xsl:apply-templates select="sbxx"/>
		<xsl:apply-templates select="nsrxx"/>
		<xsl:apply-templates select="czzbsj"/>
		<table id="tzzTable" width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td nowrap="nowrap" class="2-td1-left">选择</td>
				<td colspan="5" class="2-td1-left">投资者姓名</td>
				<td colspan="4" class="2-td1-center">投资者身份证件号码</td>
			</tr>
			<xsl:apply-templates select="tzfsj"/>
			<input name="jmsjControl" type="hidden" id="jmsjControl">
				<xsl:attribute name="value"><xsl:value-of select="0"/></xsl:attribute>
			</input>
		</table>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" height="5">
			<tr>
				<td/>
			</tr>
		</table>
		<xsl:call-template name="cztable"/>
	</xsl:template>
	<xsl:template name="cztable">
		<table width="100%" cellspacing="0" class="table-99">
			<TR>
				<td valign="top" width="33%">
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td height="25" align="center" nowrap="nowrap" class="2-td1-left">项目</td>
							<td height="25" align="center" nowrap="nowrap" class="2-td1-left">行次</td>
							<td height="25" align="center" nowrap="nowrap" class="2-td1-center">本期累计数</td>
						</tr>
						<xsl:call-template name="colwsbxm">
							<xsl:with-param name="startCol1" select="1"/>
							<xsl:with-param name="endCol1" select="4"/>
							<xsl:with-param name="thename" select="'bl1'"/>
						</xsl:call-template>
						<xsl:call-template name="colrsbxm">
							<xsl:with-param name="startCol1" select="5"/>
							<xsl:with-param name="endCol1" select="7"/>
							<xsl:with-param name="thename" select="'bl1'"/>
						</xsl:call-template>
						<xsl:call-template name="colwsbxm">
							<xsl:with-param name="startCol1" select="8"/>
							<xsl:with-param name="endCol1" select="18"/>
							<xsl:with-param name="thename" select="'bl1'"/>
						</xsl:call-template>
					</table>
				</td>
				<td valign="top" width="33%">
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td height="25" align="center" nowrap="nowrap" class="2-td1-left">项目</td>
							<td height="25" align="center" nowrap="nowrap" class="2-td1-left">行次</td>
							<td height="25" align="center" nowrap="nowrap" class="2-td1-center">本期累计数</td>
						</tr>
						<xsl:call-template name="colrsbxm">
							<xsl:with-param name="startCol1" select="19"/>
							<xsl:with-param name="endCol1" select="19"/>
							<xsl:with-param name="thename" select="'bl2'"/>
						</xsl:call-template>
						<xsl:call-template name="colwsbxm">
							<xsl:with-param name="startCol1" select="20"/>
							<xsl:with-param name="endCol1" select="29"/>
							<xsl:with-param name="thename" select="'bl2'"/>
						</xsl:call-template>
						<xsl:call-template name="colrsbxm">
							<xsl:with-param name="startCol1" select="30"/>
							<xsl:with-param name="endCol1" select="30"/>
							<xsl:with-param name="thename" select="'bl2'"/>
						</xsl:call-template>
						<xsl:call-template name="colwsbxm">
							<xsl:with-param name="startCol1" select="31"/>
							<xsl:with-param name="endCol1" select="32"/>
							<xsl:with-param name="thename" select="'bl2'"/>
						</xsl:call-template>
						<xsl:call-template name="colrsbxm">
							<xsl:with-param name="startCol1" select="33"/>
							<xsl:with-param name="endCol1" select="33"/>
							<xsl:with-param name="thename" select="'bl2'"/>
						</xsl:call-template>
						<xsl:call-template name="colwsbxm">
							<xsl:with-param name="startCol1" select="34"/>
							<xsl:with-param name="endCol1" select="36"/>
							<xsl:with-param name="thename" select="'bl2'"/>
						</xsl:call-template>
					</table>
				</td>
				<td valign="top" width="35%">
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td height="25" align="center" nowrap="nowrap" class="2-td1-left">项目</td>
							<td height="25" align="center" nowrap="nowrap" class="2-td1-left">行次</td>
							<td height="25" align="center" nowrap="nowrap" class="2-td1-center">本期累计数</td>
						</tr>
						<xsl:call-template name="colwsbxm">
							<xsl:with-param name="startCol1" select="37"/>
							<xsl:with-param name="endCol1" select="37"/>
							<xsl:with-param name="thename" select="'bl3'"/>
						</xsl:call-template>
						<xsl:call-template name="colrsbxm">
							<xsl:with-param name="startCol1" select="38"/>
							<xsl:with-param name="endCol1" select="38"/>
							<xsl:with-param name="thename" select="'bl3'"/>
						</xsl:call-template>
						<xsl:for-each select="/taxdoc/tzfsj">
							<xsl:variable name="tzfPosition" select="position()"/>
							<xsl:for-each select="tzfmx">
								<xsl:variable name="mxPosition" select="position()"/>
								<xsl:if test="$tzfPosition = 1">
									<tr>
										<td height="25" align="center" class="2-td2-left">
											<div align="left">
												<xsl:value-of select="xmmc"/>
											</div>
										</td>
										<td height="25" align="center" class="2-td2-left">
											<xsl:value-of select="hc"/>
										</td>
										<td height="25" align="center" class="2-td2-center">
											<xsl:if test="$mxPosition &lt;= 4">
												<div align="right">
													<xsl:attribute name="id">div<xsl:value-of select="($mxPosition+38)"/></xsl:attribute>
<xsl:value-of select="/taxdoc/tzfsj/tzfmx[$mxPosition]/bqljs"/>
</div>
											</xsl:if>

											<xsl:if test="$mxPosition = 5">
												<input name="dataInput43" type="text" id="dataInput43" maxlength="16" size="12">
													<xsl:attribute name="value"><xsl:value-of select="/taxdoc/tzfsj/tzfmx[$mxPosition]/bqljs"/></xsl:attribute>
	</input>
											</xsl:if>
											<xsl:if test="$mxPosition &gt;= 6">
												<input name="dataInput" type="text" id="dataInput" maxlength="16" size="12">
													<xsl:attribute name="value"><xsl:value-of select="/taxdoc/tzfsj/tzfmx[$mxPosition]/bqljs"/></xsl:attribute>
	</input>
											</xsl:if>
										</td>
									</tr>
								</xsl:if>
							</xsl:for-each>
						</xsl:for-each>
						<xsl:apply-templates select="/taxdoc/tzfsj/tzfmx[1]"/>
						<xsl:apply-templates select="/taxdoc/tzfsj/tzfmx[2]"/>
						<xsl:apply-templates select="/taxdoc/tzfsj/tzfmx[3]"/>
						<xsl:apply-templates select="/taxdoc/tzfsj/tzfmx[4]"/>
						<xsl:apply-templates select="/taxdoc/tzfsj/tzfmx[5]"/>
						<xsl:apply-templates select="/taxdoc/tzfsj/tzfmx[6]"/>
						<xsl:apply-templates select="/taxdoc/tzfsj/tzfmx[7]"/>
						<xsl:apply-templates select="/taxdoc/tzfsj/tzfmx[8]"/>
						<xsl:apply-templates select="/taxdoc/tzfsj/tzfmx[9]"/>
						<xsl:apply-templates select="/taxdoc/tzfsj/tzfmx[10]"/>
						<xsl:call-template name="colwsbxm">
							<xsl:with-param name="startCol1" select="39"/>
							<xsl:with-param name="endCol1" select="40"/>
							<xsl:with-param name="thename" select="'bl3'"/>
						</xsl:call-template>
						<tr>
							<td height="50" align="center" nowrap="nowrap" class="2-td2-left">
								<div align="left">　　3.从其他企业取得的生产经营所得
                      </div>
								<div align="left">
                      　　(1) 分配比例
                      <input type="text" maxlength="16" size="8" name="bl5">
										<xsl:attribute name="value"><xsl:value-of select="/taxdoc/sbsjlist/fpblsj[1]/fpbl"/></xsl:attribute>
									</input>%
			</div>
							</td>
							<td height="50" align="center" class="2-td2-left">51</td>
							<td height="50" align="center" class="2-td2-center">
								<input type="text" maxlength="16" size="12" name="bl6">
									<xsl:attribute name="value"><xsl:value-of select="/taxdoc/sbsjlist/fpblsj[1]/bqljs"/></xsl:attribute>
								</input>
							</td>
						</tr>
						<tr>
							<td height="25" align="center" nowrap="nowrap" class="2-td2-left">
								<div align="left">　　(2)
                      分配比例
                      <input type="text" maxlength="16" size="8" name="bl5">
										<xsl:attribute name="value"><xsl:value-of select="/taxdoc/sbsjlist/fpblsj[2]/fpbl"/></xsl:attribute>
									</input>%</div>
							</td>
							<td height="25" align="center" class="2-td2-left">52</td>
							<td height="25" align="center" class="2-td2-center">
								<input type="text" maxlength="16" size="12" name="bl6">
									<xsl:attribute name="value"><xsl:value-of select="/taxdoc/sbsjlist/fpblsj[2]/bqljs"/></xsl:attribute>
								</input>
							</td>
						</tr>
						<tr>
							<td height="25" align="center" nowrap="nowrap" class="2-td2-left">
								<div align="left">　　(3)
                      分配比例
                       <input type="text" maxlength="16" size="8" name="bl5">
										<xsl:attribute name="value"><xsl:value-of select="/taxdoc/sbsjlist/fpblsj[3]/fpbl"/></xsl:attribute>
									</input>%</div>
							</td>
							<td height="25" align="center" class="2-td2-left">53</td>
							<td height="25" align="center" class="2-td2-center">
								<input type="text" maxlength="16" size="12" name="bl6">
									<xsl:attribute name="value"><xsl:value-of select="/taxdoc/sbsjlist/fpblsj[3]/bqljs"/></xsl:attribute>
								</input>
							</td>
						</tr>
						<tr>
							<td height="25" align="center" nowrap="nowrap" class="2-td2-left">
								<div align="left">　　(4)
                      分配比例
                       <input type="text" maxlength="16" size="8" name="bl5">
										<xsl:attribute name="value"><xsl:value-of select="/taxdoc/sbsjlist/fpblsj[4]/fpbl"/></xsl:attribute>
									</input>%</div>
							</td>
							<td height="25" align="center" class="2-td2-left">54 </td>
							<td height="25" align="center" class="2-td2-center">
								<input type="text" maxlength="16" size="12" name="bl6">
									<xsl:attribute name="value"><xsl:value-of select="/taxdoc/sbsjlist/fpblsj[4]/bqljs"/></xsl:attribute>
								</input>
							</td>
						</tr>
					</table>
				</td>
			</TR>
		</table>
	</xsl:template>
	<xsl:template match="sbxx">
		<table cellspacing="0" class="table-99">
			<tr class="black9">
				<input name="fsdm" type="hidden" id="fsdm">
					<xsl:attribute name="value"><xsl:value-of select="fsdm"/></xsl:attribute>
				</input>
				<td align="center" nowrap="nowrap">
					<div align="left">申报日期：<xsl:value-of select="sbrq"/>
						<input name="sbrq" type="hidden" id="sbrq">
							<xsl:attribute name="value"><xsl:value-of select="sbrq"/></xsl:attribute>
						</input>
					</div>
				</td>
				<td align="left" nowrap="nowrap">申报所属日期&#160;&#160;
				<xsl:value-of select="skssksrq"/>至<xsl:value-of select="skssjsrq"/>
					<input name="skssksrq" type="hidden" id="skssksrq">
						<xsl:attribute name="value"><xsl:value-of select="skssksrq"/></xsl:attribute>
					</input>
					<input name="skssjsrq" type="hidden" id="skssjsrq">
						<xsl:attribute name="value"><xsl:value-of select="skssjsrq"/></xsl:attribute>
					</input>
				</td>
				<td align="left" nowrap="nowrap">金额单位：人民币元</td>
				<input name="nd" type="hidden" id="nd">
					<xsl:attribute name="value"><xsl:value-of select="nd"/></xsl:attribute>
				</input>
			</tr>
		</table>
	</xsl:template>
	<xsl:template match="czzbsj">
		<input name="lrr" type="hidden" id="lrr">
			<xsl:attribute name="value"><xsl:value-of select="lrr"/></xsl:attribute>
		</input>
		<input name="lrrq" type="hidden" id="lrrq">
			<xsl:attribute name="value"><xsl:value-of select="lrrq"/></xsl:attribute>
		</input>
		<input name="cjsj" type="hidden" id="cjsj">
			<xsl:attribute name="value"><xsl:value-of select="cjsj"/></xsl:attribute>
		</input>
	</xsl:template>
	<xsl:template match="nsrxx">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td nowrap="nowrap" class="2-td2-t-left">
					<div align="right">税务计算机代码&#160;&#160;
			        </div>
				</td>
				<td nowrap="nowrap" class="2-td2-t-left">
					<div align="left">&#160;
						<xsl:value-of select="jsjdm"/>
						<input name="jsjdm" type="hidden" id="jsjdm">
							<xsl:attribute name="value"><xsl:value-of select="jsjdm"/></xsl:attribute>
						</input>
					</div>
				</td>
				<td nowrap="nowrap" class="2-td2-t-left">
					<div align="right">单位名称&#160;&#160;
			        </div>
				</td>
				<td nowrap="nowrap" class="2-td2-t-center">
					<div align="left">&#160;
						<xsl:value-of select="nsrmc"/>
						<input name="nsrmc" type="hidden" id="nsrmc">
							<xsl:attribute name="value"><xsl:value-of select="nsrmc"/></xsl:attribute>
						</input>
					</div>
				</td>
				<input name="qylxdh" type="hidden" id="qylxdh">
					<xsl:attribute name="value"><xsl:value-of select="qylxdh"/></xsl:attribute>
				</input>
				<input name="swjgzzjgdm" type="hidden" id="swjgzzjgdm">
					<xsl:attribute name="value"><xsl:value-of select="swjgzzjgdm"/></xsl:attribute>
				</input>
			</tr>
		</table>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" height="5">
			<tr>
				<td/>
			</tr>
		</table>
	</xsl:template>
	<xsl:template match="tzfsj">
		<xsl:variable name="tzPosition" select="position()-1"/>
		<tr>
			<td class="2-td2-left">
				<input type="radio" name="sel">
					<xsl:attribute name="onClick">javascript: display('<xsl:value-of select="$tzPosition"/>')</xsl:attribute>
				</input>
			</td>
			<td colspan="5" class="2-td2-left">
				<xsl:value-of select="tzzxm"/>
				<input name="tzzxm" type="hidden" id="tzzxm">
					<xsl:attribute name="value"><xsl:value-of select="tzzxm"/></xsl:attribute>
				</input>
			</td>
			<td colspan="4" class="2-td2-center">
				<xsl:value-of select="zjhm"/>
				<input name="zjhm" type="hidden" id="zjhm">
					<xsl:attribute name="value"><xsl:value-of select="zjhm"/></xsl:attribute>
				</input>
			</td>
			<input name="zjlxdm" type="hidden" id="zjlxdm">
				<xsl:attribute name="value"><xsl:value-of select="zjlxdm"/></xsl:attribute>
			</input>
			<input name="cwfzr" type="hidden" id="cwfzr">
				<xsl:attribute name="value"><xsl:value-of select="cwfzr"/></xsl:attribute>
			</input>
		</tr>
		<input name="jmsjControl" type="hidden" id="jmsjControl">
			<xsl:attribute name="value"><xsl:value-of select="jmsjcontrol"/></xsl:attribute>
		</input>
	</xsl:template>
	<xsl:template match="czzbsj">
		<input name="lrr" type="hidden" id="lrr">
			<xsl:attribute name="value"><xsl:value-of select="lrr"/></xsl:attribute>
		</input>
		<input name="lrrq" type="hidden" id="lrrq">
			<xsl:attribute name="value"><xsl:value-of select="lrrq"/></xsl:attribute>
		</input>
		<input name="cjsj" type="hidden" id="cjsj">
			<xsl:attribute name="value"><xsl:value-of select="cjsj"/></xsl:attribute>
		</input>
	</xsl:template>
	<xsl:template name="colwsbxm">
		<xsl:param name="startCol1"/>
		<xsl:param name="endCol1"/>
		<xsl:param name="thename"/>
		<!--xsl:variable name="varHei" select="25"/-->
		<xsl:for-each select="/taxdoc/sbsjlist/sbxm[position() &gt;= $startCol1 and position() &lt;= $endCol1]">
			<xsl:sort select="hc" data-type="number">
					</xsl:sort>
			<xsl:variable name="varPosition" select="hc"/>
			<xsl:variable name="varHei">
				<xsl:choose>
					<xsl:when test="$varPosition = 15">
						<xsl:text>50</xsl:text>
					</xsl:when>
					<xsl:otherwise>
						<xsl:text>25</xsl:text>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:variable>
			<tr>
				<td align="center" nowrap="nowrap" class="2-td2-left">
					<xsl:attribute name="height"><xsl:value-of select="$varHei"/></xsl:attribute>
					<div align="left">
						<xsl:value-of select="xmmc"/>
					</div>
				</td>
				<td align="center" class="2-td2-left">
					<xsl:attribute name="height"><xsl:value-of select="$varHei"/></xsl:attribute>
					<xsl:value-of select="hc"/>
				</td>
				<td align="center" class="2-td2-center">
					<xsl:attribute name="height"><xsl:value-of select="$varHei"/></xsl:attribute>
					<input type="text" maxlength="16" size="12">
						<xsl:attribute name="name"><xsl:value-of select="$thename"/></xsl:attribute>
						<xsl:attribute name="value"><xsl:value-of select="bqljs"/></xsl:attribute>
					</input>
				</td>
			</tr>
		</xsl:for-each>
	</xsl:template>
	<xsl:template name="colrsbxm">
		<xsl:param name="startCol1"/>
		<xsl:param name="endCol1"/>
		<xsl:param name="thename"/>
		<xsl:for-each select="/taxdoc/sbsjlist/sbxm[position() &gt;= $startCol1 and position() &lt;= $endCol1]">
			<xsl:sort select="hc" data-type="number">
					</xsl:sort>
			<xsl:variable name="varPosition" select="hc"/>
			<xsl:variable name="varHei">
				<xsl:choose>
					<xsl:when test="$varPosition = 33">
						<xsl:text>50</xsl:text>
					</xsl:when>
					<xsl:otherwise>
						<xsl:text>25</xsl:text>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:variable>
			<tr>
				<td align="center" class="2-td2-left">
					<xsl:attribute name="height"><xsl:value-of select="$varHei"/></xsl:attribute>
					<div align="left">
						<xsl:value-of select="xmmc"/>
					</div>
				</td>
				<td align="center" class="2-td2-left">
					<xsl:attribute name="height"><xsl:value-of select="$varHei"/></xsl:attribute>
					<xsl:value-of select="hc"/>
				</td>
				<td align="center" class="2-td2-center">
					<xsl:attribute name="height"><xsl:value-of select="$varHei"/></xsl:attribute>
					<div align="right">
						<xsl:attribute name="id">div<xsl:value-of select="hc"/></xsl:attribute>
						<xsl:value-of select="bqljs"/>
					</div>
					<input type="hidden">
						<xsl:attribute name="name"><xsl:value-of select="$thename"/></xsl:attribute>
						<xsl:attribute name="value"><xsl:value-of select="bqljs"/></xsl:attribute>
					</input>
				</td>
			</tr>
		</xsl:for-each>
	</xsl:template>
	<xsl:template match="/taxdoc/tzfsj/tzfmx">
		<input type="hidden" name="bl4">
			<xsl:attribute name="value"><xsl:value-of select="bqljs"/></xsl:attribute>
		</input>
	</xsl:template>
</xsl:stylesheet>
