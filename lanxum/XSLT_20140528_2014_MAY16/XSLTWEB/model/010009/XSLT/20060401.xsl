<?xml version="1.0" encoding="GB2312"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html"/> 
	<xsl:template match="/">
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
		   <xsl:apply-templates select="taxdoc/sbxx"/>
                       <br/>
		   <xsl:apply-templates select="taxdoc/nsrxx"/>
                       <br/>
		   <xsl:apply-templates select="taxdoc/hdxx"/>		 
		   <xsl:apply-templates select="taxdoc/sbsj"/>
	</xsl:template>
	<xsl:template match="taxdoc/sbxx">
		<table cellspacing="0" class="table-99" width="100%">			
				<tr class="2-td2-t-left">
					<input name="fsdm" type="hidden" id="fsdm">
						<xsl:attribute name="value"><xsl:value-of select="fsdm"/></xsl:attribute>
					</input>
					<input name="jd" type="hidden" id="jd">
						<xsl:attribute name="value"><xsl:value-of select="jd"/></xsl:attribute>
					</input>
					<input name="nd" type="hidden" id="nd">
						<xsl:attribute name="value"><xsl:value-of select="nd"/></xsl:attribute>
					</input>
					<input name="sbrq" type="hidden" id="sbrq">
						<xsl:attribute name="value"><xsl:value-of select="sbrq"/></xsl:attribute>
					</input>
					<td nowrap="nowrap" align="left">�걨�������ڣ�<xsl:value-of select="skssksrq"/>��<xsl:value-of select="skssjsrq"/>
						<input name="skssksrq" type="hidden" id="skssksrq" >
							<xsl:attribute name="value"><xsl:value-of select="skssksrq"/></xsl:attribute>
						</input>
						<input name="skssjsrq" type="hidden" id="skssjsrq">
							<xsl:attribute name="value"><xsl:value-of select="skssjsrq"/></xsl:attribute>
						</input>
		  			</td>
					<td align="center" nowrap="nowrap"><div align="right">��λ�������Ԫ</div>
                	</td>
				</tr>			
		</table>
	</xsl:template> 
	<xsl:template match="taxdoc/nsrxx">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table-99">
				<tr class="black9">
				<td width="20%" class="2-td2-t-left"><div align="right">˰���������� &#160;</div></td>
				<td width="30%" class="2-td2-t-left"><div align="left">
					&#160;
  					<input name="jsjdm" type="hidden" id="jsjdm" maxlength="16" class="inputnoborder" readonly="true">
	      	 			<xsl:attribute name="value"><xsl:value-of select="jsjdm"/></xsl:attribute>
		  		 	</input>
		  			<xsl:value-of select="jsjdm"/>
		  			</div>
		  		</td>
				<td width="20%" class="2-td2-t-left"><div align="right">�걨��λ &#160;</div></td>
				<td width="30%" class="2-td2-t-center"><div align="left">
					&#160;
					<input name="nsrmc" type="hidden" id="nsrmc" maxlength="16" class="inputnoborder" readonly="true">
	      				<xsl:attribute name="value"><xsl:value-of select="nsrmc"/></xsl:attribute>
		  			</input>
		  			<xsl:value-of select="nsrmc"/>
		  			</div>
				<input name="swjgzzjgdm" type="hidden" id="swjgzzjgdm">
					<xsl:attribute name="value"><xsl:value-of select="swjgzzjgdm"/></xsl:attribute>
				</input>
				</td>
				</tr>			
		</table>
	</xsl:template>
	<xsl:template match="taxdoc/hdxx">		
				<input name="jmzg" type="hidden" id="jmzg">
					<xsl:attribute name="value"><xsl:value-of select="jmzg"/></xsl:attribute>
				</input>
				<input name="ybjmsl" type="hidden" id="ybjmsl">
					<xsl:attribute name="value"><xsl:value-of select="ybjmsl"/></xsl:attribute>
				</input>
				<input name="qyzslx" type="hidden" id="qyzslx">
					<xsl:attribute name="value"><xsl:value-of select="qyzslx"/></xsl:attribute>
				</input>
				<input name="cyl" type="hidden" id="cyl">
					<xsl:attribute name="value"><xsl:value-of select="cyl"/></xsl:attribute>
				</input>
				<input name="xzqy" type="hidden" id="xzqy">
					<xsl:attribute name="value"><xsl:value-of select="xzqy"/></xsl:attribute>
				</input>
				<input name="dezsse" type="hidden" id="dezsse">
					<xsl:attribute name="value"><xsl:value-of select="dezsse"/></xsl:attribute>
				</input>			
	</xsl:template>
	<xsl:template match="taxdoc/sbsj">
		<table cellspacing="0" class="table-99" width="100%">
			<tr>
				<td class="2-td1-left"><div align="center">�� Ŀ</div></td>
				<td class="2-td1-left"><div align="center">�д�</div></td>
				<td class="2-td1-center"><div align="center">�����ۼ���</div></td>
			</tr>
			<tr>
				<td class="2-td2-left"><div align="left">�����ܶ�</div></td>
				<td class="2-td2-left"><div align="center">1</div></td>
				<td class="2-td2-center"><div align="center">
					<xsl:choose>
						<xsl:when test="../hdxx/qyzslx=4">
							 <input name="srze" id="srze" maxlength="16" value="" class="inputnoborder" tabindex="-1" readonly="true"/>
						</xsl:when>
						<xsl:otherwise>
							  <input name="srze" id="srze" maxlength="16" onchange='return formatData(this);'>
									<xsl:attribute name="value"><xsl:value-of select="srze"/></xsl:attribute>
							  </input>
						</xsl:otherwise>
					</xsl:choose>
				</div></td>
			</tr>
			<tr>
				<td class="2-td2-left"><div align="left">�����ܶӦ��˰���ö</div></td>
				<td class="2-td2-left"><div align="center">2</div></td>
				<td class="2-td2-center"><div align="center">
					<xsl:choose>
						<xsl:when test="../hdxx/qyzslx=1">
 							<input name="lrze" id="lrze" onchange='return formatData(this);'>		
								<xsl:attribute name="value"><xsl:value-of select="lrze"/></xsl:attribute>
							</input>
						</xsl:when>
						<xsl:when test="../hdxx/qyzslx=3">
 							<input name="lrze" id="lrze" onchange='return formatData(this);'>		
								<xsl:attribute name="value"><xsl:value-of select="lrze"/></xsl:attribute>
							</input>
						</xsl:when>
						<xsl:otherwise>
							  <input name="lrze" id="lrze" tabindex="-1" class="inputnoborder" readonly="true">
							  	 <xsl:attribute name="value"><xsl:value-of select="lrze"/></xsl:attribute>
							  </input>
						</xsl:otherwise>
					</xsl:choose>
				</div></td>
			</tr>
			<tr>
				<td class="2-td2-left"><div align="left">��:�ֲ���ǰ��ȿ���</div></td>
				<td class="2-td2-left"><div align="center">3</div></td>
				<td class="2-td2-center"><div align="center">
					<xsl:choose>
						<xsl:when test="../hdxx/qyzslx=4">
							<input name="mbyqndks" id="mbyqndks" tabindex="-1" class="inputnoborder" readonly="true" >
								<xsl:attribute name="value"><xsl:value-of select="mbyqndks"/></xsl:attribute>
							</input>	
						</xsl:when>
						<xsl:otherwise>
							<input name="mbyqndks" id="mbyqndks" onchange='return formatData2(this);'>
								<xsl:attribute name="value"><xsl:value-of select="mbyqndks"/></xsl:attribute>
							</input>
						</xsl:otherwise>
					</xsl:choose>
				</div></td>
			</tr>
			<tr>
				<td class="2-td2-left"><div align="left">�����������ܶӦ��˰���ö4=2-3</div></td>
				<td class="2-td2-left"><div align="center">4</div></td>
				<td class="2-td2-center"><div align="center">
				<input name="bkhlrze" id="bkhlrze" tabindex="-1" class="inputnoborder" readonly="true">
	      			<xsl:attribute name="value"><xsl:value-of select="bkhlrze"/></xsl:attribute>
		  		</input></div></td>
			</tr>
			<tr>
				<td class="2-td2-left"><div align="left">����˰��</div></td>
				<td class="2-td2-left"><div align="center">5</div></td>
				<td class="2-td2-center"><div align="center">
				<xsl:choose>
						<xsl:when test="../hdxx/qyzslx=4">
							<input name="sl" value="" tabindex="-1" class="inputnoborder" readonly="true"/>
						</xsl:when>
						<xsl:otherwise>
							  <input name="sl" tabindex="-1" class="inputnoborder" readonly="true">
							  		<xsl:attribute name="value"><xsl:value-of select="sl"/></xsl:attribute>
							  </input>
						</xsl:otherwise>
					</xsl:choose>
				</div></td>
			</tr>
			<tr>
				<td class="2-td2-left"><div align="left">Ӧ������˰��6=4��5</div></td>
				<td class="2-td2-left"><div align="center">6</div></td>
				<td class="2-td2-center"><div align="center">
					<xsl:choose>
						<xsl:when test="../hdxx/qyzslx=4">
							<input name="ynsdse" id="ynsdse" onchange='return formatData2(this);'>
								<xsl:attribute name="value"><xsl:value-of select="ynsdse"/></xsl:attribute>
							</input>
						</xsl:when>
						<xsl:otherwise>
							  <input name="ynsdse" id="ynsdse" tabindex="-1" class="inputnoborder" readonly="true">
							  	<xsl:attribute name="value"><xsl:value-of select="ynsdse"/></xsl:attribute>
							  </input>
						</xsl:otherwise>
					</xsl:choose>
				</div></td>
			</tr>
			<tr>
				<td class="2-td2-left"><div align="left">�ڳ�δ������˰��</div></td>
				<td class="2-td2-left"><div align="center">7</div></td>
				<td class="2-td2-center"><div align="center">
				<input name="qcwjsdse" type="text" id="qcwjsdse" maxlength="16" onchange='return formatData3(this);'>
	      			<xsl:attribute name="value"><xsl:value-of select="qcwjsdse"/></xsl:attribute>
		  		</input></div></td>
			</tr>
			<tr>
				<td class="2-td2-left"><div align="left">��������˰��</div></td>
				<td class="2-td2-left"><div align="center">8</div></td>
				<td class="2-td2-center"><div align="center">
				<input name="jmsdse" type="text" id="jmsdse" maxlength="16" onchange='return formatData3(this);'>
	      			<xsl:attribute name="value"><xsl:value-of select="jmsdse"/></xsl:attribute>
		  		</input></div></td>
			</tr>
			<tr>
				<td class="2-td2-left"><div align="left">�鲹��ǰ���˰��</div></td>
				<td class="2-td2-left"><div align="center">9</div></td>
				<td class="2-td2-center"><div align="center">
				<input name="cbyqndse" type="text" id="cbyqndse" maxlength="16" onchange='return formatData3(this);'>
	      			<xsl:attribute name="value"><xsl:value-of select="cbyqndse"/></xsl:attribute>
		  		</input></div></td>
			</tr>
			<tr>
				<td class="2-td2-left"><div align="left">ʵ���ѽ�������˰��</div></td>
				<td class="2-td2-left"><div align="center">10</div></td>
				<td class="2-td2-center"><div align="center">
				<input name="sjyjnssdse" type="text" id="sjyjnssdse" maxlength="16" onchange='return formatData4(this);'>
	      			<xsl:attribute name="value"><xsl:value-of select="sjyjnssdse"/></xsl:attribute>
		  		</input></div></td>
			</tr>
			<tr>
				<td class="2-td2-left"><div align="left">�����걨�ӽ�����˰��</div></td>
				<td class="2-td2-left"><div align="center">11</div></td>
				<td class="2-td2-center"><div align="center">
				<input name="bqyjsdse" type="text" id="bqyjsdse" maxlength="16" onchange='return formatData3(this);'>
	      			<xsl:attribute name="value"><xsl:value-of select="bqyjsdse"/></xsl:attribute>
		  		</input></div></td>
			</tr>
			<tr>
				<td class="2-td2-left"><div align="left">ʵ��Ӧ�����ˣ�����˰��12=6+7-8+9-10-11</div></td>
				<td class="2-td2-left"><div align="center">12</div></td>
				<td class="2-td2-center"><div align="center">
				<input name="sjybsdse" type="text" tabindex="-1" id="sjybsdse" maxlength="16" class="inputnoborder" readonly="true">
	      			<xsl:attribute name="value"><xsl:value-of select="sjybsdse"/></xsl:attribute>
		  		</input></div></td>
			</tr>
			<tr>
				<td class="2-td2-center" colspan="3">
				 &#160;
				<xsl:choose>
				    <xsl:when test="../hdxx/qyzslx=4">
					<font color="#CC0000">�������գ����ĵ�λ��������Ϊ<xsl:value-of select="../hdxx/dezsse"/>��Ӧ������˰���λ�������ݡ���������������������</font>
				    </xsl:when>
				</xsl:choose>
				</td>
			</tr>
		</table>
	</xsl:template>
</xsl:stylesheet>
