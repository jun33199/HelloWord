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
										<td>是否代缴</td>
										<td>是否缴纳外商投资企业土地使用费</td>
										<td>备注</td>
									</tr>
								</thead>
								<tbody>
									
									<xsl:for-each select="tdZiyongList">
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
												<!-- alter_data_td_ziyong -->
<td align="right"><!-- 变更后土地坐落 -->
	<input>
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghtdzl</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/tdZiyongList/bghtdzl"/></xsl:attribute>
	</input>
</td>

<td align="right"><!-- 变更后土地证书号 -->
	<input>
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghtdzsh</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/tdZiyongList/bghtdzsh"/></xsl:attribute>
	</input>
</td>

<td align="right"><!-- 变更后土地面积 -->
	<input size="10">
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghtdmj</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/tdZiyongList/bghtdmj"/></xsl:attribute>
	</input>
</td>

<td align="right" >
	<!-- 变更后其中免税面积 -->
	<input size="10">
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghqzmsmj</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/tdZiyongList/bghqzmsmj"/></xsl:attribute>
	</input>
	<br/><!-- 0-法定,1-搬迁,2-荒山,3-其他 -->
	原因：
		<select onchange="selectZhengCe(this, 3)">
			<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.jmsyzbgyy</xsl:attribute>
			<xsl:attribute name="id">bb_<xsl:value-of select="($pos)"/></xsl:attribute>
			<xsl:variable name="v_jmsyzbgyy" select="alterVO/tdZiyongList/jmsyzbgyy"/>
			<xsl:for-each select="../jianMianMJList">
				<option>
					<xsl:attribute name="value"><xsl:value-of select="value"/></xsl:attribute>
					<xsl:if test="value = ($v_jmsyzbgyy)">
						<xsl:attribute name="selected">selected</xsl:attribute>
					</xsl:if>
					<xsl:value-of select="label"/>
				</option>
			</xsl:for-each>
		</select>
		<br/>

		<div>
			<xsl:attribute name="id">bZCDiv_<xsl:value-of select="($pos)"/></xsl:attribute>
			<xsl:if test="alterVO/tdZiyongList/jmsyzbgyy = 3">
				<xsl:attribute name="style">display: block</xsl:attribute>
			</xsl:if>
			<xsl:if test="alterVO/tdZiyongList/jmsyzbgyy != 3">
				<xsl:attribute name="style">display: none</xsl:attribute>
			</xsl:if>
		文件号：
		<select>
			<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.jmzcdm</xsl:attribute>
			<xsl:variable name="v_jmzcdm" select="alterVO/tdZiyongList/jmzcdm"/>
			<option value="">请选择</option>
			<xsl:for-each select="../zhengceList">
				<option>
					<xsl:attribute name="value"><xsl:value-of select="value"/></xsl:attribute>
					<xsl:if test="value = ($v_jmzcdm)">
						<xsl:attribute name="selected">selected</xsl:attribute>
					</xsl:if>
					<xsl:value-of select="label"/>
				</option>
			</xsl:for-each>
		</select>
		<br/>
		<span class="query_zhengce">
			<xsl:attribute name="onclick">queryZhengce(<xsl:value-of select="($pos)"/>)</xsl:attribute>
			查看减免税内容
		</span>
		</div>
	
	<br/>
	减免税期限起：
	<input size="9">
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.jmsqxq</xsl:attribute>
		<xsl:attribute name="value">
			<xsl:call-template name="toDate"><xsl:with-param name="ts" select="alterVO/tdZiyongList/jmsqxq"/></xsl:call-template>
		</xsl:attribute>

	</input>
	<br/>
	减免税期限止：
	<input size="9">
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.jmsqxz</xsl:attribute>
		<xsl:attribute name="value">
			<xsl:call-template name="toDate"><xsl:with-param name="ts" select="alterVO/tdZiyongList/jmsqxz"/></xsl:call-template>
		</xsl:attribute>

	</input>
</td>
<td align="right">
	<!-- 变更后其中应税面积 -->
	<input size="10">
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghqzysmj</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/tdZiyongList/bghqzysmj"/></xsl:attribute>
	</input>
	<br/>
	原因：
	<select>
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.ysmjbgyy</xsl:attribute>
		<xsl:variable name="v_ysmjbgyy" select="alterVO/tdZiyongList/ysmjbgyy"/>
		<xsl:for-each select="../yingShuiMJList">
			<option>
				<xsl:attribute name="value"><xsl:value-of select="value"/></xsl:attribute>
				<xsl:if test="value = ($v_ysmjbgyy)">
					<xsl:attribute name="selected">selected</xsl:attribute>
				</xsl:if>
				<xsl:value-of select="label"/>
			</option>
		</xsl:for-each>
	</select>
