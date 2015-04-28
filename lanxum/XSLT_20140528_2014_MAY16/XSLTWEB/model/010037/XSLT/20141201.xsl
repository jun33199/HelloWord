<?xml version="1.0" encoding="gb2312"?>
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
		<input name="jumpFlag" type="hidden" id="jumpFlag">
			<xsl:attribute name="value"><xsl:value-of select="taxdoc/jumpFlag"/></xsl:attribute>
		</input>		
		<input name="jd" type="hidden" id="jd">
			<xsl:attribute name="value"><xsl:value-of select="taxdoc/sbxx/jd"/></xsl:attribute>
		</input>		
		<input name="jsjdm" type="hidden" id="jsjdm">
			<xsl:attribute name="value"><xsl:value-of select="//jsjdm"/></xsl:attribute>
		</input>
		<input name="sshydm" type="hidden" id="sshydm">
			<xsl:attribute name="value"><xsl:value-of select="//sshydm"/></xsl:attribute>
		</input>		

	<table cellspacing="0" class="table-99">
	<tr>
			<td class="1-td2" style="border-top: 1px solid #336699;">
			
					<table width="100%">
							<tr>
								<td class="2-td1-left" colspan="6" style="display:none">
									<div align="left">
										&#160;&#160;&#160;&#160;申报日期:
										<xsl:value-of select="//sbrqshow" />
									</div>
									<input name="sbrqshow" type="hidden" id="sbrqshow">
										<xsl:attribute name="value"><xsl:value-of select="//sbrqshow"/></xsl:attribute>
									</input>
								</td>
								<td class="2-td1-center" colspan="13">
									<div align="center">
										&#160;&#160;&#160;&#160;&#160;&#160;&#160;税款所属日期：
										<xsl:value-of select="//skssksrq" />
										&#160;至&#160;
										<xsl:value-of select="//skssjsrq" />
									</div>
									<input name="skssksrq" type="hidden" id="skssksrq">
										<xsl:attribute name="value"><xsl:value-of select="//skssksrq"/></xsl:attribute>
									</input>
									<input name="skssjsrq" type="hidden" id="skssjsrq">
										<xsl:attribute name="value"><xsl:value-of select="//skssjsrq"/></xsl:attribute>
									</input>																			
								</td>
							</tr>
							<tr>	
								<td class="2-td1-left" colspan="4">
									<div align="left">
										&#160;&#160;&#160;&#160;纳税人识别号:
										<xsl:value-of select="//nsrsbh"/>
									</div>
									<input name="nsrsbh" type="hidden" id="nsrsbh">
										<xsl:attribute name="value"><xsl:value-of select="//nsrsbh"/></xsl:attribute>
									</input>									
								</td>
								<td class="2-td1-left" colspan="4">
									<div align="left">
										&#160;&#160;&#160;&#160;纳税人名称：
										<xsl:value-of select="//nsrmc" />
									</div>
									<input name="nsrmc" type="hidden" id="nsrmc">
										<xsl:attribute name="value"><xsl:value-of select="//nsrmc"/></xsl:attribute>
									</input>									
								</td>
								<td class="2-td1-center" colspan="5" >
									<div align="left">
										&#160;&#160;&#160;&#160;所属行业:
										<xsl:value-of select="//sshy" />
									</div>
									<input name="sshy" type="hidden" id="sshy">
										<xsl:attribute name="value"><xsl:value-of select="//sshy"/></xsl:attribute>
									</input>									
								</td>
							</tr>				
					</table>
			</td>
	</tr>

	<tr>
	　　<td class="1-td2">
