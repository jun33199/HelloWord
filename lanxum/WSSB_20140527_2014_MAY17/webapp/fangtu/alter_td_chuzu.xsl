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
										<td>土地坐落</td>
										<td>土地证书号</td>
										<td>土地面积</td>
										<td>其中免税面积</td>
										<td>其中应税面积</td>   
										<td>每平方米税额</td>
										<td>年应纳税额</td>
										<td>备注</td>
									</tr>
								</thead>
								<tbody>
									
									<xsl:for-each select="tdChuzuList">
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
												
												<!-- alter_data_td_chuzu -->
<td align="right"><!-- 土地坐落 -->
	<input>
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghtdzl</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/tdChuzuList/bghtdzl"/></xsl:attribute>
	</input>
</td>


<td align="right"><!-- 土地证书号 -->
	<input>
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghtdzsh</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/tdChuzuList/bghtdzsh"/></xsl:attribute>
	</input>
</td>

<td align="right"><!-- 土地面积 -->
	<input size="10">
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghtdmj</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/tdChuzuList/bghtdmj"/></xsl:attribute>
	</input>
</td>

<td align="right"><!-- 其中免税面积 -->
	<input size="10">
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghqzmsmj</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/tdChuzuList/bghqzmsmj"/></xsl:attribute>
	</input>
	
</td>
<td align="right"><!-- 其中应税面积 -->
	<input size="10">
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghqzysmj</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/tdChuzuList/bghqzysmj"/></xsl:attribute>
	</input>
	
</td>
<td align="right"><!-- 每平方米税额 -->
	<select>
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghmpfmse</xsl:attribute>
		<xsl:variable name="v_bghmpfmse1" select="alterVO/tdChuzuList/bghmpfmse"/>
		<xsl:for-each select="../slList">
			<option>
				<xsl:attribute name="value"><xsl:value-of select="value"/></xsl:attribute>
				<xsl:if test="value = ($v_bghmpfmse1)">
					<xsl:attribute name="selected">selected</xsl:attribute>
				</xsl:if>
				<xsl:value-of select="label"/>
			</option>
		</xsl:for-each>
	</select>
</td>
<td align="right"><!-- 年应纳税额 -->
	<input size="10">
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghnynse</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/tdChuzuList/bghnynse"/></xsl:attribute>
	</input>
	
</td>

<td align="right"><!-- 备注 -->
	<input>
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghbz</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/tdChuzuList/bghbz"/></xsl:attribute>
	</input>
</td>												
												<!-- alter_data_td_chuzu -->
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
											<!-- alter_update_head_td_chuzu -->
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqtdzl</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/tdChuzuList/bgqtdzl"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqtdzsh</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/tdChuzuList/bgqtdzsh"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqtdmj</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/tdChuzuList/bgqtdmj"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqqzmsmj</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/tdChuzuList/bgqqzmsmj"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqqzysmj</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/tdChuzuList/bgqqzysmj"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqmpfmse</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/tdChuzuList/bgqmpfmse"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqnynse</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/tdChuzuList/bgqnynse"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqbz</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/tdChuzuList/bgqbz"/></xsl:attribute>
												</input>
											</td>											
											<!-- alter_update_head_td_chuzu -->
											
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
											<!-- alter_update_head_td_chuzu -->
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqtdzl</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/tdChuzuList/bgqtdzl"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqtdzsh</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/tdChuzuList/bgqtdzsh"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqtdmj</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/tdChuzuList/bgqtdmj"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqqzmsmj</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/tdChuzuList/bgqqzmsmj"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqqzysmj</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/tdChuzuList/bgqqzysmj"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqmpfmse</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/tdChuzuList/bgqmpfmse"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqnynse</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/tdChuzuList/bgqnynse"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqbz</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/tdChuzuList/bgqbz"/></xsl:attribute>
												</input>
											</td>											
											<!-- alter_update_head_td_chuzu -->
											
											
										</tr>
										<tr style="display: block">
											<xsl:attribute name="id">ba_<xsl:value-of select="($pos)"/></xsl:attribute>
											<td align="right" width="51">后</td>
											
												<!-- alter_data_td_chuzu -->
<td align="right"><!-- 土地坐落 -->
	<input>
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghtdzl</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/tdChuzuList/bghtdzl"/></xsl:attribute>
	</input>
</td>


<td align="right"><!-- 土地证书号 -->
	<input>
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghtdzsh</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/tdChuzuList/bghtdzsh"/></xsl:attribute>
	</input>
</td>

<td align="right"><!-- 土地面积 -->
	<input size="10">
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghtdmj</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/tdChuzuList/bghtdmj"/></xsl:attribute>
	</input>
</td>

<td align="right"><!-- 其中免税面积 -->
	<input size="10">
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghqzmsmj</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/tdChuzuList/bghqzmsmj"/></xsl:attribute>
	</input>
	
