<?xml version="1.0" encoding="GB2312"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" />
	<xsl:template match="/">
		
			<!-- 隐藏域-->
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
			<input name="query_jsjdm" type="hidden" id="query_jsjdm">	
			</input>
			<input name="query_sfzjhm" type="hidden" id="query_sfzjhm">
			</input>
			<input name="query_sfzjlx" type="hidden" id="query_sfzjlx">
			</input>
			
		<table id="maintable2" align="center" cellspacing="0"  width="80%">

			<!--录入信息-->
			<tr>
				<td class="1-td1" colspan="2">录入个人所得税基础信息表信息</td>
			</tr>

			<tr>
				<td class="1-td2" colspan="2">
					<br/>

				
					<table  cellSpacing="0" cellPadding="0" width="97%" border="0" align = "center">
						<tr>
							<td class="2-td2-t-left" width="10%" ><!--姓名  不可修改-->
								姓名
							</td>
							<td class="2-td2-t-left" width="10%">
								<input type="text" name="grxx_xm" id="grxx_xm" style="width:80%">
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/grxx/grxx_xm" />
									</xsl:attribute>
								</input>
							</td>
							<td class="2-td2-t-left" width="10%"><!--身份证件类型 不可修改-->
								身份证件类型
							</td>
							<td class="2-td2-t-left" width="10%">
							    
								<select name="grxx_sfzjlx" id="grxx_sfzjlx" disabled="true" >
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/grxx/grxx_sfzjlx" />
									</xsl:attribute>
								</select>
							</td>
							<td class="2-td2-t-left"  width="10%"><!--身份证件号码 不可修改-->
								身份证件号码
							</td>
							<td class="2-td2-t-center"  width="50%" colspan="5">
								<input type="text" style="background-color:#cccccc" name="grxx_sfzjhm" id="grxx_sfzjhm" readonly="true">
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/grxx/grxx_sfzjhm" />
									</xsl:attribute>
								</input>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left"  width="10%"><!--纳税人类型-->
								纳税人类型
							</td>
							<td class="2-td2-center"  width="90%" colspan="9">
								<input name="grxx_nsrlx" type="hidden" id="grxx_nsrlx">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/grxx/grxx_nsrlx" />
									</xsl:attribute>
								</input>
								<input type='checkbox' name='grxx_nsrlx_cb' id="grxx_nsrlx0" value="0">有任职受雇单位</input>&#160;&#160;&#160;&#160;
								<input type='checkbox' name='grxx_nsrlx_cb' id="grxx_nsrlx1" value="1">投资者</input>&#160;&#160;&#160;&#160;
								<input type='checkbox' name='grxx_nsrlx_cb' id="grxx_nsrlx2" value="2">无任职受雇单位（不含股东投资者）</input>&#160;&#160;&#160;&#160;
								<input type='checkbox' name='grxx_nsrlx_cb' id="grxx_nsrlx3" value="3">无住所个人</input>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left"  width="10%"><!--任职受雇单位名称-->
								任职受雇单位名称
							</td>
							<td class="2-td2-left"  width="60%" colspan="5">
								<input type="text" name="grxx_rzsgdwmc" id="grxx_rzsgdwmc" >
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/grxx/grxx_rzsgdwmc" />
									</xsl:attribute>
								</input>
							</td>
							<td class="2-td2-left"  width="10%"><!--任职受雇单位纳税人识别号-->
								任职受雇单位纳税人识别号
							</td>
							<td class="2-td2-center"  width="20%" colspan="3">
								<input type="text" name="grxx_rzsgdwnsrsbh" id="grxx_rzsgdwnsrsbh" >
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/grxx/grxx_rzsgdwnsrsbh" />
									</xsl:attribute>
								</input>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" ><!--三费一斤缴纳情况-->
								“三费一金”缴纳情况
							</td>
							<td class="2-td2-left" width="60%" colspan="5">
								<input name="grxx_sfyjjnqk" type="hidden" id="grxx_sfyjjnqk">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/grxx/grxx_sfyjjnqk" />
									</xsl:attribute>
								</input>
								<input type='checkbox' name='grxx_sfyjjnqk_cb' id="grxx_sfyjjnqk_0" value="0">基本养老保险费</input>
								<input type='checkbox' name='grxx_sfyjjnqk_cb' id="grxx_sfyjjnqk_1" value="1">基本医疗保险费</input>
								<input type='checkbox' name='grxx_sfyjjnqk_cb' id="grxx_sfyjjnqk_2" value="2">失业保险费</input>
								<input type='checkbox' name='grxx_sfyjjnqk_cb' id="grxx_sfyjjnqk_3" value="3">住房公积金</input>
							</td>
							<td class="2-td2-left"  width="10%"><!--电子邮箱-->
								电子邮箱
							</td>
							<td class="2-td2-center"  width="20%" colspan="3">
								<input type="text" name="grxx_email" id="grxx_email">
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/grxx/grxx_email" />
									</xsl:attribute>
								</input>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" ><!--境内联系地址-->
								境内联系地址
							</td>
							<td class="2-td2-left" width="60%" colspan="5">
								<input type="text" name="grxx_jnlxdz" id="grxx_jnlxdz">
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/grxx/grxx_jnlxdz" />
									</xsl:attribute>
								</input>
							</td>
							<td class="2-td2-left"  width="10%"><!--邮政编码-->
								邮政编码
							</td>
							<td class="2-td2-center"  width="20%" colspan="3">
								<input type="text" name="grxx_yzbm" id="grxx_yzbm" >
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/grxx/grxx_yzbm" />
									</xsl:attribute>
								</input>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" ><!--联系电话-->
								联系电话
							</td>
							<td class="2-td2-left" width="60%" colspan="5">
								<input type="text" name="grxx_lxdh" id="grxx_lxdh" >
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/grxx/grxx_lxdh" />
									</xsl:attribute>
								</input>
							</td>
							<td class="2-td2-left"  width="10%"><!--邮政编码-->
								职业
							</td>
							<td class="2-td2-center"  width="20%" colspan="3">
								<input type="text" name="grxx_zy" id="grxx_zy" >
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/grxx/grxx_zy" />
									</xsl:attribute>
								</input>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" ><!--联系电话-->
								职务
							</td>
							<td class="2-td2-left" width="60%" colspan="5">
								<select name="grxx_zw" id="grxx_zw">
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/grxx/grxx_zw" />
									</xsl:attribute>
									<option value="1">高层</option>
									<option value="2">中层</option>
									<option value="3">普通</option>
								</select>
							</td>
							<td class="2-td2-left"  width="10%"><!--邮政编码-->
								学历
							</td>
							<td class="2-td2-center"  width="20%" colspan="3">
								<input type="text" name="grxx_xl" id="grxx_xl" >
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/grxx/grxx_xl" />
									</xsl:attribute>
								</input>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left"><!--是否残疾人/烈属/孤老-->
								是否残疾人/烈属/孤老
							</td>
							<td class="2-td2-left" colspan="5">
								<input name="grxx_sfcjrlsgl" type="hidden" id="grxx_sfcjrlsgl">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/grxx/grxx_sfcjrlsgl" />
									</xsl:attribute>
								</input>
								<input type='checkbox' name='grxx_sfcjrlsgl_cb' id="grxx_sfcjrlsg_0" value="0">残疾</input>
								<input type='checkbox' name='grxx_sfcjrlsgl_cb' id="grxx_sfcjrlsg_1" value="1">烈属</input>
								<input type='checkbox' name='grxx_sfcjrlsgl_cb' id="grxx_sfcjrlsg_2" value="2">孤老</input>
								<input type='checkbox' name='grxx_sfcjrlsgl_cb' id="grxx_sfcjrlsg_3" value="3">否</input>
							</td>
							<td class="2-td2-left"><!--残疾等级情况-->
								残疾等级情况
							</td>
							<td class="2-td2-center" colspan="3">
								<input type="text" name="grxx_cjdjqk" id="grxx_cjdjqk">
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/grxx/grxx_cjdjqk" />
									</xsl:attribute>
								</input>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left"><!-- 该栏仅由有境外所得纳税人填写 -->
								该栏仅由有境外所得纳税人填写
							</td>
							<td class="2-td2-left" colspan="2">
								<select name="grxx_yjwsd_dzlx" id="grxx_yjwsd_dzlx">
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/grxx/grxx_yjwsd_dzlx" />
									</xsl:attribute>
									<option value="0">户籍所在地</option>
									<option value="1">经常居住地</option>
								</select>
							</td>
							<td class="2-td2-left">
								邮政编码
							</td>
							<td class="2-td2-left" colspan="2"><!--残疾等级情况-->
								<input type="text" name="grxx_yjwsd_yzbm" id="grxx_yjwsd_yzbm">
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/grxx/grxx_yjwsd_yzbm" />
									</xsl:attribute>
								</input>
							</td>
							<td class="2-td2-left">
								地址
							</td>
							<td class="2-td2-center" colspan="3">
								<input type="text" name="grxx_yjwsd_dz" id="grxx_yjwsd_dz">
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/grxx/grxx_yjwsd_dz" />
									</xsl:attribute>
								</input>
							</td>
						</tr>
						
						<tr>
							<td class="2-td2-left" rowspan="5"><!--该栏仅由投资者纳税人填写-->
								该栏仅由投资者纳税人填写
							</td>
							<td class="2-td2-left" colspan="2"><!--该栏仅由投资者纳税人填写-->
								投资者类型
							</td>
							<td class="2-td2-center" colspan="7">
								<input name="grxx_tzzlx" type="hidden" id="grxx_tzzlx">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/grxx/grxx_tzzlx" />
									</xsl:attribute>
								</input>
								<input type='checkbox' name='grxx_tzzlx_cb' id="grxx_tzzlx_0" value="0">个体工商户</input>
								<input type='checkbox' name='grxx_tzzlx_cb' id="grxx_tzzlx_1" value="1">个人独资企业投资者</input>
								<input type='checkbox' name='grxx_tzzlx_cb' id="grxx_tzzlx_2" value="2">合伙企业合伙人</input>
								<input type='checkbox' name='grxx_tzzlx_cb' id="grxx_tzzlx_3" value="3">承包、承租经营者</input>
								<input type='checkbox' name='grxx_tzzlx_cb' id="grxx_tzzlx_4" value="4">股东</input>
								<input type='checkbox' name='grxx_tzzlx_cb' id="grxx_tzzlx_5" value="5">其他投资者</input>
							</td>
						</tr>

						<tr>
							<td class="2-td2-left" rowspan="4"><!--被投资单位信息-->
								被投资单位信息
							</td>
							<td class="2-td2-left" ><!--名称-->
								名称
							</td>
							<td class="2-td2-left" colspan="3">
								<input type="text" name="qyxx_btzzxm" id="qyxx_btzzxm">
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/qyxxvo/qyxx_btzzxm" />
									</xsl:attribute>
								</input>
								<input name="qyxx_jsjdm" type="hidden" id="qyxx_jsjdm">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/qyxxvo/qyxx_jsjdm" />
									</xsl:attribute>
								</input>
							</td>
							<td class="2-td2-left" ><!--扣缴义务人编码-->
								扣缴义务人编码
							</td>
							<td class="2-td2-center" colspan="3">
								<input type="text" name="qyxx_kjywrbh" id="qyxx_kjywrbh">
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/qyxxvo/qyxx_kjywrbh" />
									</xsl:attribute>
								</input>
							</td>
						</tr>

						<tr>
							<td class="2-td2-left" ><!--名称-->
								地址
							</td>
							<td class="2-td2-left" colspan="3">
								<input type="text" name="qyxx_dz" id="qyxx_dz">
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/qyxxvo/qyxx_dz" />
									</xsl:attribute>
								</input>
							</td>
							<td class="2-td2-left" ><!--扣缴义务人编码-->
								邮政编码
							</td>
							<td class="2-td2-center" colspan="3">
								<input type="text" name="qyxx_yzbm" id="qyxx_yzbm">
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/qyxxvo/qyxx_yzbm" />
									</xsl:attribute>
								</input>
							</td>
						</tr>

						<tr>
							<td class="2-td2-left" ><!--名称-->
								登记注册类型
							</td>
							<td class="2-td2-left" colspan="3">
								<select  name="qyxx_djzclx" id="qyxx_djzclx">
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/qyxxvo/qyxx_djzclx" />
									</xsl:attribute>
								</select>
							</td>
							<td class="2-td2-left" ><!--扣缴义务人编码-->
								行业
							</td>
							<td class="2-td2-center" colspan="3">
								
								<select  name="qyxx_hy" id="qyxx_hy">
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/qyxxvo/qyxx_hy" />
									</xsl:attribute>
								</select>
							</td>
						</tr>

						<tr>
							<td class="2-td2-left" ><!--名称-->
								所得税征收方式
							</td>
							<td class="2-td2-left" colspan="3">
								<select name="qyxx_sdszsfs" id="qyxx_sdszsfs">
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/qyxxvo/qyxx_sdszsfs" />
									</xsl:attribute>
									<option value="0">查账征收</option>
									<option value="1">核定征收</option>
								</select>
							</td>
							<td class="2-td2-left" ><!--扣缴义务人编码-->
								主管税务机关
							</td>
							<td class="2-td2-center" colspan="3">
								<select type="text" name="qyxx_zgswjg" id="qyxx_zgswjg">
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/qyxxvo/qyxx_zgswjg" />
									</xsl:attribute>
								</select>
							</td>
						</tr>

						<tr>
							<td class="2-td2-left" rowspan="13"><!--名称-->
								该栏仅由无住所纳税人填写
							</td>
							<td class="2-td2-left" colspan="2"><!---->
								纳税人识别号
							</td>
							<td class="2-td2-center" colspan="7">
								<input type="text" name="grxx_wzsnsr_nsrsbh" id="grxx_wzsnsr_nsrsbh">
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/grxx/grxx_wzsnsr_nsrsbh" />
									</xsl:attribute>
								</input>
							</td>
						</tr>

						<tr>
							<td class="2-td2-left" colspan="2" ><!--国籍-->
								国籍
							</td>
							<td class="2-td2-left" colspan="3">
								<select  name="grxx_wzsnsr_gj" id="grxx_wzsnsr_gj">
									
								</select>
							</td>
							<td class="2-td2-left"><!--出生地-->
								出生地
							</td>
							<td class="2-td2-center" colspan="3">
								<input type="text" name="grxx_wzsnsr_csd" id="grxx_wzsnsr_csd">
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/grxx/grxx_wzsnsr_csd" />
									</xsl:attribute>
								</input>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2"><!--性别-->
								性别
							</td>
							<td class="2-td2-left" colspan="3">
								
								<select  name="grxx_wzsnsr_xb" id="grxx_wzsnsr_xb">
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/grxx/grxx_wzsnsr_xb" />
									</xsl:attribute>
									<option value="0">男</option>
									<option value="1">女</option>
								</select>
							</td>
							<td class="2-td2-left"><!--出生日期-->
								出生日期
							</td>
							<td class="2-td2-center" colspan="3">
								<input type="text" name="grxx_wzsnsr_csrq" id="grxx_wzsnsr_csrq">
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/grxx/grxx_wzsnsr_csrq" />
									</xsl:attribute>
								</input>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2"><!--劳动就业证号码-->
								劳动就业证号码
							</td>
							<td class="2-td2-left" colspan="3">
								<input type="text" name="grxx_wzsnsr_ldjyzhm" id="grxx_wzsnsr_ldjyzhm">
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/grxx/grxx_wzsnsr_ldjyzhm" />
									</xsl:attribute>
								</input>
							</td>
							<td class="2-td2-left"><!--是否税收协定缔约国对方居民-->
								是否税收协定缔约国对方居民
							</td>
							<td class="2-td2-center" colspan="3">
								<select name="grxx_wzsnsr_sfssxddygdfjm" id="grxx_wzsnsr_sfssxddygdfjm" >
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/grxx/grxx_wzsnsr_sfssxddygdfjm" />
									</xsl:attribute>
									<option value="0">是</option>
									<option value="1">否</option>
								</select>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2"><!--境内职务-->
								境内职务
							</td>
							<td class="2-td2-left" colspan="3">
								<input type="text" name="grxx_wzsnsr_jnzw" id="grxx_wzsnsr_jnzw">
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/grxx/grxx_wzsnsr_jnzw" />
									</xsl:attribute>
								</input>
							</td>
							<td class="2-td2-left"><!--境外职务-->
								境外职务
							</td>
							<td class="2-td2-center" colspan="3">
								<input type="text" name="grxx_wzsnsr_jwzw" id="grxx_wzsnsr_jwzw">
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/grxx/grxx_wzsnsr_jwzw" />
									</xsl:attribute>
								</input>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2"><!--来华时间-->
								来华时间
							</td>
							<td class="2-td2-left" colspan="3">
								<input type="text" name="grxx_wzsnsr_lhsj" id="grxx_wzsnsr_lhsj">
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/grxx/grxx_wzsnsr_lhsj" />
									</xsl:attribute>
								</input>
							</td>
							<td class="2-td2-left"><!--任职期限-->
								任职期限
							</td>
							<td class="2-td2-center" colspan="3">
								<input type="text" name="grxx_wzsnsr_rzqx" id="grxx_wzsnsr_rzqx">
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/grxx/grxx_wzsnsr_rzqx" />
									</xsl:attribute>
								</input>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2"><!--预计离境时间-->
								预计离境时间
							</td>
							<td class="2-td2-left" colspan="3">
								<input type="text" name="grxx_wzsnsr_yjljsj" id="grxx_wzsnsr_yjljsj">
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/grxx/grxx_wzsnsr_yjljsj" />
									</xsl:attribute>
								</input>
							</td>
							<td class="2-td2-left"><!--预计离境地点-->
								预计离境地点
							</td>
							<td class="2-td2-center" colspan="3">
								<input type="text" name="grxx_wzsnsr_yjljdd" id="grxx_wzsnsr_yjljdd">
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/grxx/grxx_wzsnsr_yjljdd" />
									</xsl:attribute>
								</input>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" rowspan="2" ><!--境内任职受雇单位-->
								境内任职受雇单位
							</td>
							<td class="2-td2-left"><!--名称-->
								名称
							</td>
							<td class="2-td2-left" colspan="3">
								<input type="text" name="grxx_wzsnsr_jnrzsgdw_mc" id="grxx_wzsnsr_jnrzsgdw_mc">
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/grxx/grxx_wzsnsr_jnrzsgdw_mc" />
									</xsl:attribute>
								</input>
							</td>
							<td class="2-td2-left"><!--扣缴义务人编码-->
								扣缴义务人编码
							</td>
							<td class="2-td2-center" colspan="3">
								<input type="text" name="grxx_wzsnsr_jnrzsgdw_kjywrbm" id="grxx_wzsnsr_jnrzsgdw_kjywrbm">
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/grxx/grxx_wzsnsr_jnrzsgdw_kjywrbm" />
									</xsl:attribute>
								</input>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" ><!--地址-->
								地址
							</td>
							<td class="2-td2-left" colspan="3">
								<input type="text" name="grxx_wzsnsr_jnrzsgdw_dz" id="grxx_wzsnsr_jnrzsgdw_dz">
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/grxx/grxx_wzsnsr_jnrzsgdw_dz" />
									</xsl:attribute>
								</input>
							</td>
							<td class="2-td2-left"><!--邮政编码-->
								邮政编码
							</td>
							<td class="2-td2-center" colspan="3">
								<input type="text" name="grxx_wzsnsr_jnrzsgdw_yzbm" id="grxx_wzsnsr_jnrzsgdw_yzbm">
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/grxx/grxx_wzsnsr_jnrzsgdw_yzbm" />
									</xsl:attribute>
								</input>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" rowspan="2" ><!--境内受聘签约单位-->
								境内受聘签约单位
							</td>
							<td class="2-td2-left" ><!--名称-->
								名称
							</td>
							<td class="2-td2-left"  colspan="3">
								<input type="text" name="grxx_wzsnsr_jnspqydw_mc" id="grxx_wzsnsr_jnspqydw_mc">
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/grxx/grxx_wzsnsr_jnspqydw_mc" />
									</xsl:attribute>
								</input>
							</td>
							<td class="2-td2-left"><!--扣缴义务人编码-->
								扣缴义务人编码
							</td>
							<td class="2-td2-center" colspan="3">
								<input type="text" name="grxx_wzsnsr_jnspqydw_kjywrbm" id="grxx_wzsnsr_jnspqydw_kjywrbm">
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/grxx/grxx_wzsnsr_jnspqydw_kjywrbm" />
									</xsl:attribute>
								</input>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" ><!--地址-->
								地址
							</td>
							<td class="2-td2-left"  colspan="3">
								<input type="text" name="grxx_wzsnsr_jnspqydw_dz" id="grxx_wzsnsr_jnspqydw_dz">
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/grxx/grxx_wzsnsr_jnspqydw_dz" />
									</xsl:attribute>
								</input>
							</td>
							<td class="2-td2-left"><!--邮政编码-->
								邮政编码
							</td>
							<td class="2-td2-center" colspan="3">
								<input type="text" name="grxx_wzsnsr_jnspqydw_yzbm" id="grxx_wzsnsr_jnspqydw_yzbm">
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/grxx/grxx_wzsnsr_jnspqydw_yzbm" />
									</xsl:attribute>
								</input>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" ><!--境外派遣单位-->
								境外派遣单位
							</td>
							<td class="2-td2-left" ><!--名称-->
								名称
							</td>
							<td class="2-td2-left"  colspan="3">
								<input type="text" name="grxx_wzsnsr_jwpqdw_mc" id="grxx_wzsnsr_jwpqdw_mc">
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/grxx/grxx_wzsnsr_jwpqdw_mc" />
									</xsl:attribute>
								</input>
							</td>
							<td class="2-td2-left"><!--地址-->
								地址
							</td>
							<td class="2-td2-center"  colspan="3">
								<input type="text" name="grxx_wzsnsr_jwpqdw_dz" id="grxx_wzsnsr_jwpqdw_dz">
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/grxx/grxx_wzsnsr_jwpqdw_dz" />
									</xsl:attribute>
								</input>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2"><!--支付地-->
								支付地
							</td>
							<td class="2-td2-left" colspan="3">
								<select name="grxx_wzsnsr_zfd" id="grxx_wzsnsr_zfd" >
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/grxx/grxx_wzsnsr_zfd" />
									</xsl:attribute>
									<option value="0">境内支付</option>
									<option value="1">境外支付</option>
									<option value="2">境内、外同时支付</option>
								</select>
							</td>
							<td class="2-td2-left"><!--境外支付国国别-->
								境外支付国国别
							</td>
							<td class="2-td2-center" colspan="3">
								<select  name="grxx_wzsnsr_jwzfggb" id="grxx_wzsnsr_jwzfggb">
									
								</select>
							</td>
						</tr>
						

					</table>
					<br/>

				</td>
			</tr>
		</table>
	</xsl:template >

</xsl:stylesheet>