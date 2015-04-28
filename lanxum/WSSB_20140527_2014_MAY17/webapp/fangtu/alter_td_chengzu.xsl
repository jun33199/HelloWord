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
										<td>出租人名称</td>
										<td>证件类型代码</td>
										<td>出租人证件号码</td>
										<td>承租土地坐落</td>
										<td>坐落区县</td>
										<td>土地面积</td>
										<td>是否缴纳外商投资企业土地使用费</td>
										<td>备注</td>
									</tr>
								</thead>
								<tbody>
									
									<xsl:for-each select="tdChengzuList">
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
												
												<!-- alter_data_td_chengzu -->
<td align="right"><!-- 出租人名称 -->
	<input>
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghczrmc</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/tdChengzuList/bghczrmc"/></xsl:attribute>
	</input>
</td>


<td align="right"><!-- 证件类型代码 -->
	<select>
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghzjlxdm</xsl:attribute>
		<xsl:variable name="v_bghzjlxdm" select="string(alterVO/tdChengzuList/bghzjlxdm)"/>
			<xsl:for-each select="../zhengjianList">
			<option>
				<xsl:attribute name="value"><xsl:value-of select="value"/></xsl:attribute>
				<xsl:if test="string(value) = string($v_bghzjlxdm)">
					<xsl:attribute name="selected">selected</xsl:attribute>
				</xsl:if>
				<xsl:value-of select="label"/>
			</option>
			</xsl:for-each>
	</select>
</td>

<td align="right"><!-- 出租人证件号码-->
	<input size="10">
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghczrzjhm</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/tdChengzuList/bghczrzjhm"/></xsl:attribute>
	</input>
</td>

<td align="right"><!-- 承租土地坐落 -->
	<input size="10">
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghcztdzl</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/tdChengzuList/bghcztdzl"/></xsl:attribute>
	</input>
	
</td>

<td align="right"><!-- 坐落 -->
	<select>
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghzlqxdm</xsl:attribute>
		<xsl:variable name="v_bghzlqxdm" select="string(alterVO/tdChengzuList/bghzlqxdm)"/>
			<xsl:for-each select="../regionList">
			<option>
				<xsl:attribute name="value"><xsl:value-of select="value"/></xsl:attribute>
				<xsl:if test="string(value) = string($v_bghzlqxdm)">
					<xsl:attribute name="selected">selected</xsl:attribute>
				</xsl:if>
				<xsl:value-of select="label"/>
			</option>
			</xsl:for-each>
	</select>
</td>

<td align="right"><!-- 土地面积 -->
	<input size="10">
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghtdmj</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/tdChengzuList/bghtdmj"/></xsl:attribute>
	</input>
	
</td>
<td align="right">
	<select><!-- 是否缴纳外商投资企业土地使用费 -->
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghsfjnws</xsl:attribute>
		<xsl:variable name="v_bghsfjnws" select="alterVO/tdChengzuList/bghsfjnws"/>
		<xsl:for-each select="../daiJiaoList">
			<option>
				<xsl:attribute name="value"><xsl:value-of select="value"/></xsl:attribute>
				<xsl:if test="value = ($v_bghsfjnws)">
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
		<xsl:attribute name="value"><xsl:value-of select="alterVO/tdChengzuList/bghbz"/></xsl:attribute>
	</input>
