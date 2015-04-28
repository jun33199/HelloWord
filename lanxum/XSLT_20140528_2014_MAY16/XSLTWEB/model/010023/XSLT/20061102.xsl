<?xml version="1.0" encoding="gb2312"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html"/>
	<xsl:template match="/">
		
		<table id="maintable" align="center" cellspacing="0" class="table-99">
		<tr><td>
		<input name="xsltVersion" type="hidden" id="xsltVersion">
			<xsl:attribute name="value"><xsl:value-of select="taxdoc/xsltVersion"/></xsl:attribute>
		</input>
		<input name="schemaVersion" type="hidden" id="schemaVersion">
			<xsl:attribute name="value"><xsl:value-of select="taxdoc/schemaVersion"/></xsl:attribute>
		</input>
		<input name="ywlx" type="hidden" id="ywlx">
			<xsl:attribute name="value"><xsl:value-of select="taxdoc/ywlx"/></xsl:attribute>
		</input>
		<input name="ywczlx" type="hidden" id="ywczlx">
			<xsl:attribute name="value"><xsl:value-of select="taxdoc/ywczlx"/></xsl:attribute>
		</input>


                <input name="jmzg" type="hidden" id="jmzg">
			<xsl:attribute name="value"><xsl:value-of select="taxdoc/hdxx/jmzg"/></xsl:attribute>
		</input>
		<input name="ybjmsl" type="hidden" id="ybjmsl">
			<xsl:attribute name="value"><xsl:value-of select="taxdoc/hdxx/ybjmsl"/></xsl:attribute>
		</input>
		<input name="qyzslx" type="hidden" id="qyzslx">
			<xsl:attribute name="value"><xsl:value-of select="taxdoc/hdxx/qyzslx"/></xsl:attribute>
		</input>
		<input name="xzqy" type="hidden" id="xzqy">
			<xsl:attribute name="value"><xsl:value-of select="taxdoc/hdxx/xzqy"/></xsl:attribute>
		</input>
		<input name="cyl" type="hidden" id="cyl">
			<xsl:attribute name="value"><xsl:value-of select="taxdoc/hdxx/cyl"/></xsl:attribute>
		</input>
		<input name="dezsse" type="hidden" id="dezsse">
			<xsl:attribute name="value"><xsl:value-of select="taxdoc/hdxx/dezsse"/></xsl:attribute>
		</input>


		<input name="nd" type="hidden" id="nd">
			<xsl:attribute name="value"><xsl:value-of select="taxdoc/sbxx/nd"/></xsl:attribute>
		</input>
		<input name="sbrq" type="hidden" id="sbrq">
			<xsl:attribute name="value"><xsl:value-of select="taxdoc/sbxx/sbrq"/></xsl:attribute>
		</input>
		<input name="skssjsrq" type="hidden" id="skssjsrq">
			<xsl:attribute name="value"><xsl:value-of select="taxdoc/sbxx/skssjsrq"/></xsl:attribute>
		</input>
		<input name="skssksrq" type="hidden" id="skssksrq">
			<xsl:attribute name="value"><xsl:value-of select="taxdoc/sbxx/skssksrq"/></xsl:attribute>
		</input>
		<input name="swjgzzjgdm" type="hidden" id="swjgzzjgdm">
			<xsl:attribute name="value"><xsl:value-of select="taxdoc/nsrxx/swjgzzjgdm"/></xsl:attribute>
		</input>
		<input name="jsjdm" type="hidden" id="jsjdm">
			<xsl:attribute name="value"><xsl:value-of select="taxdoc/nsrxx/jsjdm"/></xsl:attribute>
		</input>
		<input name="nsrmc" type="hidden" id="nsrmc">
			<xsl:attribute name="value"><xsl:value-of select="taxdoc/nsrxx/nsrmc"/></xsl:attribute>
		</input>
		</td></tr>
		<tr>
				 <td class="1-td1">核定征收企业所得税季度纳税申报表</td>
	        </tr>
		<xsl:apply-templates select="taxdoc/nsrxx"/>		
		<xsl:apply-templates select="taxdoc/sbsj"/>
		</table>
    <br/>	
	</xsl:template>
	<xsl:template match="taxdoc/nsrxx">
	
		<tr>
		 <td class="1-td2"  colspan="7"><div align="left">&#160;&#160;&#160;&#160;申报日期:<xsl:value-of select="//sbrqshow"/>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;金额单位：元（列至角分）</div></td>
		</tr>
		<tr>
		 <td class="1-td2"  colspan="7"><div align="left">&#160;&#160;&#160;&#160;计算机代码:<xsl:value-of select="jsjdm"/>&#160;&#160;&#160;&#160;纳税人名称：<xsl:value-of select="nsrmc"/>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;税款所属日期：<xsl:value-of select="//skssksrq"/>&#160;至&#160;<xsl:value-of select="//skssjsrq"/></div></td>
		</tr>	
	</xsl:template>


	<xsl:template match="taxdoc/sbsj">
			<tr>
			<td class="1-td2"  colspan="7">
				<TABLE class="table-99" align="center">
				<TR>
				<TD>
				<table id="wrklistTable" border="1" cellspacing="0" class="table-99" align="center" >
                  <tr> 
                    <td class="2-td1-left" nowrap="nowrap">行次</td>
                    <td class="2-td1-left" nowrap="nowrap">项目</td>
                    <td class="2-td1-left" nowrap="nowrap">本期数</td>
                    <td class="2-td1-center" nowrap="nowrap">累计数</td>                    
                  </tr>
				  <tr> 
                    <td class="2-td2-left" nowrap="nowrap">1</td>
					<td class="2-td2-left" nowrap="nowrap">收入总额</td>
                    <td class="2-td2-left" nowrap="nowrap"><input type='text'  name='srze_bqs' id='srze_bqs'  size='13' tabindex='2' onchange='return formatData(this);'> 
		     <xsl:attribute name="value"><xsl:value-of select="srze_bqs"/></xsl:attribute></input>
		    </td>
                    <td class="2-td2-center" nowrap="nowrap"><input type='text'  name='srze_ljs' id='srze_ljs'  size='13' tabindex='2' onchange='return formatData(this);'>
		    <xsl:attribute name="value"><xsl:value-of select="srze_ljs"/></xsl:attribute></input></td>
                  </tr>    
                  <tr> 
                    <td class="2-td2-left" nowrap="nowrap">2</td>
					<td class="2-td2-left" nowrap="nowrap">成本费用</td>
                    <td class="2-td2-left" nowrap="nowrap">*</td>
                    <td class="2-td2-center" nowrap="nowrap">*</td>
                  </tr>                  
                   
                  <tr> 
                    <td class="2-td2-left" nowrap="nowrap">3</td>
					<td class="2-td2-left" nowrap="nowrap">应税所得率</td>
                    <td class="2-td2-left" nowrap="nowrap"><input type='text'  name='yssdl_bqs' id='yssdl_bqs'  size='13' tabindex='2' onchange='return formatData(this);'> 
		    <xsl:attribute name="value"><xsl:value-of select="yssdl_bqs"/></xsl:attribute></input></td>
                    <td class="2-td2-center" nowrap="nowrap"><input type='text'  name='yssdl_ljs' id='yssdl_ljs'  size='13' tabindex='2' onchange='return formatData(this);'>
		    <xsl:attribute name="value"><xsl:value-of select="yssdl_ljs"/></xsl:attribute></input></td>
                  </tr>    
                   <tr> 
                    <td class="2-td2-left" nowrap="nowrap">4</td>
					<td class="2-td2-left" nowrap="nowrap">应纳税所得额</td>
                    <td class="2-td2-left" nowrap="nowrap"><input type='text'  name='ynssde_bqs' id='ynssde_bqs'  size='13' tabindex='2' onchange='return formatData2(this);'> 
		    <xsl:attribute name="value"><xsl:value-of select="ynssde_bqs"/></xsl:attribute></input></td>
                    <td class="2-td2-center" nowrap="nowrap"><input type='text'  name='ynssde_ljs' id='ynssde_ljs'  size='13' tabindex='2' onchange='return formatData2(this);'>
		    <xsl:attribute name="value"><xsl:value-of select="ynssde_ljs"/></xsl:attribute></input></td>
                  </tr>    
                  <tr> 
                    <td class="2-td2-left" nowrap="nowrap">5</td>
					<td class="2-td2-left" nowrap="nowrap">适用税率</td>
                    <td class="2-td2-left" nowrap="nowrap"><input type='text'  name='sysl_bqs' id='sysl_bqs'  size='13' tabindex='2' readonly='true' onchange='return formatData(this);'>
		    <xsl:attribute name="value"><xsl:value-of select="sysl_bqs"/></xsl:attribute></input></td>
                    <td class="2-td2-center" nowrap="nowrap"><input type='text'  name='sysl_ljs' id='sysl_ljs'  size='13' tabindex='2' readonly='true' onchange='return formatData(this);'>
		    <xsl:attribute name="value"><xsl:value-of select="sysl_ljs"/></xsl:attribute></input></td>
                  </tr>    
                  <tr> 
                    <td class="2-td2-left" nowrap="nowrap">6</td>
					<td class="2-td2-left" nowrap="nowrap">应缴所得税额（4×5）</td>
                    <td class="2-td2-left" nowrap="nowrap"><input type='text'  name='yjsdse_bqs' id='yjsdse_bqs'  size='13' tabindex='2' onchange='return formatData(this);'>
		    <xsl:attribute name="value"><xsl:value-of select="yjsdse_bqs"/></xsl:attribute></input></td>
                    <td class="2-td2-center" nowrap="nowrap"><input type='text'  name='yjsdse_ljs' id='yjsdse_ljs'  size='13' tabindex='2' onchange='return formatData(this);'>
		    <xsl:attribute name="value"><xsl:value-of select="yjsdse_ljs"/></xsl:attribute></input></td>
                  </tr>    
                  <tr> 
                    <td class="2-td2-left" nowrap="nowrap">7</td>
					<td class="2-td2-left" nowrap="nowrap">减：实际已预缴的所得税额</td>
                    <td class="2-td2-left" nowrap="nowrap"><input type='text'  name='sjyyjdsdse_bqs' id='sjyyjdsdse_bqs'  size='13' tabindex='2' onchange='return formatData(this);'> 
		    <xsl:attribute name="value"><xsl:value-of select="sjyyjdsdse_bqs"/></xsl:attribute></input></td>
                    <td class="2-td2-center" nowrap="nowrap"><input type='text'  name='sjyyjdsdse_ljs' id='sjyyjdsdse_ljs'  size='13' tabindex='2' onchange='return formatData(this);'>
		    <xsl:attribute name="value"><xsl:value-of select="sjyyjdsdse_ljs"/></xsl:attribute></input></td>
                  </tr>    
                  <tr> 
                    <td class="2-td2-left" nowrap="nowrap">8</td>
					<td class="2-td2-left" nowrap="nowrap">应补（退）的所得税额（8=6-7）</td>
                    <td class="2-td2-left" nowrap="nowrap"><input type='text'  name='ybdsdse_bqs' id='ybdsdse_bqs'  size='13' tabindex='2' onchange='return formatData(this);'> 
		    <xsl:attribute name="value"><xsl:value-of select="ybdsdse_bqs"/></xsl:attribute></input></td>
                    <td class="2-td2-center" nowrap="nowrap"><input type='text'  name='ybdsdse_ljs' id='ybdsdse_ljs'  size='13' tabindex='2' onchange='return formatData(this);'>
		    <xsl:attribute name="value"><xsl:value-of select="ybdsdse_ljs"/></xsl:attribute></input></td>
                  </tr>    
                </table>
				</TD>
				</TR>
				
				</TABLE>
			</td>
		</tr>
 	</xsl:template>
</xsl:stylesheet>
