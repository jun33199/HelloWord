<?xml version="1.0" encoding="gb2312"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" />
<xsl:template match="/">

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
	<input name="tbrq" type="hidden" id="tbrq">
		<xsl:attribute name="value">
			<xsl:value-of select="taxdoc/tbrq" />
		</xsl:attribute>
	</input>
	<input name="tbrqShow" type="hidden" id="tbrqShow">
		<xsl:attribute name="value">
			<xsl:value-of select="taxdoc/tbrqShow" />
		</xsl:attribute>
	</input>
	<input name="badjbxh" type="hidden" id="badjbxh">
		<xsl:attribute name="value">
			<xsl:value-of select="taxdoc/badjbxh" />
		</xsl:attribute>
	</input>
	<input name="modifyFlag" type="hidden" id="modifyFlag">
		<xsl:attribute name="value">
			<xsl:value-of select="taxdoc/modifyFlag" />
		</xsl:attribute>
	</input>
	<table class="table-99" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td class="1-t-td2" colspan="9">��ţ�&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
            &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
            &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
            &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
            &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
            &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
            �����:<xsl:value-of select="//tbrqShow" />

        </td>
    </tr>
	</table>
	<xsl:apply-templates select="taxdoc/kjywrxx" />
	<xsl:apply-templates select="taxdoc/fjmqyxx" />
	<xsl:apply-templates select="taxdoc/htxx" />
</xsl:template>
<xsl:template match="taxdoc/kjywrxx">
    <table class="table-99" border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td width="10%" rowspan="6" class="2-td2-left">�۽�������</td>
            <td width="20%" class="2-td2-left-qysds1">��������룺</td>
            <td colspan="7" class="2-td2-center">
                <div align="left">
                    &#160;<xsl:value-of select="kjrjsjdm" />
					<input name="kjrjsjdm" type="hidden" id="kjrjsjdm">
						<xsl:attribute name="value">
							<xsl:value-of select="kjrjsjdm" />
						</xsl:attribute>
					</input>
                </div>
            </td>
        </tr>
        <tr>
            <td class="2-td2-left-qysds1">�������ƣ�</td>
            <td colspan="7" class="2-td2-center">
                <div align="left">
                    &#160;<xsl:value-of select="kjrmc_cn" />
					<input name="kjrmc_cn" type="hidden" id="kjrmc_cn">
						<xsl:attribute name="value">
							<xsl:value-of select="kjrmc_cn" />
						</xsl:attribute>
					</input>
                </div>
            </td>
        </tr>
        <tr>
            <td class="2-td2-left-qysds1">Ӣ�����ƣ�</td>
            <td colspan="7" class="2-td2-center">
                <div align="left">&#160;
                    <input type='text' name='kjrmc_en' id='kjywrmc_en' size='80' onfocus="this.select()">
                        <xsl:attribute name="value">
                            <xsl:value-of select="kjrmc_en" />
                        </xsl:attribute>
                    </input>
                </div>
            </td>
        </tr>
        <tr>
            <td class="2-td2-left-qysds1">�۽���������˰ʶ��ţ�</td>
            <td colspan="7" class="2-td2-center">
                <div align="left">
                    &#160;<xsl:value-of select="kjrnssbh" />
					<input name="kjrnssbh" type="hidden" id="kjrnssbh">
						<xsl:attribute name="value">
							<xsl:value-of select="kjrnssbh" />
						</xsl:attribute>
					</input>
                </div>
            </td>
        </tr>
        <tr>
            <td class="2-td2-left-qysds1">��ַ��</td>
            <td colspan="3" class="2-td2-left">
                <div align="left">
                    &#160;<xsl:value-of select="kjrdz_cn" />
					<input name="kjrdz_cn" type="hidden" id="kjrdz_cn">
						<xsl:attribute name="value">
							<xsl:value-of select="kjrdz_cn" />
						</xsl:attribute>
					</input>
                </div>
            </td>
            <td width="9%" class="2-td2-left">�ʱࣺ</td>
            <td colspan="3" class="2-td2-center">
                <div align="left">
                    &#160;<xsl:value-of select="kjryzbm" />
					<input name="kjryzbm" type="hidden" id="kjryzbm">
						<xsl:attribute name="value">
							<xsl:value-of select="kjryzbm" />
						</xsl:attribute>
					</input>
                </div>
            </td>
        </tr>
        <tr>
            <td class="2-td2-left-qysds1">�������ˣ�</td>
            <td width="15%" class="2-td2-left">
                <div align="left">&#160;
                    <input type='text' name='kjrcwfzr' id='kjywrcwfzr' size='18' onfocus="this.select()" >
                        <xsl:attribute name="value">
                            <xsl:value-of select="kjrcwfzr" />
                        </xsl:attribute>
                    </input>
                </div>
            </td>
            <td width="7%" class="2-td2-left">��ϵ�ˣ�</td>
            <td width="15%" class="2-td2-left">
                <div align="left">&#160;
                    <input type='text' name='kjrlxr' id='kjywrlxr' size='18' onfocus="this.select()" >
                        <xsl:attribute name="value">
                            <xsl:value-of select="kjrlxr" />
                        </xsl:attribute>
                    </input>
                </div>
            </td>
            <td class="2-td2-left">�绰��</td>
            <td width="8%" class="2-td2-left">
                <div align="left">&#160;
                    <input type='text' name='kjrlxdh' id='kjywrdh' size='15' onfocus="this.select()" >
                        <xsl:attribute name="value">
                            <xsl:value-of select="kjrlxdh" />
                        </xsl:attribute>
                    </input>
                </div>
            </td>
            <td width="7%" class="2-td2-left">���棺</td>
            <td width="8%" class="2-td2-center">
                <div align="left">&#160;
                    <input type='text' name='kjrczhm' id='kjywrcz' size='15' onfocus="this.select()" >
                        <xsl:attribute name="value">
                            <xsl:value-of select="kjrczhm" />
                        </xsl:attribute>
                    </input>
                </div>
            </td>
        </tr>
    </table>
