<?xml version="1.0" encoding="gb2312"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:msxsl="urn:schemas-microsoft-com:xslt">
<xsl:template match="/">

<table width="96%" align="center" cellspacing="0" class="table-99" id="pauls">
		<tr>
			<td class="1-td1" colspan="7"><div align="center"><font size="4pt"><b>电子缴库专用缴款书</b></font></div></td>
		</tr>
		<tr>
			<td class="1-td2" colspan="7" align="center">
				<br/><div align="center"><xsl:value-of select="//sbrq"/></div><br/>
			</td>
		</tr>
		<tr>
			<td class="1-td2"  colspan="7" valign="top"> <br/>
				<table class="table-99" cellspacing="0" width="98%" >
					<tr class="black9">
						<td align="left">&#160;□ 已申报   申报序号：<u><xsl:value-of select="//sbbh"/></u>&#160;&#160;□ 未申报&#160;</td>
						<td align="right">金额单位：人民币元</td>
					</tr>
				</table>
				<table class="table-99" cellspacing="0" width="98%" border="1">
					<tr>
						<td    class="2-td2-t-left">
							<div align="center"><strong>纳税人计算机代码</strong>&#160;&#160;</div>
						</td>
						<td  colspan="3" class="2-td2-t-center">
								<div align="center">&#160;&#160;<xsl:value-of select="//jsjdm"/></div>
						</td>
						
					</tr>
				
					<tr>
						<td width="20%" class="2-td2-left">
							<div align="center"><strong>纳税人名称</strong>&#160;&#160;</div>
						</td>
						<td width="30%" class="2-td2-left">
							<div align="center">&#160;&#160;<xsl:value-of select="//nsrmc"/></div>
						</td>
						
						<td width="20%" class="2-td2-left">
							<div align="center"><strong>征收机关代码</strong>&#160;&#160;</div>
						</td>
						<td width="30%" class="2-td2-center">
							<div align="center">&#160;&#160;21100000000</div>
						</td>
					</tr>
					<tr>
						<td width="20%" class="2-td2-left">
							<div align="center"><strong>付款人名称</strong>&#160;&#160;</div>
						</td>
						<td width="30%" class="2-td2-left">
							<div align="center">&#160;&#160;<input type="text" name="nsrmc" value="{//nsrmc}"/></div>
						</td>
						<td width="20%" class="2-td2-left">
							<div align="center"><strong>征收机关名称</strong>&#160;&#160;</div>
						</td>
						<td width="30%" class="2-td2-center">
							<div align="center">&#160;&#160;<xsl:value-of select="//swjgzzjgmc"/>
                            </div>
						</td>
					</tr>
					<tr>
						<td width="20%" class="2-td2-left">
							<div align="center"><strong>付款人开户银行名称</strong>&#160;&#160;</div>
						</td>
						<td width="30%" class="2-td2-left">
							<div align="center">&#160;&#160;<input type="text" name="yhmc" value="{//yhmc}"/></div>
						</td>
						<td width="20%" class="2-td2-left">
							<div align="center"><strong>收款国库名称</strong>&#160;&#160;</div>
						</td>
						<td width="30%" class="2-td2-center">
							<div align="center">&#160;&#160;
                                <xsl:value-of select="//skgk"/>
                            </div>
						</td>
					</tr>
					<tr>
						<td class="2-td2-left">
							<div align="center"><strong>付款人帐号</strong>&#160;&#160;</div>
						</td>
						<td class="2-td2-left">
							<div align="center">&#160;&#160;<input type="text" name="zh" value="{//zh}"/></div>
						</td>
						<td class="2-td2-left">
							<div align="center"><strong>国库清算行行号</strong>&#160;&#160;</div>
						</td>
						<td class="2-td2-center">
							<div align="center">&#160;&#160;011100000003</div>
						</td>
					</tr>
				</table>
				<br/>
				<br/>
				<table class="table_99" cellspacing="0" width="98%" border="1">
					<tr>
							  <td class="2-td1-left"><div align="center">纳税项目代码</div></td>
							  <td class="2-td1-left"><div align="center">课税数量</div></td>
							  <td class="2-td1-left"><div align="center">计税金额</div></td>
							  <td class="2-td1-center"><div align="center">实际缴税额</div></td>
					</tr>
					<xsl:apply-templates select="//sbsj"/><!--##########################################-->
					<tr>
						<td class="2-td2-left">
							<div align="center"><strong>金额合计(大写):</strong>&#160;&#160;</div>
						</td>
						<td class="2-td2-left">
							<div align="center">&#160;&#160;<xsl:value-of select="//hjsjjedx"/></div>
                        </td>
						<td class="2-td2-left">
							<div align="center"><strong>金额合计(小写):</strong>&#160;&#160;</div>
						</td>
						<td colspan="2" class="2-td2-center">
							<div align="center">&#160;&#160;￥<xsl:value-of select="//hjsjje"/></div>
						</td>
					</tr>
					<tr>
						<td class="2-td2-left">
							<div align="center"><strong> <br/>付款人 盖章 <br/>   经办人（章） <br/></strong>&#160;&#160;</div>
						</td>
						<td class="2-td2-left">
							<div align="center"><strong> <br/>税务机关 <br/>（章） <br/></strong>&#160;&#160;</div>
						</td>
						<td class="2-td2-left">
							<div align="center"><strong> <br/>银行记账员  <br/>盖章 <br/></strong>&#160;&#160;</div>
						</td>
						<td colspan="2" class="2-td2-center">
							<div align="left"><strong>&#160;&#160;备注： <br/> <br/></strong>&#160;&#160;</div>
						</td>
					</tr>
				</table>
				<table class="table-99" cellspacing="0" width="98%" >
					<tr class="black9">
						<td align="right">&#160;
							
						</td>
					</tr>
					<tr class="black9">
						<td align="right">&#160;
							
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	
</xsl:template>
<xsl:template match="//sbsj">
	
	<xsl:for-each select="nxxm">
					<tr>
                        <td class="2-td2-left"><div align="center">&#160;<xsl:value-of select="nxxmmc"/>&#160;</div></td>
                        <td class="2-td2-left"><div align="center">&#160;&#160;</div></td>
                        <td class="2-td2-left"><div align="center">&#160;&#160;</div></td>
                        <td class="2-td2-center"><div align="center"><xsl:value-of select="sjje"/>&#160;&#160;</div></td>
					</tr>
					
	</xsl:for-each>
</xsl:template>
</xsl:stylesheet>    






					