<?xml version="1.0" encoding="GB2312"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<script language="javascript">
		
		
	</script>
		<xsl:for-each select="//szsm">
			<xsl:choose>
				<xsl:when test="//ccbs=0">
					<!--输出根-->
					<div>
						<a href="javascript:ToggleDisplay('{szsmdm}',div{szsmdm},document.forms[0].img{szsmdm})">
							<IMG id="img{szsmdm}" SRC="{//imagePath}plus.gif" BORDER="0"/>
							<xsl:value-of select="szsmmc"/>
						</a>
						<div id="div{szsmdm}" class="indent" style="display:none"/>
					</div>
				</xsl:when>
				<xsl:when test="//ccbs=1">
					<!--输出枝节点-->
					<div>
						<a href="javascript:ToggleDisplay('{szsmdm}',div{szsmdm},myForm.img{szsmdm} )">
							<IMG id="img{szsmdm}" SRC="{//imagePath}plus.gif" BORDER="0"/>
							<xsl:value-of select="szsmmc"/>
						</a>
						<div id="div{szsmdm}" class="indent" style="display:none"/>
					</div>
				</xsl:when>
				<xsl:when test="//ccbs=2">
					<xsl:choose>
						<xsl:when test="isSelected[.=1]">
							<xsl:if test="editable[.=0]">
								<input type="checkbox" name="check_szsmdm" id="id_{szsmdm}" checked="true" disabled="true" value="{szsmdm}"/>
								<a href="javascript:">
									<xsl:value-of select="szsmmc"/>
								</a>
								<br/>
							</xsl:if>
							<xsl:if test="editable[.=1]">
								<input type="checkbox" name="check_szsmdm" id="id_{szsmdm}" onclick="adjust('{szsmdm}','{szsmmc}')" checked="true" value="{szsmdm}"/>
										<a href="javascript:myForm.id_{szsmdm}.checked=!myForm.id_{szsmdm}.checked;adjust('{szsmdm}','{szsmmc}');">
									<xsl:value-of select="szsmmc"/>
								</a>
								<br/>
							</xsl:if>
						</xsl:when>
						<xsl:when test="isSelected[.=0]">
							<xsl:if test="editable[.=1]">
								<input type="checkbox" name="check_szsmdm" id="id_{szsmdm}" onclick="adjust('{szsmdm}','{szsmmc}')" value="{szsmdm}"/>
								<a href="javascript:myForm.id_{szsmdm}.checked=!myForm.id_{szsmdm}.checked;adjust('{szsmdm}','{szsmmc}');">
									<xsl:value-of select="szsmmc"/>
								</a>
								<br/>
							</xsl:if>
						</xsl:when>
					</xsl:choose>
				</xsl:when>
			</xsl:choose>
		</xsl:for-each>
	</xsl:template>
</xsl:stylesheet>
