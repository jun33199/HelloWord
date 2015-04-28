/*
 * Created on 2009-12-22
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.syax.bjtax.shenbao.jmba.xmlvo;

import java.util.Map;

import com.syax.common.xml.util.XMLBuildUtil;

/**
 * 
 * 项目名称：wssb   
 * 类名称：Jmba022VO   
 * 类描述：  增加节能服务公司实施合同能源管理项目的所得备案事项
 * 创建人：wangcy 
 * 创建时间：2014-2-13 下午4:50:39   
 * 修改人：wangcy   
 * 修改时间：2014-2-13 下午4:50:39   
 * 修改备注：   
 * @version  1.0
 */
public class Jmba022VO implements JmbamxVoInterface {
	private String hc;
    /*
     * 序号
     */
    private String xh;


    /*
     * 节能减排技术改造类型名称
     */
    private String jnjpjsgzxmmc;

    /*
     * 节能减排技术改造类型代码
     */
    private String jnjpjsgzxmdm;

    /*
     * 取得第一笔收入的相关证明资料名称
     */
    private String dybzlmc;

    /*
     * 取得第一笔生产经营收入的时间
     */
    private String dybrq;
    /*
     * 免征起始年度
     */
    private String mzqsnd;

    /*
     * 免征终止年度
     */
    private String mzzznd;

    /*
     * 减征起始年度
     */
    private String jbzsqsnd;

    /*
     * 减征终止年度
     */
    private String jbzszznd;
    
    
    /**
     * 是否发生合同能源管理项目转让、受让
     */
    private String ZRHTXM="";
    /**
     * 项目转让合同、项目原享受优惠备案文件
     */
    private String ZRHTXMYHWJ="";
    /**
     * 项目转让合同、项目原享受优惠
     */
    private String ZRHTXMYH="";
    
    
	public String getZRHTXM() {
		return ZRHTXM;
	}

	public void setZRHTXM(String zRHTXM) {
		ZRHTXM = zRHTXM;
	}

	public String getZRHTXMYHWJ() {
		return ZRHTXMYHWJ;
	}

	public void setZRHTXMYHWJ(String zRHTXMYHWJ) {
		ZRHTXMYHWJ = zRHTXMYHWJ;
	}

	public String getZRHTXMYH() {
		return ZRHTXMYH;
	}

	public void setZRHTXMYH(String zRHTXMYH) {
		ZRHTXMYH = zRHTXMYH;
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
		String xmlstr = "";
		xmlstr += "<qysdsjmba>";
		xmlstr += XMLBuildUtil.appendStringElement("hc", hc);
		xmlstr += XMLBuildUtil.appendStringElement("xh", xh);
		xmlstr += XMLBuildUtil.appendStringElement("jnjpjsgzxmdm", jnjpjsgzxmdm);
		xmlstr += XMLBuildUtil.appendStringElement("jnjpjsgzxmmc", jnjpjsgzxmmc);
		xmlstr += XMLBuildUtil.appendStringElement("dybzlmc", dybzlmc);
		xmlstr += XMLBuildUtil.appendStringElement("dybrq", dybrq);
		xmlstr += XMLBuildUtil.appendStringElement("jbzsqsnd", jbzsqsnd);
		xmlstr += XMLBuildUtil.appendStringElement("jbzszznd", jbzszznd);
		xmlstr += XMLBuildUtil.appendStringElement("mzqsnd", mzqsnd);
		xmlstr += XMLBuildUtil.appendStringElement("mzzznd", mzzznd);
		
		xmlstr += XMLBuildUtil.appendStringElement("ZRHTXM", ZRHTXM);
		xmlstr += XMLBuildUtil.appendStringElement("ZRHTXMYH", ZRHTXMYH);
		xmlstr += XMLBuildUtil.appendStringElement("ZRHTXMYHWJ", ZRHTXMYHWJ);
		
		xmlstr += "</qysdsjmba>";

	return xmlstr;
	}

	/* (non-Javadoc)
	 * @see com.syax.common.xml.XMLVOInterface#toXMLChilds()
	 */
	public String toXMLChilds() {
		// TODO Auto-generated method stub
		return null;
	}   
	/**
	 * @return Returns the dybrq.
	 */
	public String getDybrq() {
		return dybrq;
	}
	/**
	 * @param dybrq The dybrq to set.
	 */
	public void setDybrq(String dybrq) {
		this.dybrq = dybrq;
	}
	/**
	 * @return Returns the dybzlmc.
	 */
	public String getDybzlmc() {
		return dybzlmc;
	}
	/**
	 * @param dybzlmc The dybzlmc to set.
	 */
	public void setDybzlmc(String dybzlmc) {
		this.dybzlmc = dybzlmc;
	}
	/**
	 * @return Returns the hc.
	 */
	public String getHc() {
		return hc;
	}
	/**
	 * @param hc The hc to set.
	 */
	public void setHc(String hc) {
		this.hc = hc;
	}
	/**
	 * @return Returns the mzqsnd.
	 */
	public String getMzqsnd() {
		return mzqsnd;
	}
	/**
	 * @param mzqsnd The mzqsnd to set.
	 */
	public void setMzqsnd(String mzqsnd) {
		this.mzqsnd = mzqsnd;
	}
	/**
	 * @return Returns the mzzznd.
	 */
	public String getMzzznd() {
		return mzzznd;
	}
	/**
	 * @param mzzznd The mzzznd to set.
	 */
	public void setMzzznd(String mzzznd) {
		this.mzzznd = mzzznd;
	}
	/**
	 * @return Returns the xh.
	 */
	public String getXh() {
		return xh;
	}
	/**
	 * @param xh The xh to set.
	 */
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getJnjpjsgzxmmc() {
		return jnjpjsgzxmmc;
	}

	public void setJnjpjsgzxmmc(String jnjpjsgzxmmc) {
		this.jnjpjsgzxmmc = jnjpjsgzxmmc;
	}

	public String getJnjpjsgzxmdm() {
		return jnjpjsgzxmdm;
	}

	public void setJnjpjsgzxmdm(String jnjpjsgzxmdm) {
		this.jnjpjsgzxmdm = jnjpjsgzxmdm;
	}

	public String getJbzsqsnd() {
		return jbzsqsnd;
	}

	public void setJbzsqsnd(String jbzsqsnd) {
		this.jbzsqsnd = jbzsqsnd;
	}

	public String getJbzszznd() {
		return jbzszznd;
	}

	public void setJbzszznd(String jbzszznd) {
		this.jbzszznd = jbzszznd;
	}
	
	
}
