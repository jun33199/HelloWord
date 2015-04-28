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
		<input name="jmbasxmc" type="hidden" id="jmbasxmc">
			<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/jmbasxmc" />
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
		<input name="xh" type="hidden" id="xh">
		<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/xh" />
			</xsl:attribute>
		</input>
		<table cellspacing="0" border="0" class="table-98">
			<tr>
				<td class="2-td2-t-left">计算机代码</td>
				<td class="2-td2-t-left" width="10%">
					<div align="left"><xsl:value-of select="taxdoc/nsrxx/jsjdm"/></div>
				</td>
				<td class="2-td2-t-left">纳税人名称</td>
				<td class="2-td2-t-left" width="25%">
					<div align="left"><xsl:value-of select="taxdoc/nsrxx/nsrmc"/></div></td>
				<td class="2-td2-t-left">税种</td>
				<td class="2-td2-t-center">企业所得税</td>
			</tr>
			<tr>
				<td class="2-td2-left">减免税类别</td>
				<td class="2-td2-center" colspan="5">
					<div align="left" id="jszrlxdiv">
					 <xsl:value-of select="taxdoc/jmsbajl/jmbasxmc" />
					 		<input name="jmbasxdm" type="hidden" id="jmbasxdm">
			<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/jmbasxdm" />
			</xsl:attribute>
		</input>
					 
		<input name="jmbasxmc" type="hidden" id="jmbasxmc">
			<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/jmbasxmc" />
			</xsl:attribute>
		</input>
					
					</div>
				</td>
			</tr>
			 </table> 
			    <br/>
            <table cellspacing="0" border="0" class="table-98" height="350">            
           
			<tr>
				<td class="2-td2-t-left">起始时间</td>
				<td class="2-td2-t-left">
					<div id="mx_zsfsdm_epx_visible_1" align="left">
				<xsl:value-of select="taxdoc/jmsbajl/qsrq" />&#160;
						
					</div>
				</td>
				<td class="2-td2-t-left" >截止时间</td>
				<td class="2-td2-t-left">
					<div id="mx_zsfsdm_epx_visible_1" align="left">
				<xsl:value-of select="taxdoc/jmsbajl/jzrq" />&#160;
						
					</div>
				</td>
				
				 <td class="2-td2-t-left" width="10%">
                  减免税额
                </td>
                <td class="2-td2-t-left" width="15%">
                	
                 <xsl:value-of select="taxdoc/jmsbajl/bajmse" />&#160;
                 <xsl:if test="taxdoc/jmsbajl/bajmse[.!='']">元</xsl:if>
                 
                </td>
                <td class="2-td2-t-left" width="10%">
                  减免比例
                </td>
                <td class="2-td2-t-center" width="15%">
                	 
                  <xsl:value-of select="taxdoc/jmsbajl/bajmbl" />&#160;
                 <xsl:if test="taxdoc/jmsbajl/bajmbl[.!='']">%</xsl:if>
                </td>
				
				
			</tr>
			
			
			 <tr>
                <td class="2-td2-left">
                  减免税政策
                  <br/>
                  执行情况：
                </td>
                <td class="2-td2-center" colspan="7" style="word-break: break-all; word-wrap:break-word;">
                		<div align="left">                 
                			&#160;&#160;<xsl:value-of select="taxdoc/jmsbajl/fhwjmc" />
                    
                	</div>
                  </td>
              </tr>
			<tr>
				<td class="2-td2-center" colspan="8">

					<div align="left">
          			&#160;&#160;&#160;&#160; &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
          			减免税备案资料清单
	          </div>	
					<table width="95%" cellspacing="0" border="0"
						id="Table1">
						<xsl:choose>
							<xsl:when	test="//ztdm = '4'">									
							<xsl:call-template name="zlqdshtg"/>
							</xsl:when>							
							<xsl:when	test="//ztdm = '5'">									
							<xsl:call-template name="zlqdshtg"/>
							</xsl:when>							
							<xsl:otherwise>
							<xsl:call-template name="zlqdinit"/>									
							</xsl:otherwise>
						</xsl:choose>	
						
				
					</table>
				</td>
			</tr>


		</table>
		<br></br>
       	 
          
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
             <font color="#FF0000">
                            *
                          </font>
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
                          <font color="#FF0000">
                            *
                          </font>
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
	
  		<xsl:template name="zlqdshtg">
  				<xsl:for-each  select="//bajlzlqd">
		<xsl:variable name="zlqdPosition" select="position()"/>
						<tr>
	<xsl:attribute name="id">row<xsl:value-of select="($zlqdPosition)"/></xsl:attribute>
							<td class="2-td2-left" width="70%">							
							<xsl:if test="$zlqdPosition = 1">
		<xsl:attribute name="class">2-td2-t-left</xsl:attribute>						
							</xsl:if>
							<div align="left">
							<xsl:value-of select="$zlqdPosition"/>、
						<xsl:value-of select="zlqd"/>&#160;
						<input type="hidden" name="zlqd" id="zlqd" >
									<xsl:attribute name="value">
				<xsl:value-of select="zlqd" />
			</xsl:attribute>
						
						</input>
						</div>
							</td>
							
						
										<td class="2-td2-center" width="70%">
							<xsl:if test="$zlqdPosition = 1">
		<xsl:attribute name="class">2-td2-t-center</xsl:attribute>						
							</xsl:if>
						<xsl:value-of select="sfshtgmc"/>&#160;
							</td>
												
							
					
							
						</tr>
		</xsl:for-each>

  		
	</xsl:template>
  		<xsl:template name="zlqdinit">
  				<xsl:for-each  select="//bajlzlqd">
		<xsl:variable name="zlqdPosition" select="position()"/>
						<tr>
	<xsl:attribute name="id">row<xsl:value-of select="($zlqdPosition)"/></xsl:attribute>
							<td class="2-td2-center" width="70%">							
							<xsl:if test="$zlqdPosition = 1">
		<xsl:attribute name="class">2-td2-t-center</xsl:attribute>						
							</xsl:if>
							<div align="left">
							<xsl:value-of select="$zlqdPosition"/>、
						<xsl:value-of select="zlqd"/>&#160;
						<input type="hidden" name="zlqd" id="zlqd" >
									<xsl:attribute name="value">
				<xsl:value-of select="zlqd" />
			</xsl:attribute>
						
						</input>
						</div>
							</td>
							
						
							
						</tr>
		</xsl:for-each>

  		
	</xsl:template>
	
</xsl:stylesheet>
