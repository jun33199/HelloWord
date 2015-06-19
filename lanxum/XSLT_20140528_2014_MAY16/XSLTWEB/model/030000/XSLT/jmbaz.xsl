<?xml version="1.0" encoding="GB2312"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<input name="xsltVersion" type="hidden" id="xsltVersion">
			<xsl:attribute name="value"><xsl:value-of select="taxdoc/xsltVersion"/></xsl:attribute>
		</input>
		<input name="schemaVersion" type="hidden" id="schemaVersion">
			<xsl:attribute name="value"><xsl:value-of select="taxdoc/schemaVersion"/></xsl:attribute>
		</input>
		<br/>
		<table id="LrbasqTable" border="0" cellPadding="0" cellSpacing="0" width="100%">
			<TBODY>
                     <TR>
                        <TD class="2-td1-t-left" width="50%">
                          ����˰���
                        </TD>
                        <TD class="2-td1-t-left" width="10%">
                          ����������
                        </TD> 
                        <TD class="2-td1-t-left" width="10%">
                          �������
                        </TD>                        
                        <TD class="2-td1-t-left" width="10%">
                          ����״̬
                        </TD>                       
                        <TD class="2-td1-t-center" width="10%">
                          ����
                        </TD>
                      </TR>  
				<xsl:for-each select="taxdoc/jmsbajl">
				<xsl:variable name="selwsh" select="basqwsh"/>
				<xsl:variable name="seldm" select="jmbasxdm"/>
				<TR>
					<TD class="2-td2-left">
						<div align="left"><xsl:value-of select="jmbasxmc"/></div>
					</TD>
					<TD class="2-td2-left"><xsl:value-of select="basqbh"/></TD>
					<TD class="2-td2-left"><xsl:value-of select="band"/></TD>
                   	<TD class="2-td2-left">
						<xsl:value-of select="ztmc"/>
					</TD>
					<TD class="2-td2-center">
					<xsl:if test="ztdm='1'" ><a href="#"  onclick="doEdit('{$selwsh}','{$seldm}')">�޸�</a>&#160;<a href="#"  onclick="doDelete('{$selwsh}','{$seldm}')">ɾ��</a></xsl:if>
					<xsl:if test="ztdm='3'" ><a href="#"  onclick="doView('{$selwsh}','{$seldm}')">�鿴</a>&#160; 	<a href="#"  onclick="doRollback('{$selwsh}','{$seldm}')">����</a></xsl:if>
					<xsl:if test="ztdm='5'" ><a href="#"  onclick="doView('{$selwsh}','{$seldm}')">�鿴</a></xsl:if>
					<xsl:if test="ztdm='4'" ><a href="#"  onclick="doView('{$selwsh}','{$seldm}')">�鿴</a>&#160;</xsl:if>
					<xsl:if test="ztdm='2'" >&#160;</xsl:if>
					<xsl:if test="ztdm='9'" ><a href="#"  onclick="doView('{$selwsh}','{$seldm}')">�鿴</a>&#160;</xsl:if>
					</TD>
				</TR>
			</xsl:for-each>
			<br/>
			</TBODY>
		</table>
	</xsl:template>
</xsl:stylesheet>
