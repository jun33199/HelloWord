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
										<td>房屋坐落</td>
										<td>房产原值</td>
										<td>税务机关估值</td>
										<td>其中免税原值</td>
										<td>其中应税原值</td>
										<td>年应纳税额</td>
										<td>是否代缴</td>
										<td>备注</td>
									</tr>
								</thead>
								<tbody>
									
									<xsl:for-each select="fwZiyongList">
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
												<!-- alter_data -->
<td align="right"><!-- 产权证书号 -->
	<input>
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghcqzsh</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/fwZiyongList/bghcqzsh"/></xsl:attribute>
	</input>
</td>


<td align="right"><!-- 房屋坐落 -->
	<input>
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghfwzl</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/fwZiyongList/bghfwzl"/></xsl:attribute>
	</input>
</td>

<td align="right"><!-- 房产原值 -->
	<input size="10">
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghfcyz</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/fwZiyongList/bghfcyz"/></xsl:attribute>
	</input>
</td>

<td align="right"><!-- 税务机关估值 -->
	<input size="10">
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghswjggz</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/fwZiyongList/bghswjggz"/></xsl:attribute>
	</input>
	
</td>
<td align="right">
	<!-- 其中免税原值 -->
	<input size="10">
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghqzmsyz</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/fwZiyongList/bghqzmsyz"/></xsl:attribute>
	</input>
	<br/><!-- 0-法定,1-大修,2-其他 -->
	原因：
		<select onchange="selectZhengCe(this)">
			<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.jmsyzbgyy</xsl:attribute>
			<xsl:attribute name="id">bb_<xsl:value-of select="($pos)"/></xsl:attribute>
			<xsl:variable name="v_jmsyzbgyy" select="alterVO/fwZiyongList/jmsyzbgyy"/>
			<xsl:for-each select="../jianMianList">
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
			<xsl:if test="alterVO/fwZiyongList/jmsyzbgyy = 2">
				<xsl:attribute name="style">display: block</xsl:attribute>
			</xsl:if>
			<xsl:if test="alterVO/fwZiyongList/jmsyzbgyy != 2">
				<xsl:attribute name="style">display: none</xsl:attribute>
			</xsl:if>
		
		文件号：
		<select>
			<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.jmzcdm</xsl:attribute>
			<xsl:variable name="v_jmzcdm" select="alterVO/fwZiyongList/jmzcdm"/>
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
			<xsl:call-template name="toDate"><xsl:with-param name="ts" select="alterVO/fwZiyongList/jmsqxq"/></xsl:call-template>
		</xsl:attribute>

	</input>
	<br/>
	减免税期限止：
	<input size="9">
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.jmsqxz</xsl:attribute>
		<xsl:attribute name="value">
			<xsl:call-template name="toDate"><xsl:with-param name="ts" select="alterVO/fwZiyongList/jmsqxz"/></xsl:call-template>
		</xsl:attribute>
	</input>
</td>
<td align="right">
	<!-- 其中应税原值 -->
	<input size="10">
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghqzysyz</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/fwZiyongList/bghqzysyz"/></xsl:attribute>
	</input>
	<br/>
	原因：
	<select>
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.ysfcyzbgyy</xsl:attribute>
		<xsl:variable name="v_ysfcyzbgyy" select="alterVO/fwZiyongList/ysfcyzbgyy"/>
		<xsl:for-each select="../yingShuiList">
			<option>
				<xsl:attribute name="value"><xsl:value-of select="value"/></xsl:attribute>
				<xsl:if test="value = ($v_ysfcyzbgyy)">
					<xsl:attribute name="selected">selected</xsl:attribute>
				</xsl:if>
				<xsl:value-of select="label"/>
			</option>
		</xsl:for-each>
	</select>
</td>
<td align="right">
	<!-- 年应纳税额 -->
	<input size="10">
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghnynse</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/fwZiyongList/bghnynse"/></xsl:attribute>
	</input>
</td>
<td align="right">
	<select><!-- 是否代缴 -->
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghsfdj</xsl:attribute>
		<xsl:variable name="v_bghsfdj" select="alterVO/fwZiyongList/bghsfdj"/>
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
	<!-- 备注 -->
	<input>
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghbz</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/fwZiyongList/bghbz"/></xsl:attribute>
	</input>