</xsl:template>
<xsl:template match="taxdoc/fjmqyxx">
    <table class="table-99" border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td width="10%" rowspan="6" class="2-td2-left">�Ǿ�����ҵ</td>
            <td width="20%" class="2-td2-left-qysds1">�������ƣ�</td>
            <td colspan="7" class="2-td2-center">
                <div align="left">&#160;
                    <input type='text' name='fjmmc_cn' id='fjmqymc_cn' size='80' onfocus="this.select()" >
                        <xsl:attribute name="value">
                            <xsl:value-of select="fjmmc_cn" />
                        </xsl:attribute>
                    </input>
                </div>
            </td>
        </tr>
        <tr>
            <td class="2-td2-left-qysds1">Ӣ�����ƣ�</td>
            <td colspan="7" class="2-td2-center">
                <div align="left">&#160;
                    <input type='text' name='fjmmc_en' id='fjmqymc_en' size='80' onfocus="this.select()" >
                        <xsl:attribute name="value">
                            <xsl:value-of select="fjmmc_en" />
                        </xsl:attribute>
                    </input>
                </div>
            </td>
        </tr>
        <tr>
            <td class="2-td2-left-qysds1">����</td>
            <td colspan="2" class="2-td2-left">
                <div align="left">&#160;
                    <select name='fjmgb' id='fjmgbSelect' onChange="changeRadio(this.value)">
                        <xsl:attribute name="value">
                            <xsl:value-of select="fjmgb" />
                        </xsl:attribute>
                    </select>
					<input type='hidden' id='fjmgbdm'>
                        <xsl:attribute name="value">
                            <xsl:value-of select="fjmgb" />
                        </xsl:attribute>
                    </input>
                </div>
            </td>
            <td class="2-td2-left">���һ������</td>
            <td colspan="4" class="2-td2-center">
                �۰�̨<input type="radio" name="fjmgjdq" id="gat" value="01" disabled="true"/>
                &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
                ���<input type="radio" name="fjmgjdq" id="wg" value="02" disabled="true"/>
            </td>
        </tr>
        <tr>
            <td class="2-td2-left-qysds1">��������ַ�����ģ���</td>
            <td colspan="7" class="2-td2-center">
                <div align="left">&#160;
                    <input type='text' name='fjmdz_cn' id='fjmqyjmgdz_cn' size='80' onfocus="this.select()" >
                        <xsl:attribute name="value">
                            <xsl:value-of select="fjmdz_cn" />
                        </xsl:attribute>
                    </input>
                </div>
            </td>
        </tr>
        <tr>
            <td class="2-td2-left-qysds1">��������ַ��Ӣ�ģ���</td>
            <td colspan="7" class="2-td2-center">
                <div align="left">&#160;
                    <input type='text' name='fjmdz_en' id='fjmqyjmgdz_en' size='80' onfocus="this.select()" >
                        <xsl:attribute name="value">
                            <xsl:value-of select="fjmdz_en" />
                        </xsl:attribute>
                    </input>
                </div>
            </td>
        </tr>
        <tr>
            <td class="2-td2-left-qysds1">�������ˣ�</td>
            <td width="15%" class="2-td2-left">
                <div align="left">&#160;
                    <input type='text' name='fjmcwfzr' id='fjmqycwfzr' size='18' onfocus="this.select()" >
                        <xsl:attribute name="value">
                            <xsl:value-of select="fjmcwfzr" />
                        </xsl:attribute>
                    </input>
                </div>
            </td>
            <td width="7%" class="2-td2-left">��ϵ�ˣ�</td>
            <td width="15%" class="2-td2-left">
                <div align="left">&#160;
                    <input type='text' name='fjmlxr' id='fjmqylxr' size='18' onfocus="this.select()" >
                        <xsl:attribute name="value">
                            <xsl:value-of select="fjmlxr" />
                        </xsl:attribute>
                    </input>
                </div>
            </td>
            <td width="9%" class="2-td2-left">�绰��</td>
            <td width="8%" class="2-td2-left">
                <div align="left">&#160;
                    <input type='text' name='fjmlxdh' id='fjmqydh' size='15' onfocus="this.select()">
                        <xsl:attribute name="value">
                            <xsl:value-of select="fjmlxdh" />
                        </xsl:attribute>
                    </input>
                </div>
            </td>
            <td width="7%" class="2-td2-left">���棺</td>
            <td width="8%" class="2-td2-center">
                <div align="left">&#160;
                    <input type='text' name='fjmczhm' id='fjmqycz' size='15' onfocus="this.select()" >
                        <xsl:attribute name="value">
                            <xsl:value-of select="fjmczhm" />
                        </xsl:attribute>
                    </input>
                </div>
            </td>
        </tr>
    </table>
