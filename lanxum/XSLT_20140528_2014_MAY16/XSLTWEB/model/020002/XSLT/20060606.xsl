<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format">
<xsl:output method="html"/>
<xsl:template match="/">
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
		<xsl:apply-templates select="taxdoc/nsrxx"/><br/>
		<xsl:apply-templates select="taxdoc/lxrxxlist"/><br/>
		<xsl:apply-templates select="taxdoc/swdlxx"/><br/>
</xsl:template>
<xsl:template match="taxdoc/nsrxx">
 <table width="78%" cellspacing="0"  class="table-99" >
              <tr bordercolor="#9BB4CA">
                <td width="9%" nowrap="nowrap" class="2-td2-t-left"> <div align="right">计算机代码</div></td>
                <td colspan="4" nowrap="nowrap" class="2-td2-t-center"> <div align="left">
                    <font color="#57937D">
<input name="jsjdm" id="jsjdm" maxlength="8" Class="txtInput" readonly="true" size="20" onkeydown="output()" disabled="disabled">		
                   <xsl:attribute name="value"><xsl:value-of select="jsjdm"/></xsl:attribute>
		</input></font> </div>
                  <div align="right"></div>
                  <div align="left"> </div>
                  <div align="left"> </div></td>
              </tr>
              <tr >
                <td nowrap="nowrap" class="2-td2-left"><div align="right">纳税人名称</div></td>
                <td colspan="4" nowrap="nowrap" class="2-td2-center"> <div align="left">
                   <input name="nsrmc" id="nsrmc" maxlength="8" Class="txtInput" readonly="true" size="60" disabled="disabled">		
                   <xsl:attribute name="value"><xsl:value-of select="nsrmc"/></xsl:attribute>
		</input></div>

                  <div align="right"></div>
                  <div align="left"> <span class="bitian"> </span></div></td>
              </tr>
              <tr >
                <td rowspan="2"  nowrap="nowrap" class="2-td2-left">注册地</td>
                <td  nowrap="nowrap" class="2-td2-left">地址</td>
                <td colspan="3"  nowrap="nowrap" class="2-td2-center">
 <input name="zcdz" id="zcdz" size="83" disabled="disabled">		
                   <xsl:attribute name="value"><xsl:value-of select="zcdz/zcddz"/></xsl:attribute>
		</input>
                  <span class="bitian">*</span></td>
              </tr>
              <tr >
                <td width="4%" nowrap="nowrap" class="2-td2-left">
                    <div align="right">邮编</div></td>
                <td width="47%" nowrap="nowrap" class="2-td2-left"><div align="left">
<input name="zcdzyb" id="zcdzyb" maxlength="6" size="20">		
                   <xsl:attribute name="value"><xsl:value-of select="zcdz/zcdzyzbm"/></xsl:attribute>
		</input>

                  </div></td>
                <td width="11%" nowrap="nowrap" class="2-td2-left">联系电话</td>
                <td width="29%" nowrap="nowrap" class="2-td2-center"><div align="left">
<input name="zcdzlxdh" id="zcdzlxdh" maxlength="50" size="25">		
                   <xsl:attribute name="value"><xsl:value-of select="zcdz/zcdzlxdh"/></xsl:attribute>
		</input>

                  </div></td>
              </tr>

               <tr >
                <td rowspan="2" nowrap="nowrap" class="2-td2-left">经营地</td>
                <td nowrap="nowrap" class="2-td2-left">地址</td>
                <td colspan="3" nowrap="nowrap" class="2-td2-center">	
       <input name="jydz" id="jydz" size="83">		
                   <xsl:attribute name="value"><xsl:value-of select="jydz/jyddz"/></xsl:attribute>
		</input>

                  <span class="bitian">*</span></td>
              </tr>
              <tr >
                <td width="4%" nowrap="nowrap" class="2-td2-left">
                <div align="right">邮编</div></td>
                <td width="47%" nowrap="nowrap" class="2-td2-left"><div align="left">
<input name="yydzyb" id="yydzyb" maxlength="6" size="20">		
                   <xsl:attribute name="value"><xsl:value-of select="jydz/jydzyzbm"/></xsl:attribute>
		</input>

                  </div></td>
                <td width="11%" nowrap="nowrap" class="2-td2-left">联系电话</td>
                <td width="29%" nowrap="nowrap" class="2-td2-center"><div align="left">
<input name="yydzlxdm" id="yydzlxdm" maxlength="50" size="25">		
                   <xsl:attribute name="value"><xsl:value-of select="jydz/jydzlxdh"/></xsl:attribute>
		</input>

                  </div></td>
              </tr>
            </table>