</td>
<td align="right">
	<!-- 变更后每平方米税额 -->
	<select>
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghmpfmse</xsl:attribute>
		<xsl:variable name="v_bghmpfmse1" select="alterVO/tdZiyongList/bghmpfmse"/>
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
<td align="right">
	<!-- 变更后年应纳税额 -->
	<input size="10">
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghnynse</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/tdZiyongList/bghnynse"/></xsl:attribute>
	</input>
</td>
<td align="right">
	<select><!-- 是否代缴 -->
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghsfdj</xsl:attribute>
		<xsl:variable name="v_bghsfdj" select="alterVO/tdZiyongList/bghsfdj"/>
		<xsl:for-each select="../daiJiaoList">
			<option>
				<xsl:attribute name="value"><xsl:value-of select="value"/></xsl:attribute>
				<xsl:if test="value = ($v_bghsfdj)">
					<xsl:attribute name="selected">selected</xsl:attribute>
				</xsl:if>
				<xsl:value-of select="label"/>
			</option>
		</xsl:for-each>
	</select>
</td>
<td align="right">
	<select><!-- 是否缴纳外商投资企业土地使用费 -->
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghsfjnws</xsl:attribute>
		<xsl:variable name="v_bghsfjnws" select="alterVO/tdZiyongList/bghsfjnws"/>
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
<td align="right">
	<!-- 备注 -->
	<input>
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghbz</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/tdZiyongList/bghbz"/></xsl:attribute>
	</input>
</td>												
												<!-- alter_data_td_ziyong -->
												
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
											<!-- alter_update_head_td_ziyong -->
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqtdzl</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/tdZiyongList/bgqtdzl"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqtdzsh</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/tdZiyongList/bgqtdzsh"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqtdmj</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/tdZiyongList/bgqtdmj"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqqzmsmj</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/tdZiyongList/bgqqzmsmj"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqqzysmj</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/tdZiyongList/bgqqzysmj"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqmpfmse</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/tdZiyongList/bgqmpfmse"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqnynse</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/tdZiyongList/bgqnynse"/></xsl:attribute>
												</input>
											</td>
											<td>
												<select readonly="true" disabled="true"><!-- 是否代缴 -->
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqsfdj</xsl:attribute>
													<xsl:variable name="v_bgqsfdj1" select="alterVO/tdZiyongList/bgqsfdj"/>
														<xsl:for-each select="../daiJiaoList">
														<option>
															<xsl:attribute name="value"><xsl:value-of select="value"/></xsl:attribute>
															<xsl:if test="value = ($v_bgqsfdj1)">
																<xsl:attribute name="selected">selected</xsl:attribute>
															</xsl:if>
															<xsl:value-of select="label"/>
														</option>
														</xsl:for-each>
												</select>																								
											</td>
											<td>
												<select readonly="true" disabled="true"><!-- 是否缴纳外商投资企业土地使用费 -->
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqsfjnws</xsl:attribute>
													<xsl:variable name="v_bgqsfjnws1" select="alterVO/tdZiyongList/bgqsfjnws"/>
														<xsl:for-each select="../daiJiaoList">
														<option>
															<xsl:attribute name="value"><xsl:value-of select="value"/></xsl:attribute>
															<xsl:if test="value = ($v_bgqsfjnws1)">
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
													<xsl:attribute name="value"><xsl:value-of select="alterVO/tdZiyongList/bgqbz"/></xsl:attribute>
												</input>
											</td>			
											<!-- alter_update_head_td_ziyong -->
											
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
											
											<!-- alter_update_head_td_ziyong -->
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqtdzl</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/tdZiyongList/bgqtdzl"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqtdzsh</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/tdZiyongList/bgqtdzsh"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqtdmj</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/tdZiyongList/bgqtdmj"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqqzmsmj</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/tdZiyongList/bgqqzmsmj"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqqzysmj</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/tdZiyongList/bgqqzysmj"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqmpfmse</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/tdZiyongList/bgqmpfmse"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqnynse</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/tdZiyongList/bgqnynse"/></xsl:attribute>
												</input>
											</td>
											<td>
												<select readonly="true" disabled="true"><!-- 是否代缴 -->
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqsfdj</xsl:attribute>
													<xsl:variable name="v_bgqsfdj2" select="alterVO/tdZiyongList/bgqsfdj"/>
														<xsl:for-each select="../daiJiaoList">
														<option>
															<xsl:attribute name="value"><xsl:value-of select="value"/></xsl:attribute>
															<xsl:if test="value = ($v_bgqsfdj2)">
																<xsl:attribute name="selected">selected</xsl:attribute>
															</xsl:if>
															<xsl:value-of select="label"/>
														</option>
														</xsl:for-each>
												</select>																								
											</td>
											<td>
												<select readonly="true" disabled="true"><!-- 是否缴纳外商投资企业土地使用费 -->
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqsfjnws</xsl:attribute>
													<xsl:variable name="v_bgqsfjnws2" select="alterVO/tdZiyongList/bgqsfjnws"/>
														<xsl:for-each select="../daiJiaoList">
														<option>
															<xsl:attribute name="value"><xsl:value-of select="value"/></xsl:attribute>
															<xsl:if test="value = ($v_bgqsfjnws2)">
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
													<xsl:attribute name="value"><xsl:value-of select="alterVO/tdZiyongList/bgqbz"/></xsl:attribute>
												</input>
											</td>										
											<!-- alter_update_head_td_ziyong -->
										</tr>
										<tr style="display: block">
											<xsl:attribute name="id">ba_<xsl:value-of select="($pos)"/></xsl:attribute>
											<td align="right" width="51">后</td>
												<!-- alter_data_td_ziyong -->
