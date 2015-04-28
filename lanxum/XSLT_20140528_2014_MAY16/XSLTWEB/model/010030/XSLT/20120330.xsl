<?xml version="1.0" encoding="gb2312"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" />

<xsl:template match="/">
<table width="96%" align="center" cellspacing="0" class="table-99">
	<tr>			
		<td class="1-td1" colspan="7">中华人民共和国企业所得税汇总纳税分支机构分配表</td>
	</tr>
	<tr>
		<td>
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
			<input name="nd" type="hidden" id="nd">
				<xsl:attribute name="value">
					<xsl:value-of select="taxdoc/sbxx/nd" />
				</xsl:attribute>
			</input>
			<input name="sbrq" type="hidden" id="sbrq">
				<xsl:attribute name="value">
					<xsl:value-of select="taxdoc/sbxx/sbrq" />
				</xsl:attribute>
			</input>
			<input name="fsdm" type="hidden" id="fsdm">
				<xsl:attribute name="value">
					<xsl:value-of select="taxdoc/sbxx/fsdm" />
				</xsl:attribute>
			</input>
			<input name="jd" type="hidden" id="jd">
				<xsl:attribute name="value">
					<xsl:value-of select="taxdoc/sbxx/jd" />
				</xsl:attribute>
			</input>
			<input name="skssksrq" type="hidden" id="skssksrq">
				<xsl:attribute name="value">
					<xsl:value-of select="taxdoc/sbxx/skssksrq" />
				</xsl:attribute>
			</input>
			<input name="skssjsrq" type="hidden" id="skssjsrq">
				<xsl:attribute name="value">
					<xsl:value-of select="taxdoc/sbxx/skssjsrq" />
				</xsl:attribute>
			</input>
			<input name="jsjdm" type="hidden" id="jsjdm">
				<xsl:attribute name="value">
					<xsl:value-of select="taxdoc/zjgxx/jsjdm" />
				</xsl:attribute>
			</input>
			<input name="sbrqshow" type="hidden" id="sbrqshow">
				<xsl:attribute name="value">
					<xsl:value-of select="taxdoc/sbxx/sbrqshow" />
				</xsl:attribute>
			</input>
		</td>
	</tr>
	<tr>
		<td class="1-td2" colspan="7"><br/>
			<table cellspacing="0" class="table-99">			
				<tr class="black9">
					<td align="center">
						<div align="left">
							<!--申报日期：<xsl:value-of select="taxdoc/sbxx/sbrqshow" />-->
						</div>
					</td>
					<td colspan="2" align="center">税款所属期间：
						<xsl:value-of select="taxdoc/sbxx/skssksrq" />
						&#160;至&#160; 
						<xsl:value-of select="taxdoc/sbxx/skssjsrq" />					
					</td>
				</tr>
				<tr class="black9"> 
					<td width="82%" align="left" colspan="2"> 
						<div align="left">
							总机构名称:&#160;
						<input type='text' name='nsrmc' id='1_1' size='26' tabindex='15' style='text-align:left' readonly='readonly' class="text-gray">
							<xsl:attribute name="value">
								<xsl:value-of select="taxdoc/zjgxx/nsrmc" />
							</xsl:attribute>
						</input>
						</div>
					</td>
					<td width="18%" align="right">金额单位：元（列至角分）</td>
				</tr>
			</table>
			
			<table id="table_30" class="table-99" border="0" cellpadding="0" cellspacing="0">
				<tr> 
					<td width="15%" colspan="2" class="2-td2-t-left">纳税人识别号</td>
					<td width="15%" class="2-td2-t-left">应纳所得税额</td>
					<td colspan="2" class="2-td2-t-left">总机构分摊所得税额</td>
					<td class="2-td2-t-left">总机构财政集中分配所得税额</td>
					<td colspan="2" class="2-td2-t-center">分支机构分摊的所得税额</td>
				</tr>
				<tr> 
					<td colspan="2" class="2-td2-left" align="left">
						<input type='text' name='nsrsbh' id='2_1' size='19' tabindex='15' style='text-align:left' readonly='readonly' class="text-gray">
							<xsl:attribute name="value">
								<xsl:value-of select="taxdoc/zjgxx/nsrsbh" />
							</xsl:attribute>
						</input>
					</td>
					<td class="2-td2-left" align="left">
						<input type='text' name='ynsdse' id='3_1' size='19' tabindex='15' style='text-align:right' onblur="return formatData(this)">
							<xsl:attribute name="value">
								<xsl:value-of select="taxdoc/zjgxx/ynsdse" />
							</xsl:attribute>
						</input>
					</td>
					<td class="2-td2-left" align="left" colspan="2">
						<input type='text' name='ftsdse' id='4_1' style='text-align:right' size='12' value='0.00' onblur="return formatData(this)">
							<xsl:attribute name="value">
								<xsl:value-of select="taxdoc/zjgxx/ftsdse" />
							</xsl:attribute>
						</input>
					</td>
					<td class="2-td2-left" align="left">
						<input type='text' name='fpsdse' id='5_1' style='text-align:right' size='12' value='0.00' onblur="return formatData(this)">
							<xsl:attribute name="value">
								<xsl:value-of select="taxdoc/zjgxx/fpsdse" />
							</xsl:attribute>
						</input>
					</td>
					<td colspan="2" align="left" class="2-td2-center">
						<input type='text' name='fzjgftse' id='6_1' style='text-align:right' size='20' value='0.00' readonly='readonly' class='text-gray' onChange="return formatData(this)" >
							<xsl:attribute name="value">
								<xsl:value-of select="taxdoc/zjgxx/fzjgftse" />
							</xsl:attribute>
						</input>
					</td>
				</tr>
				<tr> 
					<td rowspan="19" id="fzjgqk" valign="middle" class="2-td2-left">分支机构情况</td>
					<td rowspan="2" align="left" class="2-td2-left">纳税人识别号</td>
					<td rowspan="2" class="2-td2-left">分支机构名称</td>
					<td colspan="3" align="left" class="2-td2-left">三项因素</td>
					<td width="6%" rowspan="2" align="left" class="2-td2-left">分配比例</td>
					<td width="12%" rowspan="2" align="left" class="2-td2-center">分配税额</td>
				</tr>
				<tr> 
					<td class="2-td2-left">收入总额</td>
					<td class="2-td2-left">工资总额</td>
					<td class="2-td2-left">资产总额</td>
				</tr>
				<xsl:apply-templates select="taxdoc/fzjgxx" />
				<tr> 
					<td class="2-td2-left">
						合计
					</td>
				<td class="2-td2-left">
						——
					</td>
				<td class="2-td2-left" align="left">
						<input type='text' name='srehj' id='7_1' style='text-align:right' value='0.00' size='13' readonly='readonly' class="text-gray" >
							<xsl:attribute name="value">
								<xsl:value-of select="taxdoc/fzjghj/srehj" />
							</xsl:attribute>
						</input>
					</td>
				<td class="2-td2-left" align="left">
						<input type='text' name='gzehj' id='8_1' style='text-align:right' value='0.00' size='13' readonly='readonly' class="text-gray" >
							<xsl:attribute name="value">
								<xsl:value-of select="taxdoc/fzjghj/gzehj" />
							</xsl:attribute>
						</input>
					</td>
				<td class="2-td2-left" align="left">
						<input type='text' name='zcehj' id='9_1' style='text-align:right' value='0.00' size='13' readonly='readonly' class="text-gray" >
							<xsl:attribute name="value">
								<xsl:value-of select="taxdoc/fzjghj/zcehj" />
							</xsl:attribute>
						</input>
					</td>
				<td align="left" class="2-td2-left"> 
						<input type='text' name='fpblhj' id='10_1' style='text-align:right' value='0' size='5' onblur="checkPercent(this)" readonly='readonly' class="text-gray"> 
							<xsl:attribute name="value">
								<xsl:value-of select="taxdoc/fzjghj/fpblhj" />
							</xsl:attribute>
						</input>
				</td>
				<td align="left" class="2-td2-center">
						<input type='text' name='fpsehj' id='11_1' style='text-align:right' value='0.00' size='13' onblur="checkInput(this)" readonly='readonly' class="text-gray">
							<xsl:attribute name="value">
								<xsl:value-of select="taxdoc/fzjghj/fpsehj" />
							</xsl:attribute>
						</input>
					</td>
				</tr>
			</table>
		</td>
	</tr>	
