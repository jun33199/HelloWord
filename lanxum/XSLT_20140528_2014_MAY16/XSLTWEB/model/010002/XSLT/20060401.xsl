<?xml version="1.0" encoding="GB2312"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

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
		<table cellspacing="0" class="table-99">
			<tr><td>
			   <xsl:apply-templates select="//sbxx"/>
			   <br/>
			   <xsl:apply-templates select="//nsrxx"/>
			   <br/>
			</td></tr>
			<tr align="center"><td>
				<div align="left" style="font-size:10pt;font-weigth:bold;color:#3C5564">
				税款类型：<xsl:value-of select="//sklx"/>
				</div>
			   <table id="Table_Master_zcjk" width="100%" border="0" cellpadding="0" cellspacing="0" class="table-99">
					<tr>
						<xsl:choose>
							<xsl:when test="//sklxdm[.='100']">
								<td width="5%" nowrap="nowrap" class="2-td1-left">&#160;</td>
							</xsl:when>
						</xsl:choose>
						<xsl:choose>
							<xsl:when test="//sklxdm[.='200']">
								<td width="10%" nowrap="nowrap" class="2-td1-left">本月税款</td>
							</xsl:when>
						</xsl:choose>
						<td width="10%" nowrap="nowrap" class="2-td1-left"><!--税种税目代码-->&#160;</td>
						<td width="15%" nowrap="nowrap" class="2-td1-left">税种名称</td>
						<td width="25%" nowrap="nowrap" class="2-td1-left">税目名称</td>
						<td width="5%" nowrap="nowrap" class="2-td1-left">税率/计税基数</td>
						<td width="10%" nowrap="nowrap" class="2-td1-left">课税数量</td>
						<td width="15%" nowrap="nowrap" class="2-td1-left">计税金额</td>
						<td width="15%" nowrap="nowrap" class="2-td1-left">实际缴税额</td>
						<td width="5%" nowrap="nowrap" class="2-td1-center">&#160;</td>
					</tr>
					<input name="sskkbz" type="hidden" id="sskkbz">
						<xsl:attribute name="value"><xsl:value-of select="//sskkbz"/></xsl:attribute>
					</input>
					<input name="lwzt" type="hidden" id="lwzt">
						<xsl:attribute name="value"><xsl:value-of select="//lwzt"/></xsl:attribute>
					</input>
					<input name="sbbh" type="hidden" id="sbbh">
						<xsl:attribute name="value"><xsl:value-of select="//sbbh"/></xsl:attribute>
					</input>
					<input name="sklxdm" type="hidden" id="sklxdm">
						<xsl:attribute name="value"><xsl:value-of select="//sklxdm"/></xsl:attribute>
					</input>
					<input name="sklx" type="hidden" id="sklx">
						<xsl:attribute name="value"><xsl:value-of select="//sklx"/></xsl:attribute>
					</input>
					<input name="gkzzjgdm" type="hidden" id="gkzzjgdm">
						<xsl:attribute name="value"><xsl:value-of select="//gkzzjgdm"/></xsl:attribute>
					</input>
					<input name="gkzzjgmc" type="hidden" id="gkzzjgmc">
						<xsl:attribute name="value"><xsl:value-of select="//gkzzjgmc"/></xsl:attribute>
					</input>
					<input name="zsswjgzzjgdm" type="hidden" id="zsswjgzzjgdm">
						<xsl:attribute name="value"><xsl:value-of select="//zsswjgzzjgdm"/></xsl:attribute>
					</input>
					<input name="zsswjgzzjgmc" type="hidden" id="zsswjgzzjgmc">
						<xsl:attribute name="value"><xsl:value-of select="//zsswjgzzjgmc"/></xsl:attribute>
					</input>
					<input name="swjgzzjgdm" type="hidden" id="swjgzzjgdm">
						<xsl:attribute name="value"><xsl:value-of select="//swjgzzjgdm"/></xsl:attribute>
					</input><input name="swjgzzjgmc" type="hidden" id="swjgzzjgmc">
						<xsl:attribute name="value"><xsl:value-of select="//swjgzzjgmc"/></xsl:attribute>
					</input>
					<input name="djzclxmc" type="hidden" id="djzclxmc">
						<xsl:attribute name="value"><xsl:value-of select="//djzclxmc"/></xsl:attribute>
					</input>
					<input name="lxdh" type="hidden" id="lxdh">
						<xsl:attribute name="value"><xsl:value-of select="//lxdh"/></xsl:attribute>
					</input>
					<input name="lsgxmc" type="hidden" id="lsgxmc">
						<xsl:attribute name="value"><xsl:value-of select="//lsgxmc"/></xsl:attribute>
					</input>
					<input name="sfkyzf" type="hidden" id="sfkyzf">
						<xsl:attribute name="value"><xsl:value-of select="//sfkyzf"/></xsl:attribute>
					</input>
					<xsl:apply-templates select="//smitem"/>
					<tr>
						<td nowrap="nowrap" class="2-td2-left" colspan="7"><div align="right">合计</div></td>
						<td nowrap="nowrap" class="2-td2-left">
							<xsl:choose>
								<xsl:when test="//sklxdm[.='100']">
									<input name="hj_zcjk" id="hj" readonly="true" size="15" class="inputnoborder" tabindex="-1"></input>
								</xsl:when>
							</xsl:choose>
							<xsl:choose>
								<xsl:when test="//sklxdm[.='200']">
									<input name="hj_sdjj" id="hj" readonly="true" size="15" class="inputnoborder" tabindex="-1"></input>
								</xsl:when>
							</xsl:choose>
						</td>
						<td nowrap="nowrap" class="2-td2-center">&#160;</td>
					</tr>				 
			    </table>
			</td></tr>
		</table>
	</xsl:template>
	<xsl:template match="//nsrxx">
		<table align="center" width="100%" border="0" cellpadding="0" cellspacing="0" class="table-99">
			<tr class="black9">
				<td width="15%" class="2-td2-t-left"><div align="right">税务计算机代码&#160;</div></td>
				<td width="35%" class="2-td2-t-left"><div align="left">&#160;<xsl:value-of select="//jsjdm"/>
				<input name="jsjdm" type="hidden" id="jsjdm">
	      			<xsl:attribute name="value"><xsl:value-of select="//jsjdm"/></xsl:attribute>
		  		</input></div></td>
				<td width="15%" class="2-td2-t-left"><div align="right">单位名称&#160;</div></td>
				<td width="35%" class="2-td2-t-center"><div align="left">&#160;<xsl:value-of select="//nsrmc"/>
				<input name="nsrmc" type="hidden" id="nsrmc">
	      			<xsl:attribute name="value"><xsl:value-of select="//nsrmc"/></xsl:attribute>
		  		</input>
				<input name="swjgzzjgdm" type="hidden" id="swjgzzjgdm">
	      			<xsl:attribute name="value"><xsl:value-of select="//swjgzzjgdm"/></xsl:attribute>
		  		</input></div></td>
			</tr>		
			<tr class="black9">
				<xsl:variable name="yhzh" select="//zh"/>
				<td class="2-td2-left"><div align="right">缴款银行帐号&#160;</div></td>
				<td class="2-td2-left"><div align="left">&#160;
					<xsl:choose>
						<xsl:when test="//sskkbz[.='1']">
								<xsl:value-of select="//zh"/>
								<input name="zh" type="hidden" id="zh">
	      						<xsl:attribute name="value"><xsl:value-of select="//zh"/></xsl:attribute>
		  					</input>
						</xsl:when>
						<xsl:otherwise>
							  <select name="zh" id="zh" onchange="setBankName(this)">
									<option value="{$yhzh}" selected="selected"><xsl:value-of select="//zh"/>
									</option>
								</select>
						</xsl:otherwise>			
			  	 </xsl:choose>		
					</div></td>
				<td class="2-td2-left"><div align="right">缴款银行名称&#160;</div></td>
				<td class="2-td2-center"><div align="left" id="bankName">&#160;<xsl:value-of select="//yhmc"/></div>
				<input name="yhmc" type="hidden" id="yhmc">
					<xsl:attribute name="value"><xsl:value-of select="//yhmc"/></xsl:attribute>
				</input>
				<input name="yhdm" type="hidden" id="yhdm">
					<xsl:attribute name="value"><xsl:value-of select="//yhdm"/></xsl:attribute>
				</input>
				</td>
			</tr>		
		</table>
	</xsl:template>
	<xsl:template match="//sbxx">
		<input name="fsdm" type="hidden" id="fsdm">
	      	<xsl:attribute name="value"><xsl:value-of select="fsdm"/></xsl:attribute>
		</input>
		<table cellspacing="0" class="table-99">			
				<tr class="black9">							
					<td align="left" nowrap="nowrap"><div align="left">&#160;&#160;申报日期:<xsl:value-of select="//sbrq"/>
					<input name="sbrq" type="hidden" id="sbrq">
	      					<xsl:attribute name="value"><xsl:value-of select="//sbrq"/></xsl:attribute>
		  			</input>
					<input name="sbxgrq" type="hidden" id="sbxgrq">
	      					<xsl:attribute name="value"><xsl:value-of select="//sbxgrq"/></xsl:attribute>
		  			</input></div></td>
					<td align="center" nowrap="nowrap"><div align="right">金额单位：人民币元</div></td>
				</tr>			
		</table>
	</xsl:template> 
	<xsl:template match="//smitem">
		
		<tr>
				<xsl:choose>
					<xsl:when test="//sklxdm[.='100']">
					<td nowrap="nowrap" class="2-td2-left">&#160;
						<input name="sbfs_zcjk" type="hidden" id="sbfs">
							<xsl:attribute name="value"><xsl:value-of select="sbfs"/></xsl:attribute>
						</input>
					</td>	
					</xsl:when>
				</xsl:choose>
				<xsl:choose>
					<xsl:when test="//sklxdm[.='200']">
					<td nowrap="nowrap" class="2-td2-left">&#160;
						<input name="sbfs_sdjj" type="checkbox" id="sbfs">
							<xsl:attribute name="value"><xsl:value-of select="sbfs"/></xsl:attribute>
						</input>
					</td>
					</xsl:when>
				</xsl:choose>
			<td nowrap="nowrap" class="2-td2-left">&#160;
				<input name="jkpzh_zcjk" type="hidden" id="jkpzh">
					<xsl:attribute name="value"><xsl:value-of select="jkpzh"/></xsl:attribute>
				</input>
				<xsl:choose>
					<xsl:when test="//sklxdm[.='100']">
						<input name="aksslj_zcjk" type="hidden" id="aksslj">
							<xsl:attribute name="value"><xsl:value-of select="aksslj"/></xsl:attribute>
						</input>
					</xsl:when>
				</xsl:choose>
				<xsl:choose>
					<xsl:when test="//sklxdm[.='200']">
						<input name="aksslj_sdjj" type="hidden" id="aksslj">
							<xsl:attribute name="value"><xsl:value-of select="aksslj"/></xsl:attribute>
						</input>
					</xsl:when>
				</xsl:choose>
				<xsl:choose>
				<xsl:when test="//sklxdm[.='100']">
					<input type="text" name="szsmID_zcjk" id="szsmdm" class="inputnoborder" size="6" readonly="true" tabIndex="-1">
						<xsl:attribute name="value"><xsl:value-of select="szsmdm"/></xsl:attribute>
					</input>
				</xsl:when>
				</xsl:choose>
				<xsl:choose>
				<xsl:when test="//sklxdm[.='200']">
					<input type="text" name="szsmID_sdjj" id="szsmdm" class="inputnoborder" size="6" readonly="true" tabIndex="-1">
						<xsl:attribute name="value"><xsl:value-of select="szsmdm"/></xsl:attribute>
					</input>
				</xsl:when>
				</xsl:choose>
			</td>
			<td nowrap="nowrap" class="2-td2-left"><xsl:value-of select="szmc"/>
				<xsl:choose>
					<xsl:when test="//sklxdm[.='100']">
						<input type="hidden" name="szmc_zcjk" id="szmc">
							<xsl:attribute name="value"><xsl:value-of select="szmc"/></xsl:attribute>
						</input>
					</xsl:when>
				</xsl:choose>
				<xsl:choose>
					<xsl:when test="//sklxdm[.='200']">
						<input type="hidden" name="szmc_sdjj" id="szmc">
							<xsl:attribute name="value"><xsl:value-of select="szmc"/></xsl:attribute>
						</input>
					</xsl:when>
				</xsl:choose>
			</td>
			<td nowrap="nowrap" class="2-td2-left"><xsl:value-of select="szsmmc"/>
				<xsl:choose>
					<xsl:when test="//sklxdm[.='100']">
						<input type="hidden" name="szsmmc_zcjk" id="szsmmc">
							<xsl:attribute name="value"><xsl:value-of select="szsmmc"/></xsl:attribute>
						</input>
					</xsl:when>
				</xsl:choose>
				<xsl:choose>
					<xsl:when test="//sklxdm[.='200']">
						<input type="hidden" name="szsmmc_sdjj" id="szsmmc">
							<xsl:attribute name="value"><xsl:value-of select="szsmmc"/></xsl:attribute>
						</input>
					</xsl:when>
				</xsl:choose>
			</td>
			<td nowrap="nowrap" class="2-td2-left">
				<xsl:choose>
					<xsl:when test="//sklxdm[.='100']">
						<input type="text" name="sl_zcjk" id="sl" size="6" readonly="true" class="inputnoborder" tabindex="-1">
							<xsl:attribute name="value"><xsl:value-of select="sl"/></xsl:attribute>
						</input>
					</xsl:when>
				</xsl:choose>
				<xsl:choose>
					<xsl:when test="//sklxdm[.='200']">
						<input type="text" name="sl_sdjj" id="sl" size="6" readonly="true" class="inputnoborder" tabindex="-1">
							<xsl:attribute name="value"><xsl:value-of select="sl"/></xsl:attribute>
						</input>
					</xsl:when>
				</xsl:choose>
			</td>
			<td nowrap="nowrap" class="2-td2-left">&#160;
				<xsl:choose>
						<xsl:when test="aksslj[.='1']">
							<xsl:choose>
								<xsl:when test="//sklxdm[.='100']">
									<input type="text" name="kssl_zcjk" id="kssl" onchange='return checkKssl(this);' size="15" maxlength="16">
										<xsl:attribute name="value"><xsl:value-of select="kssl"/></xsl:attribute>
									</input>
								</xsl:when>
							</xsl:choose>
							<xsl:choose>
								<xsl:when test="//sklxdm[.='200']">
									<input type="text" name="kssl_sdjj" id="kssl" onchange='return checkKssl(this);' size="15" maxlength="16">
										<xsl:attribute name="value"><xsl:value-of select="kssl"/></xsl:attribute>
									</input>
								</xsl:when>
							</xsl:choose>
						</xsl:when>
						<xsl:otherwise>
							  <xsl:choose>
								<xsl:when test="//sklxdm[.='100']">
									<input type="hidden" name="kssl_zcjk" id="kssl">
										<xsl:attribute name="value"><xsl:value-of select="kssl"/></xsl:attribute>
							  		</input>
								</xsl:when>
							</xsl:choose>
							<xsl:choose>
								<xsl:when test="//sklxdm[.='200']">
									<input type="hidden" name="kssl_sdjj" id="kssl">
										<xsl:attribute name="value"><xsl:value-of select="kssl"/></xsl:attribute>
							 		</input>
								</xsl:when>
							</xsl:choose>
						</xsl:otherwise>			
			  	 </xsl:choose>				
			</td>
			<td nowrap="nowrap" class="2-td2-left">
				<xsl:choose>
					<xsl:when test="//sklxdm[.='100']">
						<input type="text" name="jsje_zcjk" id="jsje" onchange='return checkJsje(this);' size="15" maxlength="16">
							<xsl:attribute name="value"><xsl:value-of select="jsje"/></xsl:attribute>
						</input>
						
					</xsl:when>
				</xsl:choose>
				<xsl:choose>
					<xsl:when test="//sklxdm[.='200']">
						<input type="text" name="jsje_sdjj" id="jsje" onchange='return checkJsje(this);' size="15" maxlength="16">
							<xsl:attribute name="value"><xsl:value-of select="jsje"/></xsl:attribute>
						</input>
					</xsl:when>
				</xsl:choose>
			</td>
			<td nowrap="nowrap" class="2-td2-left">
				<xsl:choose>
					<xsl:when test="//sklxdm[.='100']">
						<input type="text" name="sjse_zcjk" onchange='return checkSjse(this);' size="15" maxlength="16">
							<xsl:attribute name="value"><xsl:value-of select="sjse"/></xsl:attribute>
						</input>
						<input name="asljbs_zcjk" type="hidden" id="asljbs">
	      					<xsl:attribute name="value"><xsl:value-of select="asljbs"/></xsl:attribute>
						</input>
						<input name="coefficient_zcjk" type="hidden" id="coefficient">
	      					<xsl:attribute name="value"><xsl:value-of select="coefficient"/></xsl:attribute>
						</input>
					</xsl:when>
				</xsl:choose>
				<xsl:choose>
					<xsl:when test="//sklxdm[.='200']">
						<input type="text" name="sjse_sdjj" onchange='return checkSjse(this);' size="15" maxlength="16">
							<xsl:attribute name="value"><xsl:value-of select="sjse"/></xsl:attribute>
						</input>
						<input name="asljbs_sdjj" type="hidden" id="asljbs">
	      					<xsl:attribute name="value"><xsl:value-of select="asljbs"/></xsl:attribute>
						</input>
						<input name="coefficient_sdjj" type="hidden" id="coefficient">
	      					<xsl:attribute name="value"><xsl:value-of select="coefficient"/></xsl:attribute>
						</input>
					</xsl:when>
				</xsl:choose>
			</td>
			<td nowrap="nowrap" class="2-td2-center">
				<xsl:choose>
					<xsl:when test="//sklxdm[.='100']">
						<a id="delIndex_zcjk" href="#" onclick="doDel('zcjk','Table_Master_zcjk',this);">删除</a>
					</xsl:when>
				</xsl:choose>
				<xsl:choose>
					<xsl:when test="//sklxdm[.='200']">
						<a id="delIndex_sdjj" href="#" onclick="doDel('sdjj','Table_Master_zcjk',this);">删除</a>
					</xsl:when>
				</xsl:choose>
			</td>
		</tr>
	</xsl:template>
</xsl:stylesheet>
