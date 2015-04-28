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
				 <td class="1-td1">税收优惠明细表</td>
	        </tr>
		<xsl:apply-templates select="taxdoc/nsrxx"/>		
		<xsl:apply-templates select="taxdoc/sbsj"/>
		</table>
    <br/>	
	</xsl:template>
	<xsl:template match="taxdoc/nsrxx">
	
		<tr>
		 <td class="1-td2"  colspan="7"><div align="left">&#160;&#160;&#160;&#160;申报日期:<xsl:value-of select="//sbrqshow"/>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;金额单位：元（列至角分）</div></td>
		</tr>
		<tr>
		 <td class="1-td2"  colspan="7"><div align="left">&#160;&#160;&#160;&#160;计算机代码:<xsl:value-of select="jsjdm"/>&#160;&#160;&#160;&#160;纳税人名称：<xsl:value-of select="nsrmc"/>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;税款所属日期：<xsl:value-of select="//skssksrq"/>&#160;至&#160;<xsl:value-of select="//skssjsrq"/></div></td>
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
                    <td class="2-td1-left" nowrap="nowrap">行次</td>
                    <td class="2-td1-left" nowrap="nowrap">项目</td>
                    <td class="2-td1-left" nowrap="nowrap">金额</td>                    
                    <td class="2-td1-left" nowrap="nowrap">行次</td>
                    <td class="2-td1-left" nowrap="nowrap">项目</td>
                    <td class="2-td1-center" nowrap="nowrap">金额</td>                    
                  </tr>
				  <tr> 
          <td class="2-td2-left" nowrap="nowrap">1</td>
					<td align="left" class="2-td2-left"><div align="left">一、免税收入（2＋3＋4＋5）</div></td>
					<td class="2-td2-left" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='5' readOnly='true' tabindex='2'></input></td>
					<td class="2-td2-left" nowrap="nowrap">25</td>
          <td class="2-td2-left" nowrap="nowrap"><div align="left">&#160;（二）减税所得（26＋27＋28）</div></td>
					<td class="2-td2-center" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='13' readOnly='true' tabindex='2'></input></td>
          </tr>    
				  <tr> 
          <td class="2-td2-left" nowrap="nowrap">2</td>
					<td class="2-td2-left" ><div align="left">&#160;1、国债利息收入</div></td>
					<td class="2-td2-left" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='5' readOnly='true' tabindex='2'></input></td>
					<td class="2-td2-left" nowrap="nowrap">26</td>
          <td class="2-td2-left" nowrap="nowrap"><div align="left">&#160;1、花卉、茶以及其他饮料作物和香料作物的种植</div></td>
					<td class="2-td2-center" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='13' readOnly='true' tabindex='2'></input></td>
          </tr>    
				  <tr> 
          <td class="2-td2-left" nowrap="nowrap">3</td>
					<td class="2-td2-left" ><div align="left">&#160;2、符合条件的居民企业之间的股息、红利等权益性投资收益</div></td>
					<td class="2-td2-left" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='5' readOnly='true' tabindex='2'></input></td>
					<td class="2-td2-left" nowrap="nowrap">27</td>
          <td class="2-td2-left" nowrap="nowrap"><div align="left">&#160;2、海水养殖、内陆养殖</div></td>
					<td class="2-td2-center" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='13' readOnly='true' tabindex='2'></input></td>
          </tr>    
				  <tr> 
          <td class="2-td2-left" nowrap="nowrap">4</td>
					<td class="2-td2-left" ><div align="left">&#160;3、符合条件的非营利组织的收入</div></td>
					<td class="2-td2-left" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='5' readOnly='true' tabindex='2'></input></td>
					<td class="2-td2-left" nowrap="nowrap">28</td>
          <td class="2-td2-left" nowrap="nowrap"><div align="left">&#160;3、其他</div></td>
					<td class="2-td2-center" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='13' readOnly='true' tabindex='2'></input></td>
          </tr>    
				  <tr> 
          <td class="2-td2-left" nowrap="nowrap">5</td>
					<td class="2-td2-left" ><div align="left">&#160;4、其他</div></td>
					<td class="2-td2-left" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='5' readOnly='true' tabindex='2'></input></td>
					<td class="2-td2-left" nowrap="nowrap">29</td>
          <td class="2-td2-left" nowrap="nowrap"><div align="left">&#160;（三）从事国家重点扶持的公共基础设施项目投资经营的所得</div></td>
					<td class="2-td2-center" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='13' readOnly='true' tabindex='2'></input></td>
          </tr>    
				  <tr> 
          <td class="2-td2-left" nowrap="nowrap">6</td>
					<td align="left" class="2-td2-left" ><div align="left">二、减计收入（7＋8）</div></td>
					<td class="2-td2-left" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='5' readOnly='true' tabindex='2'></input></td>
					<td class="2-td2-left" nowrap="nowrap">30</td>
          <td class="2-td2-left" nowrap="nowrap"><div align="left">&#160;（四）从事符合条件的环境保护、节能节水项目的所得</div></td>
					<td class="2-td2-center" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='13' readOnly='true' tabindex='2'></input></td>
          </tr>    
				  <tr> 
          <td class="2-td2-left" nowrap="nowrap">7</td>
					<td class="2-td2-left"><div align="left">&#160;1、企业综合利用资源,生产符合国家产业政策规定的产品所取得的收入</div></td>
					<td class="2-td2-left" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='5' readOnly='true' tabindex='2'></input></td>
					<td class="2-td2-left" nowrap="nowrap">31</td>
          <td class="2-td2-left" nowrap="nowrap"><div align="left">&#160;（五）符合条件的技术转让所得</div></td>
					<td class="2-td2-center" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='13' readOnly='true' tabindex='2'></input></td>
          </tr>    
				  <tr> 
          <td class="2-td2-left" nowrap="nowrap">8</td>
					<td class="2-td2-left" ><div align="left">&#160;2、其他</div></td>
					<td class="2-td2-left" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='5' readOnly='true' tabindex='2'></input></td>
					<td class="2-td2-left" nowrap="nowrap">32</td>
          <td class="2-td2-left" nowrap="nowrap"><div align="left">&#160;（六）其他</div></td>
					<td class="2-td2-center" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='13' readOnly='true' tabindex='2'></input></td>
          </tr>    
				  <tr> 
          <td class="2-td2-left" nowrap="nowrap">9</td>
					<td align="left" class="2-td2-left" ><div align="left">三、加计扣除额合计（10＋11＋12＋13）</div></td>
					<td class="2-td2-left" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='5' readOnly='true' tabindex='2'></input></td>
					<td class="2-td2-left" nowrap="nowrap">33</td>
          <td align="left" class="2-td2-left" nowrap="nowrap"><div align="left">五、减免税合计（34＋35＋36＋37＋38）</div></td>
					<td class="2-td2-center" nowrap="nowrap"><input type='text'  name='jmshj_je' id='jmshj_je'  size='13' tabindex='1' readOnly='true' class='text-gray' onchange='return checkNumInput(this);'>
		    <xsl:attribute name="value"><xsl:value-of select="jmshj_je"/></xsl:attribute></input></td>
          </tr>    
				  <tr> 
          <td class="2-td2-left" nowrap="nowrap">10</td>
					<td class="2-td2-left" ><div align="left">&#160;1、开发新技术、新产品、新工艺发生的研究开发费用</div></td>
					<td class="2-td2-left" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='5' readOnly='true' tabindex='2'></input></td>
					<td class="2-td2-left" nowrap="nowrap">34</td>
          <td class="2-td2-left" nowrap="nowrap"><div align="left">&#160;（一）符合条件的小型微利企业</div></td>
					<td class="2-td2-center" nowrap="nowrap"><input type='text'  name='xxwl_je' id='xxwl_je'  size='13' tabindex='1' onblur ='return checkNumInput(this);' onchange='computeJe();'>
		    <xsl:attribute name="value"><xsl:value-of select="xxwl_je"/></xsl:attribute></input></td>
          </tr>    
				  <tr> 
          <td class="2-td2-left" nowrap="nowrap">11</td>
					<td class="2-td2-left"><div align="left">&#160;2、安置残疾人员所支付的工资</div></td>
					<td class="2-td2-left" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='5' readOnly='true' tabindex='2'></input></td>
					<td class="2-td2-left" nowrap="nowrap">35</td>
          <td class="2-td2-left" nowrap="nowrap"><div align="left">&#160;（二）国家需要重点扶持的高新技术企业</div></td>
					<td class="2-td2-center" nowrap="nowrap"><input type='text'  name='gxqy_je' id='gxqy_je'  class='text-gray' readOnly='true' size='13' tabindex='1' onblur ='return checkNumInput(this);' onchange='computeJe();'>
		    <xsl:attribute name="value"><xsl:value-of select="gxqy_je"/></xsl:attribute></input></td>
          </tr>    
				  <tr> 
          <td class="2-td2-left" nowrap="nowrap">12</td>
					<td class="2-td2-left"><div align="left">&#160;3、国家鼓励安置的其他就业人员支付的工资</div></td>
					<td class="2-td2-left" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='5' readOnly='true' tabindex='2'></input></td>
					<td class="2-td2-left" nowrap="nowrap">36</td>
          <td class="2-td2-left" nowrap="nowrap"><div align="left">&#160;（三）民族自治地方的企业应缴纳的企业所得税中属于地方分享的部分</div></td>
					<td class="2-td2-center" nowrap="nowrap"><input type='text'  name='mzzz_je' id='mzzz_je'  size='13' class='text-gray' readOnly='true' tabindex='1' onblur ='return checkNumInput(this);' onchange='computeJe();'>
		    <xsl:attribute name="value"><xsl:value-of select="mzzz_je"/></xsl:attribute></input></td>
          </tr>    
				  <tr> 
          <td class="2-td2-left" nowrap="nowrap">13</td>
					<td class="2-td2-left"><div align="left">&#160;4、其他</div></td>
					<td class="2-td2-left" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='5' readOnly='true' tabindex='2'></input></td>
					<td class="2-td2-left" nowrap="nowrap">37</td>
          <td class="2-td2-left" nowrap="nowrap"><div align="left">&#160;（四）过渡期税收优惠</div></td>
					<td class="2-td2-center" nowrap="nowrap"><input type='text'  name='gdqyh_je' id='gdqyh_je'  size='13' class='text-gray' readOnly='true' tabindex='1' onblur ='return checkNumInput(this);' onchange='computeJe();'>
		    <xsl:attribute name="value"><xsl:value-of select="gdqyh_je"/></xsl:attribute></input></td>
          </tr>    
				  <tr> 
          <td class="2-td2-left" nowrap="nowrap">14</td>
					<td align="left" class="2-td2-left"><div align="left">四、减免所得额合计（15＋25＋29＋30＋31＋32）</div></td>
					<td class="2-td2-left" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='5' readOnly='true' tabindex='2'></input></td>
					<td class="2-td2-left" nowrap="nowrap">38</td>
          <td class="2-td2-left" nowrap="nowrap"><div align="left">&#160;（五）其他</div></td>
					<td class="2-td2-center" nowrap="nowrap"><input type='text'  name='qt_je' id='qt_je'  size='13' tabindex='1' onblur ='return checkNumInput(this);' onchange='computeJe();'>
		    <xsl:attribute name="value"><xsl:value-of select="qt_je"/></xsl:attribute></input></td>
          </tr>    
				  <tr> 
          <td class="2-td2-left" nowrap="nowrap">15</td>
					<td class="2-td2-left"><div align="left">&#160;（一）免税所得（16＋17＋…＋24）</div></td>
					<td class="2-td2-left" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='5' readOnly='true' tabindex='2'></input></td>
					<td class="2-td2-left" nowrap="nowrap">39</td>
          <td align="left" class="2-td2-left" nowrap="nowrap"><div align="left">六、创业投资企业抵扣的应纳税所得额</div></td>
					<td class="2-td2-center" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='13' readOnly='true' tabindex='2'></input></td>
          </tr>    
				  <tr> 
          <td class="2-td2-left" nowrap="nowrap">16</td>
					<td class="2-td2-left"><div align="left">&#160;1、蔬菜、谷物、薯类、油料、豆类、棉花、麻类、糖料、水果、坚果的种植</div></td>
					<td class="2-td2-left" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='5' readOnly='true' tabindex='2'></input></td>
					<td class="2-td2-left" nowrap="nowrap">40</td>
          <td align="left" class="2-td2-left" nowrap="nowrap"><div align="left">七、抵免所得税额合计（41＋42＋43＋44）</div></td>
					<td class="2-td2-center" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='13' readOnly='true' tabindex='2'></input></td>
          </tr>    
				  <tr> 
          <td class="2-td2-left" nowrap="nowrap">17</td>
					<td class="2-td2-left"><div align="left">&#160;2、农作物新品种的选育</div></td>
					<td class="2-td2-left" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='5' readOnly='true' tabindex='2'></input></td>
					<td class="2-td2-left" nowrap="nowrap">41</td>
          <td class="2-td2-left" nowrap="nowrap"><div align="left">&#160;（一）企业购置用于环境保护专用设备的投资额抵免的税额</div></td>
					<td class="2-td2-center" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='13' readOnly='true' tabindex='2'></input></td>
          </tr>    
				  <tr> 
          <td class="2-td2-left" nowrap="nowrap">18</td>
					<td class="2-td2-left"><div align="left">&#160;3、中药材的种植</div></td>
					<td class="2-td2-left" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='5' readOnly='true' tabindex='2'></input></td>
					<td class="2-td2-left" nowrap="nowrap">42</td>
          <td class="2-td2-left" nowrap="nowrap"><div align="left">&#160;（二）企业购置用于节能节水专用设备的投资额抵免的税额</div></td>
					<td class="2-td2-center" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='13' readOnly='true' tabindex='2'></input></td>
          </tr>    
				  <tr> 
          <td class="2-td2-left" nowrap="nowrap">19</td>
					<td class="2-td2-left"><div align="left">&#160;4、林木的培育和种植</div></td>
					<td class="2-td2-left" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='5' readOnly='true' tabindex='2'></input></td>
					<td class="2-td2-left" nowrap="nowrap">43</td>
          <td class="2-td2-left" nowrap="nowrap"><div align="left">&#160;（三）企业购置用于安全生产专用设备的投资额抵免的税额</div></td>
					<td class="2-td2-center" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='13' readOnly='true' tabindex='2'></input></td>
          </tr>    
				  <tr> 
          <td class="2-td2-left" nowrap="nowrap">20</td>
					<td class="2-td2-left"><div align="left">&#160;5、牲畜、家禽的饲养</div></td>
					<td class="2-td2-left" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='5' readOnly='true' tabindex='2'></input></td>
					<td class="2-td2-left" nowrap="nowrap">44</td>
          <td class="2-td2-left" nowrap="nowrap"><div align="left">&#160;（四）其他</div></td>
					<td class="2-td2-center" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='13' readOnly='true' tabindex='2'></input></td>
          </tr>    
				  <tr> 
          <td class="2-td2-left" nowrap="nowrap">21</td>
					<td class="2-td2-left"><div align="left">&#160;6、林产品的采集</div></td>
					<td class="2-td2-left" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='5' readOnly='true' tabindex='2'></input></td>
					<td class="2-td2-left" nowrap="nowrap">45</td>
          <td align="left" class="2-td2-left" nowrap="nowrap"><div align="left">企业从业人数（全年平均人数）</div></td>
					<td class="2-td2-center" nowrap="nowrap"><input type='text'  name='qyrs' id='qyrs'  size='13' tabindex='1' onchange='return checkNumZsInput(this);'>
		    <xsl:attribute name="value"><xsl:value-of select="qyrs"/></xsl:attribute></input></td>
          </tr>    
				  <tr> 
          <td class="2-td2-left" nowrap="nowrap">22</td>
					<td class="2-td2-left"><div align="left">&#160;7、灌溉、农产品初加工、兽医、农技推广、农机作业和维修等农、林、牧、渔服务业项目</div></td>
					<td class="2-td2-left" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='5' readOnly='true' tabindex='2'></input></td>
					<td class="2-td2-left" nowrap="nowrap">46</td>
          <td align="left" class="2-td2-left" nowrap="nowrap"><div align="left">资产总额（全年平均数）</div></td>
					<td class="2-td2-center" nowrap="nowrap"><input type='text'  name='zcze' id='zcze'  size='13' tabindex='1' onchange='return checkNumInput(this);'>
		    <xsl:attribute name="value"><xsl:value-of select="zcze"/></xsl:attribute></input></td>
          </tr>    
				  <tr> 
          <td class="2-td2-left" nowrap="nowrap">23</td>
					<td class="2-td2-left"><div align="left">&#160;8、远洋捕捞</div></td>
					<td class="2-td2-left" nowrap="nowrap"><input type='text'  class='text-gray' name='view1' id='view1'  size='5' readOnly='true' tabindex='2'></input></td>
					<td class="2-td2-left" nowrap="nowrap">47</td>
          <td align="left" class="2-td2-left" nowrap="nowrap"><div align="left">所属行业（工业企业   其他企业 ）</div></td>
					<td class="2-td2-center" nowrap="nowrap"><input type="Radio" name="hyje" id="hy47_1" value="01" onclick="changeSelect()" onchange="changeSelect()">工业企业</input>
								<input type="Radio" name="hyje" id="hy47_2" value="02" onclick="changeSelect()" onchange="changeSelect()">其他企业</input>
</td>
          </tr>    
				  <tr> 
          <td class="2-td2-left" nowrap="nowrap">24</td>
					<td class="2-td2-left"><div align="left">&#160;9、其他</div></td>
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
