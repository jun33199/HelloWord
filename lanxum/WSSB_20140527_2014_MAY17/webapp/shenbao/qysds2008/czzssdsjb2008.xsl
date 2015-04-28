<?xml version="1.0" encoding="gb2312"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" />
	<xsl:template match="/">

		<table id="maintable" align="center" cellspacing="0" class="table-99">
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


					<input name="jmzg" type="hidden" id="jmzg">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/hdxx/jmzg" />
						</xsl:attribute>
					</input>
					<input name="ybjmsl" type="hidden" id="ybjmsl">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/hdxx/ybjmsl" />
						</xsl:attribute>
					</input>
					<input name="qyzslx" type="hidden" id="qyzslx">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/hdxx/qyzslx" />
						</xsl:attribute>
					</input>
					<input name="xzqy" type="hidden" id="xzqy">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/hdxx/xzqy" />
						</xsl:attribute>
					</input>
					<input name="cyl" type="hidden" id="cyl">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/hdxx/cyl" />
						</xsl:attribute>
					</input>
					<input name="dezsse" type="hidden" id="dezsse">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/hdxx/dezsse" />
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
					<input name="skssjsrq" type="hidden" id="skssjsrq">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/sbxx/skssjsrq" />
						</xsl:attribute>
					</input>
					<input name="skssksrq" type="hidden" id="skssksrq">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/sbxx/skssksrq" />
						</xsl:attribute>
					</input>
					<input name="swjgzzjgdm" type="hidden" id="swjgzzjgdm">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrxx/swjgzzjgdm" />
						</xsl:attribute>
					</input>
					<input name="jsjdm" type="hidden" id="jsjdm">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrxx/jsjdm" />
						</xsl:attribute>
					</input>
					<input name="nsrmc" type="hidden" id="nsrmc">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrxx/nsrmc" />
						</xsl:attribute>
					</input>
				</td>
			</tr>
			<tr>
				<td colspan="7" class="1-td1">中华人民共和国<br/>企业所得税月(季)度预缴纳税申报表(A类)</td>
			</tr>
			<xsl:apply-templates select="taxdoc/nsrxx" />
			<xsl:apply-templates select="taxdoc/sbsj" />
		</table>
		<br />
	</xsl:template>
	<xsl:template match="taxdoc/nsrxx">
		<tr>
			<td class="1-td2" colspan="3">
				<div align="left">
					&#160;&#160;&#160;&#160;申报日期:
					<xsl:value-of select="//sbrqshow" />
				</div>
			</td>
			<td class="1-td2" colspan="3">
				<div align="center">
					&#160;&#160;&#160;&#160;&#160;&#160;&#160;税款所属日期：
					<xsl:value-of select="//skssksrq" />
					&#160;至&#160;
					<xsl:value-of select="//skssjsrq" />
				</div>
			</td>
			<td class="1-td2" colspan="1">
				<div align="right">
					金额单位：元（列至角分）
				</div>
			</td>
		</tr>
		<tr>
			<td class="1-td2" colspan="7">
				<div align="left">
					&#160;&#160;&#160;&#160;计算机代码:
					<xsl:value-of select="jsjdm" />
				</div>
			</td>
		</tr>
		<tr>
			
			<td class="1-td2" colspan="7">
				<div align="left">
					&#160;&#160;&#160;&#160;纳税人识别号:
					<xsl:value-of select="nsrsbh"/>
				</div>
			</td>
		</tr>
		<tr>			
			<td class="1-td2" colspan="7">
				<div align="left">
					&#160;&#160;&#160;&#160;纳税人名称：
					<xsl:value-of select="nsrmc" />
				</div>
			</td>
		</tr>
	</xsl:template>


	<xsl:template match="taxdoc/sbsj">
	<tr>
	　　<td class="1-td2" colspan="7">
		<table class="table-99" align="center">
		  <tr>					
            <td width="895"> 
              <div id="Layer2" style="position:static;">					
                <table id="wrklistTable" border="1" cellspacing="0"
						class="table-99" align="center" width="100%">		  
                  <tr>
                    <td colspan="2" nowrap="nowrap" class="2-td1-left"><div align="right">纳税方法：</div></td>
                    <td colspan="3" nowrap="nowrap" class="2-td1-left">
			<input type="radio" name="lje_nsfs" style='text-align:right' size='13' checked="true">
				<xsl:attribute name="onclick">javascript: compute_Row_1()</xsl:attribute>
				<xsl:attribute name="value">2</xsl:attribute>
			</input>独立纳税
			<input type="radio" name="lje_nsfs" style='text-align:right' size='13'>
				<xsl:attribute name="onclick">javascript: compute_Row_1()</xsl:attribute>
				<xsl:attribute name="value">1</xsl:attribute>
			</input>汇总纳税
			<input name="nsfs" type="hidden" id="nsfs">
				<xsl:attribute name="value">
					<xsl:value-of select="nsfs" />
				</xsl:attribute>
			</input>	
                    </td>
                    <td colspan="3" class="2-td1-center" nowrap="nowrap">
			<div align="left">
				<input type='radio' name='lje_zfjg' style='text-align:right' size='13'>			
					<xsl:attribute name="onclick">javascript: compute_Row_2()</xsl:attribute>
					<xsl:attribute name="disabled">true</xsl:attribute>
					<xsl:attribute name="value">1</xsl:attribute>
				</input>总机构
				<br/>
				<input type='radio' name='lje_zfjg' style='text-align:right' size='13'>			
					<xsl:attribute name="onclick">javascript: compute_Row_2()</xsl:attribute>
					<xsl:attribute name="disabled">true</xsl:attribute>
					<xsl:attribute name="value">2</xsl:attribute>
				</input>分支机构
				<input name="zfjg" type="hidden" id="zfjg">
					<xsl:attribute name="value">
					<xsl:value-of select="zfjg" />
				</xsl:attribute>
			</input>		
			</div>
                    </td>
                  </tr>
		  </table>
			<br/>
		  <table id="wrklistTable" border="1" cellspacing="0" class="table-99" align="center" width="100%">
		  <tr> 
                    <td width="40" colspan="1" nowrap="nowrap" class="2-td1-left"><div align="center">行次</div></td>
                    <td colspan="2" width="600" nowrap="nowrap" class="2-td1-left"><div align="center">项目</div></td>
                    <td colspan="2" width="100" nowrap="nowrap" class="2-td1-left"><div align="center">本期金额</div></td>
                    <td colspan="2" width="100" nowrap="nowrap" class="2-td1-center">累计金额</td>
                  </tr>
                  <tr> 
                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">1</div></td>
                    <td colspan="6" nowrap="nowrap" class="2-td2-center"><div align="left"><B>一、据实预缴</B></div></td>
                  </tr>
                  <tr> 
                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">2</div></td>
                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="left">营业收入</div></td>
                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="center">---</div></td>
                    <td colspan="2" nowrap="nowrap" class="2-td2-center">
			<input type='text' name='yysr'
				id='1_1' style='text-align:right' value='0.00' size='13'
				onblur="checkNumInput(this)" onchange="compute_Row_3()" >
				<xsl:attribute name="value">
					<xsl:value-of select="yysr" />
				</xsl:attribute>
			</input>
		    </td>
                  </tr>
                  <tr> 
                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">3</div></td>
                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="left">营业成本</div></td>
                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="center">---</div></td>
                    <td colspan="2" nowrap="nowrap" class="2-td2-center">
			<input type='text' name='yycb'
				id='1_1' style='text-align:right' value='0.00' size='13'
				onblur="checkNumInput(this)" onchange="compute_Row_4()" >
				<xsl:attribute name="value">
					<xsl:value-of select="yycb" />
				</xsl:attribute>
			</input>
		    </td>
                  </tr>
                  <tr> 
                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">4</div></td>
                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="left">利润总额</div></td>
                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="center">---</div></td>
                    <td colspan="2" nowrap="nowrap" class="2-td2-center">
			<input type='text' name='lrze'
				id='1_1' style='text-align:right' value='0.00' size='13'
				onblur="checkNumInput(this)" onchange="compute_Row_5()" >
				<xsl:attribute name="value">
					<xsl:value-of select="lrze" />
				</xsl:attribute>
			</input>
		    </td>
                  </tr>
                  <tr> 
                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">5</div></td>
                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="left">税率（25%）</div></td>
                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="center">---</div></td>
                    <td colspan="2" nowrap="nowrap" class="2-td2-center">
			<input type='text' name='sl'
				id='1_1' style='text-align:right' value='25' size='13'
				onblur="checkNumInput(this)" onchange="compute_Row_6()" readonly="true">
				<xsl:attribute name="value">
					<xsl:value-of select="sl" />
				</xsl:attribute>
			</input>%
		    </td>
                  </tr>
                  <tr> 
                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">6</div></td>
                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="left">应纳所得税额（4行×5行）</div></td>
                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="center">---</div></td>
                    <td colspan="2" nowrap="nowrap" class="2-td2-center">
			<input type='text' name='ynsdse'
				id='1_1' style='text-align:right' value='25' size='13'
				onblur="checkNumInput(this)" onchange="compute_Row_7()" >
				<xsl:attribute name="value">
					<xsl:value-of select="ynsdse" />
				</xsl:attribute>
			</input>
		    </td>
                  </tr>
                  <tr> 
                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">7</div></td>
                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="left">减免所得税额</div></td>
                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="center">---</div></td>
                    <td colspan="2" nowrap="nowrap" class="2-td2-center">
			<input type='text' name='jmsdse'
				id='1_1' style='text-align:right' value='25' size='13'
				onblur="checkNumInput(this)" onchange="compute_Row_8()" >
				<xsl:attribute name="value">
					<xsl:value-of select="jmsdse" />
				</xsl:attribute>
			</input>
		    </td>
                  </tr>
                  <tr> 
                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">8</div></td>
                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="left">实际已缴所得税额</div></td>
                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="center">---</div></td>
                    <td colspan="2" nowrap="nowrap" class="2-td2-center">
			<input type='text' name='sjyjsdse'
				id='1_1' style='text-align:right' value='25' size='13'
				onblur="checkNumInput(this)" onchange="compute_Row_9()" >
				<xsl:attribute name="value">
					<xsl:value-of select="sjyjsdse" />
				</xsl:attribute>
			</input>
		    </td>
                  </tr>
                  <tr> 
                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">9</div></td>
                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="left">应补（退）的所得税额（6行-7行-8行）</div></td>
                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="center">---</div></td>
                    <td colspan="2" nowrap="nowrap" class="2-td2-center">
			<input type='text' name='ybtsdse'
				id='1_1' style='text-align:right' value='25' size='13'
				onblur="checkNumInput(this)" onchange="compute_Row_10()" readonly="true">
				<xsl:attribute name="value">
					<xsl:value-of select="ybtsdse" />
				</xsl:attribute>
			</input>
		    </td>
                  </tr>
                  <tr> 
                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">10</div></td>
                    <td colspan="6" nowrap="nowrap" class="2-td2-center"><div align="left"><B>二、按照上一纳税年度应纳税所得额的平均额预缴</B></div></td>
                  </tr>
                  <tr> 
                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">11</div></td>
                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="left">上一纳税年度应纳税所得额</div></td>
                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="center">---</div></td>
                    <td colspan="2" nowrap="nowrap" class="2-td2-center"><div align="center">---</div></td>
                  </tr>
                  <tr> 
                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">12</div></td>
                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="left">本月（季）应纳税所得额（11行÷12或11行÷4）</div></td>
                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="center">---</div></td>
                    <td colspan="2" nowrap="nowrap" class="2-td2-center"><div align="center">---</div></td>
                  </tr>
                  <tr> 
                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">13</div></td>
                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="left">税率(25%)</div></td>
                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="center">---</div></td>
                    <td colspan="2" nowrap="nowrap" class="2-td2-center"><div align="center">---</div></td>
                  </tr>
                  <tr> 
                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">14</div></td>
                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="left">本月（季）应纳所得税额（12行×13行）</div></td>
                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="center">---</div></td>
                    <td colspan="2" nowrap="nowrap" class="2-td2-center"><div align="center">---</div></td>
                  </tr>
                  <tr> 
                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">15</div></td>
                    <td colspan="6" nowrap="nowrap" class="2-td2-center"><div align="left"><B>三、按照税务机关确定的其他方法预缴</B></div></td>
                  </tr>
                  <tr> 
                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">16</div></td>
                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="left">本月（季）确定预缴的所得税额</div></td>
                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="center">---</div></td>
                    <td colspan="2" nowrap="nowrap" class="2-td2-center"><div align="center">---</div></td>
                  </tr>
                  <tr> 
                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">17</div></td>
                    <td colspan="6" nowrap="nowrap" class="2-td2-center"><div align="center"><B>总分机构纳税人</B></div></td>
                  </tr>
                  <tr> 
                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">18</div></td>
                    <td colspan="1" rowspan="4" nowrap="nowrap" class="2-td2-left"><div align="center">总机构</div></td>
                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="left">总机构应分摊的所得税额（9行或14行或16行×25%）</div></td>
                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="center">---</div></td>
                    <td colspan="2" nowrap="nowrap" class="2-td2-center">
			<input type='text' name='zjgftsdse'
				id='1_1' style='text-align:right' value='25' size='13'
				onblur="formate(this)" onchange="compute_Row_16()" readOnly="true">
				<xsl:attribute name="value">
					<xsl:value-of select="zjgftsdse" />
				</xsl:attribute>
			</input>
		    </td>
                  </tr>
                  <tr> 
                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">19</div></td>
                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="left">中央财政集中分配的所得税额（9行或14行或16行×25%）</div></td>
                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="center">---</div></td>
                    <td colspan="2" nowrap="nowrap" class="2-td2-center">
			<input type='text' name='zyczjzsdse'
				id='1_1' style='text-align:right' value='25' size='13'
				onblur="formate(this)" onchange="compute_Row_17()" readOnly="true">
				<xsl:attribute name="value">
					<xsl:value-of select="zyczjzsdse" />
				</xsl:attribute>
			</input>
		    </td>
                  </tr>
                  <tr> 
                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">20</div></td>
                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="left">分支机构分摊的所得税额（9行或14行或16行×50%）</div></td>
                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="center">---</div></td>
                    <td colspan="2" nowrap="nowrap" class="2-td2-center">
			<input type='text' name='fzjgftsdse'
				id='18_1' style='text-align:right' value='25' size='13'
				onblur="formate(this)" onchange="compute_Row_18()" readOnly="true">
				<xsl:attribute name="value">
					<xsl:value-of select="fzjgftsdse" />
				</xsl:attribute>
			</input>
		    </td>
                  </tr>
			<tr> 
				<td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">20.1</div></td>
                <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="left">&#160;&#160;&#160;&#160;其中：总机构集中缴纳本市分支机构所得税额</div></td>
                <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="center">---</div></td>
                <td colspan="2" nowrap="nowrap" class="2-td2-center">
					<input type='text' name='jzjnfzjgsdse' id='21_1' style='text-align:right' value='0.00' size='13' onblur="checkNumInput(this)" onchange="compute_Row_21()" readOnly="true">
						<xsl:attribute name="value">
							<xsl:value-of select="jzjnfzjgsdse" />
						</xsl:attribute>
					</input>
				</td>
            </tr>
                  <tr> 
                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">21</div></td>
                    <td colspan="1" rowspan="3" nowrap="nowrap" class="2-td2-left"><div align="center">分支机构</div></td>
                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="left">分配比例</div></td>
                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="center">---</div></td>
                    <td colspan="2" nowrap="nowrap" class="2-td2-center">
			<input type='text' name='fpbl'
				id='19_1' style='text-align:right' value='25' size='13'
				onblur="formate(this)" onchange="compute_Row_19()">
				<xsl:attribute name="value">
					<xsl:value-of select="fpbl" />
				</xsl:attribute>
			</input>
		    </td>
                  </tr>
                  <tr> 
                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">22</div></td>
                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="left">分配的所得税额（20行×21行）</div></td>
                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="center">---</div></td>
                    <td colspan="2" nowrap="nowrap" class="2-td2-center">
			<input type='text' name='fpsdse'
				id='20_1' style='text-align:right' value='25' size='13'
				onblur="formate(this)" onchange="compute_Row_20()">
				<xsl:attribute name="value">
					<xsl:value-of select="fpsdse" />
				</xsl:attribute>
			</input>
		    </td>
                  </tr>
                </table>
                </div></td></tr></table>
			</td>
		</tr>
	</xsl:template>
</xsl:stylesheet>
