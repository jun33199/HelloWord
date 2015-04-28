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
										<td>证件类型</td>
										<td>出租人证件号码</td>
										<td>承租房屋坐落</td>
										<td>坐落区县</td>
										<td>年租金</td>
										<td>备注</td>
									</tr>
								</thead>
								<tbody>
									
									<xsl:for-each select="fwChengzuList">
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
												<!-- alter_data_chengzu" -->
<td align="right"><!-- 出租人名称 -->
	<input>
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghczrmc</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/fwChengzuList/bghczrmc"/></xsl:attribute>
	</input>
</td>


<td align="right"><!-- 证件类型 -->
	<select>
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghzjlxdm</xsl:attribute>
		<xsl:variable name="v_bghzjlxdm" select="alterVO/fwChengzuList/bghzjlxdm"/>
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

<td align="right"><!-- 出租人证件号码 -->
	<input size="10">
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghczrzjhm</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/fwChengzuList/bghczrzjhm"/></xsl:attribute>
	</input>
</td>

<td align="right"><!-- 承租房屋坐落 -->
	<input>
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghczfwzl</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/fwChengzuList/bghczfwzl"/></xsl:attribute>
	</input>
	
</td>
<td align="right"><!-- 坐落 -->
								
	<select>
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghzlqxdm</xsl:attribute>
		<xsl:variable name="v_bghzlqxdm" select="alterVO/fwChengzuList/bghzlqxdm"/>
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
<td align="right"><!-- 年租金 -->
	<input>
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghnzj</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/fwChengzuList/bghnzj"/></xsl:attribute>
	</input>
</td>
<td align="right"><!-- 备注 -->
	<input>
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghbz</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/fwChengzuList/bghbz"/></xsl:attribute>
	</input>
</td>
												<!-- alter_data_chengzu" -->
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
												<!-- alter_update_head_chengzu -->
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqczrmc</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/fwChengzuList/bgqczrmc"/></xsl:attribute>
												</input>
											</td>
											<td><!-- 证件类型代码 -->
												<select  readOnly="true" disabled="true">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqzjlxdm</xsl:attribute>
													<xsl:variable name="v_bgqzjlxdm" select="alterVO/fwChengzuList/bgqzjlxdm"/>
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
													<xsl:attribute name="value"><xsl:value-of select="alterVO/fwChengzuList/bgqczrzjhm"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqczfwzl</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/fwChengzuList/bgqczfwzl"/></xsl:attribute>
												</input>
											</td>
											<td><!-- 坐落区县 -->
												<select  readOnly="true" disabled="true">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqzlqxdm</xsl:attribute>
													<xsl:variable name="v_bgqzlqxdm" select="alterVO/fwChengzuList/bgqzlqxdm"/>
														<xsl:for-each select="../regionList">
														<option>
															<xsl:attribute name="value"><xsl:value-of select="value"/></xsl:attribute>
															<xsl:if test="value = ($v_bgqzlqxdm)">
																<xsl:attribute name="selected">selected</xsl:attribute>
															</xsl:if>
															<xsl:value-of select="label"/>
														</option>
														</xsl:for-each>
												</select>
											</td>
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqnzj</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/fwChengzuList/bgqnzj"/></xsl:attribute>
												</input>
											</td>
											<td><!-- 备注 -->
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqbz</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/fwChengzuList/bgqbz"/></xsl:attribute>
												</input>
											</td>
												<!-- alter_update_head_chengzu -->
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
												<!-- alter_update_head_chengzu -->
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqczrmc</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/fwChengzuList/bgqczrmc"/></xsl:attribute>
												</input>
											</td>
											<td><!-- 证件类型代码 -->
												<select  readOnly="true" disabled="true">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqzjlxdm</xsl:attribute>
													<xsl:variable name="v_bgqzjlxdm" select="alterVO/fwChengzuList/bgqzjlxdm"/>
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
													<xsl:attribute name="value"><xsl:value-of select="alterVO/fwChengzuList/bgqczrzjhm"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqczfwzl</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/fwChengzuList/bgqczfwzl"/></xsl:attribute>
												</input>
											</td>
											<td>
												<select  readOnly="true" disabled="true">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqzlqxdm</xsl:attribute>
													<xsl:variable name="v_bgqzlqxdm" select="alterVO/fwChengzuList/bgqzlqxdm"/>
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
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqnzj</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/fwChengzuList/bgqnzj"/></xsl:attribute>
												</input>
											</td>
											<td><!-- 备注 -->
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqbz</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/fwChengzuList/bgqbz"/></xsl:attribute>
												</input>
											</td>
												<!-- alter_update_head_chengzu -->
										</tr>
										<tr style="display: block">
											<xsl:attribute name="id">ba_<xsl:value-of select="($pos)"/></xsl:attribute>
											<td align="right" width="51">后</td>
												<!-- alter_data_chengzu" -->
<td align="right"><!-- 出租人名称 -->
	<input>
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghczrmc</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/fwChengzuList/bghczrmc"/></xsl:attribute>
	</input>
</td>


<td align="right"><!-- 证件类型代码 -->
	<select>
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghzjlxdm</xsl:attribute>
		<xsl:variable name="v_bghzjlxdm" select="alterVO/fwChengzuList/bghzjlxdm"/>
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

<td align="right"><!-- 出租人证件号码 -->
	<input size="10">
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghczrzjhm</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/fwChengzuList/bghczrzjhm"/></xsl:attribute>
	</input>
</td>

<td align="right"><!-- 承租房屋坐落 -->
	<input size="10">
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghczfwzl</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/fwChengzuList/bghczfwzl"/></xsl:attribute>
	</input>
	
