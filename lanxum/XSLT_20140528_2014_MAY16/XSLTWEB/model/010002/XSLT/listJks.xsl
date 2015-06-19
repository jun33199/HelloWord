<?xml version="1.0" encoding="gb2312"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<table cellspacing="0" class="table-99">
			<tr class="black9">
				<td width="20%" nowrap="true" class="2-td2-t-left">
					<div align="right">˰����������&#160;&#160;</div>
				</td>
				<td width="30%" nowrap="true" class="2-td2-t-left">
					<div align="left">&#160;&#160;<xsl:value-of select="//jsjdm"/>
					</div>
				</td>
				<td width="20%" nowrap="true" class="2-td2-t-left">
					<div align="right">��λ����&#160;&#160;</div>
				</td>
				<td width="30%" nowrap="true" class="2-td2-t-center">
					<div align="left">&#160;&#160;<xsl:value-of select="//nsrmc"/>
					</div>
				</td>
			</tr>
		</table>
		<br/>
		<xsl:choose>
			<xsl:when test="taxdoc/sbsj/sskkbz[1]='1'"><![CDATA[  ]]></xsl:when><!--ʵʱ�ۿ�-->
			<xsl:when test="taxdoc/sbsj/sskkbz[1]='0'">
				<xsl:if test="//lwzt[.=1]"><!--��ʵʱ�ۿ�-->
					<tr>
						<td><div align="center"><strong><font size="4pt">���ж˲�ѯ��˰ƾ֤</font></strong></div></td>
					</tr>
				</xsl:if>
				<xsl:for-each select="taxdoc/sbsj[lwzt=1]"><!--��ʼ ���ӽɿ���ѭ����ʾ-->
					<table class="table-99" cellspacing="0"><!---->
						<tr>
							<td colspan="5" class="2-td1-center">
						�걨���ڣ�<xsl:value-of select="sbrq"/>˰�����ͣ�<xsl:value-of select="sklx"/>&#160;�걨��ţ�<xsl:value-of select="sbbh"/>
								<xsl:if test="sfkyzf='1'">
									<font color="#0000CC">&#160;<a href="#" onclick="doDelete('{sbbh}','')" style="Cursor:hand">����</a>&#160;</font>
								</xsl:if>
								<font color="#0000CC">
									<a href="#" onclick="doPrint('{sbbh}');" style="Cursor:hand">�鿴��˰ƾ֤</a>
								</font>
							</td>
						</tr>
					</table>
					<!---->
				</xsl:for-each>
				
				<xsl:if test="//lwzt[.=0]"><!--****�±���ʾһƱһ˰*****-->
					<tr>
						<td><br/><div align="center"><strong><font size="4pt">��ͨ�ɿ���</font></strong></div></td>
					</tr>
				</xsl:if>
				<xsl:for-each select="taxdoc/sbsj[lwzt=0]">
					<!---->
					<table class="table-99" cellspacing="0">
						<tr>
							<td colspan="5" class="2-td1-center">
						�걨���ڣ�<xsl:value-of select="sbrq"/>˰�����ͣ�<xsl:value-of select="sklx"/>&#160;�걨��ţ�<xsl:value-of select="sbbh"/>
								<xsl:if test="sfkyzf='1'">
									<font color="#0000CC">&#160;<a href="#" onclick="doDelete('{sbbh}','')" style="Cursor:hand">����</a>&#160;</font>
								</xsl:if>
							</td>
						</tr>
						<tr>
							<td width="20%" class="2-td1-left">�鿴�ɿ���</td>
							<td width="30%" class="2-td1-left">�ɿ����</td>
							<td width="15%" class="2-td1-left">˰��</td>
							<td width="20%" class="2-td1-left">�ϼƽ��</td>
							<td width="15%" class="2-td1-center">&#160;</td>
						</tr>
						<xsl:for-each select="jks">
							<!--ѭ����ʾһƱһ˰��-->
							<tr>
								<td class="2-td2-left">
									<a>
										<xsl:attribute name="href"><![CDATA[listJks.dc?actionType=ViewOne&sbbhIndex=]]><xsl:value-of select="../sbbh"/><![CDATA[&jkshIndex=]]><xsl:value-of select="jkpzh"/></xsl:attribute>�鿴�ɿ�����ϸ��Ϣ</a>
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
											<font color="#0000CC">&#160;<a href="#" style="Cursor:hand" onclick="doDelete('{../sbbh}','{jkpzh}');">����</a>&#160;</font>
										</xsl:when>
									</xsl:choose>
								</td>
							</tr>
						</xsl:for-each>
					</table>
					<!---->
				</xsl:for-each>
			</xsl:when>
			<xsl:otherwise>���ı��β鿴�����걨δ���Ľɿ���</xsl:otherwise>
			<!--û�нɿ�����-->
		</xsl:choose>
	</xsl:template>
</xsl:stylesheet>