<!-- 		<table class="table-99" align="center">
		  <tr>					
            <td> 
              <div id="Layer2" style="position:static;"> -->	
				  <table id="wrklistTable" border="1" cellspacing="0" class="table-99" align="center" width="100%">
	                  <tr> 
	                    <td class="2-td1-left" rowspan="4" width="30"><div align="center">行次</div></td>
	                    <td width="300"  align="center" class="2-td1-left" rowspan="4"><div align="center">项     目</div></td>
	                    <td  nowrap="nowrap" align="center" class="2-td1-left" colspan="3"><div align="center">房屋、建筑物</div></td>
	                    <td  nowrap="nowrap" align="center" class="2-td1-left" colspan="3"><div align="center">机器设备和其他固定资产</div></td>
	                    <td  nowrap="nowrap" align="center" class="2-td1-center" colspan="5"><div align="center">合计</div></td>
	                  </tr>
	                  <tr> 
	                    <td width="80"  align="center" class="2-td1-left" rowspan="2"><div align="center">原值</div></td>
	                    <td width="80"  align="center" class="2-td1-left" rowspan="2"><div align="center">本期折旧（扣除）额</div></td>
	                    <td width="80"  align="center" class="2-td1-left" rowspan="2"><div align="center">累计折旧（扣除）额</div></td>
	                    <td width="80"  align="center" class="2-td1-left" rowspan="2"><div align="center">原值</div></td>
	                    <td width="80"  align="center" class="2-td1-left" rowspan="2"><div align="center">本期折旧（扣除）额</div></td>
	                    <td width="80"  align="center" class="2-td1-left" rowspan="2"><div align="center">累计折旧（扣除）额</div></td>
	                    <td width="80"  align="center" class="2-td1-left" rowspan="2"><div align="center">原值</div></td>
	                    <td  align="center" class="2-td1-left" colspan="2">本期折旧（扣除）额</td>
	                    <td  align="center" class="2-td1-center" colspan="2">累计折旧（扣除）额</td>
	                  </tr>
	                  <tr> 
	                    <td width="80"   class="2-td1-left" >正常折旧额</td>
	                    <td width="80"   class="2-td1-left" >加速折旧额</td>
	                    <td width="80"   class="2-td1-left" >正常折旧额</td>
	                    <td width="80"   class="2-td1-center" >加速折旧额</td>
	                  </tr>
	                  <tr> 
	                    <td  align="center" class="2-td1-left" ><div align="center">1</div></td>
	                    <td  align="center" class="2-td1-left" ><div align="center">2</div></td>
	                    <td  align="center" class="2-td1-left" ><div align="center">3</div></td>
	                    <td  align="center" class="2-td1-center" ><div align="center">4</div></td>
	                    <td  align="center" class="2-td1-left" ><div align="center">5</div></td>
	                    <td  align="center" class="2-td1-left" ><div align="center">6</div></td>
	                    <td  align="center" class="2-td1-left" ><div align="center">7</div></td>
	                    <td  align="center" class="2-td1-center" ><div align="center">8</div></td>
	                    <td  align="center" class="2-td1-left" ><div align="center">9</div></td>
	                    <td  align="center" class="2-td1-left" ><div align="center">10</div></td>
	                    <td  align="center" class="2-td1-center" ><div align="center">11</div></td>	                    	                    
	                  </tr>
			          <xsl:for-each select="//sbsj[position()>0 and 13>position()]">
					  	<tr>
			                <td class="2-td2-left"><div align="center">&#160;<xsl:value-of select="hc"/></div>
								<input name="hc" type="hidden" >
									<xsl:attribute name="id">1.<xsl:value-of select="hc" />_1</xsl:attribute>
									<xsl:attribute name="value"><xsl:value-of select="hc"/></xsl:attribute>
								</input>
			                </td>
			                <td  class="2-td2-left"><div align="left">&#160;<xsl:value-of select="xm"/></div>
								<input name="xm" type="hidden" >
									<xsl:attribute name="id">2.<xsl:value-of select="hc" />_1</xsl:attribute>
									<xsl:attribute name="value"><xsl:value-of select="xm"/></xsl:attribute>
								</input>			                
			                </td>			                							  
			                <td class="2-td2-left">
			                   <input name="fwjzw_yz" maxlength = "13" size = "13" onchange='return changeNum(this,3);'>
			                   		<xsl:attribute name="id">3.<xsl:value-of select="hc" />_1</xsl:attribute>
							  		 <xsl:attribute name="value"><xsl:value-of select="fwjzw_yz"/></xsl:attribute>
							  </input>
			                </td>
			                <td class="2-td2-left">
			                   <input name="fwjzw_bqzje" maxlength = "13" size = "13" onchange='return changeNum(this,4);'>
			                   		<xsl:attribute name="id">4.<xsl:value-of select="hc" />_1</xsl:attribute>
			               			<xsl:attribute name="value"><xsl:value-of select="fwjzw_bqzje"/></xsl:attribute>
							  </input>
						    </td>
			                <td class="2-td2-left">
			                   <input name="fwjzw_ljzje" maxlength = "13" size = "13" onchange='return changeNum(this,5);'>
			                   		<xsl:attribute name="id">5.<xsl:value-of select="hc" />_1</xsl:attribute>
							  		 <xsl:attribute name="value"><xsl:value-of select="fwjzw_ljzje"/></xsl:attribute>
							  </input>
			                </td>
			                <td class="2-td2-left">
			                   <input name="jqsbhqtgdzc_yz" maxlength = "13" size = "13" onchange='return changeNum(this,6);'>
			                   		<xsl:attribute name="id">6.<xsl:value-of select="hc" />_1</xsl:attribute>
			               			<xsl:attribute name="value"><xsl:value-of select="jqsbhqtgdzc_yz"/></xsl:attribute>
							  </input>
						    </td>
			                <td class="2-td2-left">
			                   <input name="jqsbhqtgdzc_bqzje" maxlength = "13" size = "13" onchange='return changeNum(this,7);'>
			                   		<xsl:attribute name="id">7.<xsl:value-of select="hc" />_1</xsl:attribute>
							  		 <xsl:attribute name="value"><xsl:value-of select="jqsbhqtgdzc_bqzje"/></xsl:attribute>
							  </input>
			                </td>
			                <td class="2-td2-left">
			                   <input name="jqsbhqtgdzc_ljzje" maxlength = "13" size = "13" onchange='return changeNum(this,8);'>
			                   		<xsl:attribute name="id">8.<xsl:value-of select="hc" />_1</xsl:attribute>
			               			<xsl:attribute name="value"><xsl:value-of select="jqsbhqtgdzc_ljzje"/></xsl:attribute>
							  </input>
						    </td>
			                <td class="2-td2-left">
			                   <input name="hj_yz" maxlength = "13" size = "13" onchange='return changeNum(this,9);'>
			                   		<xsl:attribute name="id">9.<xsl:value-of select="hc" />_1</xsl:attribute>
							  		 <xsl:attribute name="value"><xsl:value-of select="hj_yz"/></xsl:attribute>
							  </input>
			                </td>
			                <td class="2-td2-left">
			                   <input name="hj_bqzje_zczje" maxlength = "13" size = "13" onchange='return changeNum(this,10);'>
			                   		<xsl:attribute name="id">10.<xsl:value-of select="hc" />_1</xsl:attribute>
			               			<xsl:attribute name="value"><xsl:value-of select="hj_bqzje_zczje"/></xsl:attribute>
							  </input>
						    </td>	
			                <td class="2-td2-left">
			                   <input name="hj_bqzje_jszje" maxlength = "13" size = "13" onchange='return changeNum(this,11);'>
			                   		<xsl:attribute name="id">11.<xsl:value-of select="hc" />_1</xsl:attribute>
							  		 <xsl:attribute name="value"><xsl:value-of select="hj_bqzje_jszje"/></xsl:attribute>
							  </input>
			                </td>
			                <td class="2-td2-left">
			                   <input name="hj_ljzje_zczje" maxlength = "13" size = "13" onchange='return changeNum(this,12);'>
			                   		<xsl:attribute name="id">12.<xsl:value-of select="hc" />_1</xsl:attribute>
			               			<xsl:attribute name="value"><xsl:value-of select="hj_ljzje_zczje"/></xsl:attribute>
							  </input>
						    </td>
			                <td class="2-td2-center">
			                   <input name="hj_ljzje_jszje" maxlength = "13" size = "13" onchange='return changeNum(this,13);'>
			                   		<xsl:attribute name="id">13.<xsl:value-of select="hc" />_1</xsl:attribute>
							  		 <xsl:attribute name="value"><xsl:value-of select="hj_ljzje_jszje"/></xsl:attribute>
							  </input>
			                </td>						    					    						    
						</tr>
 				 	  </xsl:for-each>
					<xsl:for-each select="//sbsjlist">
 					  	<tr>
			                <td class="2-td2-left"><div align="center">&#160;<xsl:value-of select="zj_hc"/></div>
								<input name="zj_hc" type="hidden">
									<xsl:attribute name="id">1.<xsl:value-of select="zj_hc" />_1</xsl:attribute>
									<xsl:attribute name="value"><xsl:value-of select="zj_hc"/></xsl:attribute>
								</input>
							</td>				                
			                <td class="2-td2-left" align="center"><div align="center"><xsl:value-of select="zj_xm"/></div>
								<input name="zj_xm" type="hidden">
									<xsl:attribute name="id">2.<xsl:value-of select="zj_hc" />_1</xsl:attribute>
									<xsl:attribute name="value"><xsl:value-of select="zj_xm"/></xsl:attribute>
								</input>			                
			                </td>	
			                <td class="2-td2-left">
			                   <input name="zj_fwjzw_yz" maxlength = "13" size = "13" onchange=''>
			                   		<xsl:attribute name="id">3.<xsl:value-of select="zj_hc" />_1</xsl:attribute>
							  		 <xsl:attribute name="value"><xsl:value-of select="zj_fwjzw_yz"/></xsl:attribute>
							  </input>
			                </td>
			                <td class="2-td2-left">
			                   <input name="zj_fwjzw_bqzje" maxlength = "13" size = "13" onchange=''>
			                   		<xsl:attribute name="id">4.<xsl:value-of select="zj_hc" />_1</xsl:attribute>
			               			<xsl:attribute name="value"><xsl:value-of select="zj_fwjzw_bqzje"/></xsl:attribute>
							  </input>
						    </td>
			                <td class="2-td2-left">
			                   <input name="zj_fwjzw_ljzje"  maxlength = "13" size = "13" onchange=''>
			                   		<xsl:attribute name="id">5.<xsl:value-of select="zj_hc" />_1</xsl:attribute>
							  		 <xsl:attribute name="value"><xsl:value-of select="zj_fwjzw_ljzje"/></xsl:attribute>
							  </input>
			                </td>
			                <td class="2-td2-left">
			                   <input name="zj_jqsbhqtgdzc_yz" maxlength = "13" size = "13" onchange=''>
			                   		<xsl:attribute name="id">6.<xsl:value-of select="zj_hc" />_1</xsl:attribute>
			               			<xsl:attribute name="value"><xsl:value-of select="zj_jqsbhqtgdzc_yz"/></xsl:attribute>
							  </input>
						    </td>
			                <td class="2-td2-left">
			                   <input name="zj_jqsbhqtgdzc_bqzje"  maxlength = "13" size = "13" onchange=''>
			                   		<xsl:attribute name="id">7.<xsl:value-of select="zj_hc" />_1</xsl:attribute>
							  		 <xsl:attribute name="value"><xsl:value-of select="zj_jqsbhqtgdzc_bqzje"/></xsl:attribute>
							  </input>
			                </td>
			                <td class="2-td2-left">
			                   <input name="zj_jqsbhqtgdzc_ljzje"  maxlength = "13" size = "13" onchange=''>
			                   		<xsl:attribute name="id">8.<xsl:value-of select="zj_hc" />_1</xsl:attribute>
			               			<xsl:attribute name="value"><xsl:value-of select="zj_jqsbhqtgdzc_ljzje"/></xsl:attribute>
							  </input>
						    </td>
			                <td class="2-td2-left">
			                   <input name="zj_hj_yz" maxlength = "13" size = "13" onchange=''>
			                   		<xsl:attribute name="id">9.<xsl:value-of select="zj_hc" />_1</xsl:attribute>
							  		 <xsl:attribute name="value"><xsl:value-of select="zj_hj_yz"/></xsl:attribute>
							  </input>
			                </td>
			                <td class="2-td2-left">
			                   <input name="zj_hj_bqzje_zczje"  maxlength = "13" size = "13" onchange=''>
			                   		<xsl:attribute name="id">10.<xsl:value-of select="zj_hc" />_1</xsl:attribute>
			               			<xsl:attribute name="value"><xsl:value-of select="zj_hj_bqzje_zczje"/></xsl:attribute>
							  </input>
						    </td>	
			                <td class="2-td2-left">
			                   <input name="zj_hj_bqzje_jszje" maxlength = "13" size = "13" onchange=''>
			                   		<xsl:attribute name="id">11.<xsl:value-of select="zj_hc" />_1</xsl:attribute>
							  		 <xsl:attribute name="value"><xsl:value-of select="zj_hj_bqzje_jszje"/></xsl:attribute>
							  </input>
			                </td>
			                <td class="2-td2-left">
			                   <input name="zj_hj_ljzje_zczje"  maxlength = "13" size = "13" onchange=''>
			                   		<xsl:attribute name="id">12.<xsl:value-of select="zj_hc" />_1</xsl:attribute>
			               			<xsl:attribute name="value"><xsl:value-of select="zj_hj_ljzje_zczje"/></xsl:attribute>
							  </input>
						    </td>
			                <td class="2-td2-right">
			                   <input name="zj_hj_ljzje_jszje"  maxlength = "13" size = "13" onchange=''>
			                   		<xsl:attribute name="id">13.<xsl:value-of select="zj_hc" />_1</xsl:attribute>
							  		 <xsl:attribute name="value"><xsl:value-of select="zj_hj_ljzje_jszje"/></xsl:attribute>
							  </input>
			                </td>						    					    						    
						</tr>
						</xsl:for-each>
		          </table>
 <!--             </div>
            </td>
          </tr>
        </table>-->
	   </td>
	</tr>
	</table>
	</xsl:template>


</xsl:stylesheet>