<td align="right"><!-- 变更后土地坐落 -->
	<input>
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghtdzl</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/tdZiyongList/bghtdzl"/></xsl:attribute>
		<xsl:if test="regVO/tdZiyongList/fhbs = '0'">
				<xsl:attribute name="readOnly">true</xsl:attribute>
				<xsl:attribute name="disabled">true</xsl:attribute>
		</xsl:if>
	</input>
</td>

<td align="right"><!-- 变更后土地证书号 -->
	<input>
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghtdzsh</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/tdZiyongList/bghtdzsh"/></xsl:attribute>
		<xsl:if test="regVO/tdZiyongList/fhbs = '0'">
				<xsl:attribute name="readOnly">true</xsl:attribute>
				<xsl:attribute name="disabled">true</xsl:attribute>
		</xsl:if>
	</input>
</td>

<td align="right"><!-- 变更后土地面积 -->
	<input size="10">
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghtdmj</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/tdZiyongList/bghtdmj"/></xsl:attribute>
		<xsl:if test="regVO/tdZiyongList/fhbs = '0'">
				<xsl:attribute name="readOnly">true</xsl:attribute>
				<xsl:attribute name="disabled">true</xsl:attribute>
		</xsl:if>
	</input>
</td>

<td align="right" >
	<!-- 变更后其中免税面积 -->
	<input size="10">
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghqzmsmj</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/tdZiyongList/bghqzmsmj"/></xsl:attribute>
	</input>
	<br/><!-- 0-法定,1-搬迁,2-荒山,3-其他 -->
	原因：
		<select onchange="selectZhengCe(this, 3)">
			<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.jmsyzbgyy</xsl:attribute>
			<xsl:attribute name="id">bb_<xsl:value-of select="($pos)"/></xsl:attribute>
			<xsl:variable name="v_jmsyzbgyy" select="alterVO/tdZiyongList/jmsyzbgyy"/>
			<xsl:for-each select="../jianMianMJList">
				<option>
					<xsl:attribute name="value"><xsl:value-of select="value"/></xsl:attribute>
					<xsl:if test="value = ($v_jmsyzbgyy)">
						<xsl:attribute name="selected">selected</xsl:attribute>
					</xsl:if>
					<xsl:value-of select="label"/>
				</option>
			</xsl:for-each>
		</select>
		<br/>

		<div>
			<xsl:attribute name="id">bZCDiv_<xsl:value-of select="($pos)"/></xsl:attribute>
			<xsl:if test="alterVO/tdZiyongList/jmsyzbgyy = 3">
				<xsl:attribute name="style">display: block</xsl:attribute>
			</xsl:if>
			<xsl:if test="alterVO/tdZiyongList/jmsyzbgyy != 3">
				<xsl:attribute name="style">display: none</xsl:attribute>
			</xsl:if>
		文件号：
		<select>
			<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.jmzcdm</xsl:attribute>
			<xsl:variable name="v_jmzcdm" select="alterVO/tdZiyongList/jmzcdm"/>
			<option value="">请选择</option>
			<xsl:for-each select="../zhengceList">
				<option>
					<xsl:attribute name="value"><xsl:value-of select="value"/></xsl:attribute>
					<xsl:if test="value = ($v_jmzcdm)">
						<xsl:attribute name="selected">selected</xsl:attribute>
					</xsl:if>
					<xsl:value-of select="label"/>
				</option>
			</xsl:for-each>
		</select>
		<br/>
		<span class="query_zhengce">
			<xsl:attribute name="onclick">queryZhengce(<xsl:value-of select="($pos)"/>)</xsl:attribute>
			查看减免税内容
		</span>
		</div>
	
	<br/>
	减免税期限起：
	<input size="9">
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.jmsqxq</xsl:attribute>
		<xsl:attribute name="value">
			<xsl:call-template name="toDate"><xsl:with-param name="ts" select="alterVO/tdZiyongList/jmsqxq"/></xsl:call-template>
		</xsl:attribute>
	</input>
	<br/>
	减免税期限止：
	<input size="9">
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.jmsqxz</xsl:attribute>
		<xsl:attribute name="value">
			<xsl:call-template name="toDate"><xsl:with-param name="ts" select="alterVO/tdZiyongList/jmsqxz"/></xsl:call-template>
		</xsl:attribute>
	</input>