</td>												
												<!-- alter_data -->
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
											
											<!-- alter_update_head -->
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqcqzsh</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/fwZiyongList/bgqcqzsh"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqfwzl</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/fwZiyongList/bgqfwzl"/></xsl:attribute>
												</input>
											</td>

											<td>
												<input size="10" readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqfcyz</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/fwZiyongList/bgqfcyz"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input size="10" readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqswjggz</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/fwZiyongList/bgqswjggz"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input size="10" readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqqzmsyz</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/fwZiyongList/bgqqzmsyz"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input size="10" readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqqzysyz</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/fwZiyongList/bgqqzysyz"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input size="10" readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqnynse</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/fwZiyongList/bgqnynse"/></xsl:attribute>
												</input>
											</td>
											<td>
												<select readonly="true" disabled="true"><!-- 是否代缴 -->
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqsfdj</xsl:attribute>
													<xsl:variable name="v_bgqsfdj" select="alterVO/fwZiyongList/bgqsfdj"/>
														<xsl:for-each select="../daiJiaoList">
														<option>
															<xsl:attribute name="value"><xsl:value-of select="value"/></xsl:attribute>
															<xsl:if test="value = ($v_bgqsfdj)">
																<xsl:attribute name="selected">selected</xsl:attribute>
															</xsl:if>
															<xsl:value-of select="label"/>
														</option>
														</xsl:for-each>
												</select>
																								
											</td>
											<td>
												<input size="10" readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqbz</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/fwZiyongList/bgqbz"/></xsl:attribute>
												</input>
											</td>	
											<!-- alter_update_head -->									
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
											<!-- alter_update_head -->
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqcqzsh</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/fwZiyongList/bgqcqzsh"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqfwzl</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/fwZiyongList/bgqfwzl"/></xsl:attribute>
												</input>
											</td>

											<td>
												<input size="10" readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqfcyz</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/fwZiyongList/bgqfcyz"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input size="10" readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqswjggz</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/fwZiyongList/bgqswjggz"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input size="10" readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqqzmsyz</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/fwZiyongList/bgqqzmsyz"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input size="10" readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqqzysyz</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/fwZiyongList/bgqqzysyz"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input size="10" readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqnynse</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/fwZiyongList/bgqnynse"/></xsl:attribute>
												</input>
											</td>
											<td>												
												<select readonly="true" disabled="true"><!-- 是否代缴 -->
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqsfdj</xsl:attribute>
													<xsl:variable name="v_bgqsfdj" select="alterVO/fwZiyongList/bgqsfdj"/>
														<xsl:for-each select="../daiJiaoList">
														<option>
															<xsl:attribute name="value"><xsl:value-of select="value"/></xsl:attribute>
															<xsl:if test="value = ($v_bgqsfdj)">
																<xsl:attribute name="selected">selected</xsl:attribute>
															</xsl:if>
															<xsl:value-of select="label"/>
														</option>
														</xsl:for-each>
												</select>												
											</td>
											<td>
												<input size="10" readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bgqbz</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/fwZiyongList/bgqbz"/></xsl:attribute>
												</input>
											</td>	
											<!-- alter_update_head -->	
											
										</tr>
										<tr style="display: block">
											<xsl:attribute name="id">ba_<xsl:value-of select="($pos)"/></xsl:attribute>
											<td align="right" width="51">后</td>
											
											<!-- alter_data -->
<td align="right"><!-- 产权证书号 -->
	<input>
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghcqzsh</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/fwZiyongList/bghcqzsh"/></xsl:attribute>
		<xsl:if test="regVO/fwZiyongList/fhbs = '0'">
				<xsl:attribute name="readOnly">true</xsl:attribute>
				<xsl:attribute name="disabled">true</xsl:attribute>
		</xsl:if>
	</input>
</td>


<td align="right"><!-- 房屋坐落 -->
	<input>
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghfwzl</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/fwZiyongList/bghfwzl"/></xsl:attribute>
		<xsl:if test="regVO/fwZiyongList/fhbs = '0'">
				<xsl:attribute name="readOnly">true</xsl:attribute>
				<xsl:attribute name="disabled">true</xsl:attribute>
		</xsl:if>
	</input>
</td>

<td align="right"><!-- 房产原值 -->
	<input size="10">
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghfcyz</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/fwZiyongList/bghfcyz"/></xsl:attribute>
		<xsl:if test="regVO/fwZiyongList/fhbs = '0'">
				<xsl:attribute name="readOnly">true</xsl:attribute>
				<xsl:attribute name="disabled">true</xsl:attribute>
		</xsl:if>
	</input>
</td>