</xsl:template>
<xsl:template match="taxdoc/lxrxxlist">
<table  cellspacing="0" class="table-99">
              <tr>
                <td> <hr width="100%" size="1" class="hr1" /> </td>
                <td width="87" align="center" class="black9"><strong> 联系人信息</strong></td>
                <td > <hr width="100%" size="1" class="hr1" /> </td>
              </tr>
            </table>
            <table cellspacing="0" border="0" class="table-99"   id="TABLE_LIST">
          <tr>
            <td width="11%" class="2-td1-left">　</td>
            <td width="9%" class="2-td1-left">姓名</td>
            <td width="12%" class="2-td1-left">证件类型</td>
            <td width="21%" class="2-td1-left">证件号码</td>
            <td width="20%" class="2-td1-left">有效联系电话</td>
            <td width="10%" class="2-td1-left">移动电话</td>
            <td width="17%" class="2-td1-center">个人电子邮箱（E-mail)</td>
          </tr>
              <tr>
            <td class="2-td2-left">法人代表（负责人）</td>
            <td nowrap="nowrap" class="2-td2-left">
            <div align="left">
<input name="xm" id="xm" maxlength="50" type="text" class="txtinput" readonly="true"  size="9">	
                   <xsl:attribute name="value"><xsl:value-of select="frdb/frdbxm"/></xsl:attribute>
</input>                  
                  </div></td>
                <td nowrap="nowrap" class="2-td2-left"><div align="left">
                    <select id="zjlx_frdb" name="zjlx_frdb" disabled="disabled" class="black9" style="width:70px"></select>
                  </div></td>
                <td nowrap="nowrap" class="2-td2-left"> <div align="left">
<input name="zjhm" id="zjhm" maxlength="18" type="text" class="txtinput" size="20" readonly="true" >	
                   <xsl:attribute name="value"><xsl:value-of select="frdb/frdbzjhm"/></xsl:attribute>
</input>    
                  </div></td>
                <td class="2-td2-left"> <div align="left">
<input name="gddh" id="gddh" maxlength="50" type="text" size="20">	
                   <xsl:attribute name="value"><xsl:value-of select="frdb/frdblxdh"/></xsl:attribute>
</input>
                  </div></td>
                <td class="2-td2-left"> <div align="left">
  <input name="yddh" id="yddh" type="text" size="10" maxlength="11">	
                   <xsl:attribute name="value"><xsl:value-of select="frdb/frdbyddh"/></xsl:attribute>
</input>
                  </div></td>
                <td class="2-td2-center">
                    <input name="dzyx" id="dzyx" maxlength="50" type="text" size="16">	
                   <xsl:attribute name="value"><xsl:value-of select="frdb/frdbdzyx"/></xsl:attribute>
</input>
                </td>
              </tr>

              <tr>
                <td class="2-td2-left">财务负责人</td>
                <td nowrap="nowrap" class="2-td2-left"> <div align="left">
                   <input name="xm" id="xm" maxlength="50" type="text" size="9">	
                   <xsl:attribute name="value"><xsl:value-of select="cwry/cwryxm"/></xsl:attribute>
</input>                      </div></td>
                <td nowrap="nowrap" class="2-td2-left"><div align="left">
                    <select id="zjlx_cwry" name="zjlx_cwry" class="black9" style="width:70px"></select>
                  </div></td>
                <td nowrap="nowrap" class="2-td2-left"> <div align="left">
                    <input name="zjhm" id="zjhm" maxlength="18" type="text" size="20">	
                   <xsl:attribute name="value"><xsl:value-of select="cwry/cwryzjhm"/></xsl:attribute>
</input>    
                  </div></td>
                <td class="2-td2-left"> <div align="left">
                   <input name="gddh" id="gddh" maxlength="50" type="text" size="20">	
                   <xsl:attribute name="value"><xsl:value-of select="cwry/cwrylxdh"/></xsl:attribute>
</input>
                  </div></td>
                <td class="2-td2-left"> <div align="left">
                     <input name="yddh" id="yddh" type="text" size="10" maxlength="11">	
                   <xsl:attribute name="value"><xsl:value-of select="cwry/cwryyddh"/></xsl:attribute>
</input>
                  </div></td>
                <td class="2-td2-center">
                    <input name="dzyx" id="dzyx" maxlength="50" type="text" size="16">	
                   <xsl:attribute name="value"><xsl:value-of select="cwry/cwrydzyx"/></xsl:attribute>
</input>
                </td>
              </tr>
              <tr>
                <td class="2-td2-left">办税人员</td>
                <td nowrap="nowrap" class="2-td2-left"> <div align="left">
                   <input name="xm" id="xm" maxlength="50" type="text" size="9">	
                   <xsl:attribute name="value"><xsl:value-of select="bsry/bsryxm"/></xsl:attribute>
