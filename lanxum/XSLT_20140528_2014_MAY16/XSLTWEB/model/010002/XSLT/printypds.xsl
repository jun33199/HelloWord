<?xml version="1.0" encoding="gb2312"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:msxsl="urn:schemas-microsoft-com:xslt">
<xsl:template match="/">

<table width="96%" align="center" cellspacing="0" class="table-99" id="pauls">
		<tr>
			<td class="1-td1" colspan="7"><div align="center"><font size="4pt"><b>���ӽɿ�ר�ýɿ���</b></font></div></td>
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
						<td align="left">&#160;�� ���걨   �걨��ţ�<u><xsl:value-of select="//sbbh"/></u>&#160;&#160;�� δ�걨&#160;</td>
						<td align="right">��λ�������Ԫ</td>
					</tr>
				</table>
				<table class="table-99" cellspacing="0" width="98%" border="1">
					<tr>
						<td    class="2-td2-t-left">
							<div align="center"><strong>��˰�˼��������</strong>&#160;&#160;</div>
						</td>
						<td  colspan="3" class="2-td2-t-center">
								<div align="center">&#160;&#160;<xsl:value-of select="//jsjdm"/></div>
						</td>
						
					</tr>
				
					<tr>
						<td width="20%" class="2-td2-left">
							<div align="center"><strong>��˰������</strong>&#160;&#160;</div>
						</td>
						<td width="30%" class="2-td2-left">
							<div align="center">&#160;&#160;<xsl:value-of select="//nsrmc"/></div>
						</td>
						
						<td width="20%" class="2-td2-left">
							<div align="center"><strong>���ջ��ش���</strong>&#160;&#160;</div>
						</td>
						<td width="30%" class="2-td2-center">
							<div align="center">&#160;&#160;21100000000</div>
						</td>
					</tr>
					<tr>
						<td width="20%" class="2-td2-left">
							<div align="center"><strong>����������</strong>&#160;&#160;</div>
						</td>
						<td width="30%" class="2-td2-left">
							<div align="center">&#160;&#160;<input type="text" name="nsrmc" value="{//nsrmc}"/></div>
						</td>
						<td width="20%" class="2-td2-left">
							<div align="center"><strong>���ջ�������</strong>&#160;&#160;</div>
						</td>
						<td width="30%" class="2-td2-center">
							<div align="center">&#160;&#160;<xsl:value-of select="//swjgzzjgmc"/>
                            </div>
						</td>
					</tr>
					<tr>
						<td width="20%" class="2-td2-left">
							<div align="center"><strong>�����˿�����������</strong>&#160;&#160;</div>
						</td>
						<td width="30%" class="2-td2-left">
							<div align="center">&#160;&#160;<input type="text" name="yhmc" value="{//yhmc}"/></div>
						</td>
						<td width="20%" class="2-td2-left">
							<div align="center"><strong>�տ��������</strong>&#160;&#160;</div>
						</td>
						<td width="30%" class="2-td2-center">
							<div align="center">&#160;&#160;
                                <xsl:value-of select="//skgk"/>
                            </div>
						</td>
					</tr>
					<tr>
						<td class="2-td2-left">
							<div align="center"><strong>�������ʺ�</strong>&#160;&#160;</div>
						</td>
						<td class="2-td2-left">
							<div align="center">&#160;&#160;<input type="text" name="zh" value="{//zh}"/></div>
						</td>
						<td class="2-td2-left">
							<div align="center"><strong>�����������к�</strong>&#160;&#160;</div>
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
							  <td class="2-td1-left"><div align="center">��˰��Ŀ����</div></td>
							  <td class="2-td1-left"><div align="center">��˰����</div></td>
							  <td class="2-td1-left"><div align="center">��˰���</div></td>
							  <td class="2-td1-center"><div align="center">ʵ�ʽ�˰��</div></td>
					</tr>
					<xsl:apply-templates select="//sbsj"/><!--##########################################-->
					<tr>
						<td class="2-td2-left">
							<div align="center"><strong>���ϼ�(��д):</strong>&#160;&#160;</div>
						</td>
						<td class="2-td2-left">
							<div align="center">&#160;&#160;<xsl:value-of select="//hjsjjedx"/></div>
                        </td>
						<td class="2-td2-left">
							<div align="center"><strong>���ϼ�(Сд):</strong>&#160;&#160;</div>
						</td>
						<td colspan="2" class="2-td2-center">
							<div align="center">&#160;&#160;��<xsl:value-of select="//hjsjje"/></div>
						</td>
					</tr>
					<tr>
						<td class="2-td2-left">
							<div align="center"><strong> <br/>������ ���� <br/>   �����ˣ��£� <br/></strong>&#160;&#160;</div>
						</td>
						<td class="2-td2-left">
							<div align="center"><strong> <br/>˰����� <br/>���£� <br/></strong>&#160;&#160;</div>
						</td>
						<td class="2-td2-left">
							<div align="center"><strong> <br/>���м���Ա  <br/>���� <br/></strong>&#160;&#160;</div>
						</td>
						<td colspan="2" class="2-td2-center">
							<div align="left"><strong>&#160;&#160;��ע�� <br/> <br/></strong>&#160;&#160;</div>
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






					