</xsl:template>
<xsl:template match="taxdoc/htxx">
    <table class="table-99" border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td width="8%" rowspan="9" class="2-td2-left">��ͬ��Ϣ</td>
            <td width="18%" class="2-td2-left-qysds1">��ͬ��Э�����ƣ� </td>
            <td width="74%" class="2-td2-center">
                <div align="left">&#160;
                    <input type='text' name='htmc' id='htxymc' size='80' onfocus="this.select()" >
                        <xsl:attribute name="value">
                            <xsl:value-of select="htmc" />
                        </xsl:attribute>
                    </input>
                </div>
            </td>
        </tr>
        <tr>
            <td class="2-td2-left-qysds1">��ͬ��ţ�</td>
            <td class="2-td2-center">
                <div align="left">&#160;
                    <input type='text' name='htbh' id='htxybh' size='80' onfocus="this.select()" >
                        <xsl:attribute name="value">
                            <xsl:value-of select="htbh" />
                        </xsl:attribute>
                    </input>
                </div>
            </td>
        </tr>
        <tr>
            <td class="2-td2-left-qysds1">��ͬǩԼ���ڣ� </td>
            <td class="2-td2-center">
                <div align="left">&#160;
                    <input type='text' name='qyrq' id='htqyrq' size='70' onfocus="this.select()" onblur="checkDateInput(this);">
                        <xsl:attribute name="value">
                            <xsl:value-of select="qyrq" />
                        </xsl:attribute>
                    </input>
					&#160;<font color="red">��������д��ʽ��YYYYMMDD��</font>
                </div>
            </td>
        </tr>
        <tr>
            <td class="2-td2-left-qysds1">��ͬ��Ч���ޣ�</td>
            <td class="2-td2-center">
                <div align="left">&#160;
                    <input type='text' name='htyxq' id='htyxqx' size='70' onfocus="this.select()" onblur="checkDateInput(this)">
                        <xsl:attribute name="value">
                            <xsl:value-of select="htyxq" />
                        </xsl:attribute>
                    </input>
					&#160;<font color="red">��������д��ʽ��YYYYMMDD��</font>
                </div>
            </td>
        </tr>
        <tr>
            <td class="2-td2-left-qysds1">��ͬ��</td>
            <td class="2-td2-center">
                <div align="left">&#160;
                    <input type='text' name='htje' id='htzje' size='80' onfocus="this.select()" onblur="checkNumInput(this, 1)">
                        <xsl:attribute name="value">
                            <xsl:value-of select="htje" />
                        </xsl:attribute>
                    </input>
                </div>
            </td>
        </tr>
        <tr>
            <td class="2-td2-left-qysds1">���֣�</td>
            <td class="2-td2-center">
                <div align="left">&#160;
                    <select name='bz' id='bzSelect'>
                        <xsl:attribute name="value">
                            <xsl:value-of select="bz" />
                        </xsl:attribute>
                    </select>
					<input type='hidden' id='bzdm'>
                        <xsl:attribute name="value">
                            <xsl:value-of select="bz" />
                        </xsl:attribute>
                    </input>
                </div>
            </td>
        </tr>
        <tr>
            <td class="2-td2-left-qysds1">֧����Ŀ��</td>
            <td class="2-td2-center">
                <div align="left">&#160;
                    <input type='text' name='zfxm' id='htzfxm' size='80' onfocus="this.select()" >
                        <xsl:attribute name="value">
                            <xsl:value-of select="zfxm" />
                        </xsl:attribute>
                    </input>
                </div>
            </td>
        </tr>
        <tr>
            <td class="2-td2-left-qysds1">���������</td>
            <td class="2-td2-center">
                <div align="left">&#160;
                    <input type='text' name='fkcs' id='htfkcs' size='80' onfocus="this.select()" onblur="checkNumInput(this, 2)">
                        <xsl:attribute name="value">
                            <xsl:value-of select="fkcs" />
                        </xsl:attribute>
                    </input>
                </div>
            </td>
        </tr>
        <tr>
            <td class="2-td2-left-qysds1">�����������ƣ�</td>
            <td class="2-td2-center">
                <div align="left">&#160;
                    <textarea name='qtzlmc' rows="3" cols="80%" id='htqtzlmc' onfocus="this.select()" >
						<xsl:value-of select="qtzlmc" />
                    </textarea>
                </div>
            </td>
        </tr>
    </table>
</xsl:template>
</xsl:stylesheet>