</input>                      </div></td>
                <td nowrap="nowrap" class="2-td2-left"><div align="left">
                    <select id="zjlx_bsry" name="zjlx_bsry" class="black9" style="width:70px"></select>
                  </div></td>
                <td nowrap="nowrap" class="2-td2-left"> <div align="left">
                    <input name="zjhm" id="zjhm" maxlength="18" type="text" size="20">	
                   <xsl:attribute name="value"><xsl:value-of select="bsry/bsryzjhm"/></xsl:attribute>
</input>    
                  </div></td>
                <td class="2-td2-left"> <div align="left">
                   <input name="gddh" id="gddh" maxlength="50" type="text" size="20">	
                   <xsl:attribute name="value"><xsl:value-of select="bsry/bsrylxdh"/></xsl:attribute>
</input>     </div></td>
                <td class="2-td2-left"> <div align="left">
                    <input name="yddh" id="yddh" type="text" size="10" maxlength="11">	
                   <xsl:attribute name="value"><xsl:value-of select="bsry/bsryyddh"/></xsl:attribute>
</input>
                  </div></td>
                <td class="2-td2-center">
                    <input name="dzyx" id="dzyx" maxlength="50" type="text" size="16">	
                   <xsl:attribute name="value"><xsl:value-of select="bsry/bsrydzyx"/></xsl:attribute>
</input>
                </td>
               </tr>
              <tr >
            <td colspan="2" class="2-td2-left">是否有税务代理信息</td>
            <td colspan="5" class="2-td2-center">
            <div align="left">
                    <input type="checkbox" name="swdl" value="1" onClick="display_swdl()">
		      <xsl:if test="sfyswdl='1'">
		        <xsl:attribute name="checked"/>
		      </xsl:if>
		    </input>
                    </div></td>
              </tr>
            </table>
</xsl:template>
<xsl:template match="taxdoc/swdlxx">
<table cellspacing="0" class="table-99" id="table_swdl" >
          <tr>
            <td>
            <table  cellspacing="0" class="table-99">
              <tr>
                <td  > <hr width="100%" size="1" class="hr1" /> </td>
                <td width="87" align="center" class="black9"><strong> 税务代理信息</strong></td>
                <td > <hr width="100%" size="1" class="hr1" /> </td>
              </tr>
            </table>
            <table cellspacing="0" border="0" class="table-99">
              <tr>
                <td width="12%" class="2-td1-left">　</td>
                <td width="11%" class="2-td1-left">名称<span class="bitian">*</span></td>
                <td width="20%" class="2-td1-left">税务登记证号<span class="bitian">*</span></td>
                <td width="19%" class="2-td1-left">有效联系电话</td>
                <td width="14%" class="2-td1-left">移动电话</td>
                <td width="24%" class="2-td1-center">联系电子邮箱（E-mail)</td>
              </tr>
              <tr>
                <td class="2-td2-left">税务代理</td>
                <td nowrap="nowrap" class="2-td2-left">
                <div align="left">
                <input name="swdlmc" id="swdlmc" maxlength="50" type="text" size="12">	
                   <xsl:attribute name="value"><xsl:value-of select="mc"/></xsl:attribute>
</input>

                </div>
                </td>
                <td nowrap="nowrap" class="2-td2-left">
                <div align="left">
<input name="swdlswdjzh" id="swdlswdjzh" maxlength="30" type="text" size="20">	
                   <xsl:attribute name="value"><xsl:value-of select="swdjzh"/></xsl:attribute>
</input>

                </div>
                </td>
                <td class="2-td2-left">
                <div align="left">
<input name="swdlgddh" id="swdlgddh" maxlength="30" type="text" size="20">	
                   <xsl:attribute name="value"><xsl:value-of select="lxdh"/></xsl:attribute>
</input>
                </div>
                </td>
                <td class="2-td2-left">
                <div align="left">
                  <input name="swdlyddh" id="swdlyddh" maxlength="11" type="text" size="10">	
                   <xsl:attribute name="value"><xsl:value-of select="yddh"/></xsl:attribute>
</input>
                </div>
                </td>
                <td class="2-td2-center">
                                  <input name="swdldzyx" id="swdldzyx" maxlength="60" type="text" size="20">	
                   <xsl:attribute name="value"><xsl:value-of select="dzyx"/></xsl:attribute>
</input>
                </td>
              </tr>
            </table>
            </td>
          </tr>
        </table>
</xsl:template>
</xsl:stylesheet>
