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
		</td></tr>
		<tr>
				 <td class="1-td1">˰���Ż���ϸ��</td>
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
                    <td class="2-td1-left" nowrap="nowrap">�д�</td>
                    <td class="2-td1-left" nowrap="nowrap">��Ŀ</td>
                    <td class="2-td1-left" nowrap="nowrap">���</td>                    
                    <td class="2-td1-left" nowrap="nowrap">�д�</td>
                    <td class="2-td1-left" nowrap="nowrap">��Ŀ</td>
                    <td class="2-td1-center" nowrap="nowrap">���</td>                    
                  </tr>
				  <tr> 
          <td class="2-td2-left" nowrap="nowrap">1</td>
					<td align="left" class="2-td2-left"><div align="left">һ����˰���루2��3��4��5��</div></td>
					<td class="2-td2-left" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='5' readOnly='true' tabindex='2'></input></td>
					<td class="2-td2-left" nowrap="nowrap">25</td>
          <td class="2-td2-left" nowrap="nowrap"><div align="left">&#160;��������˰���ã�26��27��28��</div></td>
					<td class="2-td2-center" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='13' readOnly='true' tabindex='2'></input></td>
          </tr>    
				  <tr> 
          <td class="2-td2-left" nowrap="nowrap">2</td>
					<td class="2-td2-left" ><div align="left">&#160;1����ծ��Ϣ����</div></td>
					<td class="2-td2-left" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='5' readOnly='true' tabindex='2'></input></td>
					<td class="2-td2-left" nowrap="nowrap">26</td>
          <td class="2-td2-left" nowrap="nowrap"><div align="left">&#160;1�����ܡ����Լ�������������������������ֲ</div></td>
					<td class="2-td2-center" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='13' readOnly='true' tabindex='2'></input></td>
          </tr>    
				  <tr> 
          <td class="2-td2-left" nowrap="nowrap">3</td>
					<td class="2-td2-left" ><div align="left">&#160;2�����������ľ�����ҵ֮��Ĺ�Ϣ��������Ȩ����Ͷ������</div></td>
					<td class="2-td2-left" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='5' readOnly='true' tabindex='2'></input></td>
					<td class="2-td2-left" nowrap="nowrap">27</td>
          <td class="2-td2-left" nowrap="nowrap"><div align="left">&#160;2����ˮ��ֳ����½��ֳ</div></td>
					<td class="2-td2-center" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='13' readOnly='true' tabindex='2'></input></td>
          </tr>    
				  <tr> 
          <td class="2-td2-left" nowrap="nowrap">4</td>
					<td class="2-td2-left" ><div align="left">&#160;3�����������ķ�Ӫ����֯������</div></td>
					<td class="2-td2-left" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='5' readOnly='true' tabindex='2'></input></td>
					<td class="2-td2-left" nowrap="nowrap">28</td>
          <td class="2-td2-left" nowrap="nowrap"><div align="left">&#160;3������</div></td>
					<td class="2-td2-center" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='13' readOnly='true' tabindex='2'></input></td>
          </tr>    
				  <tr> 
          <td class="2-td2-left" nowrap="nowrap">5</td>
					<td class="2-td2-left" ><div align="left">&#160;4������</div></td>
					<td class="2-td2-left" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='5' readOnly='true' tabindex='2'></input></td>
					<td class="2-td2-left" nowrap="nowrap">29</td>
          <td class="2-td2-left" nowrap="nowrap"><div align="left">&#160;���������¹����ص���ֵĹ���������ʩ��ĿͶ�ʾ�Ӫ������</div></td>
					<td class="2-td2-center" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='13' readOnly='true' tabindex='2'></input></td>
          </tr>    
				  <tr> 
          <td class="2-td2-left" nowrap="nowrap">6</td>
					<td align="left" class="2-td2-left" ><div align="left">�����������루7��8��</div></td>
					<td class="2-td2-left" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='5' readOnly='true' tabindex='2'></input></td>
					<td class="2-td2-left" nowrap="nowrap">30</td>
          <td class="2-td2-left" nowrap="nowrap"><div align="left">&#160;���ģ����·��������Ļ������������ܽ�ˮ��Ŀ������</div></td>
					<td class="2-td2-center" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='13' readOnly='true' tabindex='2'></input></td>
          </tr>    
				  <tr> 
          <td class="2-td2-left" nowrap="nowrap">7</td>
					<td class="2-td2-left"><div align="left">&#160;1����ҵ�ۺ�������Դ,�������Ϲ��Ҳ�ҵ���߹涨�Ĳ�Ʒ��ȡ�õ�����</div></td>
					<td class="2-td2-left" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='5' readOnly='true' tabindex='2'></input></td>
					<td class="2-td2-left" nowrap="nowrap">31</td>
          <td class="2-td2-left" nowrap="nowrap"><div align="left">&#160;���壩���������ļ���ת������</div></td>
					<td class="2-td2-center" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='13' readOnly='true' tabindex='2'></input></td>
          </tr>    
				  <tr> 
          <td class="2-td2-left" nowrap="nowrap">8</td>
					<td class="2-td2-left" ><div align="left">&#160;2������</div></td>
					<td class="2-td2-left" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='5' readOnly='true' tabindex='2'></input></td>
					<td class="2-td2-left" nowrap="nowrap">32</td>
          <td class="2-td2-left" nowrap="nowrap"><div align="left">&#160;����������</div></td>
					<td class="2-td2-center" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='13' readOnly='true' tabindex='2'></input></td>
          </tr>    
				  <tr> 
          <td class="2-td2-left" nowrap="nowrap">9</td>
					<td align="left" class="2-td2-left" ><div align="left">�����Ӽƿ۳���ϼƣ�10��11��12��13��</div></td>
					<td class="2-td2-left" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='5' readOnly='true' tabindex='2'></input></td>
					<td class="2-td2-left" nowrap="nowrap">33</td>
          <td align="left" class="2-td2-left" nowrap="nowrap"><div align="left">�塢����˰�ϼƣ�34��35��36��37��38��</div></td>
					<td class="2-td2-center" nowrap="nowrap"><input type='text'  name='jmshj_je' id='jmshj_je'  size='13' tabindex='1' readOnly='true' class='text-gray' onchange='return checkNumInput(this);'>
		    <xsl:attribute name="value"><xsl:value-of select="jmshj_je"/></xsl:attribute></input></td>
          </tr>    
				  <tr> 
          <td class="2-td2-left" nowrap="nowrap">10</td>
					<td class="2-td2-left" ><div align="left">&#160;1�������¼������²�Ʒ���¹��շ������о���������</div></td>
					<td class="2-td2-left" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='5' readOnly='true' tabindex='2'></input></td>
					<td class="2-td2-left" nowrap="nowrap">34</td>
          <td class="2-td2-left" nowrap="nowrap"><div align="left">&#160;��һ������������С��΢����ҵ</div></td>
					<td class="2-td2-center" nowrap="nowrap"><input type='text'  name='xxwl_je' id='xxwl_je'  size='13' tabindex='1' onblur ='return checkNumInput(this);' onchange='computeJe();'>
		    <xsl:attribute name="value"><xsl:value-of select="xxwl_je"/></xsl:attribute></input></td>
          </tr>    
				  <tr> 
          <td class="2-td2-left" nowrap="nowrap">11</td>
					<td class="2-td2-left"><div align="left">&#160;2�����òм���Ա��֧���Ĺ���</div></td>
					<td class="2-td2-left" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='5' readOnly='true' tabindex='2'></input></td>
					<td class="2-td2-left" nowrap="nowrap">35</td>
          <td class="2-td2-left" nowrap="nowrap"><div align="left">&#160;������������Ҫ�ص���ֵĸ��¼�����ҵ</div></td>
					<td class="2-td2-center" nowrap="nowrap"><input type='text'  name='gxqy_je' id='gxqy_je'  class='text-gray' readOnly='true' size='13' tabindex='1' onblur ='return checkNumInput(this);' onchange='computeJe();'>
		    <xsl:attribute name="value"><xsl:value-of select="gxqy_je"/></xsl:attribute></input></td>
          </tr>    
				  <tr> 
          <td class="2-td2-left" nowrap="nowrap">12</td>
					<td class="2-td2-left"><div align="left">&#160;3�����ҹ������õ�������ҵ��Ա֧���Ĺ���</div></td>
					<td class="2-td2-left" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='5' readOnly='true' tabindex='2'></input></td>
					<td class="2-td2-left" nowrap="nowrap">36</td>
          <td class="2-td2-left" nowrap="nowrap"><div align="left">&#160;�������������εط�����ҵӦ���ɵ���ҵ����˰�����ڵط�����Ĳ���</div></td>
					<td class="2-td2-center" nowrap="nowrap"><input type='text'  name='mzzz_je' id='mzzz_je'  size='13' class='text-gray' readOnly='true' tabindex='1' onblur ='return checkNumInput(this);' onchange='computeJe();'>
		    <xsl:attribute name="value"><xsl:value-of select="mzzz_je"/></xsl:attribute></input></td>
          </tr>    
				  <tr> 
          <td class="2-td2-left" nowrap="nowrap">13</td>
					<td class="2-td2-left"><div align="left">&#160;4������</div></td>
					<td class="2-td2-left" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='5' readOnly='true' tabindex='2'></input></td>
					<td class="2-td2-left" nowrap="nowrap">37</td>
          <td class="2-td2-left" nowrap="nowrap"><div align="left">&#160;���ģ�������˰���Ż�</div></td>
					<td class="2-td2-center" nowrap="nowrap"><input type='text'  name='gdqyh_je' id='gdqyh_je'  size='13' class='text-gray' readOnly='true' tabindex='1' onblur ='return checkNumInput(this);' onchange='computeJe();'>
		    <xsl:attribute name="value"><xsl:value-of select="gdqyh_je"/></xsl:attribute></input></td>
          </tr>    
				  <tr> 
          <td class="2-td2-left" nowrap="nowrap">14</td>
					<td align="left" class="2-td2-left"><div align="left">�ġ��������ö�ϼƣ�15��25��29��30��31��32��</div></td>
					<td class="2-td2-left" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='5' readOnly='true' tabindex='2'></input></td>
					<td class="2-td2-left" nowrap="nowrap">38</td>
          <td class="2-td2-left" nowrap="nowrap"><div align="left">&#160;���壩����</div></td>
					<td class="2-td2-center" nowrap="nowrap"><input type='text'  name='qt_je' id='qt_je'  size='13' tabindex='1' onblur ='return checkNumInput(this);' onchange='computeJe();'>
		    <xsl:attribute name="value"><xsl:value-of select="qt_je"/></xsl:attribute></input></td>
          </tr>    
				  <tr> 
          <td class="2-td2-left" nowrap="nowrap">15</td>
					<td class="2-td2-left"><div align="left">&#160;��һ����˰���ã�16��17������24��</div></td>
					<td class="2-td2-left" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='5' readOnly='true' tabindex='2'></input></td>
					<td class="2-td2-left" nowrap="nowrap">39</td>
          <td align="left" class="2-td2-left" nowrap="nowrap"><div align="left">������ҵͶ����ҵ�ֿ۵�Ӧ��˰���ö�</div></td>
					<td class="2-td2-center" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='13' readOnly='true' tabindex='2'></input></td>
          </tr>    
				  <tr> 
          <td class="2-td2-left" nowrap="nowrap">16</td>
					<td class="2-td2-left"><div align="left">&#160;1���߲ˡ�������ࡢ���ϡ����ࡢ�޻������ࡢ���ϡ�ˮ�����������ֲ</div></td>
					<td class="2-td2-left" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='5' readOnly='true' tabindex='2'></input></td>
					<td class="2-td2-left" nowrap="nowrap">40</td>
          <td align="left" class="2-td2-left" nowrap="nowrap"><div align="left">�ߡ���������˰��ϼƣ�41��42��43��44��</div></td>
					<td class="2-td2-center" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='13' readOnly='true' tabindex='2'></input></td>
          </tr>    
				  <tr> 
          <td class="2-td2-left" nowrap="nowrap">17</td>
					<td class="2-td2-left"><div align="left">&#160;2��ũ������Ʒ�ֵ�ѡ��</div></td>
					<td class="2-td2-left" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='5' readOnly='true' tabindex='2'></input></td>
					<td class="2-td2-left" nowrap="nowrap">41</td>
          <td class="2-td2-left" nowrap="nowrap"><div align="left">&#160;��һ����ҵ�������ڻ�������ר���豸��Ͷ�ʶ�����˰��</div></td>
					<td class="2-td2-center" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='13' readOnly='true' tabindex='2'></input></td>
          </tr>    
				  <tr> 
          <td class="2-td2-left" nowrap="nowrap">18</td>
					<td class="2-td2-left"><div align="left">&#160;3����ҩ�ĵ���ֲ</div></td>
					<td class="2-td2-left" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='5' readOnly='true' tabindex='2'></input></td>
					<td class="2-td2-left" nowrap="nowrap">42</td>
          <td class="2-td2-left" nowrap="nowrap"><div align="left">&#160;��������ҵ�������ڽ��ܽ�ˮר���豸��Ͷ�ʶ�����˰��</div></td>
					<td class="2-td2-center" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='13' readOnly='true' tabindex='2'></input></td>
          </tr>    
				  <tr> 
          <td class="2-td2-left" nowrap="nowrap">19</td>
					<td class="2-td2-left"><div align="left">&#160;4����ľ����������ֲ</div></td>
					<td class="2-td2-left" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='5' readOnly='true' tabindex='2'></input></td>
					<td class="2-td2-left" nowrap="nowrap">43</td>
          <td class="2-td2-left" nowrap="nowrap"><div align="left">&#160;��������ҵ�������ڰ�ȫ����ר���豸��Ͷ�ʶ�����˰��</div></td>
					<td class="2-td2-center" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='13' readOnly='true' tabindex='2'></input></td>
          </tr>    
				  <tr> 
          <td class="2-td2-left" nowrap="nowrap">20</td>
					<td class="2-td2-left"><div align="left">&#160;5�����󡢼��ݵ�����</div></td>
					<td class="2-td2-left" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='5' readOnly='true' tabindex='2'></input></td>
					<td class="2-td2-left" nowrap="nowrap">44</td>
          <td class="2-td2-left" nowrap="nowrap"><div align="left">&#160;���ģ�����</div></td>
					<td class="2-td2-center" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='13' readOnly='true' tabindex='2'></input></td>
          </tr>    
				  <tr> 
          <td class="2-td2-left" nowrap="nowrap">21</td>
					<td class="2-td2-left"><div align="left">&#160;6���ֲ�Ʒ�Ĳɼ�</div></td>
					<td class="2-td2-left" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='5' readOnly='true' tabindex='2'></input></td>
					<td class="2-td2-left" nowrap="nowrap">45</td>
          <td align="left" class="2-td2-left" nowrap="nowrap"><div align="left">��ҵ��ҵ������ȫ��ƽ��������</div></td>
					<td class="2-td2-center" nowrap="nowrap"><input type='text'  name='qyrs' id='qyrs'  size='13' tabindex='1' onchange='return checkNumZsInput(this);'>
		    <xsl:attribute name="value"><xsl:value-of select="qyrs"/></xsl:attribute></input></td>
          </tr>    
				  <tr> 
          <td class="2-td2-left" nowrap="nowrap">22</td>
					<td class="2-td2-left"><div align="left">&#160;7����ȡ�ũ��Ʒ���ӹ�����ҽ��ũ���ƹ㡢ũ����ҵ��ά�޵�ũ���֡����������ҵ��Ŀ</div></td>
					<td class="2-td2-left" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='5' readOnly='true' tabindex='2'></input></td>
					<td class="2-td2-left" nowrap="nowrap">46</td>
          <td align="left" class="2-td2-left" nowrap="nowrap"><div align="left">�ʲ��ܶȫ��ƽ������</div></td>
					<td class="2-td2-center" nowrap="nowrap"><input type='text'  name='zcze' id='zcze'  size='13' tabindex='1' onchange='return checkNumInput(this);'>
		    <xsl:attribute name="value"><xsl:value-of select="zcze"/></xsl:attribute></input></td>
          </tr>    
				  <tr> 
          <td class="2-td2-left" nowrap="nowrap">23</td>
					<td class="2-td2-left"><div align="left">&#160;8��Զ����</div></td>
					<td class="2-td2-left" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='5' readOnly='true' tabindex='2'></input></td>
					<td class="2-td2-left" nowrap="nowrap">47</td>
          <td align="left" class="2-td2-left" nowrap="nowrap"><div align="left">������ҵ����ҵ��ҵ   ������ҵ ��</div></td>
					<td class="2-td2-center" nowrap="nowrap"><input type="Radio" name="hyje" id="hy47_1" value="01" onclick="changeSelect()" onchange="changeSelect()">��ҵ��ҵ</input>
								<input type="Radio" name="hyje" id="hy47_2" value="02" onclick="changeSelect()" onchange="changeSelect()">������ҵ</input>
</td>
          </tr>    
				  <tr> 
          <td class="2-td2-left" nowrap="nowrap">24</td>
					<td class="2-td2-left"><div align="left">&#160;9������</div></td>
					<td class="2-td2-left" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='5' readOnly='true' tabindex='2'></input></td>
					<td class="2-td2-left" nowrap="nowrap">&#160;&#160;</td>
          <td class="2-td2-left" nowrap="nowrap">&#160;&#160;</td>
		 <td class="2-td2-center" nowrap="nowrap">&#160;&#160;
			<input name="sshy" type="hidden" id="sshy">
			<xsl:attribute name="value"><xsl:value-of select="sshy"/></xsl:attribute></input>
			<input name="zbYnsdse" type="hidden" id="zbYnsdse">
			<xsl:attribute name="value"><xsl:value-of select="zbYnsdse"/></xsl:attribute></input>
		</td>
          </tr>    
          </table>
				</TD>
				</TR>				
				</TABLE>
			</td>
		</tr>
 	</xsl:template>
</xsl:stylesheet>
