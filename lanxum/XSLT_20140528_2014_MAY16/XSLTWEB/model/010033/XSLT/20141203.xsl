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
				<td colspan="7" class="1-td1">�л����񹲺͹�<br/>��ҵ����˰��֧���������˰�걨��</td>
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
						&#160;&#160;&#160;&#160;�걨����:
						<xsl:value-of select="//sbrqshow" />
					</div>
				</td>
				<td class="1-td2" colspan="3">
					<div align="center">
						&#160;&#160;&#160;&#160;&#160;&#160;&#160;˰���������ڣ�
						<xsl:value-of select="//skssksrq" />
						&#160;��&#160;
						<xsl:value-of select="//skssjsrq" />
					</div>
				</td>
				<td class="1-td2" colspan="1">
					<div align="right">
						��λ��Ԫ�������Ƿ֣�
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
										&#160;&#160;&#160;&#160;���������:
										<xsl:value-of select="jsjdm" />
									</div>
								</td>
							</tr>
							
							<tr>	
								<td class="1-td2" colspan="7">
									<div align="left">
										&#160;&#160;&#160;&#160;��˰��ʶ���:
										<xsl:value-of select="nsrsbh"/>
									</div>
								</td>
							</tr>
							<tr>			
								<td class="1-td2" colspan="7">
									<div align="left">
										&#160;&#160;&#160;&#160;��˰�����ƣ�
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
									</input>������˰
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
											</input>�ܻ���
											<br/>
											<input type='radio' name='lje_zfjg' style='text-align:right' size='13'  disabled='true' >			
												<xsl:attribute name="onclick">javascript: compute_Row_2()</xsl:attribute>
												<xsl:attribute name="disabled">true</xsl:attribute>
												<xsl:attribute name="value">2</xsl:attribute>
											</input>��֧����
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
										</input>������˰
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
				<td class="2-td2-t-left" nowrap="nowrap">ע���ʱ�(��Ԫ��������)</td>
				<td class="2-td2-t-left" nowrap="nowrap">
					<input type='text' name='zczbje' id='zczbje' value='zczbje' size='13' maxlength='16' tabindex='2' onblur="checkNumInput(this)" onchange ='return formatData10(this,1);'>
							<xsl:attribute name="value">
								<xsl:value-of select="zczbje" />
							</xsl:attribute>
						</input>
				</td>
				<td class="2-td2-t-left" nowrap="nowrap">�ʲ��ܶ�(��Ԫ��������)</td>
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
		
	����<td class="1-td2" colspan="7">
		<table class="table-99" align="center">
		  <tr>					
            <td> 
              <div id="Layer2" style="position:static;">	
				  <table id="wrklistTable" border="1" cellspacing="0" class="table-99" align="center" width="100%">
					  <tr> 
	                    <td width="5%" nowrap="nowrap" class="2-td1-left"><div align="center">�д�</div></td>
	                    <td colspan="2" width="60%" nowrap="nowrap" class="2-td1-left"><div align="center">��Ŀ</div></td>
	                    <td colspan="2" width="15%" nowrap="nowrap" class="2-td1-left"><div align="center">���ڽ��</div></td>
	                    <td colspan="2" width="15%" nowrap="nowrap" class="2-td1-center">�ۼƽ��</td>
			          </tr>
	                  <tr> 
	                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">1</div></td>
	                    <td colspan="6" nowrap="nowrap" class="2-td2-center"><div align="left"><B>һ����ʵԤ��</B></div></td>
	                  </tr>
	                  <tr> 
	                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">2</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="left">Ӫҵ����</div></td>
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
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="left">Ӫҵ�ɱ�</div></td>
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
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="left">�����ܶ�</div></td>
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
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="left">�ӣ��ض�ҵ������Ӧ��˰���ö�</div></td>
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
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="left">��������˰����</div></td>
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
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="left">&#160;&#160;&#160;&#160;��˰����</div></td>
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
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="left">&#160;&#160;&#160;&#160;����������Ӧ��˰���ö�</div></td>
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
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="left">&#160;&#160;&#160;&#160;�ֲ���ǰ��ȿ���</div></td>
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
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="left">ʵ������4��+5��-6��-7��-8��-9�У�</div></td>
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
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="left">˰�ʣ�25%��</div></td>
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
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="left">Ӧ������˰��</div></td>
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
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="left">������������˰��</div></td>
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
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="left">&#160;&#160;&#160;&#160;���У�����������С��΢����ҵ��������˰��</div></td>
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
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="left">����ʵ����Ԥ������˰��</div></td>
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
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="left">�����ض�ҵ��Ԥ�ɣ���������˰��</div></td>
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
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="left">Ӧ�����ˣ�����˰�12��-13��-15��-16�У�</div></td>
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
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="left">������ǰ��ȶ���ڱ��ڵֽ�����˰��</div></td>
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
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="left">���£�����ʵ��Ӧ�����ˣ�����˰��</div></td>
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
	                    <td colspan="6" nowrap="nowrap" class="2-td2-center"><div align="left"><B>����������һ��˰���Ӧ��˰���ö�ƽ����Ԥ��</B></div></td>
	                  </tr>
	                  <tr> 
	                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">21</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="left">��һ��˰���Ӧ��˰���ö�</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="center">---</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-center"><div align="center">---</div></td>
	                  </tr>
	                  <tr> 
	                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">22</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="left">���£�����Ӧ��˰���ö21�С�1/4��1/12��</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="center">---</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-center"><div align="center">---</div></td>
	                  </tr>
	                  <tr> 
	                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">23</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="left">˰��(25%)</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="center">---</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-center"><div align="center">---</div></td>
	                  </tr>
	                  <tr> 
	                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">24</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="left">���£�����Ӧ������˰�22�С�23�У�</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="center">---</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-center"><div align="center">---</div></td>
	                  </tr>
					   <tr> 
	                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">25</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="left">��������������С��΢����ҵ��������˰��</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="center">---</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-center"><div align="center">---</div></td>
	                  </tr>
					   <tr> 
	                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">26</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="left">���£�����ʵ��Ӧ������˰�24��-25�У�</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="center">---</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-center"><div align="center">---</div></td>
	                  </tr>
	                  <tr> 
	                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">27</div></td>
	                    <td colspan="6" nowrap="nowrap" class="2-td2-center"><div align="left"><B>��������˰�����ȷ������������Ԥ��</B></div></td>
	                  </tr>
	                  <tr> 
	                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">28</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="left">���£�����˰�����ȷ����Ԥ������˰��</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-left"><div align="center">---</div></td>
	                    <td colspan="2" nowrap="nowrap" class="2-td2-center"><div align="center">---</div></td>
	                  </tr>
	                  <tr> 
	                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">29</div></td>
	                    <td colspan="6" nowrap="nowrap" class="2-td2-center"><div align="center"><B>�ֻܷ�����˰��</B></div></td>
	                  </tr>
	                  <tr> 
	                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="center">30</div></td>
	                    <td colspan="1" rowspan="4" width="20%" class="2-td2-left"><div align="center">�ܻ���</div></td>
	                    <td colspan="1" nowrap="nowrap" width="40%" class="2-td2-left"><div align="left">�ܻ�����̯����˰�19�л�26�л�28�С��ܻ�����̯Ԥ�ɱ�����</div></td>
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
	                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="left">�������з�������˰��</div></td>
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
	                    <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="left">��֧������̯����˰��(19�л�26�л�28�С���֧������̯������</div></td>
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
		                <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="left">���У��ܻ�������������Ӫ����Ӧ��̯����˰��</div></td>
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
	                  <td colspan="1" rowspan="3" nowrap="nowrap" class="2-td2-left"><div align="center">��֧����</div></td>
	                  <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="left">�������</div></td>
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
	                  <td colspan="1" nowrap="nowrap" class="2-td2-left"><div align="left">��������˰��</div></td>
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