</td>
<td align="right"><!-- 其中应税面积 -->
	<input size="10">
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghqzysmj</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/tdChuzuList/bghqzysmj"/></xsl:attribute>
	</input>
	
</td>
<td align="right"><!-- 每平方米税额 -->
	<select>
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghmpfmse</xsl:attribute>
		<xsl:variable name="v_bghmpfmse2" select="alterVO/tdChuzuList/bghmpfmse"/>
		<xsl:for-each select="../slList">
			<option>
				<xsl:attribute name="value"><xsl:value-of select="value"/></xsl:attribute>
				<xsl:if test="value = ($v_bghmpfmse2)">
					<xsl:attribute name="selected">selected</xsl:attribute>
				</xsl:if>
				<xsl:value-of select="label"/>
			</option>
		</xsl:for-each>
	</select>
</td>
<td align="right"><!-- 年应纳税额 -->
	<input size="10">
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghnynse</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/tdChuzuList/bghnynse"/></xsl:attribute>
	</input>
	
</td>

<td align="right"><!-- 备注 -->
	<input>
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghbz</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/tdChuzuList/bghbz"/></xsl:attribute>
	</input>
</td>												
												<!-- alter_data_td_chuzu -->
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
											
											<!-- alter_add_head_td_chuzu -->
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.tdzl</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/tdChuzuList/tdzl"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.tdzsh</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/tdChuzuList/tdzsh"/></xsl:attribute>
												</input>
											</td>

											<td>
												<input size="10" readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.tdmj</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/tdChuzuList/tdmj"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input size="10" readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.qzmsmj</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/tdChuzuList/qzmsmj"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input size="10" readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.qzysmj</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/tdChuzuList/qzysmj"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input size="10" readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.mpfmse</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/tdChuzuList/mpfmse"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input size="10" readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.nynse</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/tdChuzuList/nynse"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input size="10" readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.bz</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/tdChuzuList/bz"/></xsl:attribute>
												</input>
											</td>											
											<!-- alter_add_head_td_chuzu -->
										</tr>


										<tr style="display: none;">
											<xsl:attribute name="id">ba_<xsl:value-of select="($pos)"/></xsl:attribute>

											<td align="right" width="51">后</td>
											<td align="right">
												<input>
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghtdzl</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/tdChuzuList/tdzl"/></xsl:attribute>
												</input>
											</td>
											<td align="right">
												<input>
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghtdzsh</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/tdChuzuList/tdzsh"/></xsl:attribute>
												</input>
											</td>
											<td align="right">
												<input>
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghtdmj</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/tdChuzuList/tdmj"/></xsl:attribute>
												</input>
											</td>
											<td align="right">
												<input>
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghqzmsmj</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/tdChuzuList/qzmsmj"/></xsl:attribute>
												</input>
											</td>
											<td align="right">
												<input>
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghqzysmj</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/tdChuzuList/qzysmj"/></xsl:attribute>
												</input>
											</td>
											<td align="right">
												<select>
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghmpfmse</xsl:attribute>
													<xsl:variable name="v_mpfmse" select="regVO/tdChuzuList/mpfmse"/>
													<xsl:for-each select="../slList">
														<option>
															<xsl:attribute name="value"><xsl:value-of select="value"/></xsl:attribute>
															<xsl:if test="value = ($v_mpfmse)">
																<xsl:attribute name="selected">selected</xsl:attribute>
															</xsl:if>
															<xsl:value-of select="label"/>
														</option>
													</xsl:for-each>
												</select>
											</td>
											<td align="right">
												<input>
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghnynse</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/tdChuzuList/nynse"/></xsl:attribute>
												</input>
											</td>
											<td align="right">
												<input>
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghbz</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/tdChuzuList/bz"/></xsl:attribute>
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
													<xsl:attribute name="value"><xsl:value-of select="alterVO/tdChuzuList/id"/></xsl:attribute>
												</input>
												<input type="hidden">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.id</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/tdChuzuList/id"/></xsl:attribute>
												</input>
												<input type="hidden">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.jsjdm</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/tdChuzuList/jsjdm"/></xsl:attribute>
												</input>
												<input type="hidden">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.djbh</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/tdChuzuList/djbh"/></xsl:attribute>
												</input>
												<input type="hidden">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.cjr</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/tdChuzuList/cjr"/></xsl:attribute>
												</input>
												<input type="hidden">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.cjrq</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/tdChuzuList/cjrq"/></xsl:attribute>
												</input>
												<input type="hidden">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.jsjdm</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/tdChuzuList/jsjdm"/></xsl:attribute>
												</input>
												<input type="hidden">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bglx</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/tdChuzuList/bglx"/></xsl:attribute>
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
