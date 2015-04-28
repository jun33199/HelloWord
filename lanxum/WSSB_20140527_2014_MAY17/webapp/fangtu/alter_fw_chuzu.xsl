<?xml version='1.0' encoding='utf-8'?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html"/>

<xsl:template match="taxdoc">


							<table cellspacing="1" cellpadding="1" width="100%" id="theTable">
								<thead>
									<tr>
										<td width="36">序号</td>
										<td width="38">变更</td>
										<td width="41">删除</td>
										<td width="51">状态</td>
										<td></td>
										<td>产权证书号</td>
										<td>出租房屋原值</td>
										<td>房屋坐落</td>
										<td>年租金收入</td>
										<td>年应纳税额</td>
										<td>其他</td>
									</tr>
								</thead>
								<tbody>
									
									<xsl:for-each select="fwChuzuList">
										<xsl:variable name="pos" select="position() - 1"/>
										<!-- 如果是新增变更 -->
										<xsl:if test="opFlag = 'new'">
											<tr name="old_new">
												<xsl:attribute name="id">aa_<xsl:value-of select="($pos)"/></xsl:attribute>
												<xsl:call-template name="alter_inc"/>
												<td width="36"><xsl:value-of select="($pos + 1)"/></td>
												<td width="38">
													<!-- 选中后，表示该条新增的变更为要更新 -->
													<input type="checkbox"
	                                                       onclick="modify_new(this)"
														   value="1"
	                                                >
														<xsl:attribute name="id">ab_<xsl:value-of select="($pos)"/></xsl:attribute>
														<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].updateFlag</xsl:attribute>
													</input>
												</td>
												<td width="41">
	                                                <!-- 选中后，标记该条新增的变更为要删除 -->
													<input type="checkbox"
	                                                       onclick="delete_m(this)"
														   value="1"
	                                                >
														<xsl:attribute name="id">ac_<xsl:value-of select="($pos)"/></xsl:attribute>
														<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].deleteFlag</xsl:attribute>
													</input>
												</td>
												<td>新增</td>
												<td align="right" width="51" >
													<xsl:attribute name="id">ad_<xsl:value-of select="($pos)"/></xsl:attribute>
													后
												</td>
												<!-- alter_data_chuzu -->
<td align="right"><!-- 产权证书号 -->
	<input>
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghcqzsh</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/fwChuzuList/bghcqzsh"/></xsl:attribute>
	</input>
</td>

<td align="right"><!-- 出租房屋原值 -->
	<input size="10">
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghczfwyz</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/fwChuzuList/bghczfwyz"/></xsl:attribute>
	</input>
</td>

<td align="right"><!-- 房屋坐落 -->
	<input>
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghfwzl</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/fwChuzuList/bghfwzl"/></xsl:attribute>
	</input>
</td>

<td align="right"><!-- 年租金收入 -->
	<input size="10">
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghnzjsr</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/fwChuzuList/bghnzjsr"/></xsl:attribute>
	</input>
</td>

<td align="right"><!-- 年应纳税额 -->
	<input size="10">
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghnynse</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/fwChuzuList/bghnynse"/></xsl:attribute>
	</input>
	
</td>

<td align="right">
	<select onchange="selectXgrczbs(this)"><!-- 是否按市场价格出租用于居住的住房 -->
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghxgrczbs</xsl:attribute>
		<xsl:variable name="v_bghxgrczbs" select="alterVO/fwChuzuList/bghxgrczbs"/>
		<xsl:for-each select="../daiJiaoList">
			<option>
				<xsl:attribute name="value"><xsl:value-of select="value"/></xsl:attribute>
				<xsl:if test="value = ($v_bghxgrczbs)">
					<xsl:attribute name="selected">selected</xsl:attribute>
				</xsl:if>
				<xsl:value-of select="label"/>
			</option>
		</xsl:for-each>
	</select>
</td>

<td align="right"><!-- 备注 -->
	<input>
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghbz</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/fwChuzuList/bghbz"/></xsl:attribute>
	</input>
