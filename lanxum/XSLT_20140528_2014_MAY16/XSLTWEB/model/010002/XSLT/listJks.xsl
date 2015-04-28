<?xml version="1.0" encoding="gb2312"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<table cellspacing="0" class="table-99">
			<tr class="black9">
				<td width="20%" nowrap="true" class="2-td2-t-left">
					<div align="right">税务计算机代码&#160;&#160;</div>
				</td>
				<td width="30%" nowrap="true" class="2-td2-t-left">
					<div align="left">&#160;&#160;<xsl:value-of select="//jsjdm"/>
					</div>
				</td>
				<td width="20%" nowrap="true" class="2-td2-t-left">
					<div align="right">单位名称&#160;&#160;</div>
				</td>
				<td width="30%" nowrap="true" class="2-td2-t-center">
					<div align="left">&#160;&#160;<xsl:value-of select="//nsrmc"/>
					</div>
				</td>
			</tr>
		</table>
		<br/>
		<xsl:choose>
			<xsl:when test="taxdoc/sbsj/sskkbz[1]='1'"><![CDATA[  ]]></xsl:when><!--实时扣款-->
			<xsl:when test="taxdoc/sbsj/sskkbz[1]='0'">
				<xsl:if test="//lwzt[.=1]"><!--非实时扣款-->
					<tr>
						<td><div align="center"><strong><font size="4pt">银行端查询缴税凭证</font></strong></div></td>
					</tr>
				</xsl:if>
				<xsl:for-each select="taxdoc/sbsj[lwzt=1]"><!--开始 电子缴库书循环显示-->
					<table class="table-99" cellspacing="0"><!---->
						<tr>
							<td colspan="5" class="2-td1-center">
						申报日期：<xsl:value-of select="sbrq"/>税款类型：<xsl:value-of select="sklx"/>&#160;申报序号：<xsl:value-of select="sbbh"/>
								<xsl:if test="sfkyzf='1'">
									<font color="#0000CC">&#160;<a href="#" onclick="doDelete('{sbbh}','')" style="Cursor:hand">作废</a>&#160;</font>
								</xsl:if>
								<font color="#0000CC">
									<a href="#" onclick="doPrint('{sbbh}');" style="Cursor:hand">查看缴税凭证</a>
								</font>
							</td>
						</tr>
					</table>
					<!---->
				</xsl:for-each>
				
				<xsl:if test="//lwzt[.=0]"><!--****下边显示一票一税*****-->
					<tr>
						<td><br/><div align="center"><strong><font size="4pt">普通缴款书</font></strong></div></td>
					</tr>
				</xsl:if>
				<xsl:for-each select="taxdoc/sbsj[lwzt=0]">
					<!---->
					<table class="table-99" cellspacing="0">
						<tr>
							<td colspan="5" class="2-td1-center">
						申报日期：<xsl:value-of select="sbrq"/>税款类型：<xsl:value-of select="sklx"/>&#160;申报序号：<xsl:value-of select="sbbh"/>
								<xsl:if test="sfkyzf='1'">
									<font color="#0000CC">&#160;<a href="#" onclick="doDelete('{sbbh}','')" style="Cursor:hand">作废</a>&#160;</font>
								</xsl:if>
							</td>
						</tr>
						<tr>
							<td width="20%" class="2-td1-left">查看缴款书</td>
							<td width="30%" class="2-td1-left">缴款书号</td>
							<td width="15%" class="2-td1-left">税种</td>
							<td width="20%" class="2-td1-left">合计金额</td>
							<td width="15%" class="2-td1-center">&#160;</td>
						</tr>
						<xsl:for-each select="jks">
							<!--循环显示一票一税务-->
							<tr>
								<td class="2-td2-left">
									<a>
										<xsl:attribute name="href"><![CDATA[listJks.dc?actionType=ViewOne&sbbhIndex=]]><xsl:value-of select="../sbbh"/><![CDATA[&jkshIndex=]]><xsl:value-of select="jkpzh"/></xsl:attribute>查看缴款书详细信息</a>
								</td>
								<td class="2-td2-left">
									<xsl:value-of select="jkpzh"/>
								</td>
								<td class="2-td2-left">
									<xsl:value-of select="szmc"/>
								</td>
								<td class="2-td2-left">
									<div align="right">
										<xsl:value-of select="sjje"/>&#160;&#160;</div>
								</td>
								<td class="2-td2-center">
									<xsl:value-of select="kkzt"/>&#160;
									<xsl:choose>
										<xsl:when test="sfkyzf='1'">
											<font color="#0000CC">&#160;<a href="#" style="Cursor:hand" onclick="doDelete('{../sbbh}','{jkpzh}');">作废</a>&#160;</font>
										</xsl:when>
									</xsl:choose>
								</td>
							</tr>
						</xsl:for-each>
					</table>
					<!---->
				</xsl:for-each>
			</xsl:when>
			<xsl:otherwise>您的本次查看中无申报未入库的缴款书</xsl:otherwise>
			<!--没有缴款数据-->
		</xsl:choose>
	</xsl:template>
</xsl:stylesheet>
