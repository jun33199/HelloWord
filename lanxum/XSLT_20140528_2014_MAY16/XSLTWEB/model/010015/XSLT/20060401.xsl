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
	        <tr>
	          <td class="1-td2">
			   <xsl:apply-templates select="//sbxx"/>
			   <xsl:apply-templates select="//nsrxx"/>
			   <br/>
			    <table cellspacing="0" class="table-99" >
             	 <tr>
					<td colspan="2" class="2-td1-left" align="center" width="30%"> 税 目</td>
					<td valign="top" class="2-td1-left" width="10%"> 份数</td>
					<td valign="top" class="2-td1-left" width="20%"> 计税金额</td>
					<td valign="top" class="2-td1-center" width="10%"> 税率(单位税额)</td>
					<td valign="top" class="2-td1-right" width="20%"> 已纳税额</td>
             	 </tr>
             	 
		 <xsl:for-each select="//sbsj[position()>0 and 12>position()]">
			  <tr>
                <td colspan="2" class="2-td2-left"><div align="left">&#160;<xsl:value-of select="szsmmc"/></div></td>
                  <input name="szsmdm" id="szsmdm" type="hidden">
				  		<xsl:attribute name="value"><xsl:value-of select="szsmdm"/></xsl:attribute>
				  </input>
				  <input name="szsmmc" id="szsmmc" type="hidden">
				  		<xsl:attribute name="value"><xsl:value-of select="szsmmc"/></xsl:attribute>
				  </input>
                <td class="2-td2-left">
                   <input name="fs" id="fs" maxlength = "16" onchange='return formatFs1(this);'>
				  		 <xsl:attribute name="value"><xsl:value-of select="fs"/></xsl:attribute>
				  </input>
                </td>
                <td class="2-td2-left">
                   <input name="jsje" id="jsje" maxlength = "16">
               			<xsl:attribute name="value"><xsl:value-of select="jsje"/></xsl:attribute>
               			<xsl:attribute name="onchange">return formatJsje(this,<xsl:value-of select="position()-1"/>);</xsl:attribute>
				  </input>
			    </td>
                <td class="2-td2-center">
                   <input name="sl" id="sl" class="noborder-txtleft" tabindex="-1" readonly="true">
				   		<xsl:attribute name="value"><xsl:value-of select="sl"/></xsl:attribute>
				  </input>
                </td>
                <td class="2-td2-right">
                   <input name="ynse" id="ynse" maxlength="16" onchange='return formatYnse(this);'>
              			 <xsl:attribute name="value"><xsl:value-of select="ynse"/></xsl:attribute>
				  </input>
			    </td>
				</tr>
		 </xsl:for-each>		

		<xsl:for-each select="//sbsj[position()>11 and 13>position()]">
			 <tr>
                <td rowspan="2" valign="middle" class="2-td2-left"><div align="left">&#160;帐簿</div></td>
                <td class="2-td2-left"><div align="left">&#160;<xsl:value-of select="szsmmc"/></div></td>
					 <input name="szsmdm" id="szsmdm" type="hidden">
							<xsl:attribute name="value"><xsl:value-of select="szsmdm"/></xsl:attribute>
					  </input>
					  <input name="szsmmc" id="szsmmc" type="hidden">
							<xsl:attribute name="value"><xsl:value-of select="szsmmc"/></xsl:attribute>
					  </input>
                <td class="2-td2-left">
                   <input name="fs" id="fs" maxlength = "16" onchange="return formatFs1(this);">
					   <xsl:attribute name="value"><xsl:value-of select="fs"/></xsl:attribute>
                   </input>
　　　　　　　　</td>
                <td class="2-td2-left">
                   <input name="jsje" id="jsje" maxlength = "16">
						<xsl:attribute name="value"><xsl:value-of select="jsje"/></xsl:attribute>
						<xsl:attribute name="onchange">return formatJsje(this,11);</xsl:attribute>
                   </input>
　　　　　　　　</td>
                <td class="2-td2-center">
                   <input name="sl" id="sl" class="noborder-txtleft" tabindex="-1" readonly="readonly">
					   <xsl:attribute name="value"><xsl:value-of select="sl"/></xsl:attribute>
                   </input>
　　　　　　　　　</td>
                <td class="2-td2-right">
                   <input name="ynse" id="ynse" maxlength="16" onchange='return formatYnse(this);'>
					   <xsl:attribute name="value"><xsl:value-of select="ynse"/></xsl:attribute>
                   </input>
