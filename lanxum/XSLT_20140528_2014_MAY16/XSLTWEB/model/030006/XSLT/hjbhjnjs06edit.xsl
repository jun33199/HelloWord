<?xml version="1.0" encoding="GB2312"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" />
	<xsl:template match="/">
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
		<input name="basqbh" type="hidden" id="basqbh">
			<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/basqbh" />
			</xsl:attribute>
		</input>
		<input name="basqwsh" type="hidden" id="basqwsh">
			<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/basqwsh" />
			</xsl:attribute>
		</input>
		<input name="band" type="hidden" id="band">
			<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/band" />
			</xsl:attribute>
		</input>
		<input name="jmbasxmc" type="hidden" id="jmbasxmc">
			<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/jmbasxmc" />
			</xsl:attribute>
		</input>
		<input name="lrrq" type="hidden" id="lrrq">
			<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/lrrq" />
			</xsl:attribute>
		</input>
		<input name="ztdm" type="hidden" id="ztdm">
		<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/ztdm" />
			</xsl:attribute>
		</input>
		<input name="ztmc" type="hidden" id="ztmc">
		<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/ztmc" />
			</xsl:attribute>
		</input>
		<input name="szdm" type="hidden" id="szdm">
		<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/szdm" />
			</xsl:attribute>
		</input>
		<input name="szmc" type="hidden" id="szmc">
		<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/szmc" />
			</xsl:attribute>
		</input>
		<input name="jmbasxdm" type="hidden" id="jmbasxdm">
			<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/jmbasxdm" />
			</xsl:attribute>
		</input>
		<input name="bsfsdm" type="hidden" id="bsfsdm">
		<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/bsfsdm" />
			</xsl:attribute>
		</input>
		<input name="bsfsmc" type="hidden" id="bsfsmc">
		<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/bsfsmc" />
			</xsl:attribute>
		</input>
		<input name="zcba" type="hidden" id="zcba">
		<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/qysdsjmba/zcba" />
			</xsl:attribute>
		</input>
	