</td>
												<!-- alter_data_chuzu -->
											</tr>
										</xsl:if>
										
									<!-- 如果是删除变更 -->
									<xsl:if test="opFlag = 'delete'">
										<tr name="delete">
											<xsl:attribute name="id" >aa_<xsl:value-of select="($pos)"/></xsl:attribute>
											<xsl:call-template name="alter_inc"/>
											<td width="36"><xsl:value-of select="($pos + 1)"/></td>
											<td width="38">
												<input value="0" type="hidden">
													<xsl:attribute name="id">ac_<xsl:value-of select="($pos)"/></xsl:attribute>
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].updateFlag</xsl:attribute>
												</input>
											</td>
											<td width="41">
												<input value="1"  type="checkbox" onclick="delete_m(this)" >
													<xsl:attribute name="id">ac_<xsl:value-of select="($pos)"/></xsl:attribute>
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].deleteFlag</xsl:attribute>
												</input>
											</td>
											<td>减少</td>
											<td width="51">
												<xsl:attribute name="id">ad_<xsl:value-of select="($pos)"/></xsl:attribute>
												前
											</td>
											<!-- alter_update_head_chuzu -->
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqcqzsh</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/fwChuzuList/bgqcqzsh"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqczfwyz</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/fwChuzuList/bgqczfwyz"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqfwzl</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/fwChuzuList/bgqfwzl"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqnzjsr</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/fwChuzuList/bgqnzjsr"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqnynse</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/fwChuzuList/bgqnynse"/></xsl:attribute>
												</input>
											</td>
											<td>
												<select readonly="true" disabled="true"><!-- 是否按市场价格向个人出租用于居住的住房 -->
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqxgrczbs</xsl:attribute>
													<xsl:variable name="v_bgqxgrczbs" select="alterVO/fwChuzuList/bgqxgrczbs"/>
														<xsl:for-each select="../daiJiaoList">
														<option>
															<xsl:attribute name="value"><xsl:value-of select="value"/></xsl:attribute>
															<xsl:if test="value = ($v_bgqxgrczbs)">
																<xsl:attribute name="selected">selected</xsl:attribute>
															</xsl:if>
															<xsl:value-of select="label"/>
														</option>
														</xsl:for-each>
												</select>																								
											</td>
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqbz</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/fwChuzuList/bgqbz"/></xsl:attribute>
												</input>
											</td>
											<!-- alter_update_head_chuzu -->
										</tr>
									</xsl:if>


									<!-- 如果是修改变更 -->
									<xsl:if test="opFlag = 'update'">
										<tr name="update">
											<xsl:attribute name="id">aa_<xsl:value-of select="($pos)"/></xsl:attribute>
											<xsl:call-template name="alter_inc"/>
											<td rowspan="2" width="36"><xsl:value-of select="($pos + 1)"/></td>
											<td rowspan="2" width="38">  
													<!--选中后，将变更后内容变成可编辑状态-->
	                                                <input type="checkbox" onclick="show_m1(this)" value="1">
														<xsl:attribute name="id">ab_<xsl:value-of select="($pos)"/></xsl:attribute>
														<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].updateFlag</xsl:attribute>
													</input>
											</td>
											<td rowspan="2" width="41">
                                                <!--选中后，表示删除该条变更记录-->
                                                <input type="checkbox"
                                                       onclick="delete_m(this)"
													   value="1"
                                                >
													<xsl:attribute name="id">ac_<xsl:value-of select="($pos)"/></xsl:attribute>
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].deleteFlag</xsl:attribute>
												</input>
                                            </td>
											<td rowspan="2">变更</td>
											<td width="51">
												<xsl:attribute name="id">ad_<xsl:value-of select="($pos)"/></xsl:attribute>
												前
											</td>
											<!-- alter_update_head_chuzu -->
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqcqzsh</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/fwChuzuList/bgqcqzsh"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqczfwyz</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/fwChuzuList/bgqczfwyz"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqfwzl</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/fwChuzuList/bgqfwzl"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqnzjsr</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/fwChuzuList/bgqnzjsr"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqnynse</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/fwChuzuList/bgqnynse"/></xsl:attribute>
												</input>
											</td>
											<td>
												<select readonly="true" disabled="true"><!-- 是否代缴 -->
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqxgrczbs</xsl:attribute>
													<xsl:variable name="v_bgqxgrczbs2" select="alterVO/fwChuzuList/bgqxgrczbs"/>
														<xsl:for-each select="../daiJiaoList">
														<option>
															<xsl:attribute name="value"><xsl:value-of select="value"/></xsl:attribute>
															<xsl:if test="value = ($v_bgqxgrczbs2)">
																<xsl:attribute name="selected">selected</xsl:attribute>
															</xsl:if>
															<xsl:value-of select="label"/>
														</option>
														</xsl:for-each>
												</select>																								
											</td>
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqbz</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/fwChuzuList/bgqbz"/></xsl:attribute>
												</input>
											</td>
											<!-- alter_update_head_chuzu -->
										</tr>
										<tr style="display: block">
											<xsl:attribute name="id">ba_<xsl:value-of select="($pos)"/></xsl:attribute>
											<td align="right" width="51">后</td>
												<!-- alter_data_chuzu -->