</td>												
												<!-- alter_data_td_chengzu -->
												
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
											
											<!-- alter_update_head_td_chengzu -->
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqczrmc</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/tdChengzuList/bgqczrmc"/></xsl:attribute>
												</input>
											</td>
											<td><!-- 证件类型代码 -->
												<select  readOnly="true" disabled="true">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqzjlxdm</xsl:attribute>
													<xsl:variable name="v_bghzjlxdm" select="string(alterVO/tdChengzuList/bghzjlxdm)"/>
														<xsl:for-each select="../zhengjianList">
														<option>
															<xsl:attribute name="value"><xsl:value-of select="value"/></xsl:attribute>
															<xsl:if test="string(value) = string($v_bghzjlxdm)">
																<xsl:attribute name="selected">selected</xsl:attribute>
															</xsl:if>
															<xsl:value-of select="label"/>
														</option>
														</xsl:for-each>
												</select>
											</td>
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqczrzjhm</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/tdChengzuList/bgqczrzjhm"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqcztdzl</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/tdChengzuList/bgqcztdzl"/></xsl:attribute>
												</input>
											</td>
											<td><!-- 坐落区县 -->
												<select  readOnly="true" disabled="true">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqzlqxdm</xsl:attribute>
													<xsl:variable name="v_bgqzlqxdm" select="string(alterVO/tdChengzuList/bgqzlqxdm)"/>
														<xsl:for-each select="../regionList">
														<option>
															<xsl:attribute name="value"><xsl:value-of select="value"/></xsl:attribute>
															<xsl:if test="string(value) = string($v_bgqzlqxdm)">
																<xsl:attribute name="selected">selected</xsl:attribute>
															</xsl:if>
															<xsl:value-of select="label"/>
														</option>
														</xsl:for-each>
												</select>
											</td>
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqtdmj</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/tdChengzuList/bgqtdmj"/></xsl:attribute>
												</input>
											</td>
											<td>
												<select readonly="true" disabled="true"><!-- 是否缴纳外商投资企业土地使用费 -->
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqsfjnws</xsl:attribute>
													<xsl:variable name="v_bgqsfjnws" select="alterVO/tdChengzuList/bgqsfjnws"/>
														<xsl:for-each select="../daiJiaoList">
														<option>
															<xsl:attribute name="value"><xsl:value-of select="value"/></xsl:attribute>
															<xsl:if test="value = ($v_bgqsfjnws)">
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
													<xsl:attribute name="value"><xsl:value-of select="alterVO/tdChengzuList/bgqbz"/></xsl:attribute>
												</input>
											</td>											
											<!-- alter_update_head_td_chengzu -->
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
											
											<!-- alter_update_head_td_chengzu -->
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqczrmc</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/tdChengzuList/bgqczrmc"/></xsl:attribute>
												</input>
											</td>
											<td><!-- 证件类型代码 -->
												<select  readOnly="true" disabled="true">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqzjlxdm</xsl:attribute>
													<xsl:variable name="v_bgqzjlxdm" select="string(alterVO/tdChengzuList/bgqzjlxdm)"/>	
														<xsl:for-each select="../zhengjianList">
														<option>
															<xsl:attribute name="value"><xsl:value-of select="value"/></xsl:attribute>
															<xsl:if test="string(value) = string($v_bgqzjlxdm)">
																<xsl:attribute name="selected">selected</xsl:attribute>
															</xsl:if>
															<xsl:value-of select="label"/>
														</option>
														</xsl:for-each>
												</select>
											</td>
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqczrzjhm</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/tdChengzuList/bgqczrzjhm"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqcztdzl</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/tdChengzuList/bgqcztdzl"/></xsl:attribute>
												</input>
											</td>
											<td><!-- 坐落区县 -->
												<select  readOnly="true" disabled="true">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqzlqxdm</xsl:attribute>
													<xsl:variable name="v_bgqzlqxdm" select="string(alterVO/tdChengzuList/bgqzlqxdm)"/>		
														<xsl:for-each select="../regionList">
														<option>
															<xsl:attribute name="value"><xsl:value-of select="value"/></xsl:attribute>
															<xsl:if test="string(value) = string($v_bgqzlqxdm)">
																<xsl:attribute name="selected">selected</xsl:attribute>
															</xsl:if>
															<xsl:value-of select="label"/>
														</option>
														</xsl:for-each>
												</select>
											</td>
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqtdmj</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/tdChengzuList/bgqtdmj"/></xsl:attribute>
												</input>
											</td>
											<td>
												<select readonly="true" disabled="true"><!-- 是否缴纳外商投资企业土地使用费 -->
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqsfjnws</xsl:attribute>
													<xsl:variable name="v_bgqsfjnws" select="alterVO/tdChengzuList/bgqsfjnws"/>
														<xsl:for-each select="../daiJiaoList">
														<option>
															<xsl:attribute name="value"><xsl:value-of select="value"/></xsl:attribute>
															<xsl:if test="value = ($v_bgqsfjnws)">
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
													<xsl:attribute name="value"><xsl:value-of select="alterVO/tdChengzuList/bgqbz"/></xsl:attribute>
												</input>
											</td>											
											<!-- alter_update_head_td_chengzu -->
										</tr>
										<tr style="display: block">
											<xsl:attribute name="id">ba_<xsl:value-of select="($pos)"/></xsl:attribute>
											<td align="right" width="51">后</td>
											<!-- alter data -->
<td align="right"><!-- 出租人名称 -->
	<input>
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghczrmc</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/tdChengzuList/bghczrmc"/></xsl:attribute>
	</input>
</td>


<td align="right"><!-- 证件类型代码 -->
	<select>
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghzjlxdm</xsl:attribute>
		<xsl:variable name="v_bghzjlxdm" select="string(alterVO/tdChengzuList/bghzjlxdm)"/>		
			<xsl:for-each select="../zhengjianList">
			<option>
				<xsl:attribute name="value"><xsl:value-of select="value"/></xsl:attribute>
				<xsl:if test="string(value) = string($v_bghzjlxdm)">
					<xsl:attribute name="selected">selected</xsl:attribute>
				</xsl:if>
				<xsl:value-of select="label"/>
			</option>
			</xsl:for-each>
	</select>
