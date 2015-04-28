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
		<xsl:apply-templates select="taxdoc/sbxx"/>
		<xsl:apply-templates select="taxdoc/nsrxx"/>
		<br/>
		 <table id="JmsTable" width="100%" border="0" cellpadding="0" cellspacing="0" class="table-99">
               <tr>
                 <td nowrap="nowrap" class="2-td1-left">序号</td>
				 <td class="2-td1-left">减免分类</td>
				 <td class="2-td1-left">减免税种</td>
                 <td class="2-td1-left">减免税目</td>
                 <td class="2-td1-left">减免项目及代码</td>
                 <td class="2-td1-left">减免项目开始日期</td>
                 <td class="2-td1-left">减免项目结束日期</td>
                 <td class="2-td1-left" id="skssrqId">税款所属开始日期</td>
                 <td class="2-td1-left" id="skjsrqId">税款所属结束日期</td>
                 <td class="2-td1-left">课税数量</td>
                 <td class="2-td1-left">计税金额</td>
                 <td class="2-td1-left">减免税额</td>
                 <td nowrap="nowrap" class="2-td1-center" id="delIndex">&#160;</td>
              </tr>
				<xsl:apply-templates select="//sbsj"/>
				 <tr>
                	<td nowrap="nowrap" class="2-td2-left" colspan="11" id ="hjId">合计</td>
               		<td class="2-td2-left">
		 			 	<input name="hj" id="hj" class="inputnoborder" size="12" readonly="true">
							<xsl:attribute name="value"><xsl:value-of select="//hj"/></xsl:attribute>
						</input>
					</td>
                	<td nowrap="nowrap" class="2-td2-center">&#160;</td>
                </tr>
         </table>
         <input name="ifjmzg" type="hidden" id="ifjmzg">
			<xsl:attribute name="value"><xsl:value-of select="//ifjmzg"/></xsl:attribute>
		</input>
		<input name="jmzghc" type="hidden" id="jmzghc">
			<xsl:attribute name="value"><xsl:value-of select="//jmzghc"/></xsl:attribute>
		</input>
		 <xsl:if test="//ifjmzg[.='false']">
			 <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table-99">
				  <tr>
						<td class="2-td2-left">注意</td>
						<td class="2-td2-center" colspan="7"><div align="left"><font color="red">您的第<xsl:value-of select="//jmzghc"/>行数据无审批减免资格！</font></div></td>
				  </tr>
			</table>
		</xsl:if>
	</xsl:template>
	<xsl:template match="taxdoc/sbxx">
		<input name="fsdm" type="hidden" id="fsdm">
			<xsl:attribute name="value"><xsl:value-of select="fsdm"/></xsl:attribute>
		</input>
		<input name="nd" type="hidden" id="nd">
			<xsl:attribute name="value"><xsl:value-of select="nd"/></xsl:attribute>
		</input>
		<input name="skssksrq1" type="hidden" id="skssksrq1" >
			<xsl:attribute name="value"><xsl:value-of select="skssksrq"/></xsl:attribute>
		</input>
		<input name="skssjsrq1" type="hidden" id="skssjsrq1">
			<xsl:attribute name="value"><xsl:value-of select="skssjsrq"/></xsl:attribute>
		</input>	
	 	<table cellspacing="0" class="table-99">
              <tr class="black9">
                  <td nowrap="nowrap" align="left">申报日期：<xsl:value-of select="sbrq"/>
				  <input name="sbrq" type="hidden" id="sbrq" >
						<xsl:attribute name="value"><xsl:value-of select="sbrq"/></xsl:attribute>
					</input>			
				  </td>
                <td align="center" nowrap="nowrap">
                	<div align="right">金额单位：人民币元</div>
                </td>
              </tr>
        </table>
	</xsl:template>
	<xsl:template match="taxdoc/nsrxx">
		<table  align="center" cellspacing="0" class="table-99">
              <tr class="black9">
                <td nowrap="nowrap" class="2-td2-t-left"><div align="right">税务计算机代码&#160;&#160;</div></td>
                <td nowrap="nowrap" class="2-td2-t-left"><div align="left">&#160;&#160;<xsl:value-of select="jsjdm"/></div>
					<input name="jsjdm" type="hidden" id="jsjdm">
						<xsl:attribute name="value"><xsl:value-of select="jsjdm"/></xsl:attribute>
					</input>
				</td>
                <td nowrap="nowrap" class="2-td2-t-left"><div align="right">单位名称&#160;&#160;</div></td>
                <td nowrap="nowrap" class="2-td2-t-center"><div align="left">&#160;&#160;<xsl:value-of select="nsrmc"/></div>
					<input name="nsrmc" type="hidden" id="nsrmc">
						<xsl:attribute name="value"><xsl:value-of select="nsrmc"/></xsl:attribute>
					</input>
				</td>
              </tr>
			  <input name="qylxdh" type="hidden" id="qylxdh">
					<xsl:attribute name="value"><xsl:value-of select="qylxdh"/></xsl:attribute>
			  </input>
			  <input name="swjgzzjgdm" type="hidden" id="swjgzzjgdm">
					<xsl:attribute name="value"><xsl:value-of select="swjgzzjgdm"/></xsl:attribute>
			  </input>	
        </table>
        <br/>
        <table  align="center" cellspacing="0" class="table-99">
        	<tr class="black9">
                <td width="20%" nowrap="nowrap" class="2-td2-t-left">
                	<div align="center">&#160;&#160;当期销售额：
                	<input name="dqxse" type="text" id="dqxse" size="8" onchange="return formatDqje(this);" 
                		style="border-style:solid; border-top-width:0px; border-right-width:0px; border-left-width:0px; border-bottom-width:1px;">
                		<xsl:attribute name="value"><xsl:value-of select="dqxse"/></xsl:attribute>元</input>&#160;&#160;</div></td>
                <td width="20%" nowrap="nowrap" class="2-td2-t-left">
                	<div align="center">&#160;&#160;当期利润总额：
                	<input name="dqlrze" type="text" id="dqlrze" size="8" onchange="return formatLrje(this);" 
                		style="border-style:solid; border-top-width:0px; border-right-width:0px; border-left-width:0px; border-bottom-width:1px;">
                		<xsl:attribute name="value"><xsl:value-of select="dqlrze"/></xsl:attribute>元</input>&#160;&#160;</div></td>
                <td width="20%" nowrap="nowrap" class="2-td2-t-left">
                	<div align="center">&#160;&#160;企业人数：
                	<input name="qyrs" type="text" id="qyrs" size="8" onchange ="getAzbl();" onclick ="getAzbl();"
                		style="border-style:solid; border-top-width:0px; border-right-width:0px; border-left-width:0px; border-bottom-width:1px;">
                		<xsl:attribute name="value"><xsl:value-of select="qyrs"/></xsl:attribute>人</input>&#160;&#160;</div></td>
                <td width="20%" nowrap="nowrap" class="2-td2-t-center">
                	<div align="center">&#160;&#160;安置相关人员
                	<input name="azrs" type="text" id="azrs" size="8" onchange ="getAzbl();"  
                		style="border-style:solid; border-top-width:0px; border-right-width:0px; border-left-width:0px; border-bottom-width:1px;">
                		<xsl:attribute name="value"><xsl:value-of select="azrs"/></xsl:attribute>人</input>&#160;&#160;</div></td>
              </tr>
        </table>
	</xsl:template>

	<xsl:template match="//sbsj">
		 <tr>
                <td class="2-td2-left"><xsl:value-of select="hc"/>
					<input type="hidden" name="hc" id="hc">
						<xsl:attribute name="value"><xsl:value-of select="hc"/></xsl:attribute>
					</input>
				</td>
                <td class="2-td2-left">
					<xsl:variable name="jdm" select="jmfldm"/>
					<select name="jmfldm" id="jmfldm">
						<option value="{$jdm}" selected="selected"><xsl:value-of select="jmflmc"/>					
						</option>
					</select>
					<input type="hidden" name="jmflmc" id="jmflmc">
						<xsl:attribute name="value"><xsl:value-of select="jmflmc"/></xsl:attribute>
					</input>
				</td>
                <td class="2-td2-left">
					<xsl:variable name="sdm" select="szdm"/>
					<select name="szdm" id="szdm" style="width:150"  onchange="makeSzsm(this);" onclick="makeSzsm(this);">
			  			<option value="{$sdm}" selected="selected"><xsl:value-of select="szmc"/>
						</option>
					</select>
					<input type="hidden" name="szmc" id="szmc">
						<xsl:attribute name="value"><xsl:value-of select="szmc"/></xsl:attribute>
					</input>
				</td>
				 <td class="2-td2-left">
					<xsl:variable name="smdm" select="szsmdm"/>
					<select name="szsmdm" id="szsmdm" style="width:150" onchange="formatSzsmdm(this);">
						<option value="{$smdm}" selected="selected"><xsl:value-of select="szsmmc"/>
						</option>
					</select>
					<input type="hidden" name="szsmmc" id="szsmmc">
						<xsl:attribute name="value"><xsl:value-of select="szsmmc"/></xsl:attribute>
					</input>
				</td>
				<td class="2-td2-left">
					<xsl:variable name="jmxm" select="jmxmdm"/>
					<select name="jmxmdm" id="jmxmdm" style="width:150" onclick="FixWidth(this);" onmouseover="helpor_net_show(this);" onmouseout="helpor_net_hide();">
						<option value="{$jmxm}" selected="selected"><xsl:value-of select="jmxmjdm"/>					
						</option>
					</select>
					<input type="hidden" name="jmxmjdm" id="jmxmjdm">
						<xsl:attribute name="value"><xsl:value-of select="jmxmjdm"/></xsl:attribute>
					</input>
				</td>
				<td class="2-td2-left">
                  	<input name="jmxmksrq" id="jmxmksrq" size="10"  maxlength="8">
						<xsl:attribute name="value"><xsl:value-of select="jmxmksrq"/></xsl:attribute>
					</input>
				</td>
				<td class="2-td2-left">
                  	<input name="jmxmjsrq" id="jmxmjsrq" size="10"  maxlength="8">
						<xsl:attribute name="value"><xsl:value-of select="jmxmjsrq"/></xsl:attribute>
					</input>
				</td>
				<td class="2-td2-left">
					<xsl:variable name="skssrq" select="skssksrq"/>
					<select name="skssksrq" id="skssksrq" onchange="setSkssjsrq();">
						<option value="{$skssrq}" selected="selected"><xsl:value-of select="skssksrq"/>						
						</option>
					</select>
				</td>
				<td class="2-td2-left">
					<xsl:variable name="skjsrq" select="skssjsrq"/>
					<select name="skssjsrq" id="skssjsrq">
						<option value="{$skjsrq}" selected="selected"><xsl:value-of select="skssjsrq"/>						
						</option>
					</select>
				</td>
				<td class="2-td2-left">
					<input type="hidden" name="aslj" id="aslj">
						<xsl:attribute name="value"><xsl:value-of select="aslj"/></xsl:attribute>
					</input>
					<xsl:choose>
						<xsl:when test="aslj[.='true']">
							<input name="kssl" id="kssl" size="8" onchange="return formatKsslJsje(this);" maxlength="16">
								<xsl:attribute name="value"><xsl:value-of select="kssl"/></xsl:attribute>
							</input>
						</xsl:when>
						<xsl:otherwise>
							  <input name="kssl" id="kssl" size="8" readonly="true" maxlength="16" class="inputnoborder" tabindex="-1">
								<xsl:attribute name="value"><xsl:value-of select="kssl"/></xsl:attribute>
							  </input>
						</xsl:otherwise>			
			  		 </xsl:choose>		
				</td>
				<td class="2-td2-left">
                  	<input name="jsje" id="jsje" size="10" onchange="return formatKsslJsje(this);" maxlength="16">
						<xsl:attribute name="value"><xsl:value-of select="jsje"/></xsl:attribute>
					</input>
				</td>
                <td class="2-td2-left">
		 			 <input name="jmse" id="jmse" size="10" onchange="return formatJmse(this);" maxlength="16">
					 	<xsl:attribute name="value"><xsl:value-of select="jmse"/></xsl:attribute>
					 </input>
				</td>
                <td nowrap="nowrap" class="2-td2-center"><a href="#" id="delIndex" onclick="doDelete(this);return false;">删除</a> 
				</td>
					<input type="hidden" name="cjsj" id="cjsj">
						<xsl:attribute name="value"><xsl:value-of select="cjsj"/></xsl:attribute>
					</input>
          </tr>
	</xsl:template>
</xsl:stylesheet>