<td align="right"><!-- 产权证书号 -->
	<input>
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghcqzsh</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/fwChuzuList/bghcqzsh"/></xsl:attribute>
	</input>
</td>

<td align="right"><!-- 出租房屋原值 -->
	<input size="10">
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghczfwyz</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/fwChuzuList/bghczfwyz"/></xsl:attribute>
	</input>
</td>

<td align="right"><!-- 房屋坐落 -->
	<input>
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghfwzl</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/fwChuzuList/bghfwzl"/></xsl:attribute>
	</input>
</td>

<td align="right"><!-- 年租金收入 -->
	<input size="10">
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghnzjsr</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/fwChuzuList/bghnzjsr"/></xsl:attribute>
	</input>
</td>

<td align="right"><!-- 年应纳税额 -->
	<input size="10">
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghnynse</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/fwChuzuList/bghnynse"/></xsl:attribute>
	</input>
	
</td>

<td align="right">
	<select onchange="selectXgrczbs(this)"><!-- 是否按市场价格向个人出租用于居住的住房 -->
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghxgrczbs</xsl:attribute>
		<xsl:if test="regVO/fwChuzuList/fhbs = '0'">
				<xsl:attribute name="readOnly">true</xsl:attribute>
				<xsl:attribute name="disabled">true</xsl:attribute>
		</xsl:if>
		<xsl:variable name="v_bghxgrczbs3" select="alterVO/fwChuzuList/bghxgrczbs"/>
		<xsl:for-each select="../daiJiaoList">
			<option>
				<xsl:attribute name="value"><xsl:value-of select="value"/></xsl:attribute>
				<xsl:if test="value = ($v_bghxgrczbs3)">
					<xsl:attribute name="selected">selected</xsl:attribute>
				</xsl:if>
				<xsl:value-of select="label"/>
			</option>
		</xsl:for-each>
	</select>
</td>

<td align="right"><!-- 备注 -->
	<input>
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghbz</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/fwChuzuList/bghbz"/></xsl:attribute>
	</input>