</td>
<td align="right">
	<!-- 变更后其中应税面积 -->
	<input size="10">
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghqzysmj</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/tdZiyongList/bghqzysmj"/></xsl:attribute>
	</input>
	<br/>
	原因：
	<select >
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.ysmjbgyy</xsl:attribute>
		<xsl:variable name="v_ysmjbgyy" select="alterVO/tdZiyongList/ysmjbgyy"/>
		<xsl:for-each select="../yingShuiMJList">
			<option>
				<xsl:attribute name="value"><xsl:value-of select="value"/></xsl:attribute>
				<xsl:if test="value = ($v_ysmjbgyy)">
					<xsl:attribute name="selected">selected</xsl:attribute>
				</xsl:if>
				<xsl:value-of select="label"/>
			</option>
		</xsl:for-each>
	</select>
</td>
<td align="right">
	<!-- 变更后每平方米税额 -->
	<select>
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghmpfmse</xsl:attribute>
		<xsl:variable name="v_bghmpfmse2" select="alterVO/tdZiyongList/bghmpfmse"/>
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
<td align="right">
	<!-- 变更后年应纳税额 -->
	<input size="10">
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghnynse</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/tdZiyongList/bghnynse"/></xsl:attribute>
	</input>
</td>
<td align="right">
	<select><!-- 是否代缴 -->
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghsfdj</xsl:attribute>
		<xsl:if test="regVO/tdZiyongList/fhbs = '0'">
				<xsl:attribute name="readOnly">true</xsl:attribute>
				<xsl:attribute name="disabled">true</xsl:attribute>
		</xsl:if>
		<xsl:variable name="v_bghsfdj3" select="alterVO/tdZiyongList/bghsfdj"/>
		<xsl:for-each select="../daiJiaoList">
			<option>
				<xsl:attribute name="value"><xsl:value-of select="value"/></xsl:attribute>
				<xsl:if test="value = ($v_bghsfdj3)">
					<xsl:attribute name="selected">selected</xsl:attribute>
				</xsl:if>
				<xsl:value-of select="label"/>
			</option>
		</xsl:for-each>
	</select>
</td>
<td align="right">
	<select><!-- 是否缴纳外商投资企业土地使用费 -->
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghsfjnws</xsl:attribute>
		<xsl:if test="regVO/tdZiyongList/fhbs = '0'">
				<xsl:attribute name="readOnly">true</xsl:attribute>
				<xsl:attribute name="disabled">true</xsl:attribute>
		</xsl:if>
		<xsl:variable name="v_bghsfjnws3" select="alterVO/tdZiyongList/bghsfjnws"/>
		<xsl:for-each select="../daiJiaoList">
			<option>
				<xsl:attribute name="value"><xsl:value-of select="value"/></xsl:attribute>
				<xsl:if test="value = ($v_bghsfjnws3)">
					<xsl:attribute name="selected">selected</xsl:attribute>
				</xsl:if>
				<xsl:value-of select="label"/>
			</option>
		</xsl:for-each>
	</select>