<xsl:apply-templates select="//jmsbajl">
<xsl:with-param  name="disacheck">false</xsl:with-param>
</xsl:apply-templates>
		<input name="xh" type="hidden" id="xh">
		<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/qysdsjmba/xh" />
			</xsl:attribute>
		</input>
	
                <br/>  
                
                  <table class="table-99" cellspacing="0">
                    <tr>
                      <td width="10%" class="2-td2-t-left">
                        <div align="right">
                          录入日期
                        </div>
                      </td>
                      <td width="30%" class="2-td2-t-left">
                        <div align="left">
                                <input type="text" name="lrrq" size="15" readonly="readonly"  class="txtInput">
									<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/lrrq" />
			</xsl:attribute>
					 </input>
            
                        </div>
                      </td>
                      <td width="10%" class="2-td2-t-left">
                        <div align="right">
                          备案年度
                        </div>
                      </td>
                      <td width="30%" class="2-td2-t-left">
                        <div align="left">
                       <input type="text" name="band" size="15" readonly="readonly"  class="txtInput">
									<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/band" />
			</xsl:attribute>
					 </input>
                          
                        </div>
                      </td>
                      <td width="10%" class="2-td2-t-left">
                        <div align="right">
                          录入人
                        </div>
                      </td>
                      <td width="30%" class="2-td2-t-center">
                        <div align="left">
                        <input type="text" name="lrr" size="15" readonly="readonly"  class="txtInput">
									<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/nsrxx/nsrmc" />
			</xsl:attribute>
					 </input>
                       </div>
                      </td>
                    </tr>
                  </table>
                  
	
	
  	</xsl:template>
  	
  	
  	
  		<xsl:template match="//jmsbajl">
  		<xsl:param name="disacheck">false</xsl:param>
  		
  		
  		        <table class="table-99" cellspacing="0">
  	<tr>
                      <td  width="40%"  class="2-td2-t-left" >
                        <div align="left">
                          备案申请文书号
                        </div>
                      </td>
                      <td  class="2-td2-t-center" height="21" >
                        <div align="left">
                          <input type="text" name="basqbh1" maxlength="11" size="30" tabindex="-1"
                           disabled="true">
                             		<xsl:attribute name="value">
				<xsl:value-of select="/taxdoc/jmsbajl/basqbh" />
			</xsl:attribute></input>
                        </div>
                      </td>
                    </tr>
                  
                     <tr>
                      <td width="40%"  class="2-td2-left" >
                        <div align="left">
                          项目所得类型<font color="#FF0000">*</font>
                        </div>
                      </td>
                      <td class="2-td2-center">
                        <div id="mx_zsfsdm_epx_visible_1" align="left">
                                   <xsl:variable name="jdm" select="//jnjsxmlxdm"/>
					<select name="jnjsxmlxdm" id="jnjsxmlxdm">
				<xsl:if test="$disacheck='true'">
					<xsl:attribute name="disabled">true</xsl:attribute>
					</xsl:if>
						<option value="{$jdm}" selected="selected"><xsl:value-of select="//jnjsxmlxmc"/>					
						</option>
					</select>
					<input type="hidden" name="jnjsxmlxmc" id="jnjsxmlxmc">
						<xsl:attribute name="value"><xsl:value-of select="//jnjsxmlxmc"/></xsl:attribute>
					</input>
 
                        </div>
                      </td>
                    </tr>
                
                    <tr>
                      <td width="40%" class="2-td2-left" >
                        <div align="left">
                          取得第一笔收入的相关证明资料名称<font color="#FF0000">*</font>
                        </div>
                      </td>
                      <td class="2-td2-center">
                        <div id="mx_zsfsdm_epx_visible_1" align="left">
                  <input type="text" id="zlmc" name="zlmc" size="30"  tabindex="1"
                     maxlength="200"      >
				<xsl:if test="$disacheck='true'">
					<xsl:attribute name="disabled">true</xsl:attribute>
					</xsl:if>
                          		<xsl:attribute name="value">
				<xsl:value-of select="//zlmc" />
			</xsl:attribute>
	</input>
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td width="40%" class="2-td2-left">
                        <div align="left">
                          取得第一笔生产经营收入的时间<font color="#FF0000">*</font>
                          
                        </div>
                      </td>
                      <td class="2-td2-center">
                      <div align="left">
                            <input type="text" id="dybsrnd" name="dybsrnd" maxlength="4" size="8" tabindex="2" >
				<xsl:if test="$disacheck='true'">
					<xsl:attribute name="disabled">true</xsl:attribute>
					</xsl:if>
                          		<xsl:attribute name="value">
				<xsl:value-of select="//dybsrnd" />
			</xsl:attribute>
	</input></div>
                      </td>
                    </tr>
                    <tr>
                      <td width="40%" class="2-td2-left">
                        <div align="left">
                          免征年度<font color="#FF0000">*</font>
                        </div>
                      </td>
                      <td class="2-td2-center">
                        <div id="mx_zsfsdm_epx_visible_1" align="left">
                          从（
    <input type="text" id="mzqsnd" name="mzqsnd" maxlength="4" size="5" tabindex="3" >
                          		<xsl:attribute name="value">
				<xsl:value-of select="//mzqsnd" />
			</xsl:attribute>
	</input>
                          ）年至（
   <input type="text" id="mzzznd" name="mzzznd" maxlength="4" size="5" tabindex="4" >
                          		<xsl:attribute name="value">
				<xsl:value-of select="//mzzznd" />
			</xsl:attribute>
	</input>                          ）年止
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td width="40%" class="2-td2-left">
                        <div align="left">
                          减征年度<font color="#FF0000">*</font>
                        </div>
                      </td>
                      <td class="2-td2-center">
                        <div id="mx_zsfsdm_epx_visible_1" align="left">
                          从（
   <input type="text" id="jzqsnd" name="jzqsnd" maxlength="4" size="5" tabindex="5" >
                          		<xsl:attribute name="value">
				<xsl:value-of select="//jzqsnd" />
			</xsl:attribute>
	</input>                          ）年至（
     <input type="text" id="jzzznd" name="jzzznd" maxlength="4" size="5" tabindex="6" >
                       	<xsl:attribute name="value">
				<xsl:value-of select="//jzzznd" />
			</xsl:attribute>
	</input>
	                       ）年止
                        </div>
                      </td>
                    </tr>
                  </table>
  	</xsl:template>
</xsl:stylesheet>
                  