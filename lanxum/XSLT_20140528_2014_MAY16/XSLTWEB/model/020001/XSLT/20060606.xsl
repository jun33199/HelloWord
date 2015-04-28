<?xml version="1.0" encoding="gb2312"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
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
		   <xsl:apply-templates select="taxdoc/nsrxx"/>
                       <br/>	
	</xsl:template>
	<xsl:template match="taxdoc/nsrxx">
	  <table width="78%" cellspacing="0" class="table-99">
            <tr bordercolor="#9BB4CA">
              <td width="15%" height="23" class="2-td2-t-left">
              <div align="right">
                计算机代码</div>
              </td>
              <td width="85%" class="2-td2-t-center">
              <div align="left">
                <font color="#57937D">
                <input name="jsjdm" id="jsjdm" maxlength="8" Class="txtInput" readonly="true" size="20">		
                   <xsl:attribute name="value"><xsl:value-of select="jsjdm"/></xsl:attribute>
		</input>
                </font>
              </div>
              <div align="right">
              </div>
              <div align="left">
              </div>
              <div align="left">
              </div>
              </td>
            </tr>
            <tr>
              <td class="2-td2-left">
              <div align="right">
                纳税人名称</div>
              </td>
              <td class="2-td2-center">
              <div align="left">
                <span class="bitian">
		<input name="nsrmc" id="nsrmc" maxlength="8" Class="txtInput" readonly="true" size="60">		
                   <xsl:attribute name="value"><xsl:value-of select="nsrmc"/></xsl:attribute>
		</input>
                </span>
		<input name="swjgzzjgdm" type="hidden" id="swjgzzjgdm">		
                   <xsl:attribute name="value"><xsl:value-of select="swjgzzjgdm"/></xsl:attribute>
	      </input>
              </div>
              </td>
            </tr>
          </table>
          <p>
          </p>
          <table cellspacing="0" class="table-99" id="Table_List" style="TABLE-LAYOUT: fixed">
            <tr>
              <td width="7%" align="middle" class="2-td1-left">序号</td>
              <td width="22%" align="middle" class="2-td1-left">分支机构名称<span class="bitian">*</span></td>	  
              <td width="21%" align="middle" class="2-td1-left">注册地址<span class="bitian">*</span></td>
              <td width="22%" align="middle" class="2-td1-left">税务登记证号<span class="bitian">*</span></td>
	      <td width="20%" align="middle" class="2-td1-center">分支机构电话<span class="bitian">*</span></td>
            </tr>
	    <xsl:for-each select="//fzjgxx">
             <tr>
              <td width="7%" align="middle" class="2-td1-left"><xsl:number value="position()"/></td>
              <td width="22%" align="middle" class="2-td1-left"><xsl:value-of select="fzjgmc"/></td>	  
              <td width="21%" align="middle" class="2-td1-left"><xsl:value-of select="zcdz"/></td>
              <td width="22%" align="middle" class="2-td1-left"><xsl:value-of select="fzjgswdjzh"/></td>
	      <td width="20%" align="middle" class="2-td1-center"><xsl:value-of select="fzjglxdh"/></td>
            </tr>	   
	  </xsl:for-each>
           </table>
       
          <font color="#57937D"></font>
	</xsl:template>	
</xsl:stylesheet>
