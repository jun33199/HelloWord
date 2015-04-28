<?xml version="1.0" encoding="gb2312"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:msxsl="urn:schemas-microsoft-com:xslt">
<xsl:template match="/">
<!--##########################################-->
	<table width="80%" border="2" align="center" cellpadding="0" cellspacing="4" bordercolor="#9DB9D2">
        <tr>
          <td height="50" bgcolor="#F0F0F0">
          <div align="center">
		  	<span class="black9">
				<font size="5" color="#999999">中华人民共和国</font>
             </span>
		  </div>
          <p align="center">
			<font color="#ABC6CD">&#160;</font>
			<font color="#d32e2e"><u>&#160;&#160;<xsl:value-of select="taxdoc/sbsj/jks/szmc"/>&#160;&#160;</u></font>
			<font color="#2C556D">税收缴款书</font>
		  </p>
          <p align="center" class="black9"><font color="#999999"></font></p>
		  </td>
        </tr>
		<tr>
			<td>
				<table width="100%" cellpadding="4" cellspacing="0" class="black9" id="AutoNumber4" style="border-collapse: collapse">			
					<tr>
                		<td width="35%" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF">
							<font color="#2C556D">&#160;&#160;隶属关系：</font>
				  			<font color="#d32e2e"><xsl:value-of select="taxdoc/sbsj/lsgxmc"/></font>
						</td>
                		<td width="30%" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF">
							<font color="#2C556D">税款类型：</font>
							<font color="#d32e2e"><xsl:value-of select="taxdoc/sbsj/sklx"/></font>
						</td>
                		<td width="35%" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF">
							<font color="#2C556D">电脑编号：</font>
							<font color="#d32e2e"><xsl:value-of select="taxdoc/sbsj/jks/jkpzh"/></font>
						</td>
              	   </tr>
              		<tr>
                		<td width="35%" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF">
							<font color="#2C556D">&#160;&#160;注册类型：</font>
							<font color="#d32e2e"><xsl:value-of select="taxdoc/sbsj/djzclxmc"/></font>
						</td>
                		<td width="30%" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF">
							<font color="#2C556D">填发日期：</font>
							<font color="#d32e2e"><xsl:value-of select="taxdoc/sbsj/sbrq"/></font>
						</td>
                		<td width="35%" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF">
							<font color="#2C556D">征收机关：</font>
							<font color="#d32e2e"><xsl:value-of select="taxdoc/sbsj/zsswjgzzjgmc"/></font>
						</td>
              		</tr>
            </table>
			
		<!--##########################################-->
			<table width="100%" height="102" border="2" cellpaddng="4" cellspacing="0" bordercolor="#9DB9D2" class="black9" id="AutoNumber3" style="border-collapse: collapse">
              <tr>
                <td width="35" rowspan="4" height="85"><div align="center"><font color="#2C556D">缴<br/>
                    <br/>
                    款<br/>
                    <br/>
                    单<br/>
                    <br/>
                    位<br/>
                    ︵<br/>
                    人<br/>
                    ︶</font></div></td>
                <td width="86" height="16">
					<div align="center"><font color="#2C556D">代&#160;&#160;&#160;&#160;码</font></div>
                </td>
                <td width="89" height="16">
					<font color="#d32e2e"><xsl:value-of select="//jsjdm"/></font>
				</td>
                <td width="38" height="16">
					<font color="#2C556D">电&#160;话</font>
				</td>
                <td width="127" height="16">
					<font color="#d32e2e"><xsl:value-of select="taxdoc/sbsj/lxdh"/></font>
				</td>
                <td width="84" rowspan="3" height="51">
					<div align="center"><font color="#2C556D">预算科目</font></div>
				</td>
                <td width="61" height="16">
					<div align="center"><font color="#2C556D">编&#160;码</font></div>
				</td>
                <td width="221" height="16">
					<font color="#d32e2e"><xsl:value-of select="taxdoc/sbsj/jks/yskmdm"/></font>
				</td>
              </tr>
              <tr>
                <td width="86" height="16">
					<div align="center"><font color="#2C556D">全&#160;&#160;&#160;&#160;称</font></div>
				</td>
                <td width="256" colspan="3" height="16">
					<font color="#d32e2e"><xsl:value-of select="//nsrmc"/></font>
				</td>
                <td width="61" height="16">
					<div align="center"><font color="#2C556D">名&#160;称</font></div>
				</td>
                <td width="221" height="16">
					<font color="#d32e2e"><xsl:value-of select="taxdoc/sbsj/jks/yskmmc"/></font>
				</td>
              </tr>
              <tr>
                <td width="86" height="17">
					<div align="center"><font color="#2C556D">开户银行</font></div>
				</td>
                <td width="256" colspan="3" height="17">
					<font color="#d32e2e"><xsl:value-of select="taxdoc/sbsj/yhmc"/></font>
				</td>
                <td width="61" height="17">
					<div align="center"><font color="#2C556D">级&#160;次</font></div>
				</td>
                <td width="221" height="17">
					<font color="#d32e2e"><xsl:value-of select="taxdoc/sbsj/jks/ysjcmc"/></font>
				</td>
              </tr>
              <tr>
                <td width="86" height="33">
					<div align="center"><font color="#2C556D">帐&#160;&#160;&#160;&#160;号</font></div>
				</td>
                <td width="256" colspan="3" height="33">
					<font color="#d32e2e"><xsl:value-of select="taxdoc/sbsj/zh"/></font>
				</td>
                <td width="84" height="33">
					<div align="center"><font color="#2C556D">收款国库</font></div>
				</td>
                <td width="283" colspan="2" height="33">
					<font color="#d32e2e">(<xsl:value-of select="taxdoc/sbsj/gkzzjgdm"/>)<xsl:value-of select="taxdoc/sbsj/gkzzjgmc"/></font>
				</td>
              </tr>
              <tr>
                <td width="379" colspan="5" height="16">
					<font color="#2C556D">税款所属时期：</font>
					<font color="#d32e2e"><xsl:value-of select="taxdoc/sbsj/jks/skssksrq"/>至<xsl:value-of select="taxdoc/sbsj/jks/skssjsrq"/></font>
				</td>
                <td width="368" colspan="3" height="16">
					<font color="#2C556D">税款限缴日期：</font>
					<font color="#d32e2e"><xsl:value-of select="//xjrq"/></font>
				</td>
              </tr>
            </table>
			<!--##########################################-->
			<table width="100%" height="122" border="2" cellpadding="4" cellspacing="0" bordercolor="#9DB9D2" class="black9" id="AutoNumber2" style="border-collapse: collapse">
				  <tr>
					<td width="302" style="padding-top: 0" height="32" align="center"><font color="#2C556D">品目名称</font></td>
					<td width="110" style="padding-top: 0" height="32" align="center"><font color="#2C556D">课税数量</font></td>
					<td width="145" style="padding-top: 0" height="32" align="center">
					  <p align="center"/><font color="#2C556D">计税金额或销售收入</font></td>
					<td width="127" style="padding-top: 0" height="32" align="center"><font color="#2C556D">实缴税额</font></td>
				  </tr>
			 	 <xsl:apply-templates select="taxdoc/sbsj"/><!--##########################################-->
			 	 <tr>
				<td colspan="3" style="padding-top: 0" height="15">
					<font color="#2C556D">金额合计（大写）：</font>
					<font color="#d32e2e"><xsl:value-of select="taxdoc/sbsj/jks/sjjedx"/></font>
				</td>
				<td width="127" style="padding-top: 0" height="15" align="right">
					<font color="#d32e2e">￥<xsl:value-of select="taxdoc/sbsj/jks/sjje"/></font>
				</td>
			   </tr>
			</table>
			<!--##########################################-->
			<table width="100%" border="2" cellpadding="4" cellspacing="0" bordercolor="#9DB9D2" class="black9" id="AutoNumber5" style="border-collapse: collapse">
              <tr>
                <td width="20%">
				  <font color="#2C556D">缴款单位（人）： </font>
                  <p><font color="#d32e2e"><xsl:value-of select="//nsrmc"/></font></p>
                  <p><font color="#2C556D">经办人（章） &#160;</font></p>
				</td>
                <td width="20%">
					<font color="#2C556D">地方税务机关： </font>
                  	<p><font color="#d32e2e"><xsl:value-of select="taxdoc/sbsj/swjgzzjgmc"/></font></p>
                  	<p/><font color="#2C556D">填票人：</font>
					<font color="#d32e2e"><xsl:value-of select="//nsrmc"/></font>
				</td>
                <td width="60%">
					<font color="#2C556D">备注：<xsl:value-of select="taxdoc/sbsj/jks/bz"/></font>
			    </td>
              </tr>
            </table> 
			</td>
		</tr>
		</table>
<!--##########################################-->		
</xsl:template>
	
<!--##########################################-->
<xsl:template match="taxdoc/sbsj">
	<xsl:for-each select="smitem">
		 <tr>
                <td width="302" style="padding-top: 0" height="16">
					<font color="#d32e2e"><xsl:value-of select="szsmdm"/>&#160;<xsl:value-of select="szsmmc"/></font>
				</td>
                <td width="110" style="padding-top: 0" height="16">
					<div align="right"><font color="#d32e2e"><xsl:value-of select="kssl"/></font></div>
				</td>
                <td width="145" style="padding-top: 0" height="16">
					<div align="right"><font color="#d32e2e"><xsl:value-of select="jsje"/></font></div>
				</td>
                <td width="127" style="padding-top: 0" height="16">
					<div align="right"><font color="#d32e2e"><xsl:value-of select="sjse"/></font></div>
				</td>
          </tr>
	</xsl:for-each>
			
</xsl:template>

</xsl:stylesheet>    
		   
		   
		   
		   
		   
	