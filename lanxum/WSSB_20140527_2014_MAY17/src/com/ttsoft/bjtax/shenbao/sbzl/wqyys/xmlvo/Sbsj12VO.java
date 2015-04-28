package com.ttsoft.bjtax.shenbao.sbzl.wqyys.xmlvo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;

public class Sbsj12VO implements XMLVOInterface
{

    //税种税目代码；
    String szsmdm;
    String szsmmc;
    String kssl;
    String jsje;

    //税种税目名称
    String sl;
    //计算机代码
    String ynse;
    //纳税人名称
    String yjnse;
    
    //税款类型代码
    String bqybse;
    //税款类型名称
    String sre;
    //征收机关代码
    String htcje;
    //征收机关名称
    String yjl;
    //征收机关代码
    String hdsre;
    //征收机关名称
    String jfzce;
    //税种代码
    String hssre;

    
    public Sbsj12VO()
    {
        super();
    }
    public Map getListTypeMap()
    {
        return null;
    }
    public String toXML()
    {
        String xmlstr = "<sbsj>";
        
        xmlstr += toXMLChilds();
        xmlstr += "</sbsj>";
        return xmlstr;
    }
    public String toXMLChilds()
    {
        String xmlstr = "";
        xmlstr += XMLBuildUtil.appendStringElement("szsmdm",szsmdm);
        xmlstr += XMLBuildUtil.appendStringElement("kssl",kssl);
        xmlstr += XMLBuildUtil.appendStringElement("jsje",jsje);
        xmlstr += XMLBuildUtil.appendStringElement("sl",sl);
        xmlstr += XMLBuildUtil.appendStringElement("ynse",ynse);
        xmlstr += XMLBuildUtil.appendStringElement("yjnse",yjnse);
        xmlstr += XMLBuildUtil.appendStringElement("bqybse",bqybse);
        xmlstr += XMLBuildUtil.appendStringElement("sre",sre);
        xmlstr += XMLBuildUtil.appendStringElement("htcje",htcje);

        xmlstr += XMLBuildUtil.appendStringElement("yjl",yjl);
        xmlstr += XMLBuildUtil.appendStringElement("hdsre",hdsre);
        xmlstr += XMLBuildUtil.appendStringElement("jfzce",jfzce);
        xmlstr += XMLBuildUtil.appendStringElement("hssre",hssre);


        return xmlstr;
    }
    

	/**
	 * @return Returns the bqybse.
	 */
	public String getBqybse() {
		return bqybse;
	}
	/**
	 * @param bqybse The bqybse to set.
	 */
	public void setBqybse(String bqybse) {
		this.bqybse = bqybse;
	}
	/**
	 * @return Returns the hdsre.
	 */
	public String getHdsre() {
		return hdsre;
	}
	/**
	 * @param hdsre The hdsre to set.
	 */
	public void setHdsre(String hdsre) {
		this.hdsre = hdsre;
	}
	/**
	 * @return Returns the hssre.
	 */
	public String getHssre() {
		return hssre;
	}
	/**
	 * @param hssre The hssre to set.
	 */
	public void setHssre(String hssre) {
		this.hssre = hssre;
	}
	/**
	 * @return Returns the htcje.
	 */
	public String getHtcje() {
		return htcje;
	}
	/**
	 * @param htcje The htcje to set.
	 */
	public void setHtcje(String htcje) {
		this.htcje = htcje;
	}
	/**
	 * @return Returns the jfzce.
	 */
	public String getJfzce() {
		return jfzce;
	}
	/**
	 * @param jfzce The jfzce to set.
	 */
	public void setJfzce(String jfzce) {
		this.jfzce = jfzce;
	}
	/**
	 * @return Returns the jsje.
	 */
	public String getJsje() {
		return jsje;
	}
	/**
	 * @param jsje The jsje to set.
	 */
	public void setJsje(String jsje) {
		this.jsje = jsje;
	}
	/**
	 * @return Returns the kssl.
	 */
	public String getKssl() {
		return kssl;
	}
	/**
	 * @param kssl The kssl to set.
	 */
	public void setKssl(String kssl) {
		this.kssl = kssl;
	}
	/**
	 * @return Returns the sl.
	 */
	public String getSl() {
		return sl;
	}
	/**
	 * @param sl The sl to set.
	 */
	public void setSl(String sl) {
		this.sl = sl;
	}
	/**
	 * @return Returns the sre.
	 */
	public String getSre() {
		return sre;
	}
	/**
	 * @param sre The sre to set.
	 */
	public void setSre(String sre) {
		this.sre = sre;
	}
	/**
	 * @return Returns the szsmdm.
	 */
	public String getSzsmdm() {
		return szsmdm;
	}
	/**
	 * @param szsmdm The szsmdm to set.
	 */
	public void setSzsmdm(String szsmdm) {
		this.szsmdm = szsmdm;
	}
	/**
	 * @return Returns the yjl.
	 */
	public String getYjl() {
		return yjl;
	}
	/**
	 * @param yjl The yjl to set.
	 */
	public void setYjl(String yjl) {
		this.yjl = yjl;
	}
	/**
	 * @return Returns the yjnse.
	 */
	public String getYjnse() {
		return yjnse;
	}
	/**
	 * @param yjnse The yjnse to set.
	 */
	public void setYjnse(String yjnse) {
		this.yjnse = yjnse;
	}
	/**
	 * @return Returns the ynse.
	 */
	public String getYnse() {
		return ynse;
	}
	/**
	 * @param ynse The ynse to set.
	 */
	public void setYnse(String ynse) {
		this.ynse = ynse;
	}
	/**
	 * @return Returns the szsmmc.
	 */
	public String getSzsmmc() {
		return szsmmc;
	}
	/**
	 * @param szsmmc The szsmmc to set.
	 */
	public void setSzsmmc(String szsmmc) {
		this.szsmmc = szsmmc;
	}
}
