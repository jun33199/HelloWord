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
					 
					
					</div>
				</td>
			</tr>
		 </table> 
            <br/>
            <table cellspacing="0" border="0" class="table-98" height="350">            
           
            
			<tr>
				<td class="2-td2-t-left" width="10%">起始时间</td>
				<td class="2-td2-t-left" width="15%">
					<div id="mx_zsfsdm_epx_visible_1" align="left">
						<input id="qsrq" type="text"
							onClick="WdatePicker()" size="12" >
		<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/qsrq" />
			</xsl:attribute>
		</input>
						
					</div>
				</td>
				<td class="2-td2-t-left"  width="10%">截止时间</td>
				<td class="2-td2-t-left" width="15%">
					<div id="mx_zsfsdm_epx_visible_1" align="left">
						<input id="jzrq" type="text"
							onClick="WdatePicker()" size="12" >
		<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/jzrq" />
			</xsl:attribute>
		</input>
						
					</div>
				</td>
				
				
				 <td class="2-td2-t-left" width="10%">
                  减免税额
                </td>
                <td class="2-td2-t-left" width="15%">
                	
                  <input type="text" name="bajmse" size="10" maxlength="18" >
		<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/bajmse" />
			</xsl:attribute>
		</input>
                  元
                </td>
                <td class="2-td2-t-left" width="10%">
                  减免比例
                </td>
                <td class="2-td2-t-center" width="15%">
                	 
                 <input type="text" name="bajmbl" size="10"  maxlength="18">
		<xsl:attribute name="value">
				<xsl:value-of select="taxdoc/jmsbajl/bajmbl" />
			</xsl:attribute>
		</input>
                 %
                </td>
				
			</tr>
			 <tr>
                <td class="2-td2-left">
                  减免税政策
                  <br/>
                  执行情况：
                </td>
                <td class="2-td2-center" colspan="7">
                		<div align="left">                 
                			&#160;&#160;&#160;&#160;
                   
                <textarea name="fhwjmc" rows="5" cols="90" >
            	<xsl:value-of select="taxdoc/jmsbajl/fhwjmc" />
		               </textarea>
                		
                	</div>
                  </td>
              </tr>
			<tr>
				<td class="2-td2-center" colspan="8">

					
					<table width="95%" cellspacing="0" border="0"
						id="Table1">
										<tr>
							<td class="2-td2-t-center" colspan="2">
						<div align="left">
						减免税备案资料清单：
					</div>	
					 </td>
              </tr>	
		<xsl:for-each  select="//bajlzlqd[./sfkysc='1']">
		<xsl:variable name="zlqdPosition" select="position()"/>
						<tr>
	<xsl:attribute name="id">row<xsl:value-of select="($zlqdPosition)"/></xsl:attribute>
							<td class="2-td2-left" width="70%">
						<div align="left">
						<xsl:value-of select="zlqd"/>
					</div>	
						<input type="hidden" name="zlqd" id="zlqd" >
									<xsl:attribute name="value">
				<xsl:value-of select="zlqd" />
			</xsl:attribute>
						
						</input>
					<input type="hidden" name="sfkysc" id="sfkysc" >
									<xsl:attribute name="value">
				<xsl:value-of select="sfkysc" />
			</xsl:attribute>
						
						</input>
							</td>
							<td class="2-td2-center" width="250%">&#160;
		</td>
						</tr>
		</xsl:for-each>

		<xsl:for-each  select="//bajlzlqd[./sfkysc='0']">
		<xsl:variable name="zlqdPosition" select="position()"/>
						<tr>
	<xsl:attribute name="id">row<xsl:value-of select="($zlqdPosition)"/></xsl:attribute>
							<td class="2-td2-left" width="70%">
						<div align="left">
						<xsl:value-of select="zlqd"/>
					</div>	
						<input type="hidden" name="zlqd" id="zlqd" >
									<xsl:attribute name="value">
				<xsl:value-of select="zlqd" />
			</xsl:attribute>
						</input>
			
					<input type="hidden" name="sfkysc" id="sfkysc" >
									<xsl:attribute name="value">
				<xsl:value-of select="sfkysc" />
			</xsl:attribute>
						
						</input>
							</td>
							<td class="2-td2-center" width="250%">
	<!--a href='#' id='delIndex'  onclick='delete_row(this);return false;'>
	删除此行
	</a-->
	<input id="delIndex" name="delIndex" type="button"	value=" 删除  " onclick="delete_row(this);return false;" />
	<!--img id="delIndex" name="delIndex" src="http://localhost:7777/StaticFile/images/shanchu2.jpg" onclick="delete_row(this);return false;"/-->
		</td>
						</tr>
		</xsl:for-each>
    </table>

					<table width="95%" cellspacing="0" border="0"  id="Table2">
						<tr>
							
							<td class="2-td2-left" width="70%" align="left">
								<div id="div1left" style="display:none;" align="left">是否发生合同能源管理项目转让和受让</div>
							</td>
							<td class="2-td2-center" width="30%">
								<div id="div1right" style="display:none;" align="left">&#160;</div>
							</td>
							
						</tr>
						<tr>
							<td class="2-td2-left" width="70%">
								<div id="div2left" style="display:none;"  align="left">项目转让合同、项目原享受优惠</div>
							</td>
						    <td class="2-td2-center" width="30%">
								<div id="div2right" style="display:none;"  align="left">&#160;</div>
							</td>
							
						</tr>
						<tr>
							
							<td class="2-td2-left" width="70%">
								<div id="div3left" style="display:none;"  align="left">项目转让合同、项目原享受优惠备案文件</div>
							</td>
							<td class="2-td2-center" width="30%">
								<div id="div3right" style="display:none;"  align="left">&#160;</div>
							</td>
							
						</tr>

					</table>

                  <br/>
                  <table width="95%" cellspacing="0" border="0">  

						<tr>
							<td class="2-td2-t-center" colspan="2">
								国家税务总局或北京市地方税务局要求提交的其他资料：
								<input type="text" name="zlmc" size="50" maxLength="100"
									 onkeydown="onKeySubmit()"/>
								<xsl:text></xsl:text>
							
								<input id="Button" type="button"
									value=" 增加  " onclick="addzl()" />
							</td>
						</tr>


					</table>
				</td>
			</tr>


		</table>
		<br></br>
	</xsl:template>
</xsl:stylesheet>