</td>

<td align="right"><!-- 出租人证件号码-->
	<input size="10">
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghczrzjhm</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/tdChengzuList/bghczrzjhm"/></xsl:attribute>
	</input>
</td>

<td align="right"><!-- 承租土地坐落 -->
	<input size="10">
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghcztdzl</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/tdChengzuList/bghcztdzl"/></xsl:attribute>
	</input>
	
</td>

<td align="right"><!-- 坐落区县 -->
	<select>
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghzlqxdm</xsl:attribute>
		<xsl:variable name="v_bghzlqxdm" select="string(alterVO/tdChengzuList/bghzlqxdm)"/>
			<xsl:for-each select="../regionList">
			<option>
				<xsl:attribute name="value"><xsl:value-of select="value"/></xsl:attribute>
				<xsl:if test='string(value) = string($v_bghzlqxdm)'>
					<xsl:attribute name="selected">selected</xsl:attribute>
				</xsl:if>
				<xsl:value-of select="label"/>
			</option>
			</xsl:for-each>
	</select>
</td>

<td align="right"><!-- 土地面积 -->
	<input size="10">
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghtdmj</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/tdChengzuList/bghtdmj"/></xsl:attribute>
	</input>
	
</td>
<td align="right">
	<select><!-- 是否缴纳外商投资企业土地使用费 -->
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghsfjnws</xsl:attribute>
		<xsl:if test="regVO/tdChengzuList/fhbs = '0'">
				<xsl:attribute name="readOnly">true</xsl:attribute>
				<xsl:attribute name="disabled">true</xsl:attribute>
		</xsl:if>
		<xsl:variable name="v_bghsfjnws" select="alterVO/tdChengzuList/bghsfjnws"/>
		<xsl:for-each select="../daiJiaoList">
			<option>
				<xsl:attribute name="value"><xsl:value-of select="value"/></xsl:attribute>
				<xsl:if test="value = ($v_bghsfjnws)">
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
		<xsl:attribute name="value"><xsl:value-of select="alterVO/tdChengzuList/bghbz"/></xsl:attribute>
	</input>
