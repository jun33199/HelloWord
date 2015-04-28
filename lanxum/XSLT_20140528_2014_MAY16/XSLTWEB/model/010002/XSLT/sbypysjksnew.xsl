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
				<font size="5" color="#999999">中 华 人 民 共 和 国<br/>税收缴款书(银行经收专用)</font>
             </span>
		  </div>
					</td>
        </tr>
		<tr>
			<td>
				<table width="100%" cellpadding="4" cellspacing="0" class="black9" id="AutoNumber4" style="border-collapse: collapse">
				
						<tr>
                		<td width="35%" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF">
							<font color="#2C556D">&#160;&#160;税款类型：</font>
				  			<font color="#d32e2e"><xsl:value-of select="taxdoc/sbsj/sklx"/></font>
						</td>
                		<td width="30%" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF">
							<font color="#2C556D">电脑编号：</font>
							<font color="#d32e2e"><xsl:value-of select="taxdoc/sbsj/jks/jkpzh"/></font>
						</td>
                		<td width="35%" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF">
							<font color="#2C556D"></font>
							<font color="#d32e2e"></font>
						</td>
              	   </tr>
						<tr>
                		<td width="35%" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF">
							<font color="#2C556D">&#160;&#160;登记注册类型：</font>
				  			<font color="#d32e2e"><xsl:value-of select="taxdoc/sbsj/djzclxmc"/></font>
						</td>
                		<td width="30%" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF">
							<font color="#2C556D">填发日期：</font>
							<font color="#d32e2e"><xsl:value-of select="taxdoc/sbsj/sbrqn"/>年<xsl:value-of select="taxdoc/sbsj/sbrqy"/>月<xsl:value-of select="taxdoc/sbsj/sbrqr"/>日</font>
						</td>
                		<td width="35%" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF">
							<font color="#2C556D">税务机关：</font>
							<font color="#d32e2e"><xsl:value-of select="taxdoc/sbsj/zsswjgzzjgmc"/></font>
						</td>
              	   </tr>
            </table>

		<!--##########################################-->
			<table width="100%" height="102" border="2" cellpaddng="4" cellspacing="0" bordercolor="#9DB9D2" class="black9" id="AutoNumber3" style="border-collapse: collapse">
              <tr>
                <td width="50" rowspan="2" height="32">
                	<div align="center"><font color="#2C556D">缴款单位<br/><br/>（人）</font></div>
                </td>
                <td width="50" height="16">
									<div align="center"><font color="#2C556D">识&#160;&#160;别&#160;&#160;号</font></div>
                </td>
                <td width="300" height="16">
									<font color="#d32e2e"><xsl:value-of select="//jsjdm"/></font>
								</td>
                <td width="80" height="16">
									<div align="center"><font color="#2C556D">开户银行</font></div>
								</td>
                <td width="270" height="16">
									<font color="#d32e2e"><xsl:value-of select="taxdoc/sbsj/yhmc"/></font>
								</td>
              </tr>
              <tr>
                <td width="50" height="16">
									<div align="center"><font color="#2C556D">名&#160;&#160;&#160;&#160;称</font></div>
							</td>
                <td width="300" height="16">
									<font color="#d32e2e"><xsl:value-of select="//nsrmc"/></font>
							</td>
							<td width="80" height="16">
									<div align="center"><font color="#2C556D">账&#160;&#160;&#160;&#160;号</font></div>
							</td>
                <td width="270" height="16">
									<font color="#d32e2e"><xsl:value-of select="taxdoc/sbsj/zh"/></font>
							</td>
              </tr>
              <tr>
                <td width="100" colspan="2" height="16">
									<div align="center"><font color="#2C556D">收款国库</font></div>
								</td>
                <td width="300" height="16">
									<font color="#d32e2e">(<xsl:value-of select="taxdoc/sbsj/gkzzjgdm"/>)<xsl:value-of select="taxdoc/sbsj/gkzzjgmc"/></font>
								</td>
                <td width="80" height="16" align="center">
									<div align="center"><font color="#2C556D">税款限缴日期：</font></div>
								</td>
                <td width="270" height="16">
									<font color="#d32e2e"><xsl:value-of select="//xjrq"/></font>
							</td>
              </tr>             
            </table>
      <!--##########################################-->      
			<table width="100%" height="122" border="2" cellpadding="4" cellspacing="0" bordercolor="#9DB9D2" class="black9" id="AutoNumber2" style="border-collapse: collapse">
				  <tr>
					<td width="280" colspan="3" style="padding-top: 0" height="16" align="center"><font color="#2C556D">预算科目</font></td>					  
					<td width="114" rowspan="2" style="padding-top: 0" height="32" align="center"><font color="#2C556D">品目名称</font></td>
					<td width="52" rowspan="2" style="padding-top: 0" height="32" align="center"><font color="#2C556D">课税数量</font></td>
					<td width="58" rowspan="2" style="padding-top: 0" height="32" align="center"><font color="#2C556D">计税金额或销售收入</font></td>
					<td width="46" rowspan="2" style="padding-top: 0" height="32" align="center"><font color="#2C556D">税率或<br/>单位税额</font></td>
					<td width="112" rowspan="2" style="padding-top: 0" height="32" align="center"><font color="#2C556D">税款所属时期</font></td>																	
					<td width="42" rowspan="2" style="padding-top: 0" height="32" align="center"><font color="#2C556D">已缴或扣除额</font></td>
					<td width="46" rowspan="2" style="padding-top: 0" height="32" align="center"><font color="#2C556D">实缴金额</font></td>
				  </tr>
				  <tr>
					<td width="80" style="padding-top: 0" height="16" align="center"><font color="#2C556D">编码</font></td>					  
					<td width="180" style="padding-top: 0" height="16" align="center"><font color="#2C556D">名称</font></td>
					<td width="80" style="padding-top: 0" height="16" align="center"><font color="#2C556D">级次</font></td>
				  </tr>
		 	 <xsl:apply-templates select="taxdoc/sbsj"/><!--##########################################-->				  				  
			 	 <tr>
				<td width="80" style="padding-top: 0" height="15" align="center">
					<font color="#2C556D">金额合计</font>
				</td>			 	 
				<td colspan="8" style="padding-top: 0" height="15">
					<font color="#2C556D">（大写）</font>
					<font color="#d32e2e"><xsl:value-of select="taxdoc/sbsj/jks/sjjedx"/></font>
				</td>
				<td width="46" style="padding-top: 0" height="15" align="right">
					<font color="#d32e2e">￥<xsl:value-of select="taxdoc/sbsj/jks/sjje"/></font>
				</td>
			   </tr>
			</table>            			
		<!--##########################################-->
			<table width="100%" border="2" cellpadding="4" cellspacing="0" bordercolor="#9DB9D2" class="black9" id="AutoNumber5" style="border-collapse: collapse">
              <tr>
                <td width="20%">
										<font color="#2C556D">税务机关： </font>
                  	<p><font color="#d32e2e"><xsl:value-of select="taxdoc/sbsj/swjgzzjgmc"/></font></p>
                  	<p/><font color="#2C556D">填票人：</font>
									<font color="#d32e2e"><xsl:value-of select="//nsrmc"/></font>
								</td>              
                <td width="20%">
				  <font color="#2C556D">缴款单位（人）： </font>
                  <p><font color="#d32e2e"><xsl:value-of select="//nsrmc"/></font></p>
                  <p><font color="#2C556D">经办人&#160;</font></p>
				</td>
                <td width="30%" align="center">
					<font color="#2C556D" >上列款项已收妥并划转收款单位账户</font>
                  	<p/><font color="#2C556D">国库（银行）盖章</font>
                  	<p/><font color="#2C556D">&#160;&#160;&#160;&#160;年&#160;&#160;月&#160;&#160;日</font>
				</td>				
                <td width="30%">
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
<!--税收票证升级项目取消缴款书税种税目代码的显示及打印-->
<xsl:template match="taxdoc/sbsj">
	<xsl:for-each select="smitem">
		 <tr>
                <td width="80" style="padding-top: 0" height="16">
					<font color="#d32e2e"><xsl:value-of select="yskmbm"/></font>
				</td>
                <td width="180" style="padding-top: 0" height="16">
					<font color="#d32e2e"><xsl:value-of select="yskmmc"/></font>
				</td>
                <td width="80" style="padding-top: 0" height="16">
					<font color="#d32e2e"><xsl:value-of select="yskmjc"/></font>
				</td>										 
                <td width="114" style="padding-top: 0" height="16">
					<font color="#d32e2e"><xsl:value-of select="szsmmc"/></font>
				</td>
                <td width="52" style="padding-top: 0" height="16">
				 <font color="#d32e2e"><xsl:value-of select="kssl"/></font>
				</td>
                <td width="58" style="padding-top: 0" height="16">
					<div align="right"><font color="#d32e2e"><xsl:value-of select="jsje"/></font></div>
				</td>
                <td width="46" style="padding-top: 0" height="16">
					<div align="right"></div>
				</td>					
                <td width="112" style="padding-top: 0" height="16">
					<font color="#d32e2e"><xsl:value-of select="sksssq"/></font>
				</td>
                <td width="42" style="padding-top: 0" height="16">
					<div align="right"></div>
				</td>									
                <td width="46" style="padding-top: 0" height="16">
					<div align="right"><font color="#d32e2e"><xsl:value-of select="sjse"/></font></div>
				</td>
          </tr>
	</xsl:for-each>
			
</xsl:template>

</xsl:stylesheet>    
		   
		   
		   
		   
		   
	