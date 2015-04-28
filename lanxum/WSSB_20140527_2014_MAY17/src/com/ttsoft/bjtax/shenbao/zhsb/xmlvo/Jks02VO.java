/*
 * Created on 2006-4-28
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ttsoft.bjtax.shenbao.zhsb.xmlvo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;

import com.syax.bjtax.ca.vo.YWRootVO;
import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;

/**
 * @author guzx
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Jks02VO implements XMLVOInterface{

    //税种税目代码；
    String jkpzh;

    String sfkyzf;
    
    
    //税种代码
    String szdm;
    //税种名称
    String szmc;
    //预算级次代码
    String ysjcmc;

    //计算机代码
    String yskmdm;
    //纳税人名称
    String yskmmc;
    //税款类型代码
    String kkzt;
    //税款类型名称
    String sjje;
    //征收机关代码
    String sjjedx;
    //限缴日期
    String xjrq;
    //录入人
    String lxdh;
    //税款所属开始日期
    String skssksrq;
    //税款所属结束日期
    String skssjsrq;
    String bz;
   
	/**
	 * 
	 */
	public Jks02VO() {
		super();
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.syax.common.xml.XMLVOInterface#getListTypeMap()
	 */
	public Map getListTypeMap() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.syax.common.xml.XMLVOInterface#toXML()
	 */
	public String toXML() {
        String xmlstr = "<jks>";
        
        xmlstr += toXMLChilds();
        xmlstr += "</jks>";
        return xmlstr;
	}

	/* (non-Javadoc)
	 * @see com.syax.common.xml.XMLVOInterface#toXMLChilds()
	 */
	public String toXMLChilds() {
        String xmlstr = "";
        xmlstr += XMLBuildUtil.appendStringElement("sfkyzf",sfkyzf);
        xmlstr += XMLBuildUtil.appendStringElement("jkpzh",jkpzh);
        xmlstr += XMLBuildUtil.appendStringElement("szdm",szdm);
        xmlstr += XMLBuildUtil.appendStringElement("szmc",szmc);
        xmlstr += XMLBuildUtil.appendStringElement("yskmdm",yskmdm);
        xmlstr += XMLBuildUtil.appendStringElement("yskmmc",yskmmc);
        xmlstr += XMLBuildUtil.appendStringElement("ysjcmc",ysjcmc);
        xmlstr += XMLBuildUtil.appendStringElement("kkzt",kkzt);
        xmlstr += XMLBuildUtil.appendStringElement("sjje",sjje);
        xmlstr += XMLBuildUtil.appendStringElement("sjjedx",sjjedx);
        
        xmlstr += XMLBuildUtil.appendStringElement("skssksrq",skssksrq);
        xmlstr += XMLBuildUtil.appendStringElement("skssjsrq",skssjsrq);
        xmlstr += XMLBuildUtil.appendStringElement("xjrq",xjrq);
        xmlstr += XMLBuildUtil.appendStringElement("bz",bz);
        
        return xmlstr;
	}

	/**
	 * @return Returns the bz.
	 */
	public String getBz() {
		return bz;
	}
	/**
	 * @param bz The bz to set.
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}
	/**
	 * @return Returns the jkpzh.
	 */
	public String getJkpzh() {
		return jkpzh;
	}
	/**
	 * @param jkpzh The jkpzh to set.
	 */
	public void setJkpzh(String jkpzh) {
		this.jkpzh = jkpzh;
	}
	/**
	 * @return Returns the kkzt.
	 */
	public String getKkzt() {
		return kkzt;
	}
	/**
	 * @param kkzt The kkzt to set.
	 */
	public void setKkzt(String kkzt) {
		this.kkzt = kkzt;
	}
	/**
	 * @return Returns the lxdh.
	 */
	public String getLxdh() {
		return lxdh;
	}
	/**
	 * @param lxdh The lxdh to set.
	 */
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	/**
	 * @return Returns the sfkyzf.
	 */
	public String getSfkyzf() {
		return sfkyzf;
	}
	/**
	 * @param sfkyzf The sfkyzf to set.
	 */
	public void setSfkyzf(String sfkyzf) {
		this.sfkyzf = sfkyzf;
	}
	/**
	 * @return Returns the sjje.
	 */
	public String getSjje() {
		return sjje;
	}
	/**
	 * @param sjje The sjje to set.
	 */
	public void setSjje(String sjje) {
		this.sjje = sjje;
	}
	/**
	 * @return Returns the sjjedx.
	 */
	public String getSjjedx() {
		return sjjedx;
	}
	/**
	 * @param sjjedx The sjjedx to set.
	 */
	public void setSjjedx(String sjjedx) {
		this.sjjedx = sjjedx;
	}
	/**
	 * @return Returns the skssjsrq.
	 */
	public String getSkssjsrq() {
		return skssjsrq;
	}
	/**
	 * @param skssjsrq The skssjsrq to set.
	 */
	public void setSkssjsrq(String skssjsrq) {
		this.skssjsrq = skssjsrq;
	}
	/**
	 * @return Returns the skssksrq.
	 */
	public String getSkssksrq() {
		return skssksrq;
	}
	/**
	 * @param skssksrq The skssksrq to set.
	 */
	public void setSkssksrq(String skssksrq) {
		this.skssksrq = skssksrq;
	}
	/**
	 * @return Returns the szdm.
	 */
	public String getSzdm() {
		return szdm;
	}
	/**
	 * @param szdm The szdm to set.
	 */
	public void setSzdm(String szdm) {
		this.szdm = szdm;
	}
	/**
	 * @return Returns the szmc.
	 */
	public String getSzmc() {
		return szmc;
	}
	/**
	 * @param szmc The szmc to set.
	 */
	public void setSzmc(String szmc) {
		this.szmc = szmc;
	}
	/**
	 * @return Returns the xjrq.
	 */
	public String getXjrq() {
		return xjrq;
	}
	/**
	 * @param xjrq The xjrq to set.
	 */
	public void setXjrq(String xjrq) {
		this.xjrq = xjrq;
	}
	/**
	 * @return Returns the ysjcmc.
	 */
	public String getYsjcmc() {
		return ysjcmc;
	}
	/**
	 * @param ysjcmc The ysjcmc to set.
	 */
	public void setYsjcmc(String ysjcmc) {
		this.ysjcmc = ysjcmc;
	}
	/**
	 * @return Returns the yskmdm.
	 */
	public String getYskmdm() {
		return yskmdm;
	}
	/**
	 * @param yskmdm The yskmdm to set.
	 */
	public void setYskmdm(String yskmdm) {
		this.yskmdm = yskmdm;
	}
	/**
	 * @return Returns the yskmmc.
	 */
	public String getYskmmc() {
		return yskmmc;
	}
	/**
	 * @param yskmmc The yskmmc to set.
	 */
	public void setYskmmc(String yskmmc) {
		this.yskmmc = yskmmc;
	}
}