</td>
											<!-- alter data -->
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
											<!-- alter_add_head_td_chengzu -->
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.czrmc</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/tdChengzuList/czrmc"/></xsl:attribute>
												</input>
											</td>
											<td><!-- 证件类型代码 -->
												<select readOnly="true" disabled="true" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.zjlxdm</xsl:attribute>
													<xsl:variable name="v_zjlxdm" select="string(regVO/tdChengzuList/zjlxdm)"/>	
														<xsl:for-each select="../zhengjianList">
														<option>
															<xsl:attribute name="value"><xsl:value-of select="value"/></xsl:attribute>
															<xsl:if test="string(value) = string($v_zjlxdm)">
																<xsl:attribute name="selected">selected</xsl:attribute>
															</xsl:if>
															<xsl:value-of select="label"/>
														</option>
														</xsl:for-each>
												</select>
											</td>

											<td>
												<input size="10" readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.czrzjhm</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/tdChengzuList/czrzjhm"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input size="10" readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.cztdzl</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/tdChengzuList/cztdzl"/></xsl:attribute>
												</input>
											</td>
											
											<td><!-- 坐落区县 -->												
												<select readOnly="true" disabled="true" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.zlqxdm</xsl:attribute>
													<xsl:variable name="v_zlqxdm" select="string(regVO/tdChengzuList/zlqxdm)"/>
														<xsl:for-each select="../regionList">
														<option>
															<xsl:attribute name="value"><xsl:value-of select="value"/></xsl:attribute>
															<xsl:if test="string(value) = string($v_zlqxdm)">
																<xsl:attribute name="selected">selected</xsl:attribute>
															</xsl:if>
															<xsl:value-of select="label"/>
														</option>
														</xsl:for-each>
												</select>
											</td>

											<td>
												<input size="10" readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.tdmj</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/tdChengzuList/tdmj"/></xsl:attribute>
												</input>
											</td>
											<td>
												<select readonly="true" disabled="true"><!-- 是否缴纳外商投资企业土地使用费 -->
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.sfjnws</xsl:attribute>
														<xsl:variable name="v_sfjnws" select="regVO/tdChengzuList/sfjnws"/>
														<xsl:for-each select="../daiJiaoList">
														<option>
															<xsl:attribute name="value"><xsl:value-of select="value"/></xsl:attribute>
															<xsl:if test="value = ($v_sfjnws)">
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
													<xsl:attribute name="value"><xsl:value-of select="regVO/tdChengzuList/bz"/></xsl:attribute>
												</input>
											</td>											
											<!-- alter_add_head_td_chengzu -->
											
										</tr>


										<tr style="display: none;">
											<xsl:attribute name="id">ba_<xsl:value-of select="($pos)"/></xsl:attribute>

											<td align="right" width="51">后</td>
											<td align="right">
												<input>
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghczrmc</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/tdChengzuList/czrmc"/></xsl:attribute>
												</input>
											</td>
											<td align="right"><!-- 证件类型代码 -->
												<select>
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghzjlxdm</xsl:attribute>
													<xsl:variable name="v_zjlxdm2" select="string(regVO/tdChengzuList/zjlxdm)"/>
														<xsl:for-each select="../zhengjianList">
														<option>
															<xsl:attribute name="value"><xsl:value-of select="value"/></xsl:attribute>
															<xsl:if test="string(value) = string($v_zjlxdm2)">
																<xsl:attribute name="selected">selected</xsl:attribute>
															</xsl:if>
															<xsl:value-of select="label"/>
														</option>
														</xsl:for-each>
												</select>
											</td>
											<td align="right">
												<input>
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghczrzjhm</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/tdChengzuList/czrzjhm"/></xsl:attribute>
												</input>
											</td>
											<td align="right">
												<input>
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghcztdzl</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/tdChengzuList/cztdzl"/></xsl:attribute>
												</input>
											</td>
											
											<td align="right"><!-- 坐落区县 -->
												<select>
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghzlqxdm</xsl:attribute>
													<xsl:variable name="v_bghzlqxdm2" select="string(alterVO/tdChengzuList/bghzlqxdm)"/>
														<xsl:for-each select="../regionList">
														<option>
															<xsl:attribute name="value"><xsl:value-of select="value"/></xsl:attribute>
															<xsl:if test="string(value) = string($v_bghzlqxdm2)">
																<xsl:attribute name="selected">selected</xsl:attribute>
															</xsl:if>
															<xsl:value-of select="label"/>
														</option>
														</xsl:for-each>
												</select>
											</td>
											<td align="right">
												<input>
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghtdmj</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/tdChengzuList/tdmj"/></xsl:attribute>
												</input>
											</td>
											<td align="right">
												<select><!-- 是否缴纳外商投资企业土地使用费 -->
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghsfjnws</xsl:attribute>
														<xsl:variable name="v_fjnws_yfh" select="regVO/tdChengzuList/sfjnws"/>
														<xsl:for-each select="../daiJiaoList">
														<option>
															<xsl:attribute name="value"><xsl:value-of select="value"/></xsl:attribute>
															<xsl:if test="value = ($v_fjnws_yfh)">
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
													<xsl:attribute name="value"><xsl:value-of select="regVO/tdChengzuList/bz"/></xsl:attribute>
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
													<xsl:attribute name="value"><xsl:value-of select="alterVO/tdChengzuList/id"/></xsl:attribute>
												</input>
												<input type="hidden">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.id</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/tdChengzuList/id"/></xsl:attribute>
												</input>
												<input type="hidden">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.jsjdm</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/tdChengzuList/jsjdm"/></xsl:attribute>
												</input>
												<input type="hidden">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.djbh</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/tdChengzuList/djbh"/></xsl:attribute>
												</input>
												<input type="hidden">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.cjr</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/tdChengzuList/cjr"/></xsl:attribute>
												</input>
												<input type="hidden">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.cjrq</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/tdChengzuList/cjrq"/></xsl:attribute>
												</input>
												<input type="hidden">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.jsjdm</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/tdChengzuList/jsjdm"/></xsl:attribute>
												</input>
												<input type="hidden">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bglx</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/tdChengzuList/bglx"/></xsl:attribute>
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
<!-- Stylus Studio meta-information - (c) 2004-2006. Progress Software Corporation. All rights reserved.
<metaInformation>
<scenarios ><scenario default="yes" name="Scenario1" userelativepaths="yes" externalpreview="no" url="file:///c:/Documents and Settings/Administrator/桌面/test.xml" htmlbaseurl="" outputurl="" processortype="internal" useresolver="yes" profilemode="0" profiledepth="" profilelength="" urlprofilexml="" commandline="" additionalpath="" additionalclasspath="" postprocessortype="none" postprocesscommandline="" postprocessadditionalpath="" postprocessgeneratedext="" validateoutput="no" validator="internal" customvalidator=""/></scenarios><MapperMetaTag><MapperInfo srcSchemaPathIsRelative="yes" srcSchemaInterpretAsXML="no" destSchemaPath="" destSchemaRoot="" destSchemaPathIsRelative="yes" destSchemaInterpretAsXML="no"/><MapperBlockPosition></MapperBlockPosition><TemplateContext></TemplateContext><MapperFilter side="source"></MapperFilter></MapperMetaTag>
</metaInformation>
-->