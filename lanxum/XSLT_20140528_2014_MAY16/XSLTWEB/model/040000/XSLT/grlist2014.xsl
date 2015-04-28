<?xml version="1.0" encoding="GB2312"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" />
	<xsl:template match="/">

	<!-- 隐藏域-->
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
					<xsl:value-of select="taxdoc/jsjdm" />
				</xsl:attribute>
			</input>	
			

		<table id="maintable1" align="center" cellspacing="0"  width="80%">
			<tr>
				<td class="1-td1" >生产、经营所得税纳税个人列表<br/></td>
			</tr>

			
			
			<tr>
				<td class="1-td2" >
					<br/>
					<div align="left"><FONT color="#000000" size="1"><STRONG>来源于税务登记的投资者信息：</STRONG></FONT></div>
					<div style="overflow: auto; width:98%; height:180;margin:0">
						<table id="tzflist" cellSpacing="0" cellPadding="0" width="100%" border="0"  align = "center">
						 	<tr>
								<td class="2-td1-left" width="20%">姓名</td>
								<td class="2-td1-left" width="20%">身份证件类型</td>
								<td class="2-td1-left" width="20%">身份证件号码</td>
								<td class="2-td1-left" width="20%">分配比例(%)</td>
								<td class="2-td1-left" width="10%">填写状态</td>
								<td class="2-td1-center" width="10%">操作</td>
							</tr>
							<tbody id="infbody">
							</tbody>
							
						</table>
					</div>
				</td>
			</tr>
		</table>

	</xsl:template >
</xsl:stylesheet>