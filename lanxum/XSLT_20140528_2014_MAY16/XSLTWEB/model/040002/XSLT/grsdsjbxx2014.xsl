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
				<td class="1-td1" colspan="2">¼���������˰������Ϣ����Ϣ</td>
			</tr>

			<tr>
				<td class="1-td2" colspan="2">
					<br/>

				
					<table  cellSpacing="0" cellPadding="0" width="97%" border="0" align = "center">
						<tr>
							<td class="2-td2-t-left" width="10%" ><!--����  �����޸�-->
								����
							</td>
							<td class="2-td2-t-left" width="10%">
								<input type="text" name="grxx_xm" id="grxx_xm" style="width:80%">
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/grxx/grxx_xm" />
									</xsl:attribute>
								</input>
							</td>
							<td class="2-td2-t-left" width="10%"><!--���֤������ �����޸�-->
								���֤������
							</td>
							<td class="2-td2-t-left" width="10%">
							    
								<select name="grxx_sfzjlx" id="grxx_sfzjlx" disabled="true" >
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/grxx/grxx_sfzjlx" />
									</xsl:attribute>
								</select>
							</td>
							<td class="2-td2-t-left"  width="10%"><!--���֤������ �����޸�-->
								���֤������
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
							<td class="2-td2-left"  width="10%"><!--��˰������-->
								��˰������
							</td>
							<td class="2-td2-center"  width="90%" colspan="9">
								<input name="grxx_nsrlx" type="hidden" id="grxx_nsrlx">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/grxx/grxx_nsrlx" />
									</xsl:attribute>
								</input>
								<input type='checkbox' name='grxx_nsrlx_cb' id="grxx_nsrlx0" value="0">����ְ�ܹ͵�λ</input>&#160;&#160;&#160;&#160;
								<input type='checkbox' name='grxx_nsrlx_cb' id="grxx_nsrlx1" value="1">Ͷ����</input>&#160;&#160;&#160;&#160;
								<input type='checkbox' name='grxx_nsrlx_cb' id="grxx_nsrlx2" value="2">����ְ�ܹ͵�λ�������ɶ�Ͷ���ߣ�</input>&#160;&#160;&#160;&#160;
								<input type='checkbox' name='grxx_nsrlx_cb' id="grxx_nsrlx3" value="3">��ס������</input>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left"  width="10%"><!--��ְ�ܹ͵�λ����-->
								��ְ�ܹ͵�λ����
							</td>
							<td class="2-td2-left"  width="60%" colspan="5">
								<input type="text" name="grxx_rzsgdwmc" id="grxx_rzsgdwmc" >
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/grxx/grxx_rzsgdwmc" />
									</xsl:attribute>
								</input>
							</td>
							<td class="2-td2-left"  width="10%"><!--��ְ�ܹ͵�λ��˰��ʶ���-->
								��ְ�ܹ͵�λ��˰��ʶ���
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
							<td class="2-td2-left" ><!--����һ��������-->
								������һ�𡱽������
							</td>
							<td class="2-td2-left" width="60%" colspan="5">
								<input name="grxx_sfyjjnqk" type="hidden" id="grxx_sfyjjnqk">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/grxx/grxx_sfyjjnqk" />
									</xsl:attribute>
								</input>
								<input type='checkbox' name='grxx_sfyjjnqk_cb' id="grxx_sfyjjnqk_0" value="0">�������ϱ��շ�</input>
								<input type='checkbox' name='grxx_sfyjjnqk_cb' id="grxx_sfyjjnqk_1" value="1">����ҽ�Ʊ��շ�</input>
								<input type='checkbox' name='grxx_sfyjjnqk_cb' id="grxx_sfyjjnqk_2" value="2">ʧҵ���շ�</input>
								<input type='checkbox' name='grxx_sfyjjnqk_cb' id="grxx_sfyjjnqk_3" value="3">ס��������</input>
							</td>
							<td class="2-td2-left"  width="10%"><!--��������-->
								��������
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
							<td class="2-td2-left" ><!--������ϵ��ַ-->
								������ϵ��ַ
							</td>
							<td class="2-td2-left" width="60%" colspan="5">
								<input type="text" name="grxx_jnlxdz" id="grxx_jnlxdz">
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/grxx/grxx_jnlxdz" />
									</xsl:attribute>
								</input>
							</td>
							<td class="2-td2-left"  width="10%"><!--��������-->
								��������
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
							<td class="2-td2-left" ><!--��ϵ�绰-->
								��ϵ�绰
							</td>
							<td class="2-td2-left" width="60%" colspan="5">
								<input type="text" name="grxx_lxdh" id="grxx_lxdh" >
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/grxx/grxx_lxdh" />
									</xsl:attribute>
								</input>
							</td>
							<td class="2-td2-left"  width="10%"><!--��������-->
								ְҵ
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
							<td class="2-td2-left" ><!--��ϵ�绰-->
								ְ��
							</td>
							<td class="2-td2-left" width="60%" colspan="5">
								<select name="grxx_zw" id="grxx_zw">
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/grxx/grxx_zw" />
									</xsl:attribute>
									<option value="1">�߲�</option>
									<option value="2">�в�</option>
									<option value="3">��ͨ</option>
								</select>
							</td>
							<td class="2-td2-left"  width="10%"><!--��������-->
								ѧ��
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
							<td class="2-td2-left"><!--�Ƿ�м���/����/����-->
								�Ƿ�м���/����/����
							</td>
							<td class="2-td2-left" colspan="5">
								<input name="grxx_sfcjrlsgl" type="hidden" id="grxx_sfcjrlsgl">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/grxx/grxx_sfcjrlsgl" />
									</xsl:attribute>
								</input>
								<input type='checkbox' name='grxx_sfcjrlsgl_cb' id="grxx_sfcjrlsg_0" value="0">�м�</input>
								<input type='checkbox' name='grxx_sfcjrlsgl_cb' id="grxx_sfcjrlsg_1" value="1">����</input>
								<input type='checkbox' name='grxx_sfcjrlsgl_cb' id="grxx_sfcjrlsg_2" value="2">����</input>
								<input type='checkbox' name='grxx_sfcjrlsgl_cb' id="grxx_sfcjrlsg_3" value="3">��</input>
							</td>
							<td class="2-td2-left"><!--�м��ȼ����-->
								�м��ȼ����
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
							<td class="2-td2-left"><!-- ���������о���������˰����д -->
								���������о���������˰����д
							</td>
							<td class="2-td2-left" colspan="2">
								<select name="grxx_yjwsd_dzlx" id="grxx_yjwsd_dzlx">
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/grxx/grxx_yjwsd_dzlx" />
									</xsl:attribute>
									<option value="0">�������ڵ�</option>
									<option value="1">������ס��</option>
								</select>
							</td>
							<td class="2-td2-left">
								��������
							</td>
							<td class="2-td2-left" colspan="2"><!--�м��ȼ����-->
								<input type="text" name="grxx_yjwsd_yzbm" id="grxx_yjwsd_yzbm">
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/grxx/grxx_yjwsd_yzbm" />
									</xsl:attribute>
								</input>
							</td>
							<td class="2-td2-left">
								��ַ
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
							<td class="2-td2-left" rowspan="5"><!--��������Ͷ������˰����д-->
								��������Ͷ������˰����д
							</td>
							<td class="2-td2-left" colspan="2"><!--��������Ͷ������˰����д-->
								Ͷ��������
							</td>
							<td class="2-td2-center" colspan="7">
								<input name="grxx_tzzlx" type="hidden" id="grxx_tzzlx">
									<xsl:attribute name="value">
										<xsl:value-of select="taxdoc/grxx/grxx_tzzlx" />
									</xsl:attribute>
								</input>
								<input type='checkbox' name='grxx_tzzlx_cb' id="grxx_tzzlx_0" value="0">���幤�̻�</input>
								<input type='checkbox' name='grxx_tzzlx_cb' id="grxx_tzzlx_1" value="1">���˶�����ҵͶ����</input>
								<input type='checkbox' name='grxx_tzzlx_cb' id="grxx_tzzlx_2" value="2">�ϻ���ҵ�ϻ���</input>
								<input type='checkbox' name='grxx_tzzlx_cb' id="grxx_tzzlx_3" value="3">�а������⾭Ӫ��</input>
								<input type='checkbox' name='grxx_tzzlx_cb' id="grxx_tzzlx_4" value="4">�ɶ�</input>
								<input type='checkbox' name='grxx_tzzlx_cb' id="grxx_tzzlx_5" value="5">����Ͷ����</input>
							</td>
						</tr>

						<tr>
							<td class="2-td2-left" rowspan="4"><!--��Ͷ�ʵ�λ��Ϣ-->
								��Ͷ�ʵ�λ��Ϣ
							</td>
							<td class="2-td2-left" ><!--����-->
								����
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
							<td class="2-td2-left" ><!--�۽������˱���-->
								�۽������˱���
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
							<td class="2-td2-left" ><!--����-->
								��ַ
							</td>
							<td class="2-td2-left" colspan="3">
								<input type="text" name="qyxx_dz" id="qyxx_dz">
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/qyxxvo/qyxx_dz" />
									</xsl:attribute>
								</input>
							</td>
							<td class="2-td2-left" ><!--�۽������˱���-->
								��������
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
							<td class="2-td2-left" ><!--����-->
								�Ǽ�ע������
							</td>
							<td class="2-td2-left" colspan="3">
								<select  name="qyxx_djzclx" id="qyxx_djzclx">
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/qyxxvo/qyxx_djzclx" />
									</xsl:attribute>
								</select>
							</td>
							<td class="2-td2-left" ><!--�۽������˱���-->
								��ҵ
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
							<td class="2-td2-left" ><!--����-->
								����˰���շ�ʽ
							</td>
							<td class="2-td2-left" colspan="3">
								<select name="qyxx_sdszsfs" id="qyxx_sdszsfs">
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/qyxxvo/qyxx_sdszsfs" />
									</xsl:attribute>
									<option value="0">��������</option>
									<option value="1">�˶�����</option>
								</select>
							</td>
							<td class="2-td2-left" ><!--�۽������˱���-->
								����˰�����
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
							<td class="2-td2-left" rowspan="13"><!--����-->
								����������ס����˰����д
							</td>
							<td class="2-td2-left" colspan="2"><!---->
								��˰��ʶ���
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
							<td class="2-td2-left" colspan="2" ><!--����-->
								����
							</td>
							<td class="2-td2-left" colspan="3">
								<select  name="grxx_wzsnsr_gj" id="grxx_wzsnsr_gj">
									
								</select>
							</td>
							<td class="2-td2-left"><!--������-->
								������
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
							<td class="2-td2-left" colspan="2"><!--�Ա�-->
								�Ա�
							</td>
							<td class="2-td2-left" colspan="3">
								
								<select  name="grxx_wzsnsr_xb" id="grxx_wzsnsr_xb">
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/grxx/grxx_wzsnsr_xb" />
									</xsl:attribute>
									<option value="0">��</option>
									<option value="1">Ů</option>
								</select>
							</td>
							<td class="2-td2-left"><!--��������-->
								��������
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
							<td class="2-td2-left" colspan="2"><!--�Ͷ���ҵ֤����-->
								�Ͷ���ҵ֤����
							</td>
							<td class="2-td2-left" colspan="3">
								<input type="text" name="grxx_wzsnsr_ldjyzhm" id="grxx_wzsnsr_ldjyzhm">
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/grxx/grxx_wzsnsr_ldjyzhm" />
									</xsl:attribute>
								</input>
							</td>
							<td class="2-td2-left"><!--�Ƿ�˰��Э����Լ���Է�����-->
								�Ƿ�˰��Э����Լ���Է�����
							</td>
							<td class="2-td2-center" colspan="3">
								<select name="grxx_wzsnsr_sfssxddygdfjm" id="grxx_wzsnsr_sfssxddygdfjm" >
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/grxx/grxx_wzsnsr_sfssxddygdfjm" />
									</xsl:attribute>
									<option value="0">��</option>
									<option value="1">��</option>
								</select>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2"><!--����ְ��-->
								����ְ��
							</td>
							<td class="2-td2-left" colspan="3">
								<input type="text" name="grxx_wzsnsr_jnzw" id="grxx_wzsnsr_jnzw">
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/grxx/grxx_wzsnsr_jnzw" />
									</xsl:attribute>
								</input>
							</td>
							<td class="2-td2-left"><!--����ְ��-->
								����ְ��
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
							<td class="2-td2-left" colspan="2"><!--����ʱ��-->
								����ʱ��
							</td>
							<td class="2-td2-left" colspan="3">
								<input type="text" name="grxx_wzsnsr_lhsj" id="grxx_wzsnsr_lhsj">
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/grxx/grxx_wzsnsr_lhsj" />
									</xsl:attribute>
								</input>
							</td>
							<td class="2-td2-left"><!--��ְ����-->
								��ְ����
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
							<td class="2-td2-left" colspan="2"><!--Ԥ���뾳ʱ��-->
								Ԥ���뾳ʱ��
							</td>
							<td class="2-td2-left" colspan="3">
								<input type="text" name="grxx_wzsnsr_yjljsj" id="grxx_wzsnsr_yjljsj">
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/grxx/grxx_wzsnsr_yjljsj" />
									</xsl:attribute>
								</input>
							</td>
							<td class="2-td2-left"><!--Ԥ���뾳�ص�-->
								Ԥ���뾳�ص�
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
							<td class="2-td2-left" rowspan="2" ><!--������ְ�ܹ͵�λ-->
								������ְ�ܹ͵�λ
							</td>
							<td class="2-td2-left"><!--����-->
								����
							</td>
							<td class="2-td2-left" colspan="3">
								<input type="text" name="grxx_wzsnsr_jnrzsgdw_mc" id="grxx_wzsnsr_jnrzsgdw_mc">
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/grxx/grxx_wzsnsr_jnrzsgdw_mc" />
									</xsl:attribute>
								</input>
							</td>
							<td class="2-td2-left"><!--�۽������˱���-->
								�۽������˱���
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
							<td class="2-td2-left" ><!--��ַ-->
								��ַ
							</td>
							<td class="2-td2-left" colspan="3">
								<input type="text" name="grxx_wzsnsr_jnrzsgdw_dz" id="grxx_wzsnsr_jnrzsgdw_dz">
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/grxx/grxx_wzsnsr_jnrzsgdw_dz" />
									</xsl:attribute>
								</input>
							</td>
							<td class="2-td2-left"><!--��������-->
								��������
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
							<td class="2-td2-left" rowspan="2" ><!--������ƸǩԼ��λ-->
								������ƸǩԼ��λ
							</td>
							<td class="2-td2-left" ><!--����-->
								����
							</td>
							<td class="2-td2-left"  colspan="3">
								<input type="text" name="grxx_wzsnsr_jnspqydw_mc" id="grxx_wzsnsr_jnspqydw_mc">
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/grxx/grxx_wzsnsr_jnspqydw_mc" />
									</xsl:attribute>
								</input>
							</td>
							<td class="2-td2-left"><!--�۽������˱���-->
								�۽������˱���
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
							<td class="2-td2-left" ><!--��ַ-->
								��ַ
							</td>
							<td class="2-td2-left"  colspan="3">
								<input type="text" name="grxx_wzsnsr_jnspqydw_dz" id="grxx_wzsnsr_jnspqydw_dz">
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/grxx/grxx_wzsnsr_jnspqydw_dz" />
									</xsl:attribute>
								</input>
							</td>
							<td class="2-td2-left"><!--��������-->
								��������
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
							<td class="2-td2-left" ><!--������ǲ��λ-->
								������ǲ��λ
							</td>
							<td class="2-td2-left" ><!--����-->
								����
							</td>
							<td class="2-td2-left"  colspan="3">
								<input type="text" name="grxx_wzsnsr_jwpqdw_mc" id="grxx_wzsnsr_jwpqdw_mc">
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/grxx/grxx_wzsnsr_jwpqdw_mc" />
									</xsl:attribute>
								</input>
							</td>
							<td class="2-td2-left"><!--��ַ-->
								��ַ
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
							<td class="2-td2-left" colspan="2"><!--֧����-->
								֧����
							</td>
							<td class="2-td2-left" colspan="3">
								<select name="grxx_wzsnsr_zfd" id="grxx_wzsnsr_zfd" >
									<xsl:attribute name="value">
											<xsl:value-of select="taxdoc/grxx/grxx_wzsnsr_zfd" />
									</xsl:attribute>
									<option value="0">����֧��</option>
									<option value="1">����֧��</option>
									<option value="2">���ڡ���ͬʱ֧��</option>
								</select>
							</td>
							<td class="2-td2-left"><!--����֧��������-->
								����֧��������
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