<td align="right"><!-- 税务机关估值 -->
	<input size="10">
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghswjggz</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/fwZiyongList/bghswjggz"/></xsl:attribute>
		<xsl:if test="regVO/fwZiyongList/fhbs = '0'">
				<xsl:attribute name="readOnly">true</xsl:attribute>
				<xsl:attribute name="disabled">true</xsl:attribute>
		</xsl:if>
	</input>
	
</td>
<td align="right" >
	<!-- 其中免税原值 -->
	<input size="10">
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghqzmsyz</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/fwZiyongList/bghqzmsyz"/></xsl:attribute>
	</input>
	<br/><!-- 0-法定,1-大修,2-其他 -->
	原因：
		<select onchange="selectZhengCe(this)">
			<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.jmsyzbgyy</xsl:attribute>
			<xsl:attribute name="id">bb_<xsl:value-of select="($pos)"/></xsl:attribute>
			<xsl:variable name="v_jmsyzbgyy" select="alterVO/fwZiyongList/jmsyzbgyy"/>
			<xsl:for-each select="../jianMianList">
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
			<xsl:if test="alterVO/fwZiyongList/jmsyzbgyy = 2">
				<xsl:attribute name="style">display: block</xsl:attribute>
			</xsl:if>
			<xsl:if test="alterVO/fwZiyongList/jmsyzbgyy != 2">
				<xsl:attribute name="style">display: none</xsl:attribute>
			</xsl:if>
		
		文件号：
		<select>
			<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.jmzcdm</xsl:attribute>
			<xsl:variable name="v_jmzcdm" select="alterVO/fwZiyongList/jmzcdm"/>
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
			<xsl:call-template name="toDate"><xsl:with-param name="ts" select="alterVO/fwZiyongList/jmsqxq"/></xsl:call-template>
		</xsl:attribute>
	</input>
	<br/>
	减免税期限止：
	<input size="9">
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.jmsqxz</xsl:attribute>
		<xsl:attribute name="value">
			<xsl:call-template name="toDate"><xsl:with-param name="ts" select="alterVO/fwZiyongList/jmsqxz"/></xsl:call-template>
		</xsl:attribute>

	</input>
</td>
<td align="right">
	<!-- 其中应税原值 -->
	<input size="10">
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghqzysyz</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/fwZiyongList/bghqzysyz"/></xsl:attribute>
	</input>
	<br/>
	原因：
	<select>
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.ysfcyzbgyy</xsl:attribute>
		<xsl:variable name="v_ysfcyzbgyy" select="alterVO/fwZiyongList/ysfcyzbgyy"/>
		<xsl:for-each select="../yingShuiList">
			<option>
				<xsl:attribute name="value"><xsl:value-of select="value"/></xsl:attribute>
				<xsl:if test="value = ($v_ysfcyzbgyy)">
					<xsl:attribute name="selected">selected</xsl:attribute>
				</xsl:if>
				<xsl:value-of select="label"/>
			</option>
		</xsl:for-each>
	</select>
</td>
<td align="right">
	<!-- 年应纳税额 -->
	<input size="10">
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghnynse</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/fwZiyongList/bghnynse"/></xsl:attribute>
	</input>
</td>
<td align="right">
	
	<select><!-- 是否代缴 -->
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghsfdj</xsl:attribute>
		<xsl:if test="regVO/fwZiyongList/fhbs = '0'">
				<xsl:attribute name="readOnly">true</xsl:attribute>
				<xsl:attribute name="disabled">true</xsl:attribute>
		</xsl:if>
		<xsl:variable name="v_bghsfdj" select="alterVO/fwZiyongList/bghsfdj"/>
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
	<!-- 备注 -->
	<input>
		<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghbz</xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="alterVO/fwZiyongList/bghbz"/></xsl:attribute>
		<xsl:if test="regVO/fwZiyongList/fhbs = '0'">
				<xsl:attribute name="readOnly">true</xsl:attribute>
				<xsl:attribute name="disabled">true</xsl:attribute>
		</xsl:if>
	</input>
