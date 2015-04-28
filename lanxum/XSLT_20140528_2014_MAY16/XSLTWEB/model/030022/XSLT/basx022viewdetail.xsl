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
                        <td   class="2-td2-t-left">
                        <div align="left">
                          备案申请文书号
                          &#160;
                        </div>
                      </td>
                      <td  class="2-td2-t-center" height="21" colspan="2">
                        <div align="left">
                          &#160;<xsl:value-of select="/taxdoc/jmsbajl/basqbh" />
                        </div>
                      </td>
                    </tr>
                  
                     <tr>
                      <td width="30%" class="2-td2-left" colspan="2">
                        <div align="left">
                          项目名称<font color="#FF0000">*</font>
                        </div>
                      </td>
                      <td class="2-td2-center">
                        <div id="mx_zsfsdm_epx_visible_1" align="left">
<xsl:value-of select="//jnjpjsgzxmmc"/>
 
                        </div>
                      </td>
                    </tr>


                   
                    <tr>
                      <td width="30%" class="2-td2-left" colspan="2">
                        <div align="left">
                          取得第一笔收入的相关证明资料名称<font color="#FF0000">*</font>
                        </div>
                      </td>
                      <td class="2-td2-center">
                        <div id="mx_zsfsdm_epx_visible_1" align="left">
				<xsl:value-of select="//dybzlmc" />&#160;
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td width="30%" class="2-td2-left" colspan="2">
                        <div align="left">
                          取得第一笔生产经营收入的时间<font color="#FF0000">*</font>
                         
                        </div>
                      </td>
                      <td class="2-td2-center">
                       <div align="left">
				<xsl:value-of select="//dybrq" />
 </div>
                      </td>
                    </tr>
                    <tr>
                      <td width="30%" class="2-td2-left" colspan="2">
                        <div align="left">
                          免征年度<font color="#FF0000">*</font>
                        </div>
                      </td>
                      <td class="2-td2-center">
                        <div id="mx_zsfsdm_epx_visible_1" align="left">
                          从（
				<xsl:value-of select="//mzqsnd" />
                          ）年至（
				<xsl:value-of select="//mzzznd" />
                         ）年止
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td width="30%" class="2-td2-left" colspan="2">
                        <div align="left">
                          减半征收年度<font color="#FF0000">*</font>
                        </div>
                      </td>
                      <td class="2-td2-center">
                        <div id="mx_zsfsdm_epx_visible_1" align="left">
                          从（
				<xsl:value-of select="//jbzsqsnd" />
                          ）年至（
				<xsl:value-of select="//jbzszznd" />
	                       ）年止
                        </div>
                      </td>
                    </tr>
<!--
					<tr>
                      <td width="50%" class="2-td2-left" colspan="2">
                        <div align="left">
                          是否发生合同能源管理项目转让和受让
                        </div>
                      </td>
                      <td class="2-td2-center">　
                        <div id="disp1" style="display:inline;" disabled="true" >
                         
							<input type="radio" id="ZRHTXM_Y" name="ZRHTXM"  value='Y' >
                         		<xsl:if test="//ZRHTXM='Y'">
					<xsl:attribute name="checked">true</xsl:attribute>
					</xsl:if>
							</input>是 
                            <input type="radio" id="ZRHTXM_N" name="ZRHTXM"  value='N' >
                         		<xsl:if test="//ZRHTXM='N'">
					<xsl:attribute name="checked">true</xsl:attribute>
					</xsl:if>
							</input>否 

                        </div>
                      </td>
                    </tr>

					 <tr>
                      <td width="50%" class="2-td2-left" colspan="2">
                        <div align="left">
                          项目转让合同、项目原享受优惠
                        </div>
                      </td>
                      <td class="2-td2-center">　
                        <div id="disp2" style="display:none;"  disabled="true">
                         
							<input type="radio" id="ZRHTXMYH_Y" name="ZRHTXMYH"  value='Y'  >
                         		<xsl:if test="//ZRHTXMYH='Y'">
					<xsl:attribute name="checked">true</xsl:attribute>
					</xsl:if>
							</input>是 
                            <input type="radio" id="ZRHTXMYH_N" name="ZRHTXMYH"  value='N' >
                         		<xsl:if test="//ZRHTXMYH='N'">
					<xsl:attribute name="checked">true</xsl:attribute>
					</xsl:if>
							</input>否 

                        </div>
                      </td>
                    </tr>

					 <tr>
                      <td width="50%" class="2-td2-left" colspan="2">
                        <div align="left">
                          项目转让合同、项目原享受优惠备案文件
                        </div>
                      </td>
                      <td class="2-td2-center">　
                        <div id="disp3" style="display:none;" disabled="true">
                         
							<input type="radio" id="ZRHTXMYHWJ_Y" name="ZRHTXMYHWJ"  value='Y' >
                         		<xsl:if test="//ZRHTXMYHWJ='Y'">
					<xsl:attribute name="checked">true</xsl:attribute>
					</xsl:if>
							</input>是 
                            <input type="radio" id="ZRHTXMYHWJ_N" name="ZRHTXMYHWJ"  value='N' >
                         		<xsl:if test="//ZRHTXMYHWJ='N'">
					<xsl:attribute name="checked">true</xsl:attribute>
					</xsl:if>
							</input>否 

                        </div>
                      </td>
                    </tr>
-->
                  </table>
  	</xsl:template>
</xsl:stylesheet>
                  