<?xml version="1.0" encoding="GB2312"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html"/>
	<xsl:template match="/">
		<input name="xsltVersion" type="hidden" id="xsltVersion">
			<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/xsltVersion"/>
			</xsl:attribute>
		</input>
		<input name="schemaVersion" type="hidden" id="schemaVersion">
			<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/schemaVersion"/>
			</xsl:attribute>
		</input>
		<input name="ywlx" type="hidden" id="ywlx">
			<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/ywlx"/>
			</xsl:attribute>
		</input>
		<input name="ywczlx" type="hidden" id="ywczlx">
			<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/ywczlx"/>
			</xsl:attribute>
		</input>
		<input name="swjgzzjgdm" type="hidden" id="swjgzzjgdm">
			<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/nsrxx/swjgzzjgdm"/>
			</xsl:attribute>
		</input>
		<input name="jsjdm" type="hidden" id="jsjdm">
			<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/nsrxx/jsjdm"/>
			</xsl:attribute>
		</input>
		<input name="nsrmc" type="hidden" id="nsrmc">
			<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/nsrxx/nsrmc"/>
			</xsl:attribute>
		</input>
		<input name="basqbh" type="hidden" id="basqbh">
			<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/basqbh"/>
			</xsl:attribute>
		</input>
		<input name="jmbasxdm" type="hidden" id="jmbasxdm">
			<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/jmbasxdm"/>
			</xsl:attribute>
		</input>
		<input name="jmbasxmc" type="hidden" id="jmbasxmc">
			<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/jmbasxmc"/>
			</xsl:attribute>
		</input>
		<input name="fhwjmc" type="hidden" id="fhwjmc">
		<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/fhwjmc"/>
			</xsl:attribute>
		</input>
		<input name="qsrq" type="hidden" id="qsrq">
		<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/qsrq"/>
			</xsl:attribute>
		</input>
		<input name="ztdm" type="hidden" id="ztdm">
		<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/ztdm"/>
			</xsl:attribute>
		</input>
		<input name="ztmc" type="hidden" id="ztmc">
		<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/ztmc"/>
			</xsl:attribute>
		</input>
		<input name="jzrq" type="hidden" id="jzrq">
		<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/jzrq"/>
			</xsl:attribute>
		</input>
		<input name="szdm" type="hidden" id="szdm">
		<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/szdm"/>
			</xsl:attribute>
		</input>
		<input name="szmc" type="hidden" id="szmc">
		<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/szmc"/>
			</xsl:attribute>
		</input>
		<input name="bsfsdm" type="hidden" id="bsfsdm">
		<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/bsfsdm"/>
			</xsl:attribute>
		</input>
		<input name="bsfsmc" type="hidden" id="bsfsmc">
		<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/bsfsmc"/>
			</xsl:attribute>
		</input>
		
		<input name="BASQWSH" type="hidden" id="BASQWSH">
			<xsl:attribute name="value">
				<xsl:value-of
					select="taxdoc/jmsbajl/qysdsjmba/BASQWSH"/>
			</xsl:attribute>
		</input>
		<input name="JSJDM" type="hidden" id="JSJDM">
		<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/qysdsjmba/JSJDM"/>
			</xsl:attribute>
		</input>		
		
		
		<input name="WHSYDWLXDM" type="hidden" id="WHSYDWLXDM">
		<xsl:attribute name="value">
				<xsl:value-of
					select="taxdoc/jmsbajl/qysdsjmba/WHSYDWLXDM"/>
			</xsl:attribute>
		</input>	
		<input name="XH" type="hidden" id="XH">
		<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/qysdsjmba/XH" />
			</xsl:attribute>
		</input>
		<input name="band" type="hidden" id="band">
			<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/band" />
			</xsl:attribute>
		</input>											             
           <table class="table-99" cellspacing="0">
           <tr>
                      <td width="50%" class="2-td2-t-left" colspan="1">
                        <div align="left">
                          �������������
                          &#160;
                        </div>
                      </td>
                      <td width="50%" class="2-td2-t-center" height="21"  >
                        <div align="left">
                          <input type="text" name="basqbh1" maxlength="11" size="30" tabindex="-1"
                           disabled="true">
                             		<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/basqbh" />
			</xsl:attribute></input>
                        </div>
                      </td>
                    </tr>
                    <tr>
                            <td width="50%" class="2-td2-left"><div align="left">�Ļ���ҵ��λ���ʹ���<font color="#FF0000">*</font></div></td>
                            <td colspan="3" class="2-td2-center">
                                 <div id="mx_zsfsdm_epx_visible_1" align="left">
							         <select  name="fudm" id="fudm" style="width:150px" disabled="true">
										<option value="">��ѡ��</option>
									  </select>                                
                                  </div>
                           </td>
                    </tr>           
                    

                   
                    <tr>
                            <td width="20%" class="2-td2-left"><div align="left">����˰��<font color="#FF0000">*</font></div></td>
                            <td colspan="3" class="2-td2-center">
                              <div id="mx_zsfsdm_epx_visible_1" align="left">
                                    <input name="JMSE" id="JMSE" tabindex="1" type="text" style="text-align:right" value="" size="15" readonly="readonly">
							           <xsl:attribute name="value"><xsl:value-of select="taxdoc/jmsbajl/qysdsjmba/JMSE"/></xsl:attribute>
						            </input>Ԫ
                              </div>              
                            </td>
                    </tr>                       
                    
                    
            </table>  	
		<table class="table-99" cellspacing="0">
			<tr>
				<td width="10%" class="2-td2-left">
					<div align="right">
                          ¼������
                        </div>
				</td>
				<td width="30%" class="2-td2-left">
					<div align="left">
						<input name="LRRQ" type="text" id="LRRQ" style="text-align:right" readonly="readonly" class="txtInput">
							<xsl:attribute name="value"><xsl:value-of select="taxdoc/jmsbajl/lrrq"/></xsl:attribute>
						</input>
					</div>
				</td>
				<td width="10%" class="2-td2-left">
					<div align="right">
                          �������
                        </div>
				</td>
				<td width="30%" class="2-td2-left">
					<div align="left">
						<input name="BAND" type="text" id="BAND" style="text-align:right" readonly="readonly" class="txtInput">
							<xsl:attribute name="value"><xsl:value-of select="taxdoc/jmsbajl/band"/></xsl:attribute>
						</input>
					</div>
				</td>
				<td width="10%" class="2-td2-left">
					<div align="right">
                          ¼����
                        </div>
				</td>
				<td width="30%" class="2-td2-center">
					<div align="left">
						<input name="LRR" type="text" id="LRR" style="text-align:right" readonly="readonly" class="txtInput">
							<xsl:attribute name="value"><xsl:value-of select="taxdoc/nsrxx/nsrmc"/></xsl:attribute>
						</input>
					</div>
				</td>
			</tr>
		</table>
	</xsl:template>
</xsl:stylesheet>
