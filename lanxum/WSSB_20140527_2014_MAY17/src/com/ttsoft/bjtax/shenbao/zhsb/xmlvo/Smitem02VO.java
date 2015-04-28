/*
 * Created on 2006-4-28
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ttsoft.bjtax.shenbao.zhsb.xmlvo;

import java.math.BigDecimal;
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
public class Smitem02VO implements XMLVOInterface{

	/**
     * 申报方式（按次1，按月0）
     */
	private String sbfs;
	/**
     * 税种名称
     */
    private String jkpzh;

    /**
     * 税种税目代码
     */
    private String szsmdm;

    /**
     * 税种名称
     */
    private String szmc;

    /**
     * 税种税目名称
     */
    private String szsmmc;

    /**
     * 课税数量
     */
    private String kssl;

    /**
     * 计税金额
     */
    private String jsje;

    /**
     * 实缴税额
     */
    private String sjse;

    /**
     * 税率
     */
    private String sl;

    /**
     * 缴纳期限
     */
    private String coefficient ;

    /**
     * 按数量计标识
     */
    private String asljbs;

    /**
     * 是否按课税数量计
     */
    private String aksslj;

    /**
     * 数据是否可编辑
     */
  //  private boolean readOnly = false;

    /**
     * 是否是定期定额数据
     */
  //  private boolean fromDqde = false;

    /**
     * 是否是附加税
     */
  //  private boolean isFjs = false;
    
	/**
	 * 
	 */
    
    /**
     * 预算科目编码
     */
    private String yskmbm;
    
    /**
     * 预算科目名称
     */
    private String yskmmc;
    
    /**
     * 预算科目级次
     */
    private String yskmjc;    

    /**
     * 税款所属时期
     */
    private String sksssq;  
    
	public Smitem02VO() {
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
        String xmlstr = "<smitem>";
        
        xmlstr += toXMLChilds();
        xmlstr += "</smitem>";
        return xmlstr;
	}

	/* (non-Javadoc)
	 * @see com.syax.common.xml.XMLVOInterface#toXMLChilds()
	 */
	public String toXMLChilds() {
        String xmlstr = "";
        xmlstr += XMLBuildUtil.appendStringElement("jkpzh",jkpzh);
        xmlstr += XMLBuildUtil.appendStringElement("szmc",szmc);
        xmlstr += XMLBuildUtil.appendStringElement("szsmdm",szsmdm);
        xmlstr += XMLBuildUtil.appendStringElement("szsmmc",szsmmc);
        xmlstr += XMLBuildUtil.appendStringElement("kssl",kssl);
        xmlstr += XMLBuildUtil.appendStringElement("jsje",jsje);
        xmlstr += XMLBuildUtil.appendStringElement("sjse",sjse);
        xmlstr += XMLBuildUtil.appendStringElement("sl",sl);
        
        xmlstr += XMLBuildUtil.appendStringElement("aksslj",aksslj);
        xmlstr += XMLBuildUtil.appendStringElement("asljbs",asljbs);
        xmlstr += XMLBuildUtil.appendStringElement("coefficient",coefficient);
        
        xmlstr += XMLBuildUtil.appendStringElement("sbfs",sbfs);
        xmlstr += XMLBuildUtil.appendStringElement("yskmbm",yskmbm);
        xmlstr += XMLBuildUtil.appendStringElement("yskmmc",yskmmc);
        xmlstr += XMLBuildUtil.appendStringElement("yskmjc",yskmjc);
        xmlstr += XMLBuildUtil.appendStringElement("sksssq",sksssq);        
        return xmlstr;
	}

	/**
	 * @return Returns the aksslj.
	 */
	public String getAksslj() {
		return aksslj;
	}
	/**
	 * @param aksslj The aksslj to set.
	 */
	public void setAksslj(String aksslj) {
		this.aksslj = aksslj;
	}
	/**
	 * @return Returns the asljbs.
	 */
	public String getAsljbs() {
		return asljbs;
	}
	/**
	 * @param asljbs The asljbs to set.
	 */
	public void setAsljbs(String asljbs) {
		this.asljbs = asljbs;
	}
	/**
	 * @return Returns the coefficient.
	 */
	public String getCoefficient() {
		return coefficient;
	}
	/**
	 * @param coefficient The coefficient to set.
	 */
	public void setCoefficient(String coefficient) {
		this.coefficient = coefficient;
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
	 * @return Returns the sjse.
	 */
	public String getSjse() {
		return sjse;
	}
	/**
	 * @param sjse The sjse to set.
	 */
	public void setSjse(String sjse) {
		this.sjse = sjse;
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
	
	/**
	 * @return Returns the yskmbm.
	 */
	public String getYskmbm() {
		return yskmbm;
	}
	/**
	 * @param yskmbm The yskmbm to set.
	 */
	public void setYskmbm(String yskmbm) {
		this.yskmbm = yskmbm;
	}
	
	/**
	 * @return Returns the yskmmc.
	 */
	public String getYskmmc() {
		return yskmmc;
	}
	/**
	 * @param yskmbm The yskmbm to set.
	 */
	public void setYskmmc(String yskmmc) {
		this.yskmmc = yskmmc;
	}
	
	/**
	 * @return Returns the yskmjc.
	 */
	public String getYskmjc() {
		return yskmjc;
	}
	/**
	 * @param yskmjc The yskmjc to set.
	 */
	public void setYskmjc(String yskmjc) {
		this.yskmjc = yskmjc;
	}

	/**
	 * @return Returns the sksssq.
	 */
	public String getSksssq() {
		return sksssq;
	}
	/**
	 * @param sksssq The sksssq to set.
	 */
	public void setSksssq(String sksssq) {
		this.sksssq = sksssq;
	}

	public String getSbfs() {
		return sbfs;
	}

	public void setSbfs(String sbfs) {
		this.sbfs = sbfs;
	}	
	
}
