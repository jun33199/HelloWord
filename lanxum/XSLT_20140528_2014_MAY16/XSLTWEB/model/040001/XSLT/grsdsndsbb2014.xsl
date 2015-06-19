<?xml version="1.0" encoding="GB2312"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" />
	<xsl:template match="/">

		<!-- ������-->
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

			<!--¼����Ϣ-->
			<tr>
				<td class="1-td1" colspan="2">¼����������Ӫ���ø�������˰��˰�걨��</td>
			</tr>
			<tr>
				<td class="1-td2" colspan="2">
					<br/>

					&#160;&#160;&#160;
					<div align="left" style="display:inline">
						<FONT color="#000000" size="1">˰�������ڣ�</FONT>
						<input type="text" readonly="true" name="skssqq" id="skssqq" style="background-color:#cccccc">
							<xsl:attribute name="value">
								<xsl:value-of select="taxdoc/inf_gr/skssqq" />
							</xsl:attribute>
						</input>��
						<input type="text" readonly="true" name="skssqz" id="skssqz" style="background-color:#cccccc">
							<xsl:attribute name="value">
								<xsl:value-of select="taxdoc/inf_gr/skssqz" />
							</xsl:attribute>
						</input>
					</div>
					<div align="right" style="display:inline"><FONT color="#000000" size="1">��λ�������Ԫ�������Ƿ֣�</FONT></div>
					
					<table  cellSpacing="0" cellPadding="0" width="97%" border="0" align = "center">
						<tr>
							<td class="2-td2-t-left" rowspan="2" width="10%">Ͷ������Ϣ</td>
							<td class="2-td2-t-left" width="10%">����</td>
							<td class="2-td2-t-left" width="10%">
								<input  type="text" size="6" id="tzzxx_name" name="tzzxx_name">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/inf_gr/tzzxx_name" />
									</xsl:attribute>
								</input>
							</td>
							<td class="2-td2-t-left" width="10%">���֤������</td>
							<td class="2-td2-t-left" width="10%">
								<select width="90%" id="tzzxx_sfzjlx" name="tzzxx_sfzjlx" disabled="true">	
								</select>
							</td>
							<td class="2-td2-t-left" width="10%">���֤������</td>
							<td class="2-td2-t-center" colspan="4" width="40%">
								<input type="text" style="background-color:#cccccc" name="tzzxx_sfzjhm" id="tzzxx_sfzjhm" readonly="true">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/inf_gr/tzzxx_sfzjhm" />
									</xsl:attribute>
								</input>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" width="10%">����</td>
							<td class="2-td2-left" colspan="3" width="30%">
								<select  id="tzzxx_gj" name="tzzxx_gj" >
									
								</select>
							</td>
							<td class="2-td2-left" width="10%">��˰��ʶ���</td>
							<td class="2-td2-center" colspan="4" width="40%">
								<input  id="tzzxx_nsrsbh" name="tzzxx_nsrsbh" type="text">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/inf_gr/tzzxx_nsrsbh" />
									</xsl:attribute>
								</input>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" rowspan="2" width="10%" >��Ͷ�ʵ�λ��Ϣ</td>
							<td class="2-td2-left" width="10%">����</td>
							<td class="2-td2-left" colspan="3" width="30%">
								<input name="btzzxx_jsjdm" type="hidden" id="btzzxx_jsjdm">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/inf_qy/btzzxx_jsjdm" />
									</xsl:attribute>
								</input>
								<input width="90%" id="btzzxx_name" name="btzzxx_name" type="text">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/inf_qy/btzzxx_name" />
									</xsl:attribute>
								</input>
							</td>
							<td class="2-td2-left" width="10%">��˰��ʶ���</td>
							<td class="2-td2-center" colspan="4" width="40%">
								<input name="btzzxx_nsrsbh" id="btzzxx_nsrsbh" type="text">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/inf_qy/btzzxx_nsrsbh" />
									</xsl:attribute>
								</input>
							</td>
						</tr>
						<tr>
							
							<td class="2-td2-left" width="10%">����</td>
							<td class="2-td2-center" colspan="8" width="80%">
								<input name="btzzxx_djzclx" type="hidden" id="btzzxx_djzclx">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/inf_qy/btzzxx_djzclx" />
									</xsl:attribute>
								</input>
								<input type='checkbox' name='btzzxx_djzclx_cb' id="btzzxx_djzclx_0" value="0">���幤�̻�</input>&#160;&#160;&#160;&#160;
								<input type='checkbox' name='btzzxx_djzclx_cb' id="btzzxx_djzclx_1" value="1">�а������⾭Ӫ��</input>&#160;&#160;&#160;&#160;
								<input type='checkbox' name='btzzxx_djzclx_cb' id="btzzxx_djzclx_2" value="2">���˶�����ҵ</input>&#160;&#160;&#160;&#160;
								<input type='checkbox' name='btzzxx_djzclx_cb' id="btzzxx_djzclx_3" value="3">�ϻ���ҵ</input>
							</td>
						</tr>
						
						<tr>
							<td class="2-td2-left" style="border-bottom-width:0px" width="10%">��ƽ��ְ������</td>
							<td class="2-td2-left" style="border-bottom-width:0px" colspan="2" width="20%">
								<input id="btzzxx_npjzgrs" name="btzzxx_npjzgrs" type="text">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/inf_qy/btzzxx_npjzgrs" />
									</xsl:attribute>
								</input>
							</td>
							<td class="2-td2-left" style="border-bottom-width:0px" width="10%">�����ܶԪ��</td>
							<td class="2-td2-left" style="border-bottom-width:0px" colspan="2" width="20%">
								<input  id="btzzxx_gzze" name="btzzxx_gzze" type="text">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/inf_qy/btzzxx_gzze" />
									</xsl:attribute>
								</input>
							</td>
							<td class="2-td2-left" style="border-bottom-width:0px" width="10%">Ͷ�����������ˣ�</td>
							<td class="2-td2-center" style="border-bottom-width:0px" colspan="3" width="30%">
								<input  id="btzzxx_tzzrs" name="btzzxx_tzzrs" type="text">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/inf_qy/btzzxx_tzzrs" />
									</xsl:attribute>
								</input>
							</td>
						</tr>
						<tr>
							<td class="2-td1-left" style="border-bottom-size:0" colspan="2" width="20%">��Ŀ</td>
							<td class="2-td1-left" style="border-bottom-size:0" width="10%">�д�</td>
							<td class="2-td1-left" style="border-bottom-size:0" colspan="2" width="20%">���</td>
							<td class="2-td1-left" style="border-bottom-size:0" colspan="2" width="20%">��Ŀ</td>
							<td class="2-td1-left" style="border-bottom-size:0" width="10%">�д�</td>
							<td class="2-td1-center" style="border-bottom-size:0" colspan="2" width="20%">���</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">һ�������ܶ�</td>
							<td class="2-td2-left">1</td>
							<td class="2-td2-left" colspan="2">
								<input type="text" name="col_1" id="col_1">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/inf_qy/col_1" />
									</xsl:attribute>
								</input>
							</td>
							<td class="2-td2-left" colspan="2">˰�����ɽ𡢷��𡢷���</td>
							<td class="2-td2-left">27</td>
							<td class="2-td2-center" colspan="2">
								<input type="text" name="col_27" id="col_27">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/inf_qy/col_27" />
									</xsl:attribute>
								</input>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">�����ɱ�</td>
							<td class="2-td2-left">2</td>
							<td class="2-td2-left" colspan="2">
								<input type="text" name="col_2" id="col_2">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/inf_qy/col_2" />
									</xsl:attribute>
								</input>
							</td>
							<td class="2-td2-left" colspan="2">����֧�����ǽ����͹�����ҵ����</td>
							<td class="2-td2-left">28</td>
							<td class="2-td2-center" colspan="2">
								<input type="text" name="col_28" id="col_28">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/inf_qy/col_28" />
									</xsl:attribute>
								</input>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">Ӫҵ����</td>
							<td class="2-td2-left">3</td>
							<td class="2-td2-left" colspan="2">
								<input type="text" name="col_3" id="col_3">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/inf_qy/col_3" />
									</xsl:attribute>
								</input>
							</td>
							<td class="2-td2-left" colspan="2">�ֺ��¹���ʧ�⳥</td>
							<td class="2-td2-left">29</td>
							<td class="2-td2-center" colspan="2">
								<input type="text" name="col_29" id="col_29">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/inf_qy/col_29" />
									</xsl:attribute>
								</input>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">�������</td>
							<td class="2-td2-left">4</td>
							<td class="2-td2-left" colspan="2">
								<input type="text" name="col_4" id="col_4">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/inf_qy/col_4" />
									</xsl:attribute>
								</input>
							</td>
							<td class="2-td2-left" colspan="2">����ĸ���׼����</td>
							<td class="2-td2-left">30</td>
							<td class="2-td2-center" colspan="2">
								<input type="text" name="col_30" id="col_30">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/inf_qy/col_30" />
									</xsl:attribute>
								</input>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">�������</td>
							<td class="2-td2-left">5</td>
							<td class="2-td2-left" colspan="2">
								<input type="text" name="col_5" id="col_5">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/inf_qy/col_5" />
									</xsl:attribute>
								</input>
							</td>
							<td class="2-td2-left" colspan="2">Ͷ���߹���н��</td>
							<td class="2-td2-left">31</td>
							<td class="2-td2-center" colspan="2">
								<input type="text" name="col_31" id="col_31">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/inf_qy/col_31" />
									</xsl:attribute>
								</input>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">Ӫҵ˰�𼰸���</td>
							<td class="2-td2-left">6</td>
							<td class="2-td2-left" colspan="2">
								<input type="text" name="col_6" id="col_6">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/inf_qy/col_6" />
									</xsl:attribute>
								</input>
							</td>
							<td class="2-td2-left" colspan="2">�������޹ص�֧��</td>
							<td class="2-td2-left">32</td>
							<td class="2-td2-center" colspan="2">
								<input type="text" name="col_32" id="col_32">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/inf_qy/col_32" />
									</xsl:attribute>
								</input>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">Ӫҵ��֧��</td>
							<td class="2-td2-left">7</td>
							<td class="2-td2-left" colspan="2">
								<input type="text" name="col_7" id="col_7">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/inf_qy/col_7" />
									</xsl:attribute>
								</input>
							</td>
							<td class="2-td2-left" colspan="2">���У�Ͷ���߼�ͥ����</td>
							<td class="2-td2-left">33</td>
							<td class="2-td2-center" colspan="2">
								<input type="text" name="col_33" id="col_33">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/inf_qy/col_33" />
									</xsl:attribute>
								</input>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">���������ܶ�</td>
							<td class="2-td2-left">8=1-2-3-4-5-6-7</td>
							<td class="2-td2-left" colspan="2">
								<input type="text" name="col_8" id="col_8">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/inf_qy/col_8" />
									</xsl:attribute>
								</input>
							</td>
							<td class="2-td2-left" colspan="2">�ġ���˰�������ٶ�</td>
							<td class="2-td2-left">34</td>
							<td class="2-td2-center" colspan="2">
								<input type="text" name="col_34" id="col_34">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/inf_qy/col_34" />
									</xsl:attribute>
								</input>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">������˰�������Ӷ�</td>
							<td class="2-td2-left">9</td>
							<td class="2-td2-left" colspan="2">
								<input type="text" name="col_9" id="col_9">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/inf_qy/col_9" />
									</xsl:attribute>
								</input>
							</td>
							<td class="2-td2-left" colspan="2">1����ծ��Ϣ����</td>
							<td class="2-td2-left">35</td>
							<td class="2-td2-center" colspan="2">
								<input type="text" name="col_35" id="col_35">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/inf_qy/col_35" />
									</xsl:attribute>
								</input>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">1�������涨��׼�۳�����Ŀ</td>
							<td class="2-td2-left">10</td>
							<td class="2-td2-left" colspan="2">
								<input type="text" name="col_10" id="col_10">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/inf_qy/col_10" />
									</xsl:attribute>
								</input>
							</td>
							<td class="2-td2-left" colspan="2">2������</td>
							<td class="2-td2-left">36</td>
							<td class="2-td2-center" colspan="2">
								<input type="text" name="col_36" id="col_36">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/inf_qy/col_36" />
									</xsl:attribute>
								</input>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">��1��ְ��������</td>
							<td class="2-td2-left">11</td>
							<td class="2-td2-left" colspan="2">
								<input type="text" name="col_11" id="col_11">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/inf_qy/col_11" />
									</xsl:attribute>
								</input>
							</td>
							<td class="2-td2-left" colspan="2">�塢��ǰ����������</td>
							<td class="2-td2-left">37</td>
							<td class="2-td2-center" colspan="2">
								<input type="text" name="col_37" id="col_37">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/inf_qy/col_37" />
									</xsl:attribute>
								</input>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">��2��ְ����������</td>
							<td class="2-td2-left">12</td>
							<td class="2-td2-left" colspan="2">
								<input type="text" name="col_12" id="col_12">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/inf_qy/col_12" />
									</xsl:attribute>
								</input>
							</td>
							<td class="2-td2-left" colspan="2">��������˰�������������Ӫ����</td>
							<td class="2-td2-left">38=8+9-34-37</td>
							<td class="2-td2-center" colspan="2">
								<input type="text" name="col_38" id="col_38">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/inf_qy/col_38" />
									</xsl:attribute>
								</input>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">��3�����ᾭ��</td>
							<td class="2-td2-left">13</td>
							<td class="2-td2-left" colspan="2">
								<input type="text" name="col_13" id="col_13">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/inf_qy/col_13" />
									</xsl:attribute>
								</input>
							</td>
							<td class="2-td2-left" colspan="2">�����ֲ���ǰ��ȿ���</td>
							<td class="2-td2-left">39</td>
							<td class="2-td2-center" colspan="2">
								<input type="text" name="col_39" id="col_39">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/inf_qy/col_39" />
									</xsl:attribute>
								</input>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">��4����Ϣ֧��</td>
							<td class="2-td2-left">14</td>
							<td class="2-td2-left" colspan="2">
								<input type="text" name="col_14" id="col_14">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/inf_qy/col_14" />
									</xsl:attribute>
								</input>
							</td>
							<td class="2-td2-left" colspan="2">�ˣ��������</td>
							<td class="2-td2-left">40</td>
							<td class="2-td2-center" colspan="2">
								&#160;
								<input type="text" name="col_40" id="col_40">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/inf_gr/col_40" />
									</xsl:attribute>
								</input> %
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">��5��ҵ���д���</td>
							<td class="2-td2-left">15</td>
							<td class="2-td2-left" colspan="2">
								<input type="text" name="col_15" id="col_15">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/inf_qy/col_15" />
									</xsl:attribute>
								</input>
							</td>
							<td class="2-td2-left" colspan="2">�ߡ�����۳�����������</td>
							<td class="2-td2-left">41</td>
							<td class="2-td2-center" colspan="2">
								<input type="text" name="col_41" id="col_41">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/inf_gr/col_41" />
									</xsl:attribute>
								</input>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">��6�����Ѻ�ҵ��������</td>
							<td class="2-td2-left">16</td>
							<td class="2-td2-left" colspan="2">
								<input type="text" name="col_16" id="col_16">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/inf_qy/col_16" />
									</xsl:attribute>
								</input>
							</td>
							<td class="2-td2-left" colspan="2">�ˡ�Ͷ���߼�������</td>
							<td class="2-td2-left">42</td>
							<td class="2-td2-center" colspan="2">
								<input type="text" name="col_42" id="col_42">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/inf_gr/col_42" />
									</xsl:attribute>
								</input>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">��7�������͹�����ҵ����</td>
							<td class="2-td2-left">17</td>
							<td class="2-td2-left" colspan="2">
								<input type="text" name="col_17" id="col_17">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/inf_qy/col_17" />
									</xsl:attribute>
								</input>
							</td>
							<td class="2-td2-left" colspan="2">�š�Ӧ��˰���ö�</td>
							<td class="2-td2-left" id = "hc_43">43</td>
							<td class="2-td2-center" colspan="2">
								<input type="text" name="col_43" id="col_43">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/inf_gr/col_43" />
									</xsl:attribute>
								</input>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">��8��ס��������</td>
							<td class="2-td2-left">18</td>
							<td class="2-td2-left" colspan="2">
								<input type="text" name="col_18" id="col_18">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/inf_qy/col_18" />
									</xsl:attribute>
								</input>
							</td>
							<td class="2-td2-left" colspan="2">ʮ��˰�ʣ�%��</td>
							<td class="2-td2-left">44</td>
							<td class="2-td2-center" colspan="2">
								<input type="text" name="col_44" id="col_44">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/inf_gr/col_44" />
									</xsl:attribute>
								</input>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">��9����ᱣ�շ�</td>
							<td class="2-td2-left">19</td>
							<td class="2-td2-left" colspan="2">
								<input type="text" name="col_19" id="col_19">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/inf_qy/col_19" />
									</xsl:attribute>
								</input>
							</td>
							<td class="2-td2-left" colspan="2">ʮһ������۳���</td>
							<td class="2-td2-left">45</td>
							<td class="2-td2-center" colspan="2">
								<input type="text" name="col_45" id="col_45">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/inf_gr/col_45" />
									</xsl:attribute>
								</input>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">��10���۾ɷ���</td>
							<td class="2-td2-left">20</td>
							<td class="2-td2-left" colspan="2">
								<input type="text" name="col_20" id="col_20">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/inf_qy/col_20" />
									</xsl:attribute>
								</input>
							</td>
							<td class="2-td2-left" colspan="2">ʮ����Ӧ��˰��</td>
							<td class="2-td2-left">46��43*44-45</td>
							<td class="2-td2-center" colspan="2">
								<input type="text" name="col_46" id="col_46">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/inf_gr/col_46" />
									</xsl:attribute>
								</input>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">��11�������ʲ�̯��</td>
							<td class="2-td2-left">21</td>
							<td class="2-td2-left" colspan="2">
								<input type="text" name="col_21" id="col_21">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/inf_qy/col_21" />
									</xsl:attribute>
								</input>
							</td>
							<td class="2-td2-left" colspan="2">��������˰��</td>
							<td class="2-td2-left">47</td>
							<td class="2-td2-center" colspan="2">
								<input type="text" name="col_47" id="col_47">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/inf_gr/col_47" />
									</xsl:attribute>
								</input>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">��12���ʲ���ʧ</td>
							<td class="2-td2-left">22</td>
							<td class="2-td2-left" colspan="2">
								<input type="text" name="col_22" id="col_22">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/inf_qy/col_22" />
									</xsl:attribute>
								</input>
							</td>
							<td class="2-td2-left" colspan="2">ʮ����ȫ��Ӧ��˰��</td>
							<td class="2-td2-left">48=46-47</td>
							<td class="2-td2-center" colspan="2">
								<input type="text" name="col_48" id="col_48">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/inf_gr/col_48" />
									</xsl:attribute>
								</input>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">��13������</td>
							<td class="2-td2-left">23</td>
							<td class="2-td2-left" colspan="2">
								<input type="text" name="col_23" id="col_23">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/inf_qy/col_23" />
									</xsl:attribute>
								</input>
							</td>
							<td class="2-td2-left" colspan="2">�ӣ��ڳ�δ��˰��</td>
							<td class="2-td2-left">49</td>
							<td class="2-td2-center" colspan="2">
								<input type="text" name="col_49" id="col_49">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/inf_gr/col_49" />
									</xsl:attribute>
								</input>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">������۳�����Ŀ</td>
							<td class="2-td2-left">24</td>
							<td class="2-td2-left" colspan="2">
								<input type="text" name="col_24" id="col_24">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/inf_qy/col_24" />
									</xsl:attribute>
								</input>
							</td>
							<td class="2-td2-left" colspan="2">����ȫ����Ԥ��˰��</td>
							<td class="2-td2-left">50</td>
							<td class="2-td2-center" colspan="2">
								<input type="text" name="col_50" id="col_50">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/inf_gr/col_50" />
									</xsl:attribute>
								</input>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">�ʱ���֧��</td>
							<td class="2-td2-left">25</td>
							<td class="2-td2-left" colspan="2">
								<input type="text" name="col_25" id="col_25">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/inf_qy/col_25" />
									</xsl:attribute>
								</input>
							</td>
							<td class="2-td2-left" colspan="2">ʮ�ġ�Ӧ�����ˣ�˰��</td>
							<td class="2-td2-left">51=48+49-50</td>
							<td class="2-td2-center" colspan="2">
								<input type="text" name="col_51" id="col_51">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/inf_gr/col_51" />
									</xsl:attribute>
								</input>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">�����ʲ����á�����֧��</td>
							<td class="2-td2-left">26</td>
							<td class="2-td2-left" colspan="2">
								<input type="text" name="col_26" id="col_26">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/inf_qy/col_26" />
									</xsl:attribute>
								</input>
							</td>
							<td class="2-td2-left" colspan="2">&#160;</td>
							<td class="2-td2-left">&#160;</td>
							<td class="2-td2-center" colspan="2">&#160;</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</xsl:template >

</xsl:stylesheet>