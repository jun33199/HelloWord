<?xml version="1.0" encoding="gb2312"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:msxsl="urn:schemas-microsoft-com:xslt">
<xsl:template match="/">
<!--##########################################-->
	<table width="80%" border="2" align="center" cellpadding="0" cellspacing="4" bordercolor="#9DB9D2">
        <tr>
          <td height="50" bgcolor="#F0F0F0">
          <div align="center">
		  	<span class="black9">
				<font size="5" color="#999999">�л����񹲺͹�</font>
             </span>
		  </div>
          <p align="center">
			<font color="#ABC6CD">&#160;</font>
			<font color="#d32e2e"><u>&#160;&#160;<xsl:value-of select="taxdoc/sbsj/jks/szmc"/>&#160;&#160;</u></font>
			<font color="#2C556D">˰�սɿ���</font>
		  </p>
          <p align="center" class="black9"><font color="#999999"></font></p>
		  </td>
        </tr>
		<tr>
			<td>
				<table width="100%" cellpadding="4" cellspacing="0" class="black9" id="AutoNumber4" style="border-collapse: collapse">			
					<tr>
                		<td width="35%" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF">
							<font color="#2C556D">&#160;&#160;������ϵ��</font>
				  			<font color="#d32e2e"><xsl:value-of select="taxdoc/sbsj/lsgxmc"/></font>
						</td>
                		<td width="30%" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF">
							<font color="#2C556D">˰�����ͣ�</font>
							<font color="#d32e2e"><xsl:value-of select="taxdoc/sbsj/sklx"/></font>
						</td>
                		<td width="35%" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF">
							<font color="#2C556D">���Ա�ţ�</font>
							<font color="#d32e2e"><xsl:value-of select="taxdoc/sbsj/jks/jkpzh"/></font>
						</td>
              	   </tr>
              		<tr>
                		<td width="35%" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF">
							<font color="#2C556D">&#160;&#160;ע�����ͣ�</font>
							<font color="#d32e2e"><xsl:value-of select="taxdoc/sbsj/djzclxmc"/></font>
						</td>
                		<td width="30%" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF">
							<font color="#2C556D">����ڣ�</font>
							<font color="#d32e2e"><xsl:value-of select="taxdoc/sbsj/sbrq"/></font>
						</td>
                		<td width="35%" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF">
							<font color="#2C556D">���ջ��أ�</font>
							<font color="#d32e2e"><xsl:value-of select="taxdoc/sbsj/zsswjgzzjgmc"/></font>
						</td>
              		</tr>
            </table>
			
		<!--##########################################-->
			<table width="100%" height="102" border="2" cellpaddng="4" cellspacing="0" bordercolor="#9DB9D2" class="black9" id="AutoNumber3" style="border-collapse: collapse">
              <tr>
                <td width="35" rowspan="4" height="85"><div align="center"><font color="#2C556D">��<br/>
                    <br/>
                    ��<br/>
                    <br/>
                    ��<br/>
                    <br/>
                    λ<br/>
                    ��<br/>
                    ��<br/>
                    ��</font></div></td>
                <td width="86" height="16">
					<div align="center"><font color="#2C556D">��&#160;&#160;&#160;&#160;��</font></div>
                </td>
                <td width="89" height="16">
					<font color="#d32e2e"><xsl:value-of select="//jsjdm"/></font>
				</td>
                <td width="38" height="16">
					<font color="#2C556D">��&#160;��</font>
				</td>
                <td width="127" height="16">
					<font color="#d32e2e"><xsl:value-of select="taxdoc/sbsj/lxdh"/></font>
				</td>
                <td width="84" rowspan="3" height="51">
					<div align="center"><font color="#2C556D">Ԥ���Ŀ</font></div>
				</td>
                <td width="61" height="16">
					<div align="center"><font color="#2C556D">��&#160;��</font></div>
				</td>
                <td width="221" height="16">
					<font color="#d32e2e"><xsl:value-of select="taxdoc/sbsj/jks/yskmdm"/></font>
				</td>
              </tr>
              <tr>
                <td width="86" height="16">
					<div align="center"><font color="#2C556D">ȫ&#160;&#160;&#160;&#160;��</font></div>
				</td>
                <td width="256" colspan="3" height="16">
					<font color="#d32e2e"><xsl:value-of select="//nsrmc"/></font>
				</td>
                <td width="61" height="16">
					<div align="center"><font color="#2C556D">��&#160;��</font></div>
				</td>
                <td width="221" height="16">
					<font color="#d32e2e"><xsl:value-of select="taxdoc/sbsj/jks/yskmmc"/></font>
				</td>
              </tr>
              <tr>
                <td width="86" height="17">
					<div align="center"><font color="#2C556D">��������</font></div>
				</td>
                <td width="256" colspan="3" height="17">
					<font color="#d32e2e"><xsl:value-of select="taxdoc/sbsj/yhmc"/></font>
				</td>
                <td width="61" height="17">
					<div align="center"><font color="#2C556D">��&#160;��</font></div>
				</td>
                <td width="221" height="17">
					<font color="#d32e2e"><xsl:value-of select="taxdoc/sbsj/jks/ysjcmc"/></font>
				</td>
              </tr>
              <tr>
                <td width="86" height="33">
					<div align="center"><font color="#2C556D">��&#160;&#160;&#160;&#160;��</font></div>
				</td>
                <td width="256" colspan="3" height="33">
					<font color="#d32e2e"><xsl:value-of select="taxdoc/sbsj/zh"/></font>
				</td>
                <td width="84" height="33">
					<div align="center"><font color="#2C556D">�տ����</font></div>
				</td>
                <td width="283" colspan="2" height="33">
					<font color="#d32e2e">(<xsl:value-of select="taxdoc/sbsj/gkzzjgdm"/>)<xsl:value-of select="taxdoc/sbsj/gkzzjgmc"/></font>
				</td>
              </tr>
              <tr>
                <td width="379" colspan="5" height="16">
					<font color="#2C556D">˰������ʱ�ڣ�</font>
					<font color="#d32e2e"><xsl:value-of select="taxdoc/sbsj/jks/skssksrq"/>��<xsl:value-of select="taxdoc/sbsj/jks/skssjsrq"/></font>
				</td>
                <td width="368" colspan="3" height="16">
					<font color="#2C556D">˰���޽����ڣ�</font>
					<font color="#d32e2e"><xsl:value-of select="//xjrq"/></font>
				</td>
              </tr>
            </table>
			<!--##########################################-->
			<table width="100%" height="122" border="2" cellpadding="4" cellspacing="0" bordercolor="#9DB9D2" class="black9" id="AutoNumber2" style="border-collapse: collapse">
				  <tr>
					<td width="302" style="padding-top: 0" height="32" align="center"><font color="#2C556D">ƷĿ����</font></td>
					<td width="110" style="padding-top: 0" height="32" align="center"><font color="#2C556D">��˰����</font></td>
					<td width="145" style="padding-top: 0" height="32" align="center">
					  <p align="center"/><font color="#2C556D">��˰������������</font></td>
					<td width="127" style="padding-top: 0" height="32" align="center"><font color="#2C556D">ʵ��˰��</font></td>
				  </tr>
			 	 <xsl:apply-templates select="taxdoc/sbsj"/><!--##########################################-->
			 	 <tr>
				<td colspan="3" style="padding-top: 0" height="15">
					<font color="#2C556D">���ϼƣ���д����</font>
					<font color="#d32e2e"><xsl:value-of select="taxdoc/sbsj/jks/sjjedx"/></font>
				</td>
				<td width="127" style="padding-top: 0" height="15" align="right">
					<font color="#d32e2e">��<xsl:value-of select="taxdoc/sbsj/jks/sjje"/></font>
				</td>
			   </tr>
			</table>
			<!--##########################################-->
			<table width="100%" border="2" cellpadding="4" cellspacing="0" bordercolor="#9DB9D2" class="black9" id="AutoNumber5" style="border-collapse: collapse">
              <tr>
                <td width="20%">
				  <font color="#2C556D">�ɿλ���ˣ��� </font>
                  <p><font color="#d32e2e"><xsl:value-of select="//nsrmc"/></font></p>
                  <p><font color="#2C556D">�����ˣ��£� &#160;</font></p>
				</td>
                <td width="20%">
					<font color="#2C556D">�ط�˰����أ� </font>
                  	<p><font color="#d32e2e"><xsl:value-of select="taxdoc/sbsj/swjgzzjgmc"/></font></p>
                  	<p/><font color="#2C556D">��Ʊ�ˣ�</font>
					<font color="#d32e2e"><xsl:value-of select="//nsrmc"/></font>
				</td>
                <td width="60%">
					<font color="#2C556D">��ע��<xsl:value-of select="taxdoc/sbsj/jks/bz"/></font>
			    </td>
              </tr>
            </table> 
			</td>
		</tr>
		</table>
<!--##########################################-->		
</xsl:template>
	
<!--##########################################-->
<xsl:template match="taxdoc/sbsj">
	<xsl:for-each select="smitem">
		 <tr>
                <td width="302" style="padding-top: 0" height="16">
					<font color="#d32e2e"><xsl:value-of select="szsmdm"/>&#160;<xsl:value-of select="szsmmc"/></font>
				</td>
                <td width="110" style="padding-top: 0" height="16">
					<div align="right"><font color="#d32e2e"><xsl:value-of select="kssl"/></font></div>
				</td>
                <td width="145" style="padding-top: 0" height="16">
					<div align="right"><font color="#d32e2e"><xsl:value-of select="jsje"/></font></div>
				</td>
                <td width="127" style="padding-top: 0" height="16">
					<div align="right"><font color="#d32e2e"><xsl:value-of select="sjse"/></font></div>
				</td>
          </tr>
	</xsl:for-each>
			
</xsl:template>

</xsl:stylesheet>    
		   
		   
		   
		   
		   
	