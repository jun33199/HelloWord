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
				<font size="5" color="#999999">�� �� �� �� �� �� ��<br/>˰�սɿ���(���о���ר��)</font>
             </span>
		  </div>
					</td>
        </tr>
		<tr>
			<td>
				<table width="100%" cellpadding="4" cellspacing="0" class="black9" id="AutoNumber4" style="border-collapse: collapse">
				
						<tr>
                		<td width="35%" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF">
							<font color="#2C556D">&#160;&#160;˰�����ͣ�</font>
				  			<font color="#d32e2e"><xsl:value-of select="taxdoc/sbsj/sklx"/></font>
						</td>
                		<td width="30%" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF">
							<font color="#2C556D">���Ա�ţ�</font>
							<font color="#d32e2e"><xsl:value-of select="taxdoc/sbsj/jks/jkpzh"/></font>
						</td>
                		<td width="35%" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF">
							<font color="#2C556D"></font>
							<font color="#d32e2e"></font>
						</td>
              	   </tr>
						<tr>
                		<td width="35%" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF">
							<font color="#2C556D">&#160;&#160;�Ǽ�ע�����ͣ�</font>
				  			<font color="#d32e2e"><xsl:value-of select="taxdoc/sbsj/djzclxmc"/></font>
						</td>
                		<td width="30%" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF">
							<font color="#2C556D">����ڣ�</font>
							<font color="#d32e2e"><xsl:value-of select="taxdoc/sbsj/sbrqn"/>��<xsl:value-of select="taxdoc/sbsj/sbrqy"/>��<xsl:value-of select="taxdoc/sbsj/sbrqr"/>��</font>
						</td>
                		<td width="35%" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF">
							<font color="#2C556D">˰����أ�</font>
							<font color="#d32e2e"><xsl:value-of select="taxdoc/sbsj/zsswjgzzjgmc"/></font>
						</td>
              	   </tr>
            </table>

		<!--##########################################-->
			<table width="100%" height="102" border="2" cellpaddng="4" cellspacing="0" bordercolor="#9DB9D2" class="black9" id="AutoNumber3" style="border-collapse: collapse">
              <tr>
                <td width="50" rowspan="2" height="32">
                	<div align="center"><font color="#2C556D">�ɿλ<br/><br/>���ˣ�</font></div>
                </td>
                <td width="50" height="16">
									<div align="center"><font color="#2C556D">ʶ&#160;&#160;��&#160;&#160;��</font></div>
                </td>
                <td width="300" height="16">
									<font color="#d32e2e"><xsl:value-of select="//jsjdm"/></font>
								</td>
                <td width="80" height="16">
									<div align="center"><font color="#2C556D">��������</font></div>
								</td>
                <td width="270" height="16">
									<font color="#d32e2e"><xsl:value-of select="taxdoc/sbsj/yhmc"/></font>
								</td>
              </tr>
              <tr>
                <td width="50" height="16">
									<div align="center"><font color="#2C556D">��&#160;&#160;&#160;&#160;��</font></div>
							</td>
                <td width="300" height="16">
									<font color="#d32e2e"><xsl:value-of select="//nsrmc"/></font>
							</td>
							<td width="80" height="16">
									<div align="center"><font color="#2C556D">��&#160;&#160;&#160;&#160;��</font></div>
							</td>
                <td width="270" height="16">
									<font color="#d32e2e"><xsl:value-of select="taxdoc/sbsj/zh"/></font>
							</td>
              </tr>
              <tr>
                <td width="100" colspan="2" height="16">
									<div align="center"><font color="#2C556D">�տ����</font></div>
								</td>
                <td width="300" height="16">
									<font color="#d32e2e">(<xsl:value-of select="taxdoc/sbsj/gkzzjgdm"/>)<xsl:value-of select="taxdoc/sbsj/gkzzjgmc"/></font>
								</td>
                <td width="80" height="16" align="center">
									<div align="center"><font color="#2C556D">˰���޽����ڣ�</font></div>
								</td>
                <td width="270" height="16">
									<font color="#d32e2e"><xsl:value-of select="//xjrq"/></font>
							</td>
              </tr>             
            </table>
      <!--##########################################-->      
			<table width="100%" height="122" border="2" cellpadding="4" cellspacing="0" bordercolor="#9DB9D2" class="black9" id="AutoNumber2" style="border-collapse: collapse">
				  <tr>
					<td width="280" colspan="3" style="padding-top: 0" height="16" align="center"><font color="#2C556D">Ԥ���Ŀ</font></td>					  
					<td width="114" rowspan="2" style="padding-top: 0" height="32" align="center"><font color="#2C556D">ƷĿ����</font></td>
					<td width="52" rowspan="2" style="padding-top: 0" height="32" align="center"><font color="#2C556D">��˰����</font></td>
					<td width="58" rowspan="2" style="padding-top: 0" height="32" align="center"><font color="#2C556D">��˰������������</font></td>
					<td width="46" rowspan="2" style="padding-top: 0" height="32" align="center"><font color="#2C556D">˰�ʻ�<br/>��λ˰��</font></td>
					<td width="112" rowspan="2" style="padding-top: 0" height="32" align="center"><font color="#2C556D">˰������ʱ��</font></td>																	
					<td width="42" rowspan="2" style="padding-top: 0" height="32" align="center"><font color="#2C556D">�ѽɻ�۳���</font></td>
					<td width="46" rowspan="2" style="padding-top: 0" height="32" align="center"><font color="#2C556D">ʵ�ɽ��</font></td>
				  </tr>
				  <tr>
					<td width="80" style="padding-top: 0" height="16" align="center"><font color="#2C556D">����</font></td>					  
					<td width="180" style="padding-top: 0" height="16" align="center"><font color="#2C556D">����</font></td>
					<td width="80" style="padding-top: 0" height="16" align="center"><font color="#2C556D">����</font></td>
				  </tr>
		 	 <xsl:apply-templates select="taxdoc/sbsj"/><!--##########################################-->				  				  
			 	 <tr>
				<td width="80" style="padding-top: 0" height="15" align="center">
					<font color="#2C556D">���ϼ�</font>
				</td>			 	 
				<td colspan="8" style="padding-top: 0" height="15">
					<font color="#2C556D">����д��</font>
					<font color="#d32e2e"><xsl:value-of select="taxdoc/sbsj/jks/sjjedx"/></font>
				</td>
				<td width="46" style="padding-top: 0" height="15" align="right">
					<font color="#d32e2e">��<xsl:value-of select="taxdoc/sbsj/jks/sjje"/></font>
				</td>
			   </tr>
			</table>            			
		<!--##########################################-->
			<table width="100%" border="2" cellpadding="4" cellspacing="0" bordercolor="#9DB9D2" class="black9" id="AutoNumber5" style="border-collapse: collapse">
              <tr>
                <td width="20%">
										<font color="#2C556D">˰����أ� </font>
                  	<p><font color="#d32e2e"><xsl:value-of select="taxdoc/sbsj/swjgzzjgmc"/></font></p>
                  	<p/><font color="#2C556D">��Ʊ�ˣ�</font>
									<font color="#d32e2e"><xsl:value-of select="//nsrmc"/></font>
								</td>              
                <td width="20%">
				  <font color="#2C556D">�ɿλ���ˣ��� </font>
                  <p><font color="#d32e2e"><xsl:value-of select="//nsrmc"/></font></p>
                  <p><font color="#2C556D">������&#160;</font></p>
				</td>
                <td width="30%" align="center">
					<font color="#2C556D" >���п��������ײ���ת�տλ�˻�</font>
                  	<p/><font color="#2C556D">���⣨���У�����</font>
                  	<p/><font color="#2C556D">&#160;&#160;&#160;&#160;��&#160;&#160;��&#160;&#160;��</font>
				</td>				
                <td width="30%">
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
<!--˰��Ʊ֤������Ŀȡ���ɿ���˰��˰Ŀ�������ʾ����ӡ-->
<xsl:template match="taxdoc/sbsj">
	<xsl:for-each select="smitem">
		 <tr>
                <td width="80" style="padding-top: 0" height="16">
					<font color="#d32e2e"><xsl:value-of select="yskmbm"/></font>
				</td>
                <td width="180" style="padding-top: 0" height="16">
					<font color="#d32e2e"><xsl:value-of select="yskmmc"/></font>
				</td>
                <td width="80" style="padding-top: 0" height="16">
					<font color="#d32e2e"><xsl:value-of select="yskmjc"/></font>
				</td>										 
                <td width="114" style="padding-top: 0" height="16">
					<font color="#d32e2e"><xsl:value-of select="szsmmc"/></font>
				</td>
                <td width="52" style="padding-top: 0" height="16">
				 <font color="#d32e2e"><xsl:value-of select="kssl"/></font>
				</td>
                <td width="58" style="padding-top: 0" height="16">
					<div align="right"><font color="#d32e2e"><xsl:value-of select="jsje"/></font></div>
				</td>
                <td width="46" style="padding-top: 0" height="16">
					<div align="right"></div>
				</td>					
                <td width="112" style="padding-top: 0" height="16">
					<font color="#d32e2e"><xsl:value-of select="sksssq"/></font>
				</td>
                <td width="42" style="padding-top: 0" height="16">
					<div align="right"></div>
				</td>									
                <td width="46" style="padding-top: 0" height="16">
					<div align="right"><font color="#d32e2e"><xsl:value-of select="sjse"/></font></div>
				</td>
          </tr>
	</xsl:for-each>
			
</xsl:template>

</xsl:stylesheet>    
		   
		   
		   
		   
		   
	