　　　　　　　　　</td>
              </tr>
		 </xsl:for-each>

		<xsl:for-each select="//sbsj[position()>12 and 14>position()]">
			  <tr>
                <td class="2-td2-left"><div align="left">&#160;<xsl:value-of select="szsmmc"/></div></td>
                   <input name="szsmdm" id="szsmdm" type="hidden">
							<xsl:attribute name="value"><xsl:value-of select="szsmdm"/></xsl:attribute>
					  </input>
					  <input name="szsmmc" id="szsmmc" type="hidden">
							<xsl:attribute name="value"><xsl:value-of select="szsmmc"/></xsl:attribute>
					  </input>
                <td class="2-td2-left"> &#160;
                  <input name="fs" id="fs" maxlength = "16" align ="right">
					   <xsl:attribute name="value"><xsl:value-of select="fs"/></xsl:attribute>
					   <xsl:attribute name="onchange">return formatFs2(this,12);</xsl:attribute>
                  </input>件</td>
                <td class="2-td2-left">
                   <input name="jsje" id="jsje" type="hidden">&#160;
					   <xsl:attribute name="value"><xsl:value-of select="jsje"/></xsl:attribute>
                   </input>
                </td>
                <td class="2-td2-center">
                   <input name="sl" id="sl" class="noborder-txtleft" tabindex="-1" readonly="readonly">
					   <xsl:attribute name="value"><xsl:value-of select="sl"/></xsl:attribute>
                   </input>
                </td>
                <td class="2-td2-right">
                   <input name="ynse" id="ynse" maxlength="16" onchange='return formatYnse(this);'>
					   <xsl:attribute name="value"><xsl:value-of select="ynse"/></xsl:attribute>
                   </input>
                </td>
              </tr>
		 </xsl:for-each>

		<xsl:for-each select="//sbsj[position()>13 and 15>position()]">
			   <tr>
                <td colspan="2" class="2-td2-left"><div align="left">&#160;<xsl:value-of select="szsmmc"/></div></td>
                   <input name="szsmdm" id="szsmdm" type="hidden">
							<xsl:attribute name="value"><xsl:value-of select="szsmdm"/></xsl:attribute>
					  </input>
					  <input name="szsmmc" id="szsmmc" type="hidden">
							<xsl:attribute name="value"><xsl:value-of select="szsmmc"/></xsl:attribute>
					  </input>
                <td class="2-td2-left">&#160;
                   <input name="fs" id="fs" maxlength = "16" align ="right">
						<xsl:attribute name="value"><xsl:value-of select="fs"/></xsl:attribute>
						<xsl:attribute name="onchange">return formatFs2(this,13);</xsl:attribute>
                    </input>件</td>
                <td class="2-td2-left">&#160;
                   <input name="jsje" id="jsje" type="hidden">
					    <xsl:attribute name="value"><xsl:value-of select="jsje"/></xsl:attribute>
                   </input>
                </td>
                <td class="2-td2-center">
                   <input name="sl" id="sl" class="noborder-txtleft" tabindex="-1" readonly="readonly">
					    <xsl:attribute name="value"><xsl:value-of select="sl"/></xsl:attribute>
                   </input>
                </td>
                <td class="2-td2-right">
                   <input name="ynse" id="ynse" maxlength="16" onchange='return formatYnse(this);'>
					   <xsl:attribute name="value"><xsl:value-of select="ynse"/></xsl:attribute>
                   </input>
                </td>
              </tr>
		 </xsl:for-each>		
	
		<xsl:for-each select="//sbsj[position()>14 and 16>position()]">
			 <tr>
                <td colspan="2" class="2-td2-left"><div align="left">&#160;<xsl:value-of select="szsmmc"/></div></td>
                   <input name="szsmdm" id="szsmdm" type="hidden">
							<xsl:attribute name="value"><xsl:value-of select="szsmdm"/></xsl:attribute>
					  </input>
					  <input name="szsmmc" id="szsmmc" type="hidden">
							<xsl:attribute name="value"><xsl:value-of select="szsmmc"/></xsl:attribute>
					  </input>
                <td class="2-td2-left">
                   <input name="fs" id="fs" maxlength = "16" onchange="return formatFs1(this);">
					   <xsl:attribute name="value"><xsl:value-of select="fs"/></xsl:attribute>
                   </input>
               </td>
                <td class="2-td2-left">
                   <input name="jsje" id="jsje" maxlength = "16">
						<xsl:attribute name="value"><xsl:value-of select="jsje"/></xsl:attribute>
						<xsl:attribute name="onchange">return formatJsje(this,14);</xsl:attribute>
					</input>
                </td>
                <td class="2-td2-center">
                   <input name="sl" id="sl" maxlength = "16" class="inputnormal">
					   <xsl:attribute name="value"><xsl:value-of select="sl"/></xsl:attribute>
					   <xsl:attribute name="onchange">return formatSl(this,14);</xsl:attribute>
                   </input>
              </td>
                <td class="2-td2-right">
                   <input name="ynse" id="ynse" maxlength = "16" onchange="return formatYnse(this);">
					   <xsl:attribute name="value"><xsl:value-of select="ynse"/></xsl:attribute>
					</input>
               </td>
              </tr>
		 </xsl:for-each>					
				  <tr>
               		 <td colspan="2" class="2-td2-left">合计 </td>
               		 <td class="2-td2-left">
                  		 <input name="hjfs" id="hjfc" class="inputnoborder" tabindex="-1" readonly="true">
						 	  <xsl:attribute name="value"><xsl:value-of select="//hjfs"/></xsl:attribute>
						 </input>
               		 </td>
                	 <td class="2-td2-left">
                   		<input name="hjjsje" id="hjjsje" class="inputnoborder" tabindex="-1" readonly="true">
							<xsl:attribute name="value"><xsl:value-of select="//hjjsje"/></xsl:attribute>
						</input>
             	     </td>
                	 <td class="2-td2-center">
                	       <div align="center">—— </div>
               		  </td>
                	<td class="2-td2-right">
                   		<input name="hjynse" id="hjynse" maxlength="16">
							<xsl:attribute name="value"><xsl:value-of select="//hjynse"/></xsl:attribute>
						</input>
                		</td>
              		</tr>
			   	</table>
				</td></tr>
			</table>
	</xsl:template>

	<xsl:template match="//nsrxx">
		 <table cellspacing="0" class="table-99">
              <tr>
                <td class="3-td1-left"><div align="right">计算机代码&#160;&#160;</div></td>
                <td class="3-td1-left"><div align="left">&#160;&#160;
                    <xsl:value-of select="//jsjdm"/>
					<input name="jsjdm" type="hidden" id="jsjdm">
	      				<xsl:attribute name="value"><xsl:value-of select="//jsjdm"/></xsl:attribute>
		  			</input>
                    </div>
                </td>
                <td class="3-td1-left"><div align="right">单位名称&#160;&#160;</div></td>
                <td class="3-td1-left"><div align="left">&#160;&#160;
                     <xsl:value-of select="//nsrmc"/>
					 <input name="nsrmc" type="hidden" id="nsrmc">
	      				<xsl:attribute name="value"><xsl:value-of select="//nsrmc"/></xsl:attribute>
		  			</input>
					 </div>
                </td>
                <td class="3-td1-centen"><div align="right">联系电话&#160;&#160;</div></td>
                <td class="3-td1-right"><div align="left">&#160;&#160;
                     <xsl:value-of select="//qylxdh"/></div>
					 <input name="qylxdh" type="hidden" id="qylxdh">
	      				<xsl:attribute name="value"><xsl:value-of select="//qylxdh"/></xsl:attribute>
		  			</input>
					<input name="swjgzzjgdm" type="hidden" id="swjgzzjgdm">
	      				<xsl:attribute name="value"><xsl:value-of select="//swjgzzjgdm"/></xsl:attribute>
		  			</input>
                </td>                 
               </tr>
        </table>
	</xsl:template>
	<xsl:template match="//sbxx">
		<input name="fsdm" type="hidden" id="fsdm">
	      	<xsl:attribute name="value"><xsl:value-of select="fsdm"/></xsl:attribute>
		</input>
		<input name="nd" type="hidden" id="nd">
	      	<xsl:attribute name="value"><xsl:value-of select="nd"/></xsl:attribute>
		</input>
		<input name="sbrq" type="hidden" id="sbrq">
	      	<xsl:attribute name="value"><xsl:value-of select="sbrq"/></xsl:attribute>
		</input>
		<table align="center" cellspacing="0" class="table-99">
                   <tr class="black9">
                    <td align="center" nowrap="nowrap">
                       <div align="left">申报所属日期： <xsl:value-of select="//skssksrq"/>
	                <input name="skssksrq" type="hidden" id="skssksrq">
						<xsl:attribute name="value"><xsl:value-of select="//skssksrq"/></xsl:attribute>
					</input>
                      至  <xsl:value-of select="//skssjsrq"/>
	                <input name="skssjsrq" type="hidden" id="skssjsrq">
						<xsl:attribute name="value"><xsl:value-of select="//skssjsrq"/></xsl:attribute>
					</input>
                       </div>
                    </td>
                   <td align="right" nowrap="nowrap">单位：人民币元(列至角分)
                    </td>
                  </tr>
        </table>
	</xsl:template> 
	
</xsl:stylesheet>