</td>
<td align="right"><!-- 坐落区县 -->
	<select>
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghzlqxdm</xsl:attribute>
		<xsl:variable name="v_bghzlqxdm" select="alterVO/fwChengzuList/bghzlqxdm"/>
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
<td align="right"><!-- 年租金 -->
	<input>
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghnzj</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/fwChengzuList/bghnzj"/></xsl:attribute>
	</input>
</td>
<td align="right"><!-- 年租金 -->
	<input>
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghbz</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/fwChengzuList/bghbz"/></xsl:attribute>
	</input>
</td>
												<!-- alter_data_chengzu" -->
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
											<!-- alter_add_head_chengzu -->
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.czrmc</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/fwChengzuList/czrmc"/></xsl:attribute>
												</input>
											</td>
											<td><!-- 证件类型代码 -->
												<select readOnly="true" disabled="true" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.zjlxdm</xsl:attribute>
													<xsl:variable name="v_zjlxdm" select="regVO/fwChengzuList/zjlxdm"/>
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
													<xsl:attribute name="value"><xsl:value-of select="regVO/fwChengzuList/czrzjhm"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input size="10" readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.czfwzl</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/fwChengzuList/czfwzl"/></xsl:attribute>
												</input>
											</td>

											<td>												
												<select readOnly="true" disabled="true" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.zlqxdm</xsl:attribute>
													<xsl:variable name="v_zlqxdm" select="regVO/fwChengzuList/zlqxdm"/>
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
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.nzj</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/fwChengzuList/nzj"/></xsl:attribute>
												</input>
											</td>
											<td><!-- 备注 -->
												<input size="10" readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.bz</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/fwChengzuList/bz"/></xsl:attribute>
												</input>
											</td>
											<!-- alter_add_head_chengzu -->
										</tr>


										<tr style="display: none;">
											<xsl:attribute name="id">ba_<xsl:value-of select="($pos)"/></xsl:attribute>

											<td align="right" width="51">后</td>
											<td align="right">
												<input>
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghczrmc</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/fwChengzuList/czrmc"/></xsl:attribute>
												</input>
											</td>
											<td align="right"><!-- 证件类型代码 -->
												<select>
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghzjlxdm</xsl:attribute>
													<xsl:variable name="v_zjlxdm2" select="regVO/fwChengzuList/zjlxdm"/>
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
													<xsl:attribute name="value"><xsl:value-of select="regVO/fwChengzuList/czrzjhm"/></xsl:attribute>
												</input>
											</td>
											<td align="right">
												<input>
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghczfwzl</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/fwChengzuList/czfwzl"/></xsl:attribute>
												</input>
											</td>
											<td align="right">
												<select>
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghzlqxdm</xsl:attribute>
													<xsl:variable name="v_bghzlqxdm2" select="alterVO/fwChengzuList/bghzlqxdm"/>
														<xsl:for-each select="../regionList">
														<option>
															<xsl:attribute name="value"><xsl:value-of select="value"/></xsl:attribute>
															<xsl:if test="value = ($v_bghzlqxdm2)">
																<xsl:attribute name="selected">selected</xsl:attribute>
															</xsl:if>
															<xsl:value-of select="label"/>
														</option>
														</xsl:for-each>
												</select>
											</td>											
											<td align="right">
												<input>
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghnzj</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/fwChengzuList/nzj"/></xsl:attribute>
												</input>
											</td>										
											<td align="right"> <!-- 备注 -->
												<input>
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghbz</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/fwChengzuList/bz"/></xsl:attribute>
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
													<xsl:attribute name="value"><xsl:value-of select="alterVO/fwChengzuList/id"/></xsl:attribute>
												</input>
												<input type="hidden">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.id</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/fwChengzuList/id"/></xsl:attribute>
												</input>
												<input type="hidden">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.jsjdm</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/fwChengzuList/jsjdm"/></xsl:attribute>
												</input>
												<input type="hidden">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.djbh</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/fwChengzuList/djbh"/></xsl:attribute>
												</input>
												<input type="hidden">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.cjr</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/fwChengzuList/cjr"/></xsl:attribute>
												</input>
												<input type="hidden">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.cjrq</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/fwChengzuList/cjrq"/></xsl:attribute>
												</input>
												<input type="hidden">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.jsjdm</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/fwChengzuList/jsjdm"/></xsl:attribute>
												</input>
												<input type="hidden">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bglx</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/fwChengzuList/bglx"/></xsl:attribute>
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
<scenarios ><scenario default="yes" name="Scenario1" userelativepaths="yes" externalpreview="no" url="file:///c:/Documents and Settings/Administrator/桌面/测试xslt/test_fwchengzu.xml" htmlbaseurl="" outputurl="" processortype="internal" useresolver="yes" profilemode="0" profiledepth="" profilelength="" urlprofilexml="" commandline="" additionalpath="" additionalclasspath="" postprocessortype="none" postprocesscommandline="" postprocessadditionalpath="" postprocessgeneratedext="" validateoutput="no" validator="internal" customvalidator=""/></scenarios><MapperMetaTag><MapperInfo srcSchemaPathIsRelative="yes" srcSchemaInterpretAsXML="no" destSchemaPath="" destSchemaRoot="" destSchemaPathIsRelative="yes" destSchemaInterpretAsXML="no"/><MapperBlockPosition></MapperBlockPosition><TemplateContext></TemplateContext><MapperFilter side="source"></MapperFilter></MapperMetaTag>
</metaInformation>
-->