</table>
</xsl:template>
<xsl:template match="taxdoc/fzjgxx/mxxx">
	<tr> 
		<td class="2-td2-left" align="left">
			<input type='text' name='fzjgnsrsbh' size='19' maxlength='18' style='text-align:left'>
				<xsl:attribute name="id">12.<xsl:value-of select="index" />_1</xsl:attribute>
				<xsl:attribute name="value">
					<xsl:value-of select="nsrsbh" />
				</xsl:attribute>
				<xsl:attribute name="onblur">checkNsrsbh('.<xsl:value-of select="index" />_1')</xsl:attribute>
			</input>
		</td>
        <td class="2-td2-left" align="left">
			<input type='text' name='fzjgmc' size='20' style='text-align:left'>
				<xsl:attribute name="id">13.<xsl:value-of select="index" />_1</xsl:attribute>
				<xsl:attribute name="value">
					<xsl:value-of select="nsrmc" />
				</xsl:attribute>
			</input>
		</td>
        <td class="2-td2-left" align="left">
			<input type='text' name='fzjgsrze' style='text-align:right' value='0.00' size='13' onblur="formatData(this)" >
				<xsl:attribute name="id">14.<xsl:value-of select="index" />_1</xsl:attribute>
				<xsl:attribute name="value">
					<xsl:value-of select="srze" />
				</xsl:attribute>
				<xsl:attribute name="onchange">compute_Row_7('fzjgsrze','srehj');</xsl:attribute>
			</input>
		</td>
        <td class="2-td2-left" align="left">
			<input type='text' name='fzjggzze' style='text-align:right' value='0.00' size='13' onblur="formatData(this)" >
				<xsl:attribute name="id">15.<xsl:value-of select="index" />_1</xsl:attribute>
				<xsl:attribute name="value">
					<xsl:value-of select="gzze" />
				</xsl:attribute>
				<xsl:attribute name="onchange">compute_Row_8('fzjggzze','gzehj')</xsl:attribute>
			</input>
		</td>
        <td class="2-td2-left" align="left">
			<input type='text' name='fzjgzcze' style='text-align:right' value='0.00' size='13' onblur="formatData(this)" >
				<xsl:attribute name="id">16.<xsl:value-of select="index" />_1</xsl:attribute>
				<xsl:attribute name="value">
					<xsl:value-of select="zcze" />
				</xsl:attribute>
				<xsl:attribute name="onchange">compute_Row_9('fzjgzcze','zcehj')</xsl:attribute>
			</input>
		</td>
        <td align="left" class="2-td2-left"> 
		  	<input type='text' name='fzjgfpbl' style='text-align:right' value='0' size='5' onblur="checkPercent(this)"> 
				<xsl:attribute name="id">17.<xsl:value-of select="index" />_1</xsl:attribute>
				<xsl:attribute name="value">
					<xsl:value-of select="fpbl" />
				</xsl:attribute>
				<xsl:attribute name="onchange">compute_Row_10('fzjgfpbl','fpblhj')</xsl:attribute>
			</input>
        </td>
        <td align="left" class="2-td2-center">
			<input type='text' name='fzjgfpse' style='text-align:right' value='0.00' size='13' onblur="checkInput(this)">
				<xsl:attribute name="id">18.<xsl:value-of select="index" />_1</xsl:attribute>
				<xsl:attribute name="value">
					<xsl:value-of select="fpse" />
				</xsl:attribute>
				<xsl:attribute name="onchange">compute_Row_11('fzjgfpse','fpsehj')</xsl:attribute>
			</input>
		</td>
	</tr>
	</xsl:template>
</xsl:stylesheet>
