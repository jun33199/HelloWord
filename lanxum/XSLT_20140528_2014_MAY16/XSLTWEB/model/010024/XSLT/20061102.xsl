<?xml version="1.0" encoding="gb2312"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html"/>
	<xsl:template match="/">
		
		<table id="maintable" align="center" cellspacing="0" class="table-99">
		<tr><td>
		<input name="xsltVersion" type="hidden" id="xsltVersion">
			<xsl:attribute name="value"><xsl:value-of select="taxdoc/xsltVersion"/></xsl:attribute>
		</input>
		<input name="schemaVersion" type="hidden" id="schemaVersion">
			<xsl:attribute name="value"><xsl:value-of select="taxdoc/schemaVersion"/></xsl:attribute>
		</input>
		<input name="ywlx" type="hidden" id="ywlx">
			<xsl:attribute name="value"><xsl:value-of select="taxdoc/ywlx"/></xsl:attribute>
		</input>
		<input name="ywczlx" type="hidden" id="ywczlx">
			<xsl:attribute name="value"><xsl:value-of select="taxdoc/ywczlx"/></xsl:attribute>
		</input>


                <input name="jmzg" type="hidden" id="jmzg">
			<xsl:attribute name="value"><xsl:value-of select="taxdoc/hdxx/jmzg"/></xsl:attribute>
		</input>
		<input name="ybjmsl" type="hidden" id="ybjmsl">
			<xsl:attribute name="value"><xsl:value-of select="taxdoc/hdxx/ybjmsl"/></xsl:attribute>
		</input>
		<input name="qyzslx" type="hidden" id="qyzslx">
			<xsl:attribute name="value"><xsl:value-of select="taxdoc/hdxx/qyzslx"/></xsl:attribute>
		</input>
		<input name="xzqy" type="hidden" id="xzqy">
			<xsl:attribute name="value"><xsl:value-of select="taxdoc/hdxx/xzqy"/></xsl:attribute>
		</input>
		<input name="cyl" type="hidden" id="cyl">
			<xsl:attribute name="value"><xsl:value-of select="taxdoc/hdxx/cyl"/></xsl:attribute>
		</input>
		<input name="dezsse" type="hidden" id="dezsse">
			<xsl:attribute name="value"><xsl:value-of select="taxdoc/hdxx/dezsse"/></xsl:attribute>
		</input>


		<input name="nd" type="hidden" id="nd">
			<xsl:attribute name="value"><xsl:value-of select="taxdoc/sbxx/nd"/></xsl:attribute>
		</input>
		<input name="sbrq" type="hidden" id="sbrq">
			<xsl:attribute name="value"><xsl:value-of select="taxdoc/sbxx/sbrq"/></xsl:attribute>
		</input>
		<input name="skssjsrq" type="hidden" id="skssjsrq">
			<xsl:attribute name="value"><xsl:value-of select="taxdoc/sbxx/skssjsrq"/></xsl:attribute>
		</input>
		<input name="skssksrq" type="hidden" id="skssksrq">
			<xsl:attribute name="value"><xsl:value-of select="taxdoc/sbxx/skssksrq"/></xsl:attribute>
		</input>
		<input name="swjgzzjgdm" type="hidden" id="swjgzzjgdm">
			<xsl:attribute name="value"><xsl:value-of select="taxdoc/nsrxx/swjgzzjgdm"/></xsl:attribute>
		</input>
		<input name="jsjdm" type="hidden" id="jsjdm">
			<xsl:attribute name="value"><xsl:value-of select="taxdoc/nsrxx/jsjdm"/></xsl:attribute>
		</input>
		<input name="nsrmc" type="hidden" id="nsrmc">
			<xsl:attribute name="value"><xsl:value-of select="taxdoc/nsrxx/nsrmc"/></xsl:attribute>
		</input>
		<input name="xxwl_je" type="hidden" id="xxwl_je">
			<xsl:attribute name="value"><xsl:value-of select="taxdoc/sbsj/xxwl_je"/></xsl:attribute>
		</input>
		</td></tr>
		<tr>
				 <td class="1-td1">�л����񹲺͹�
				  <br>��ҵ����˰�����˰�걨��(B��)</br></td>
	        </tr>
		<xsl:apply-templates select="taxdoc/nsrxx"/>		
		<xsl:apply-templates select="taxdoc/sbsj"/>
		</table>
    <br/>	
	</xsl:template>
	<xsl:template match="taxdoc/nsrxx">
	
		<tr>
		 <td class="1-td2"  colspan="7"><div align="left">&#160;&#160;&#160;&#160;�걨����:<xsl:value-of select="//sbrqshow"/>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;��λ��Ԫ�������Ƿ֣�</div></td>
		</tr>
		<tr>
		 <td class="1-td2"  colspan="7"><div align="left">&#160;&#160;&#160;&#160;���������:<xsl:value-of select="jsjdm"/>&#160;&#160;&#160;&#160;��˰�����ƣ�<xsl:value-of select="nsrmc"/>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;˰���������ڣ�<xsl:value-of select="//skssksrq"/>&#160;��&#160;<xsl:value-of select="//skssjsrq"/></div></td>
		</tr>	
	</xsl:template>


	<xsl:template match="taxdoc/sbsj">
			<tr>
			<td class="1-td2"  colspan="7">
				<TABLE class="table-99" align="center">
				<TR>
				<TD>
								<table id="wrklistTable" border="1" cellspacing="0" class="table-99" align="center" >
                  <tr> 
                    <td class="2-td1-left" nowrap="nowrap" colspan="3">��Ŀ</td>
                    <td class="2-td1-left" nowrap="nowrap">�д�</td>
                    <td class="2-td1-center" nowrap="nowrap">�ۼƽ��</td>                    
                  </tr>
				  <tr> 
          <td class="2-td2-left" rowspan="10" nowrap="nowrap">Ӧ��˰���ö�ļ���</td>
					<td class="2-td2-left" rowspan="3" nowrap="nowrap">�������ܶ�˶�Ӧ��˰���ö�</td>
					<td class="2-td2-left" nowrap="nowrap">�����ܶ�</td>
					<td class="2-td2-left" nowrap="nowrap">1</td>
          <td class="2-td2-center" nowrap="nowrap"><input type='text'  name='srze_ljs' id='srze_ljs'  size='13' tabindex='1' onblur ='return checkNumInput(this);' onchange='computeYnssde();'>
		    <xsl:attribute name="value"><xsl:value-of select="srze_ljs"/></xsl:attribute></input></td>
                  </tr>    
                  <tr> 
                    <td class="2-td2-left" nowrap="nowrap">˰����غ˶���Ӧ˰�����ʣ� %��</td>
					<td class="2-td2-left" nowrap="nowrap">2</td>
                    <td class="2-td2-center" nowrap="nowrap"><input type='text'  name='yssdl_ljs' id='yssdl_ljs'  class='text-gray' readOnly='true' size='13' tabindex='1' onblur ='return checkNumInput(this);' onchange='computeYnssde();'>
		    <xsl:attribute name="value"><xsl:value-of select="yssdl_ljs"/></xsl:attribute></input></td>
                  </tr>                  
                   <tr> 
					<td class="2-td2-left" nowrap="nowrap">Ӧ��˰���ö�</td>
                    <td class="2-td2-left" nowrap="nowrap">3</td>
                    <td class="2-td2-center" nowrap="nowrap"><input type='text'  name='ynssde_bqs' id='ynssde_bqs'  class='text-gray' readOnly='true' size='13' tabindex='1' onblur ='return checkNumInput(this);' onchange='computeYnssdse();'> 
		    <xsl:attribute name="value"><xsl:value-of select="ynssde_bqs"/></xsl:attribute></input></td>
                  </tr>    
                  
                  <tr>
					<td class="2-td2-left" rowspan="3" nowrap="nowrap">���ɱ����ú˶�Ӧ��˰���ö�</td>
 					<td class="2-td2-left" nowrap="nowrap">�ɱ������ܶ�</td>
                    <td class="2-td2-left" nowrap="nowrap">4</td>
                    <td class="2-td2-center" nowrap="nowrap">--</td>
                  </tr>                  
                  <tr>
 					<td class="2-td2-left" nowrap="nowrap">˰����غ˶���Ӧ˰�����ʣ� %��</td>
                    <td class="2-td2-left" nowrap="nowrap">5</td>
                    <td class="2-td2-center" nowrap="nowrap">--</td>
                  </tr>                  
                  <tr>
 					<td class="2-td2-left" nowrap="nowrap">Ӧ��˰���ö�[4�С£�1��5�У���5��]</td>
                    <td class="2-td2-left" nowrap="nowrap">6</td>
                    <td class="2-td2-center" nowrap="nowrap">--</td>
                  </tr>          
                          
                  <tr>
					<td class="2-td2-left" rowspan="4" nowrap="nowrap">������֧������Ӧ��˰���ö�</td>
 					<td class="2-td2-left" nowrap="nowrap">����֧���ܶ�</td>
                    <td class="2-td2-left" nowrap="nowrap">7</td>
                    <td class="2-td2-center" nowrap="nowrap">--</td>
                  </tr>                  
                  <tr>
 					<td class="2-td2-left" nowrap="nowrap">˰����غ˶���Ӧ˰�����ʣ� %��</td>
                    <td class="2-td2-left" nowrap="nowrap">8</td>
                    <td class="2-td2-center" nowrap="nowrap">--</td>
                  </tr>                  
                  <tr>
 					<td class="2-td2-left" nowrap="nowrap">����������[7�С£�1��8�У�]</td>
                    <td class="2-td2-left" nowrap="nowrap">9</td>
                    <td class="2-td2-center" nowrap="nowrap">--</td>
                  </tr>
                  <tr>                  
 					<td class="2-td2-left" nowrap="nowrap">Ӧ��˰���ö8�С�9�У�</td>
                    <td class="2-td2-left" nowrap="nowrap">10</td>
                    <td class="2-td2-center" nowrap="nowrap">--</td>
                  </tr>                  

                  <tr> 
                    <td class="2-td2-left" rowspan="3" colspan="2" nowrap="nowrap">Ӧ������˰��ļ���</td>
					<td class="2-td2-left" nowrap="nowrap">˰�ʣ�25%��</td>
                    <td class="2-td2-left" nowrap="nowrap">11</td>
                    <td class="2-td2-center" nowrap="nowrap">&#160;<input type='text'  class='text-gray' name='sysl_ljs' id='sysl_ljs'  size='13' value = '25' readOnly='true' tabindex='1' onchange='return checkNumInput(this);'>%
		    <xsl:attribute name="value"><xsl:value-of select="sysl_ljs"/></xsl:attribute></input></td>
                  </tr>
                      
                  <tr> 
					<td class="2-td2-left" nowrap="nowrap">Ӧ������˰�3�С�11�л�6�С�11�л�10�С�11�У�</td>
                    <td class="2-td2-left" nowrap="nowrap">12</td>
                    <td class="2-td2-center" nowrap="nowrap"><input type='text'  name='yjsdse_ljs' id='yjsdse_ljs'  size='13' tabindex='1' onblur ='return checkNumInput(this);' onchange='computeYbtsdse();'>
		    <xsl:attribute name="value"><xsl:value-of select="yjsdse_ljs"/></xsl:attribute></input></td>
                  </tr>    
                  <tr> 
					<td class="2-td2-left" nowrap="nowrap">��������˰��</td>
                    <td class="2-td2-left" nowrap="nowrap">13</td>                    
                    <td class="2-td2-center" nowrap="nowrap"><input type='text'  name='sjyyjdsdse_ljs' id='sjyyjdsdse_ljs'  size='13' tabindex='1' onchange='computeYbtsdse()' onblur ='return checkNumInput(this);'>
		    <xsl:attribute name="value"><xsl:value-of select="sjyyjdsdse_ljs"/></xsl:attribute></input></td>
                  </tr>    
                  
                  <tr> 
                    <td class="2-td2-left" rowspan="2" colspan="2" nowrap="nowrap">Ӧ�����ˣ�����˰��ļ���</td>
					           <td class="2-td2-left" nowrap="nowrap">��Ԥ������˰��</td>
                    <td class="2-td2-left" nowrap="nowrap">14</td>                    
                    <td class="2-td2-center" nowrap="nowrap"><input type='text'  name='sjyyjdsdse_bqs' id='sjyyjdsdse_bqs'  size='13' tabindex='1' onblur ='return checkNumInput(this);' onchange='computeYbtsdse();'> 
		                <xsl:attribute name="value"><xsl:value-of select="sjyyjdsdse_bqs"/></xsl:attribute></input></td>                         </tr>
                    <tr>
					<td class="2-td2-left" nowrap="nowrap">Ӧ�����ˣ�����˰�12�У�13�У�14�У�</td>
                    <td class="2-td2-left" nowrap="nowrap">15</td>
                    <td class="2-td2-center" nowrap="nowrap"><input type='text'  name='ybdsdse_ljs' id='ybdsdse_ljs' class='text-gray'  readonly = 'true' size='13' tabindex='1' onchange='return checkNumInput(this);'>
		    <xsl:attribute name="value"><xsl:value-of select="ybdsdse_ljs"/></xsl:attribute></input></td>
                  </tr>    
                </table>

				</TD>
				</TR>
				
				</TABLE>
			</td>
		</tr>
 	</xsl:template>
</xsl:stylesheet>
