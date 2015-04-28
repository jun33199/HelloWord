<?xml version="1.0" encoding="gb2312"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" />
	<xsl:template match="/">

		<table id="maintable" align="center" cellspacing="0" class="table-99" width="1000">
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
					<input name="zgfwjdlx" type="hidden" id="zgfwjdlx">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/sbxx/zgfwjdlx" />
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
					<input name="sybs" type="hidden" id="sybs">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrxx/sybs" />
						</xsl:attribute>
					</input>
				</td>
			</tr>
			<tr>
				<td colspan="7" class="1-td1">中华人民共和国<br/>企业所得税分支机构年度纳税申报表</td>
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
				<table width="100%">
					<td>
						<table cellspacing="0" class="table-99" align="center" width="50%">
							<tr>
								<td class="1-td2" colspan="7" style="border-top: 1px solid #336699;">
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
						</table>
					</td>
				
					<td>
						<div id="Layer2" style="position:static;">
				            <table id="wrklistTable" border="1" cellspacing="0" class="table-99" align="center" width="50%">		  
				              <tr>
				                <td height="52px" colspan="7" nowrap="nowrap" class="1-td2" style="border-top: 1px solid #336699;">
									<input type="radio" name="lje_nsfs" style='text-align:right' size='13' disabled='true' >
										<xsl:attribute name="onclick">javascript: compute_Row_1()</xsl:attribute>
										<xsl:attribute name="value">1</xsl:attribute>
									</input>汇总纳税
									<!--  
									<input name="nsfs" type="hidden" id="nsfs">
										<xsl:attribute name="value">
											<xsl:value-of select="//nsfs" />
										</xsl:attribute>
									</input>
									-->	
				                   </td>
				                   <td colspan="7" class="1-td2" nowrap="nowrap" style="border-top: 1px solid #336699;">
										<div align="left">
											<input type='radio' name='lje_zfjg' style='text-align:right' size='13'  disabled='true' >			
												<xsl:attribute name="onclick">javascript: compute_Row_2()</xsl:attribute>
												<xsl:attribute name="disabled">true</xsl:attribute>
												<xsl:attribute name="value">1</xsl:attribute>
											</input>总机构
											<br/>
											<input type='radio' name='lje_zfjg' style='text-align:right' size='13'  disabled='true' >			
												<xsl:attribute name="onclick">javascript: compute_Row_2()</xsl:attribute>
												<xsl:attribute name="disabled">true</xsl:attribute>
												<xsl:attribute name="value">2</xsl:attribute>
											</input>分支机构
											<input name="zfjg" type="hidden" id="zfjg">
												<xsl:attribute name="value">
												<xsl:value-of select="//zfjg" />
											</xsl:attribute>
										</input>		
										</div>
									</td>
		
				                   <td colspan="7" nowrap="nowrap" class="1-td2" style="border-top: 1px solid #336699;">
										<input type="radio" name="lje_nsfs" style='text-align:right' size='13' checked="true"  disabled='true' >
											<xsl:attribute name="onclick">javascript: compute_Row_1()</xsl:attribute>
											<xsl:attribute name="value">2</xsl:attribute>
										</input>独立纳税
										<input name="nsfs" type="hidden" id="nsfs">
											<xsl:attribute name="value">
												<xsl:value-of select="//nsfs" />
											</xsl:attribute>
										</input>
				                   </td>
				              </tr>
					        </table>
				        </div>			  
				    </td>
			    </table>

		
				

		

		    </tr>
	</xsl:template>

	<xsl:template match="taxdoc/sbsj">
	<tr>

		<table id="wrklistTable" border="1" cellspacing="0" class="table-99" align="center">
			<tr>
				<td class="2-td2-t-left" nowrap="nowrap">注册资本(万元，必填项)</td>
				<td class="2-td2-t-left" nowrap="nowrap">
					<input type='text' name='zczbje' id='zczbje' value='zczbje' size='13' maxlength='16' tabindex='2' onblur="checkNumInput(this)" onchange ='return formatData10(this,1);'>
							<xsl:attribute name="value">
								<xsl:value-of select="zczbje" />
							</xsl:attribute>
						</input>
				</td>
				<td class="2-td2-t-left" nowrap="nowrap">资产总额(万元，必填项)</td>
				<td class="2-td2-t-center" nowrap="nowrap">
					<input type='text' name='zcze' id='zcze' value='zcze' size='13' maxlength='16' tabindex='2' onblur="checkNumInput(this)" onchange ='return formatData10(this,0);'>
						<xsl:attribute name="value">
							<xsl:value-of select="zcze" />
							</xsl:attribute>
					</input>
				</td>
			</tr>
		</table>

		<input name="queryFlag" type="hidden" id="queryFlag">
			<xsl:attribute name="value">
				<xsl:value-of select="queryFlag" />
			</xsl:attribute>
		</input>
		
	　　<td class="1-td2" colspan="7">
		<table class="table-99" align="center">
		  <tr>					
            <td> 
              <div id="Layer2" style="position:static;">	
				  <table id="wrklistTable" border="1" cellspacing="0" class="table-99" align="center" width="100%">
					  <tr> 
	                    <td width="5%" nowrap="nowrap" class="2-td1-left"><div align="center">行次</div></td>
	                    <td colspan="2" width="60%" nowrap="nowrap" class="2-td1-left"><div align="center">项目</div></td>
	                    <td colspan="2" width="15%" nowrap="nowrap" class="2-td1-left"><div align="center">本期金额</div></td>
	                    <td colspan="2" width="15%" nowrap="nowrap" class="2-td1-center">累计金额</td>
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
								id='3_1' style='text-align:right' value='0.00' size='13'
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
								id='4_1' style='text-align:right' value='0.00' size='13'
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
								id='5_1' style='text-align:right' value='0.00' size='13'
								onblur="checkNumInput(this)" onchange="compute_Row_5()" >
								<xsl:attribute name="value">
									<xsl:value-of select="lrze" />
								</xsl:attribute>
							</input>
					    </td>
	                  </tr>
	                  <tr> 
	                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">5</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="left">加：特定业务计算的应纳税所得额</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="center">---</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-center">
							<input type='text' name='tdjsynssde'
								id='6_1' style='text-align:right' value='0.00' size='13'
								onblur="checkNumInput(this)" onchange="compute_Row_6()" >
								<xsl:attribute name="value">
									<xsl:value-of select="tdjsynssde" />
								</xsl:attribute>
							</input>
					    </td>
	                  </tr>
	                  <tr> 
	                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">6</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="left">减：不征税收入</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="center">---</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-center">
							<input type='text' name='bzsr'
								id='7_1' style='text-align:right' value='0.00' size='13'
								onblur="checkNumInput(this)" onchange="compute_Row_7()" >
								<xsl:attribute name="value">
									<xsl:value-of select="bzsr" />
								</xsl:attribute>
							</input>
					    </td>
	                  </tr>
	                  <tr> 
	                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">7</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="left">&#160;&#160;&#160;&#160;免税收入</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="center">---</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-center">
							<input type='text' name='mssr'
								id='8_1' style='text-align:right' value='0.00' size='13'
								onblur="checkNumInput(this)" onchange="compute_Row_8()" >
								<xsl:attribute name="value">
									<xsl:value-of select="mssr" />
								</xsl:attribute>
							</input>
					    </td>
	                  </tr>
	                  <tr> 
	                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">8</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="left">&#160;&#160;&#160;&#160;减征、免征应纳税所得额</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="center">---</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-center">
							<input type='text' name='jmzynssde'
								id='9_1' style='text-align:right' value='0.00' size='13'
								onblur="checkNumInput(this)" onchange="compute_Row_8_1()" >
								<xsl:attribute name="value">
									<xsl:value-of select="jmzynssde" />
								</xsl:attribute>
							</input>
					    </td>
	                  </tr>
	                  <tr> 
	                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">9</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="left">&#160;&#160;&#160;&#160;弥补以前年度亏损</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="center">---</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-center">
							<input type='text' name='mbyqndks'
								id='10_1' style='text-align:right' value='0.00' size='13'
								onblur="checkNumInput(this)" onchange="compute_Row_9()" >
								<xsl:attribute name="value">
									<xsl:value-of select="mbyqndks" />
								</xsl:attribute>
							</input>
					    </td>
	                  </tr>
	                  <tr> 
	                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">10</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="left">实际利润额（4行+5行-6行-7行-8行-9行）</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="center">---</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-center">
							<input type='text' name='sjlre'
								id='11_1' style='text-align:right' value='0.00' size='13'
								onblur="checkNumInput(this)" onchange="compute_Row_10()" readonly="true">
								<xsl:attribute name="value">
									<xsl:value-of select="sjlre" />
								</xsl:attribute>
							</input>
					    </td>
	                  </tr>
	                  <tr> 
	                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">11</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="left">税率（25%）</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="center">---</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-center">
							&#160;<input type='text' name='sl'
								id='12_1' style='text-align:right' value='25' size='13'
								onblur="checkNumInput(this)" onchange="compute_Row_11()" readonly="true">
								<xsl:attribute name="value">
									<xsl:value-of select="sl" />
								</xsl:attribute>
							</input>%
					    </td>
	                  </tr>
	                  <tr> 
	                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">12</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="left">应纳所得税额</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="center">---</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-center">
							<input type='text' name='ynsdse'
								id='13_1' style='text-align:right' value='0.00' size='13'
								onblur="checkNumInput(this)" onchange="compute_Row_12()" >
								<xsl:attribute name="value">
									<xsl:value-of select="ynsdse" />
								</xsl:attribute>
							</input>
					    </td>
	                  </tr>
           <tr> 
	                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">13</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="left">减：减免所得税额</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="center">---</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-center">
							<input type='text' name='jmsdse'
								id='14_1' style='text-align:right' value='0.00' size='13'
								onblur="checkNumInput(this)" onchange="compute_Row_13()" >
								<xsl:attribute name="value">
									<xsl:value-of select="jmsdse" />
								</xsl:attribute>
							</input>
					    </td>
	                  </tr>
					   <tr> 
	                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">14</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="left">&#160;&#160;&#160;&#160;其中：符合条件的小型微利企业减免所得税额</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="center">---</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-center">
							<input type='text' name='xwqyjmsdse'
								id='15_1' style='text-align:right' value='0.00' size='13'
								onblur="checkNumInput(this)" onchange="compute_Row_14()" >
								<xsl:attribute name="value">
									<xsl:value-of select="xwqyjmsdse" />
								</xsl:attribute>
							</input>
					    </td>
	                  </tr>
	                  <tr> 
	                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">15</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="left">减：实际已预缴所得税额</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="center">---</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-center">
							<input type='text' name='sjyyjsdse'
								id='16_1' style='text-align:right' value='0.00' size='13'
								onblur="checkNumInput(this)" onchange="compute_Row_15()" >
								<xsl:attribute name="value">
									<xsl:value-of select="sjyyjsdse" />
								</xsl:attribute>
							</input>
					    </td>
	                  </tr>
	                  <tr> 
	                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">16</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="left">减：特定业务预缴（征）所得税额</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="center">---</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-center">
							<input type='text' name='tdywyjsdse'
								id='17_1' style='text-align:right' value='0.00' size='13'
								onblur="checkNumInput(this)" onchange="compute_Row_16()" >
								<xsl:attribute name="value">
									<xsl:value-of select="tdywyjsdse" />
								</xsl:attribute>
							</input>
					    </td>
	                  </tr>
	                  <tr> 
	                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">17</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="left">应补（退）所得税额（12行-13行-15行-16行）</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="center">---</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-center">
							<input type='text' name='ybtsdse'
								id='18_1' style='text-align:right' value='0.00' size='13'
								onblur="checkNumInput(this)" onchange="compute_Row_17()" readonly="true">
								<xsl:attribute name="value">
									<xsl:value-of select="ybtsdse" />
								</xsl:attribute>
							</input>
					    </td>
	                  </tr>
	                  <tr> 
	                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">18</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="left">减：以前年度多缴在本期抵缴所得税额</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="center">---</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-center">
							<input type='text' name='yqnddjsdse'
								id='19_1' style='text-align:right' value='0.00' size='13'
								onblur="checkNumInput(this)" onchange="compute_Row_18()" >
								<xsl:attribute name="value">
									<xsl:value-of select="yqnddjsdse" />
								</xsl:attribute>
							</input>
					    </td>
	                  </tr>
	                  <tr> 
	                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">19</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="left">本月（季）实际应补（退）所得税额</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="center">---</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-center">
							<input type='text' name='bqsjybtsdse'
								id='20_1' style='text-align:right' value='0.00' size='13'
								onblur="checkNumInput(this)" onchange="compute_Row_19()" readonly="true">
								<xsl:attribute name="value">
									<xsl:value-of select="bqsjybtsdse" />
								</xsl:attribute>
							</input>
					    </td>
	                  </tr>
	                  <tr> 
	                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">20</div></td>
	                    <td colspan="6" nowrap="nowrap" class="2-td2-center"><div align="left"><B>二、按照上一纳税年度应纳税所得额平均额预缴</B></div></td>
	                  </tr>
	                  <tr> 
	                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">21</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="left">上一纳税年度应纳税所得额</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="center">---</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-center"><div align="center">---</div></td>
	                  </tr>
	                  <tr> 
	                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">22</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="left">本月（季）应纳税所得额（21行×1/4或1/12）</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="center">---</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-center"><div align="center">---</div></td>
	                  </tr>
	                  <tr> 
	                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">23</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="left">税率(25%)</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="center">---</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-center"><div align="center">---</div></td>
	                  </tr>
	                  <tr> 
	                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">24</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="left">本月（季）应纳所得税额（22行×23行）</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="center">---</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-center"><div align="center">---</div></td>
	                  </tr>
					   <tr> 
	                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">25</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="left">减：符合条件的小型微利企业减免所得税额</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="center">---</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-center"><div align="center">---</div></td>
	                  </tr>
					   <tr> 
	                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">26</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="left">本月（季）实际应纳所得税额（24行-25行）</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="center">---</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-center"><div align="center">---</div></td>
	                  </tr>
	                  <tr> 
	                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">27</div></td>
	                    <td colspan="6" nowrap="nowrap" class="2-td2-center"><div align="left"><B>三、按照税务机关确定的其他方法预缴</B></div></td>
	                  </tr>
	                  <tr> 
	                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">28</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="left">本月（季）税务机关确定的预缴所得税额</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="center">---</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-center"><div align="center">---</div></td>
	                  </tr>
	                  <tr> 
	                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">29</div></td>
	                    <td colspan="6" nowrap="nowrap" class="2-td2-center"><div align="center"><B>总分机构纳税人</B></div></td>
	                  </tr>
	                  <tr> 
	                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">30</div></td>
	                    <td colspan="1" rowspan="4" width="20%" class="2-td2-left"><div align="center">总机构</div></td>
	                    <td colspan="1" nowrap="nowrap" width="40%" class="2-td2-left"><div align="left">总机构分摊所得税额（19行或26行或28行×总机构分摊预缴比例）</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="center">---</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-center">
							<input type='text' name='zjgyftsdse'
								id='30_1' style='text-align:right' value='0.00' size='13'
								onblur="checkNumInput(this)" onchange="compute_Row_30()" readOnly="true">
								<xsl:attribute name="value">
									<xsl:value-of select="zjgyftsdse" />
								</xsl:attribute>
							</input>
					    </td>
	                  </tr>
	                  <tr> 
	                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">31</div></td>
	                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="left">财政集中分配所得税额</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="center">---</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-center">
							<input type='text' name='czjzfpsdse'
								id='31_1' style='text-align:right' value='0.00' size='13'
								onblur="checkNumInput(this)" onchange="compute_Row_31()" readOnly="true">
								<xsl:attribute name="value">
									<xsl:value-of select="czjzfpsdse" />
								</xsl:attribute>
							</input>
					    </td>
	                  </tr>
	                  <tr> 
	                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">32</div></td>
	                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="left">分支机构分摊所得税额(19行或26行或28行×分支机构分摊比例）</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="center">---</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-center">
							<input type='text' name='fzjgyftsdse'
								id='32_1' style='text-align:right' value='0.00' size='13'
								onblur="checkNumInput(this)" onchange="compute_Row_32()" readOnly="true">
								<xsl:attribute name="value">
									<xsl:value-of select="fzjgyftsdse" />
								</xsl:attribute>
							</input>
					    </td>
	                  </tr>
					 
					  <tr> 
						<td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">33</div></td>
		                <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="left">其中：总机构独立生产经营部门应分摊所得税额</div></td>
		                <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="center">---</div></td>
		                <td colspan="2" nowrap="nowrap" class="2-td2-center">
							<input type='text' name='zjgdlscjybmyftsdse' 
								id='33_1' style='text-align:right' value='0.00' size='13' 
								onblur="checkNumInput(this)" onchange="compute_Row_33()" >
								<xsl:attribute name="value">
									<xsl:value-of select="zjgdlscjybmyftsdse" />
								</xsl:attribute>
							</input>
						</td>
			          </tr>
	                  <tr> 
	                  <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">34</div></td>
	                  <td colspan="1" rowspan="3" nowrap="nowrap" class="2-td2-left"><div align="center">分支机构</div></td>
	                  <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="left">分配比例</div></td>
	                  <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="center">---</div></td>
	                  <td colspan="2" nowrap="nowrap" class="2-td2-center">
						  &#160;<input type='text' name='fpbl'
							id='34_1' style='text-align:right' value='0.00' size='13'
							onblur="checkNumInput(this)" onchange="compute_Row_34()">
							<xsl:attribute name="value">
								<xsl:value-of select="fpbl" />
							</xsl:attribute>
						  </input>%
					  </td>
	                  </tr>
	                  <tr> 
	                  <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">35</div></td>
	                  <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="left">分配所得税额</div></td>
	                  <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="center">---</div></td>
	                  <td colspan="2" nowrap="nowrap" class="2-td2-center">
						  <input type='text' name='fpsdse'
								id='35_1' style='text-align:right' value='0.00' size='13'
								onblur="checkNumInput(this)" onchange="compute_Row_35()">
								<xsl:attribute name="value">
									<xsl:value-of select="fpsdse" />
								</xsl:attribute>
						  </input>
					  </td>
	                  </tr>
		          </table>
              </div>
            </td>
          </tr>
        </table>
	   </td>
	</tr>
	</xsl:template>


</xsl:stylesheet>