</td>
<td align="right">
	<!-- 备注 -->
	<input>
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghbz</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/tdZiyongList/bghbz"/></xsl:attribute>
		<xsl:if test="regVO/tdZiyongList/fhbs = '0'">
				<xsl:attribute name="readOnly">true</xsl:attribute>
				<xsl:attribute name="disabled">true</xsl:attribute>
		</xsl:if>
	</input>
</td>												
												<!-- alter_data_td_ziyong -->
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
											
											<!-- alter_add_head_td_ziyong -->
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.tdzl</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/tdZiyongList/tdzl"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.tdzsh</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/tdZiyongList/tdzsh"/></xsl:attribute>
												</input>
											</td>

											<td>
												<input size="10" readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.tdmj</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/tdZiyongList/tdmj"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input size="10" readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.qzmsmj</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/tdZiyongList/qzmsmj"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input size="10" readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.qzysmj</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/tdZiyongList/qzysmj"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input size="10" readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.mpfmse</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/tdZiyongList/mpfmse"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input size="10" readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.nynse</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/tdZiyongList/nynse"/></xsl:attribute>
												</input>
											</td>
											<td>
												<select readonly="true" disabled="true"><!-- 是否代缴 -->
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.sfdj</xsl:attribute>
														<xsl:variable name="v_sfdj" select="regVO/tdZiyongList/sfdj"/>
														<xsl:for-each select="../daiJiaoList">
														<option>
															<xsl:attribute name="value"><xsl:value-of select="value"/></xsl:attribute>
															<xsl:if test="value = ($v_sfdj)">
																<xsl:attribute name="selected">selected</xsl:attribute>
															</xsl:if>
															<xsl:value-of select="label"/>
														</option>
														</xsl:for-each>
												</select>												
											</td>
											<td>
												<select readonly="true" disabled="true"><!-- 是否缴纳外商投资企业土地使用费 -->
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.sfjnws</xsl:attribute>
														<xsl:variable name="v_sfjnws" select="regVO/tdZiyongList/sfjnws"/>
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
													<xsl:attribute name="value"><xsl:value-of select="regVO/tdZiyongList/bz"/></xsl:attribute>
												</input>
											</td>											
											<!-- alter_add_head_td_ziyong -->
										</tr>


										<tr style="display: none;">
											<xsl:attribute name="id">ba_<xsl:value-of select="($pos)"/></xsl:attribute>

											<td align="right" width="51">后</td>
											<td align="right">
												<input>
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghtdzl</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/tdZiyongList/tdzl"/></xsl:attribute>
												</input>
											</td>
											<td align="right">
												<input>
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghtdzsh</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/tdZiyongList/tdzsh"/></xsl:attribute>
												</input>
											</td>
											<td align="right">
												<input>
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghtdmj</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/tdZiyongList/tdmj"/></xsl:attribute>
												</input>
											</td>

											<!-- alter_add_inc_td_ziyong -->
											<td align="right">
												<!-- 变更后其中免税面积 -->
												<input>
													<xsl:attribute name="id">bb_<xsl:value-of select="($pos)"/></xsl:attribute>
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghqzmsmj</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/tdZiyongList/bghqzmsmj"/></xsl:attribute>
												</input>
												<br/><!-- 0-法定,1-搬迁,2-荒山,3-其他 -->
												原因：
												<select onchange="selectZhengCe(this, 3)">
		
													<xsl:attribute name="id">bb_<xsl:value-of select="($pos)"/></xsl:attribute>
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.jmsyzbgyy</xsl:attribute>
		
													<option value="">---</option>
													<option value="0">法定</option>
													<option value="1">搬迁</option>
													<option value="2">荒山</option>
													<option value="3">其他</option>
		
												</select>
												<br/>
	
												<div style="display: none">
													<xsl:attribute name="id">bZCDiv_<xsl:value-of select="($pos)"/></xsl:attribute>
		
													文件号：
													<select>
														<xsl:attribute name="id">bZC_<xsl:value-of select="($pos)"/></xsl:attribute>
														<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.jmzcdm</xsl:attribute>
														<option value="">请选择</option>
														<xsl:for-each select="taxdoc/zhengceList">
															<option>
																<xsl:attribute name="value"><xsl:value-of select="../zhengceList/value"/></xsl:attribute>
																<xsl:value-of select="../zhengceList/label"/>
															</option>
														</xsl:for-each>
													</select>
													<br/>
													<span class="query_zhengce">
														<xsl:attribute name="onclick">queryZhengce(<xsl:value-of select="($pos)"/>)</xsl:attribute>
														查看减免税内容
													</span>
												</div>
												<br/>
												减免税期限起：
												<input>
													<xsl:attribute name="id">bb_<xsl:value-of select="($pos)"/></xsl:attribute>
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.jmsqxq</xsl:attribute>
												</input>
												<br/>
												减免税期限止：
												<input>
													<xsl:attribute name="id">bb_<xsl:value-of select="($pos)"/></xsl:attribute>
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.jmsqxz</xsl:attribute>
												</input>
											</td>											
											<!-- alter_add_inc_td_ziyong -->

											<td align="right">
												<input>
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghqzysmj</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/tdZiyongList/qzysmj"/></xsl:attribute>
												</input>
												<br/>
												原因：
												<select>
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.ysmjbgyy</xsl:attribute>
													<option value="">---</option>
													<option value="0">新增</option>
													<option value="1">减少</option>
													<option value="2">原值变更</option>
												</select>
											</td>
											<td align="right">
												<select>
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghmpfmse</xsl:attribute>
													<xsl:variable name="v_mpfmse2" select="regVO/tdZiyongList/mpfmse"/>
														<xsl:for-each select="../slList">
														<option>
															<xsl:attribute name="value"><xsl:value-of select="value"/></xsl:attribute>
															<xsl:if test="value = ($v_mpfmse2)">
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
													<xsl:attribute name="value"><xsl:value-of select="regVO/tdZiyongList/nynse"/></xsl:attribute>
												</input>
											</td>
											<td align="right">
												<select><!-- 是否代缴 -->
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghsfdj</xsl:attribute>
														<xsl:variable name="v_sfdj_yfh" select="regVO/tdZiyongList/sfdj"/>
														<xsl:for-each select="../daiJiaoList">
														<option>
															<xsl:attribute name="value"><xsl:value-of select="value"/></xsl:attribute>
															<xsl:if test="value = ($v_sfdj_yfh)">
																<xsl:attribute name="selected">selected</xsl:attribute>
															</xsl:if>
															<xsl:value-of select="label"/>
														</option>
														</xsl:for-each>
												</select>
											</td>
											<td align="right">
												<select><!-- 是否缴纳外商投资企业土地使用费 -->
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghsfjnws</xsl:attribute>
														<xsl:variable name="v_sfjnws_yfh" select="regVO/tdZiyongList/sfjnws"/>
														<xsl:for-each select="../daiJiaoList">
														<option>
															<xsl:attribute name="value"><xsl:value-of select="value"/></xsl:attribute>
															<xsl:if test="value = ($v_sfjnws_yfh)">
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
													<xsl:attribute name="value"><xsl:value-of select="regVO/tdZiyongList/bz"/></xsl:attribute>
												</input>
											</td>
										</tr>
										</xsl:if>

																			
									<!-- 如果是原始未复核登记 -->
									<xsl:if test="opFlag = 'old_uncheck'">
										<tr name="old_uncheck">
											<xsl:attribute name="id">aa_<xsl:value-of select="($pos)"/></xsl:attribute>
											<xsl:call-template name="alter_inc"/>
											<td rowspan="2" width="36"><xsl:value-of select="($pos + 1)"/></td>
											<td rowspan="2" width="38">
												<!--只能进行修改，且只允许修改与减免有关的字段-->
												<input type="checkbox"
                                                       onclick="show_m1(this)"
													   value="1"
                                                >
													<xsl:attribute name="id">ab_<xsl:value-of select="($pos)"/></xsl:attribute>
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].updateFlag</xsl:attribute>
												</input>
											</td>
											<td rowspan="2" width="41">
												<!-- 删除标识，恒为0 -->
                                                <input type="hidden" value="0">
													<xsl:attribute name="id">ac_<xsl:value-of select="($pos)"/></xsl:attribute>
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].deleteFlag</xsl:attribute>
												</input>
                       　</td>
											 <td rowspan="2" nowrap="nowrap">登记数据</td>
											 <td width="51" >
											 	<xsl:attribute name="id">ad_<xsl:value-of select="($pos)"/></xsl:attribute>
											 	前
											</td>
											
											<!-- alter_add_head_td_ziyong -->
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.tdzl</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/tdZiyongList/tdzl"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.tdzsh</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/tdZiyongList/tdzsh"/></xsl:attribute>
												</input>
											</td>

											<td>
												<input size="10" readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.tdmj</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/tdZiyongList/tdmj"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input size="10" readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.qzmsmj</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/tdZiyongList/qzmsmj"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input size="10" readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.qzysmj</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/tdZiyongList/qzysmj"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input size="10" readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.mpfmse</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/tdZiyongList/mpfmse"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input size="10" readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.nynse</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/tdZiyongList/nynse"/></xsl:attribute>
												</input>
											</td>
											<td>
												<select readonly="true" disabled="true"><!-- 是否代缴 -->
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.sfdj</xsl:attribute>
														<xsl:variable name="v_sfdj" select="regVO/tdZiyongList/sfdj"/>
														<xsl:for-each select="../daiJiaoList">
														<option>
															<xsl:attribute name="value"><xsl:value-of select="value"/></xsl:attribute>
															<xsl:if test="value = ($v_sfdj)">
																<xsl:attribute name="selected">selected</xsl:attribute>
															</xsl:if>
															<xsl:value-of select="label"/>
														</option>
														</xsl:for-each>
												</select>												
											</td>
											<td>
												<select readonly="true" disabled="true"><!-- 是否缴纳外商投资企业土地使用费 -->
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.sfjnws</xsl:attribute>
														<xsl:variable name="v_sfjnws" select="regVO/tdZiyongList/sfjnws"/>
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
													<xsl:attribute name="value"><xsl:value-of select="regVO/tdZiyongList/bz"/></xsl:attribute>
												</input>
											</td>											
											<!-- alter_add_head_td_ziyong -->
										</tr>

										<tr style="display: none;">
											<xsl:attribute name="id">ba_<xsl:value-of select="($pos)"/></xsl:attribute>

											<td align="right" width="51">后</td>
											<td align="right">
												<input readonly="true" disabled="true">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghtdzl</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/tdZiyongList/tdzl"/></xsl:attribute>
												</input>
											</td>
											<td align="right">
												<input readonly="true" disabled="true">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghtdzsh</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/tdZiyongList/tdzsh"/></xsl:attribute>
												</input>
											</td>
											<td align="right">
												<input readonly="true" disabled="true">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghtdmj</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/tdZiyongList/tdmj"/></xsl:attribute>
												</input>
											</td>

											<!-- alter_add_inc_td_ziyong -->
											<td align="right">
												<!-- 变更后其中免税面积 -->
												<input>
													<xsl:attribute name="id">bb_<xsl:value-of select="($pos)"/></xsl:attribute>
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghqzmsmj</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/tdZiyongList/bghqzmsmj"/></xsl:attribute>
												</input>
												<br/><!-- 0-法定,1-搬迁,2-荒山,3-其他 -->
												原因：
												<select onchange="selectZhengCe(this, 3)">
		
													<xsl:attribute name="id">bb_<xsl:value-of select="($pos)"/></xsl:attribute>
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.jmsyzbgyy</xsl:attribute>
		
													<option value="">---</option>
													<option value="0">法定</option>
													<option value="1">搬迁</option>
													<option value="2">荒山</option>
													<option value="3">其他</option>
		
												</select>
												<br/>
	
												<div style="display: none">
													<xsl:attribute name="id">bZCDiv_<xsl:value-of select="($pos)"/></xsl:attribute>
		
													文件号：
													<select>
														<xsl:attribute name="id">bZC_<xsl:value-of select="($pos)"/></xsl:attribute>
														<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.jmzcdm</xsl:attribute>
														<option value="">请选择</option>
														<xsl:for-each select="taxdoc/zhengceList">
															<option>
																<xsl:attribute name="value"><xsl:value-of select="../zhengceList/value"/></xsl:attribute>
																<xsl:value-of select="../zhengceList/label"/>
															</option>
														</xsl:for-each>
													</select>
													<br/>
													<span class="query_zhengce">
														<xsl:attribute name="onclick">queryZhengce(<xsl:value-of select="($pos)"/>)</xsl:attribute>
														查看减免税内容
													</span>
												</div>
												<br/>
												减免税期限起：
												<input>
													<xsl:attribute name="id">bb_<xsl:value-of select="($pos)"/></xsl:attribute>
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.jmsqxq</xsl:attribute>
												</input>
												<br/>
												减免税期限止：
												<input>
													<xsl:attribute name="id">bb_<xsl:value-of select="($pos)"/></xsl:attribute>
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.jmsqxz</xsl:attribute>
												</input>
											</td>											
											<!-- alter_add_inc_td_ziyong -->

											<td align="right">
												<input readonly="true" disabled="true">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghqzysmj</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/tdZiyongList/qzysmj"/></xsl:attribute>
												</input>
												<br/>
												原因：
												<select readonly="true" disabled="true">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.ysmjbgyy</xsl:attribute>
													<option value="">---</option>
													<option value="0">新增</option>
													<option value="1">减少</option>
													<option value="2">原值变更</option>
												</select>
											</td>
											<td align="right">
												<select readonly="true" disabled="true">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghmpfmse</xsl:attribute>
													<xsl:variable name="v_mpfmse4" select="regVO/tdZiyongList/mpfmse"/>
														<xsl:for-each select="../slList">
														<option>
															<xsl:attribute name="value"><xsl:value-of select="value"/></xsl:attribute>
															<xsl:if test="value = ($v_mpfmse4)">
																<xsl:attribute name="selected">selected</xsl:attribute>
															</xsl:if>
															<xsl:value-of select="label"/>
														</option>
														</xsl:for-each>
												</select>
											</td>
											<td align="right">
												<input readonly="true" disabled="true">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghnynse</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/tdZiyongList/nynse"/></xsl:attribute>
												</input>
											</td>
											<td align="right">
												<select readonly="true" disabled="true"><!-- 是否代缴 -->
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghsfdj</xsl:attribute>
														<xsl:variable name="v_bghsfdj" select="alterVO/tdZiyongList/bghsfdj"/>
														<xsl:for-each select="../daiJiaoList">
														<option>
															<xsl:attribute name="value"><xsl:value-of select="value"/></xsl:attribute>
															<xsl:if test="value = ($v_bghsfdj)">
																<xsl:attribute name="selected">selected</xsl:attribute>
															</xsl:if>
															<xsl:value-of select="label"/>
														</option>
														</xsl:for-each>
												</select>
											</td>
											<td align="right">
												<select readonly="true" disabled="true"><!-- 是否缴纳外商投资企业土地使用费 -->
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghsfjnws</xsl:attribute>
														<xsl:variable name="v_bghsfjnws" select="alterVO/tdZiyongList/bghsfjnws"/>
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
											<td align="right">
												<input readonly="true" disabled="true">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghbz</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/tdZiyongList/bz"/></xsl:attribute>
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
													<xsl:attribute name="value"><xsl:value-of select="alterVO/tdZiyongList/id"/></xsl:attribute>
												</input>
												<input type="hidden">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.id</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/tdZiyongList/id"/></xsl:attribute>
												</input>
												<input type="hidden">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.jsjdm</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/tdZiyongList/jsjdm"/></xsl:attribute>
												</input>
												<input type="hidden">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.djbh</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/tdZiyongList/djbh"/></xsl:attribute>
												</input>
												<input type="hidden">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.cjr</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/tdZiyongList/cjr"/></xsl:attribute>
												</input>
												<input type="hidden">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.cjrq</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/tdZiyongList/cjrq"/></xsl:attribute>
												</input>
												<input type="hidden">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.jsjdm</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/tdZiyongList/jsjdm"/></xsl:attribute>
												</input>
												<input type="hidden">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bglx</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/tdZiyongList/bglx"/></xsl:attribute>
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
<scenarios ><scenario default="yes" name="Scenario1" userelativepaths="yes" externalpreview="no" url="file:///c:/Documents and Settings/Administrator/桌面/测试xslt/test_tdziyong.xml" htmlbaseurl="" outputurl="" processortype="internal" useresolver="yes" profilemode="0" profiledepth="" profilelength="" urlprofilexml="" commandline="" additionalpath="" additionalclasspath="" postprocessortype="none" postprocesscommandline="" postprocessadditionalpath="" postprocessgeneratedext="" validateoutput="no" validator="internal" customvalidator=""/></scenarios><MapperMetaTag><MapperInfo srcSchemaPathIsRelative="yes" srcSchemaInterpretAsXML="no" destSchemaPath="" destSchemaRoot="" destSchemaPathIsRelative="yes" destSchemaInterpretAsXML="no"/><MapperBlockPosition></MapperBlockPosition><TemplateContext></TemplateContext><MapperFilter side="source"></MapperFilter></MapperMetaTag>
</metaInformation>
-->