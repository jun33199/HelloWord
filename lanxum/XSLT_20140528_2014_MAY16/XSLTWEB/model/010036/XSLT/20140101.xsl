<?xml version="1.0" encoding="gb2312"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" />
	<xsl:template match="/">
		<table width="96%" align="center" cellspacing="0" class="table-99">
			<tr>
				<td>
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
					
					<input name="jsjdm" type="hidden" id="jsjdm">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrjbxx/jsjdm" />
						</xsl:attribute>
					</input>
					<input name="nsrsbh" type="hidden" id="nsrsbh">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrjbxx/nsrsbh" />
						</xsl:attribute>
					</input>
					<input name="nsrmc" type="hidden" id="nsrmc">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrjbxx/nsrmc" />
						</xsl:attribute>
					</input>
					<input name="ssjjlxdm" type="hidden" id="ssjjlxdm">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrjbxx/ssjjlxdm" />
						</xsl:attribute>
					</input>
					<input name="ssjjlxmc" type="hidden" id="ssjjlxmc">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrjbxx/ssjjlxmc" />
						</xsl:attribute>
					</input>
					<input name="jydz" type="hidden" id="jydz">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrjbxx/jydz" />
						</xsl:attribute>
					</input>
					<input name="sshydm" type="hidden" id="sshydm">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrjbxx/sshydm" />
						</xsl:attribute>
					</input>
					<input name="sshymc" type="hidden" id="sshymc">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrjbxx/sshymc" />
						</xsl:attribute>
					</input>
					<input name="swjgzzjgdm" type="hidden" id="swjgzzjgdm">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrjbxx/swjgzzjgdm" />
						</xsl:attribute>
					</input>
					<input name="swjgzzjgmc" type="hidden" id="swjgzzjgmc">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrjbxx/swjgzzjgmc" />
						</xsl:attribute>
					</input>
					<input name="cjr" type="hidden" id="cjr">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrjbxx/cjr" />
						</xsl:attribute>
					</input>
					<input name="cjrq" type="hidden" id="cjrq">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrjbxx/cjrq" />
						</xsl:attribute>
					</input>
					<input name="lrr" type="hidden" id="lrr">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrjbxx/lrr" />
						</xsl:attribute>
					</input>
					<input name="lrrq" type="hidden" id="lrrq">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrjbxx/lrrq" />
						</xsl:attribute>
					</input>
					<input name="tbrq" type="hidden" id="tbrq">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrjbxx/tbrq" />
						</xsl:attribute>
					</input>
					<input name="xtjb" type="hidden" id="xtjb">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrjbxx/xtjb" />
						</xsl:attribute>
					</input>
					<input name="bbmsf" type="hidden" id="bbmsf">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrjbxx/bbmsf" />
						</xsl:attribute>
					</input>
					<input name="qssbksrq" type="hidden" id="qssbksrq">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrjbxx/qssbksrq" />
						</xsl:attribute>
					</input>
					<input name="qssbjsrq" type="hidden" id="qssbjsrq">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrjbxx/qssbjsrq" />
						</xsl:attribute>
					</input>
					<input name="version" type="hidden" id="version">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrjbxx/version" />
						</xsl:attribute>
					</input>
					<input name="sqspbh" type="hidden" id="sqspbh">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrjbxx/sqspbh" />
						</xsl:attribute>
					</input>

					<input name="baShztbs" type="hidden" id="baShztbs">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrjbxx/baShztbs" />
						</xsl:attribute>
					</input>

					<input name="baShztMessage" type="hidden" id="baShztMessage">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrjbxx/baShztMessage" />
						</xsl:attribute>
					</input>

					<input name="baShtgrq" type="hidden" id="baShtgrq">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrjbxx/baShtgrq" />
						</xsl:attribute>
					</input>
					<input name="qsbaksrq" type="hidden" id="qsbaksrq">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrjbxx/qsbaksrq" />
						</xsl:attribute>
					</input>
					<input name="sfwxjxba" type="hidden" id="sfwxjxba">
						<xsl:attribute name="value">
							<xsl:value-of select="taxdoc/nsrjbxx/sfwxjxba" />
						</xsl:attribute>
					</input>
				</td>
			</tr>
			<tr>
				<td class="1-td1" colspan="7">中华人民共和国企业清算所得税备案表</td>
			</tr>
			<xsl:apply-templates select="taxdoc/nsrjbxx" />
		</table>
		<br />
	</xsl:template>
	<xsl:template match="taxdoc/nsrjbxx">
		<tr>
			<td class="1-td2" colspan="7">
							<br/>
				<table class="table-99" align="center">
					<tr>
						<td>
							<div id="Layer2" style="position:static;">
								<table id="wrklistTable" border="1" cellspacing="0" class="table-99" align="center">
									
									<tr>
										<td class="2-td2-t-left" nowrap="nowrap"> 
											<div align="center">
											  清算备案起始日期
											</div>
										</td>
										<td  class="2-td2-t-left" nowrap="nowrap">
											<div id="mx_zsfsdm_epx_visible_1" align="center">
												<xsl:value-of select="qsbaksrq" />
											</div>
										</td>
										<td class="2-td2-t-left" nowrap="nowrap">计算机代码</td>
										<td class="2-td2-t-center" nowrap="nowrap">
											<xsl:value-of select="jsjdm" />
										</td>
									</tr>
									
									<tr>
										<td class="2-td2-left" nowrap="nowrap">纳税人识别号</td>
										<td class="2-td2-left" nowrap="nowrap">
											<xsl:value-of select="nsrsbh" />
										</td>
										<td class="2-td2-left" nowrap="nowrap">纳税人名称</td>
										<td class="2-td2-center" nowrap="nowrap">
											<xsl:value-of select="nsrmc" />
										</td>
									</tr>
									<tr>
										<td class="2-td2-left" nowrap="nowrap">管理人或清算组联络人员</td>
										<td class="2-td2-left" nowrap="nowrap">
											<input type='text' name='qsllry' id='qsllry' value='qsllry' size='20' maxlength='16' tabindex='2'>
												<xsl:attribute name="value">
													<xsl:value-of select="qsllry" />
												</xsl:attribute>
											</input>
										</td>
										<td class="2-td2-left" nowrap="nowrap">联系电话</td>
										<td class="2-td2-center" nowrap="nowrap">
											<input type='text' name='lxdh' id='lxdh' value='lxdh' size='20' maxlength='16' tabindex='2'>
												<xsl:attribute name="value">
													<xsl:value-of select="lxdh" />
												</xsl:attribute>
											</input>
										</td>
									</tr>

									<tr>
										<td colspan="2" class="2-td2-left" nowrap="nowrap" style="text-align:right">企业章程规定的经营期限届满</td>
										
										<td colspan="2" class="2-td2-center" nowrap="nowrap" style="text-align:left">

											<input name="jyqxjm" type="hidden" id="jyqxjm">
												<xsl:attribute name="value">
													<xsl:value-of select="jyqxjm" />
												</xsl:attribute>
											</input>
											<input type="Radio" name="jyqxjm_show" id="jyqxjm_Y" value="Y"/>是
											<input type="Radio" name="jyqxjm_show" id="jyqxjm_N" value="N"/>否
										</td>
										
									</tr>
									
									
	
									<tr>
										<td colspan="2" class="2-td2-left" nowrap="nowrap" style="text-align:right">企业股东会、股东大会或类似机构决议解散</td>
										
										<td colspan="2" class="2-td2-center" nowrap="nowrap" style="text-align:left">
											<input name="gdjyjs" type="hidden" id="gdjyjs">
												<xsl:attribute name="value">
													<xsl:value-of select="gdjyjs" />
												</xsl:attribute>
											</input>
											<input type="Radio" name="gdjyjs_show" id="gdjyjs_Y" value="Y"/>是
											<input type="Radio" name="gdjyjs_show" id="gdjyjs_N" value="N"/>否
										</td>
										
									</tr>

									<tr>
										<td colspan="2" class="2-td2-left" nowrap="nowrap" style="text-align:right">企业依法被吊销营业执照、责令关闭或者被撤销  </td>
										
										<td colspan="2" class="2-td2-center" nowrap="nowrap" style="text-align:left">
											<input name="yfdxgb" type="hidden" id="yfdxgb">
												<xsl:attribute name="value">
													<xsl:value-of select="yfdxgb" />
												</xsl:attribute>
											</input>
											<input type="Radio" name="yfdxgb_show" id="yfdxgb_Y" value="Y"/>是
											<input type="Radio" name="yfdxgb_show" id="yfdxgb_N" value="N"/>否
										</td>
										
									</tr>

									<tr>
										<td colspan="2" class="2-td2-left" nowrap="nowrap" style="text-align:right">企业被人民法院依法予以解散或宣告破产</td>
										
										<td colspan="2" class="2-td2-center" nowrap="nowrap" style="text-align:left">
											<input name="yfxgpc" type="hidden" id="yfxgpc">
												<xsl:attribute name="value">
													<xsl:value-of select="yfxgpc" />
												</xsl:attribute>
											</input>
											<input type="Radio" name="yfxgpc_show" id="yfxgpc_Y" value="Y"/>是
											<input type="Radio" name="yfxgpc_show" id="yfxgpc_N" value="N"/>否
										</td>
										
									</tr>

									<tr>
										<td colspan="2" class="2-td2-left" nowrap="nowrap" style="text-align:right">有关法律、行政法规规定清算</td>
										
										<td colspan="2" class="2-td2-center" nowrap="nowrap" style="text-align:left">
											<input name="yfgdqs" type="hidden" id="yfgdqs">
												<xsl:attribute name="value">
													<xsl:value-of select="yfgdqs" />
												</xsl:attribute>
											</input>
											<input type="Radio" name="yfgdqs_show" id="yfgdqs_Y" value="Y"/>是
											<input type="Radio" name="yfgdqs_show" id="yfgdqs_N" value="N"/>否
										</td>
										
									</tr>

									<tr>
										<td colspan="2" class="2-td2-left" nowrap="nowrap" style="text-align:right">企业因其他原因解散或进行清算</td>
										
										<td colspan="2" class="2-td2-center" nowrap="nowrap" style="text-align:left">
											<input name="qtyy" type="hidden" id="qtyy">
												<xsl:attribute name="value">
													<xsl:value-of select="qtyy" />
												</xsl:attribute>
											</input>
											<input type="Radio" name="qtyy_show" id="qtyy_Y" value="Y"/>是
											<input type="Radio" name="qtyy_show" id="qtyy_N" value="N"/>否
										</td>
										
									</tr>

									<tr>
										<td  class="2-td2-left" nowrap="nowrap">审核状态提示</td>
										
										<td colspan="3" class="2-td2-center" nowrap="nowrap">
											<div align="left" style="color:red;">
												<xsl:value-of select="baShztMessage" />
											</div>
										</td>
										
									</tr>
									
								</table>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</xsl:template>
</xsl:stylesheet>
