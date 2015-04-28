<?xml version="1.0" encoding="gb2312"?>
<!-- DWXMLSource="Test.xml" -->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<table border="0" cellpadding="0" cellspacing="0" class="table-99">
			<tr>
				<td>
					<input type="hidden" name="done" value="{//done}"/>
					<input type="hidden" name="zsffdm" value="{//zsffdm}"/>
					<input type="hidden" name="xsltVersion" value="{//xsltVersion}"/>
					<input type="hidden" name="schemaVersion" value="{//schemaVersion}"/>
					<input type="hidden" name="ywlx" value="{//ywlx}"/>
					<input type="hidden" name="ywczlx"/>
					<input type="hidden" name="sbrq" value="{//sbrq}"/>
					<input type="hidden" name="fsdm" value="{//fsdm}"/>
					<input type="hidden" name="skssjsrq" value="{//skssjsrq}"/>
					<input type="hidden" name="skssksrq" value="{//skssksrq}"/>
					<xsl:apply-templates select="//szsm"/>
					<table cellspacing="0" class="table-99">
						<tr class="black9">
							<td align="left" nowrap="true">
								申报所属日期：&#160;<xsl:value-of select="//skssksrq"/>&#160;至&#160;<xsl:value-of select="//skssjsrq"/>
							</td>
							<TD align="right" noWrap="true">
								<DIV align="right">金额单位：人民币元</DIV>
							</TD>
						</tr>
						<input name="skssksrq" type="hidden" value="{//skssksrq}"/>
						<input name="skssjsrq" type="hidden" value="{//skssjsrq}"/>
					</table>
					<table cellspacing="0" class="table-99">
						<tr>
							<td class="2-td2-t-left">
								<div align="right">税务计算机代码&#160;</div>
							</td>
							<td class="2-td2-t-left">
								<div align="left">&#160;<xsl:value-of select="//jsjdm"/>
								</div>
							</td>
							<td class="2-td2-t-left">
								<div align="right">申报单位名称&#160;</div>
							</td>
							<td class="2-td2-t-center">
								<div align="left">&#160;<xsl:value-of select="//nsrmc"/>
								</div>
							</td>
						</tr>
						<input name="jsjdm" type="hidden" value="{//jsjdm}"/>
						<input name="nsrmc" type="hidden" value="{//nsrmc}"/>
						<input name="swjgzzjgdm" type="hidden" value="{//swjgzzjgdm}"/>
					</table>
					<br/>
					<table cellspacing="0" class="table-99" id="mainTable">
						<tbody>
							<tr>
								<td rowspan="2" class="2-td1-left">序号</td>
								<td rowspan="2" class="2-td1-left">纳税项目</td>
								<td colspan="2" class="2-td1-left">纳税申报额</td>
								<td rowspan="2" class="2-td1-left">计税金额<br/>(4)</td>
								<td rowspan="2" class="2-td1-left">税率<br/>(5) </td>
								<td rowspan="2" class="2-td1-left" nowrap="true">应纳税额<br/>(6)=(4)×(5)</td>
								<td rowspan="2" class="2-td1-left">已纳税额<br/>(7) </td>
								<td rowspan="2" class="2-td1-left" nowrap="true">本期应补税额<br/>(8)=(6)-(7)</td>
								<td rowspan="2" class="2-td1-center">&#160;</td>
							</tr>
							<tr>
								<td class="2-td1-left" nowrap="true">
									<font size="2">经费支出额<br/>A</font>
								</td>
								<td class="2-td1-left" nowrap="true">
									<font size="2">换算收入额<br/>B=A/0.85</font>
								</td>
							</tr>
							<xsl:if test="taxdoc/sbxx/done[.=1]">
								<xsl:for-each select="taxdoc/sbsj">
									<xsl:variable name="outerSzsmdm" select="szsmdm"/>
									<xsl:variable name="outerSzsmmc" select="szsmmc"/>
									<tr>
										<td class="2-td2-left">
											<xsl:value-of select="position()"/>
										</td>
										<td class="2-td2-left">
											<select name="szsmdm" style="width:250" onchange="selChange(this)">
												<xsl:choose>
													<xsl:when test="string-length(szsmmc)=0">
														<xsl:for-each select="../szsmList/szsm">
															<xsl:choose>
																<xsl:when test="szsmdm[.=$outerSzsmdm]">
																	<option value="{szsmdm}" selected="true">
																		<xsl:value-of select="szsmmc"/>
																	</option>
																</xsl:when>
																<xsl:otherwise>
																	<option value="{szsmdm}">
																		<xsl:value-of select="szsmmc"/>
																	</option>
																</xsl:otherwise>
															</xsl:choose>
														</xsl:for-each>
													</xsl:when>
													<xsl:otherwise>
														<option select="true">
															<xsl:value-of select="$outerSzsmmc"/>
														</option>
													</xsl:otherwise>
												</xsl:choose>
											</select>
										</td>
										<td class="2-td2-left">
											<input maxlength="15" size="15" name="jfzce" value="{jfzce}">
												<xsl:attribute name="onChange">
										return(jfzceOnChange(this,0,0,<xsl:value-of select="position()"/>));
									</xsl:attribute>
											</input>
										</td>
										<td class="2-td2-left">
											<div id="hssreDiv" align="right">
												<xsl:value-of select="hssre"/>
											</div>
											<input type="hidden" name="hssre" value="{hssre}"/>
										</td>
										<td class="2-td2-left">
											<input maxlength="15" size="15" name="jsje" value="{jsje}">
												<xsl:attribute name="onChange">
										return(jsjeAndyjnseOnChange(this,0,0,this,'jsje'));
									</xsl:attribute>
											</input>
										</td>
										<td class="2-td2-left">
											<div id="slDiv" align="left">
												<xsl:value-of select="sl"/>
											</div>
											<input type="hidden" name="sl" value="{sl}"/>
										</td>
										<td class="2-td2-left">
											<div id="ynseDiv" align="right">
												<xsl:value-of select="ynse"/>
											</div>
											<input type="hidden" name="ynse" value="{ynse}"/>
										</td>
										<td class="2-td2-left">
											<input maxlength="15" size="15" name="yjnse" value="{yjnse}">
												<xsl:attribute name="onChange">
										return(jsjeAndyjnseOnChange(this,0,0,this,'yjnse'));
									</xsl:attribute>
											</input>
										</td>
										<td class="2-td2-left">
											<div id="bqybseDiv" align="right">
												<xsl:value-of select="bqybse"/>
											</div>
											<input type="hidden" name="bqybse" value="{bqybse}"/>
										</td>
										<td class="2-td2-center" nowrap="true">
											<a href="#" id="deleteMe" onClick="deleteRow(this);return false;">删除</a>
										</td>
									</tr>
								</xsl:for-each>
							</xsl:if>
							<tr>
								<TD class="2-td2-left" colSpan="1" noWrap="true">&#160;</TD>
								<TD class="2-td2-left" colSpan="7" noWrap="true">
									<div align="right">合计</div>
								</TD>
								<TD class="2-td2-left" colSpan="1" noWrap="true">
									<input name="hjDiv" class="inputnoborder" readOnly="true" tabIndex="-1" value="0.00"/>
								</TD>
								<TD class="2-td2-center" colSpan="1" noWrap="true">&#160;</TD>
							</tr>
						</tbody>
					</table>
					<br/>
					<br/>
				</td>
			</tr>
		</table>
	</xsl:template>
	<xsl:template match="//szsm">
		<input name="vosl" type="hidden" value="{sl}"/>
		<input name="voszsmdm" type="hidden" value="{szsmdm}"/>
		<input name="voszsmmc" type="hidden" value="{szsmmc}"/>
	</xsl:template>
</xsl:stylesheet>