</td>
												<!-- alter_data_chuzu -->
										</tr>

									</xsl:if>

										<!-- 如果是原始已复核登记 -->
										<xsl:if test="opFlag = 'old'">
										
										<tr name="old">
											<xsl:attribute name="id">aa_<xsl:value-of select="($pos)"/></xsl:attribute>
												<xsl:call-template name="alter_inc"/>

											<td rowspan="2" width="36"><xsl:value-of select="($pos + 1)"/></td>
											<td rowspan="2" width="38">
                                                <!-- 修改checkbox，选中后：弹出变更后行，未选时：隐藏变更后行 -->
                                                <input type="checkbox"
                                                       onclick="show_m1(this)"
													   value="1"
                                                >
												<xsl:attribute name="id">ab_<xsl:value-of select="($pos)"/></xsl:attribute>
												<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].updateFlag</xsl:attribute>
												</input>
                                            </td>
											<td rowspan="2" width="41">
                                                <!-- 删除checkbox，选中后：弹出变更后行，未选时：隐藏变更后行 -->
                                                <input type="checkbox"
                                                       onclick="delete_m(this)"
													   value="1"
                                                >
												<xsl:attribute name="id">ac_<xsl:value-of select="($pos)"/></xsl:attribute>
												<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].deleteFlag</xsl:attribute>
												</input>
                                            </td>
                                            <td rowspan="2" nowrap="nowrap">登记数据</td>
											<td width="51">
												<xsl:attribute name="id">ad_<xsl:value-of select="($pos)"/></xsl:attribute>
												前	
											</td>
											<!-- alter_add_head_chuzu -->
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.cqzsh</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/fwChuzuList/cqzsh"/></xsl:attribute>
												</input>
											</td>
											
											<td>
												<input size="10" readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.czfwyz</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/fwChuzuList/czfwyz"/></xsl:attribute>
												</input>
											</td>
											
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.fwzl</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/fwChuzuList/fwzl"/></xsl:attribute>
												</input>
											</td>

											<td>
												<input size="10" readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.nzjsr</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/fwChuzuList/nzjsr"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input size="10" readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.nynse</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/fwChuzuList/nynse"/></xsl:attribute>
												</input>
											</td>
											<td>
												<select readonly="true" disabled="true">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.xgrczbs</xsl:attribute>
														<xsl:variable name="v_xgrczbs" select="regVO/fwChuzuList/xgrczbs"/>
														<xsl:for-each select="../daiJiaoList">
														<option>
															<xsl:attribute name="value"><xsl:value-of select="value"/></xsl:attribute>
															<xsl:if test="value = ($v_xgrczbs)">
																<xsl:attribute name="selected">selected</xsl:attribute>
															</xsl:if>
															<xsl:value-of select="label"/>
														</option>
														</xsl:for-each>
												</select>												
											</td>
											<td>
												<input size="10" readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.bz</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/fwChuzuList/bz"/></xsl:attribute>
												</input>
											</td>
											<!-- alter_add_head_chuzu -->
											
										</tr>


										<tr style="display: none;">
											<xsl:attribute name="id">ba_<xsl:value-of select="($pos)"/></xsl:attribute>

											<td align="right" width="51">后</td>
											<td align="right">
												<input>
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghcqzsh</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/fwChuzuList/cqzsh"/></xsl:attribute>
												</input>
											</td>
											<td align="right">
												<input>
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghczfwyz</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/fwChuzuList/czfwyz"/></xsl:attribute>
												</input>
											</td>
											<td align="right">
												<input>
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghfwzl</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/fwChuzuList/fwzl"/></xsl:attribute>
												</input>
											</td>
											<td align="right">
												<input>
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghnzjsr</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/fwChuzuList/nzjsr"/></xsl:attribute>
												</input>
											</td>
											<td align="right">
												<input>
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghnynse</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/fwChuzuList/nynse"/></xsl:attribute>
												</input>
											</td>
											
											<td align="right">
												<select onchange="selectXgrczbs(this)"><!-- 是否代缴 -->
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghxgrczbs</xsl:attribute>
														<xsl:variable name="v_xgrczbs_yfh" select="regVO/fwChuzuList/xgrczbs"/>
														<xsl:for-each select="../daiJiaoList">
														<option>
															<xsl:attribute name="value"><xsl:value-of select="value"/></xsl:attribute>
															<xsl:if test="value = ($v_xgrczbs_yfh)">
																<xsl:attribute name="selected">selected</xsl:attribute>
															</xsl:if>
															<xsl:value-of select="label"/>
														</option>
														</xsl:for-each>
												</select>
											</td>
											
											<td align="right">
												<input>
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghbz</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/fwChuzuList/bz"/></xsl:attribute>
												</input>
											</td>
										</tr>
										</xsl:if>

											

									</xsl:for-each>

								</tbody>
								
							</table>


</xsl:template>



<xsl:template name="alter_inc">
<xsl:variable name="pos" select="position() - 1"/>
												<input type="hidden">
													<xsl:attribute name="id">aOpFlag_<xsl:value-of select="($pos)"/></xsl:attribute>
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].opFlag</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="opFlag"/></xsl:attribute>
												</input>
												<input type="hidden">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.id</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/fwChuzuList/id"/></xsl:attribute>
												</input>
												<input type="hidden">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.id</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/fwChuzuList/id"/></xsl:attribute>
												</input>
												<input type="hidden">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.jsjdm</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/fwChuzuList/jsjdm"/></xsl:attribute>
												</input>
												<input type="hidden">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.djbh</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/fwChuzuList/djbh"/></xsl:attribute>
												</input>
												<input type="hidden">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.cjr</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/fwChuzuList/cjr"/></xsl:attribute>
												</input>
												<input type="hidden">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.cjrq</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/fwChuzuList/cjrq"/></xsl:attribute>
												</input>
												<input type="hidden">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.jsjdm</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/fwChuzuList/jsjdm"/></xsl:attribute>
												</input>
												<input type="hidden">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bglx</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/fwChuzuList/bglx"/></xsl:attribute>
												</input>
</xsl:template>

<xsl:template name="toDate">
	<xsl:param name="ts"/>

	<xsl:choose>
		<xsl:when test="string-length($ts) > 8">
			<xsl:value-of select="substring($ts,1,4)"/>
			<xsl:value-of select="substring($ts,6,2)"/>
			<xsl:value-of select="substring($ts,9,2)"/>
		</xsl:when>
		<xsl:otherwise>
			<xsl:value-of select="$ts"/>
		</xsl:otherwise>
	</xsl:choose>
</xsl:template>

</xsl:stylesheet>