</td>											
											<!-- alter_data -->
											
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
											
											<!-- alter_add_head -->
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.cqzsh</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/fwZiyongList/cqzsh"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.fwzl</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/fwZiyongList/fwzl"/></xsl:attribute>
												</input>
											</td>

											<td>
												<input size="10" readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.fcyz</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/fwZiyongList/fcyz"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input size="10" readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.swjggz</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/fwZiyongList/swjggz"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input size="10" readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.qzmsyz</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/fwZiyongList/qzmsyz"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input size="10" readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.qzysyz</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/fwZiyongList/qzysyz"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input size="10" readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.nynse</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/fwZiyongList/nynse"/></xsl:attribute>
												</input>
											</td>
											<td>
												<select readonly="true" disabled="true"><!-- 是否代缴 -->
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.sfdj</xsl:attribute>
													<xsl:variable name="v_sfdj" select="regVO/fwZiyongList/sfdj"/>
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
												<input size="10" readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.bz</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/fwZiyongList/bz"/></xsl:attribute>
												</input>
											</td>											
											<!-- alter_add_head -->
										</tr>


										<tr style="display: none;">
											<xsl:attribute name="id">ba_<xsl:value-of select="($pos)"/></xsl:attribute>

											<td align="right" width="51">后</td>
											<td align="right">
												<input>
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghcqzsh</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/fwZiyongList/cqzsh"/></xsl:attribute>
												</input>
											</td>
											<td align="right">
												<input>
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghfwzl</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/fwZiyongList/fwzl"/></xsl:attribute>
												</input>
											</td>
											<td align="right">
												<input>
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghfcyz</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/fwZiyongList/fcyz"/></xsl:attribute>
												</input>
											</td>
											<td align="right">
												<input>
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghswjggz</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/fwZiyongList/swjggz"/></xsl:attribute>
												</input>
											</td>
											
											
											<!-- alter_add_inc -->
											<td align="right" >
												<input>
													<xsl:attribute name="id">bb_<xsl:value-of select="($pos)"/></xsl:attribute>
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghqzmsyz</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/fwZiyongList/qzmsyz"/></xsl:attribute>
												</input>
												<br/><!-- 0-法定,1-大修,2-其他 -->
												原因：
												<select onchange="selectZhengCe(this)">
		
													<xsl:attribute name="id">bb_<xsl:value-of select="($pos)"/></xsl:attribute>
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.jmsyzbgyy</xsl:attribute>
		
													<option value="">---</option>
													<option value="0">法定</option>
													<option value="1">大修</option>
													<option value="2">其他</option>
		
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
											<!-- alter_add_inc -->

											<td align="right">
												<input>
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghqzysyz</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/fwZiyongList/qzysyz"/></xsl:attribute>
												</input>
												<br/>
												原因：
												<select>
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.ysfcyzbgyy</xsl:attribute>
													<option value="">---</option>
													<option value="0">新增</option>
													<option value="1">减少</option>
													<option value="2">原值变更</option>
												</select>
											</td>
											<td align="right">
												<input>
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghnynse</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/fwZiyongList/nynse"/></xsl:attribute>
												</input>
											</td>
											<td align="right">
											
												
												<select><!-- 是否代缴 -->
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghsfdj</xsl:attribute>
													<xsl:variable name="v_bghsfdj" select="alterVO/fwZiyongList/bghsfdj"/>
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
												<input>
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghbz</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/fwZiyongList/bz"/></xsl:attribute>
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
											
											<!-- alter_add_head -->
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.cqzsh</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/fwZiyongList/cqzsh"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.fwzl</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/fwZiyongList/fwzl"/></xsl:attribute>
												</input>
											</td>

											<td>
												<input size="10" readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.fcyz</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/fwZiyongList/fcyz"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input size="10" readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.swjggz</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/fwZiyongList/swjggz"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input size="10" readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.qzmsyz</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/fwZiyongList/qzmsyz"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input size="10" readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.qzysyz</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/fwZiyongList/qzysyz"/></xsl:attribute>
												</input>
											</td>
											<td>
												<input size="10" readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.nynse</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/fwZiyongList/nynse"/></xsl:attribute>
												</input>
											</td>
											<td>
												<select readonly="true" disabled="true"><!-- 是否代缴 -->
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.sfdj</xsl:attribute>
													<xsl:variable name="v_sfdj" select="regVO/fwZiyongList/sfdj"/>
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
												<input size="10" readOnly="true" disabled="true" type="input" >
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.bz</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/fwZiyongList/bz"/></xsl:attribute>
												</input>
											</td>											
											<!-- alter_add_head -->
										</tr>

										<tr style="display: none;">
											<xsl:attribute name="id">ba_<xsl:value-of select="($pos)"/></xsl:attribute>

											<td align="right" width="51">后</td>
											<td align="right">
												<input readonly="true" disabled="true">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghcqzsh</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/fwZiyongList/cqzsh"/></xsl:attribute>
												</input>
											</td>
											<td align="right">
												<input readonly="true" disabled="true">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghfwzl</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/fwZiyongList/fwzl"/></xsl:attribute>
												</input>
											</td>
											<td align="right">
												<input readonly="true" disabled="true">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghfcyz</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/fwZiyongList/fcyz"/></xsl:attribute>
												</input>
											</td>
											<td align="right" readonly="true" disabled="true">
												<input>
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghswjggz</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/fwZiyongList/swjggz"/></xsl:attribute>
												</input>
											</td>
											
											
											<!-- alter_add_inc -->
											<td align="right" >
												<input>
													<xsl:attribute name="id">bb_<xsl:value-of select="($pos)"/></xsl:attribute>
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghqzmsyz</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/fwZiyongList/qzmsyz"/></xsl:attribute>
												</input>
												<br/><!-- 0-法定,1-大修,2-其他 -->
												原因：
												<select onchange="selectZhengCe(this)">
		
													<xsl:attribute name="id">bb_<xsl:value-of select="($pos)"/></xsl:attribute>
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.jmsyzbgyy</xsl:attribute>
		
													<option value="">---</option>
													<option value="0">法定</option>
													<option value="1">大修</option>
													<option value="2">其他</option>
		
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
											<!-- alter_add_inc -->

											<td align="right">
												<input readonly="true" disabled="true">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghqzysyz</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/fwZiyongList/qzysyz"/></xsl:attribute>
												</input>
												<br/>
												原因：
												<select readonly="true" disabled="true">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.ysfcyzbgyy</xsl:attribute>
													<option value="">---</option>
													<option value="0">新增</option>
													<option value="1">减少</option>
													<option value="2">原值变更</option>
												</select>
											</td>
											<td align="right">
												<input readonly="true" disabled="true">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghnynse</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/fwZiyongList/nynse"/></xsl:attribute>
												</input>
											</td>
											<td align="right">
											
												
												<select readonly="true" disabled="true"><!-- 是否代缴 -->
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghsfdj</xsl:attribute>
													<xsl:variable name="v_bghsfdj" select="regVO/fwZiyongList/bghsfdj"/>
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
											<td align="right" readonly="true" disabled="true">
												<input>
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bghbz</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/fwZiyongList/bz"/></xsl:attribute>
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
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.id</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/fwZiyongList/id"/></xsl:attribute>
												</input>
												<input type="hidden">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].regVO.jsjdm</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="regVO/fwZiyongList/jsjdm"/></xsl:attribute>
												</input>
												<input type="hidden">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.id</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/fwZiyongList/id"/></xsl:attribute>
												</input>
												<input type="hidden">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.jsjdm</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/fwZiyongList/jsjdm"/></xsl:attribute>
												</input>
												<input type="hidden">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.djbh</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/fwZiyongList/djbh"/></xsl:attribute>
												</input>
												<input type="hidden">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.bglx</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/fwZiyongList/bglx"/></xsl:attribute>
												</input>
												<input type="hidden">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.jcbh</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/fwZiyongList/jcbh"/></xsl:attribute>
												</input>
												<input type="hidden">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.cjr</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/fwZiyongList/cjr"/></xsl:attribute>
												</input>
												<input type="hidden">
													<xsl:attribute name="name">list[<xsl:value-of select="($pos)"/>].alterVO.cjrq</xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="alterVO/fwZiyongList/cjrq"/></xsl:attribute>
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
<scenarios ><scenario default="yes" name="Scenario1" userelativepaths="yes" externalpreview="no" url="file:///c:/Documents and Settings/Administrator/桌面/测试xslt/test_fwziyong.xml" htmlbaseurl="" outputurl="" processortype="internal" useresolver="yes" profilemode="0" profiledepth="" profilelength="" urlprofilexml="" commandline="" additionalpath="" additionalclasspath="" postprocessortype="none" postprocesscommandline="" postprocessadditionalpath="" postprocessgeneratedext="" validateoutput="no" validator="internal" customvalidator=""/></scenarios><MapperMetaTag><MapperInfo srcSchemaPathIsRelative="yes" srcSchemaInterpretAsXML="no" destSchemaPath="" destSchemaRoot="" destSchemaPathIsRelative="yes" destSchemaInterpretAsXML="no"/><MapperBlockPosition></MapperBlockPosition><TemplateContext></TemplateContext><MapperFilter side="source"></MapperFilter></MapperMetaTag>
</metaInformation>
-->