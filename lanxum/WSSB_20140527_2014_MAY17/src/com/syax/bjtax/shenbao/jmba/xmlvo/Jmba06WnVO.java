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
 * @author guzx
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Jmba06WnVO implements JmbamxVoInterface {
	private String hc;
	/**
	 * @return Returns the dybsrnd.
	 */
	public String getDybsrnd() {
		return dybsrnd;
	}
	/**
	 * @param dybsrnd The dybsrnd to set.
	 */
	public void setDybsrnd(String dybsrnd) {
		this.dybsrnd = dybsrnd;
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
	 * @return Returns the jnjsxmlxdm.
	 */
	public String getJnjsxmlxdm() {
		return jnjsxmlxdm;
	}
	/**
	 * @param jnjsxmlxdm The jnjsxmlxdm to set.
	 */
	public void setJnjsxmlxdm(String jnjsxmlxdm) {
		this.jnjsxmlxdm = jnjsxmlxdm;
	}
	/**
	 * @return Returns the jnjsxmlxmc.
	 */
	public String getJnjsxmlxmc() {
		return jnjsxmlxmc;
	}
	/**
	 * @param jnjsxmlxmc The jnjsxmlxmc to set.
	 */
	public void setJnjsxmlxmc(String jnjsxmlxmc) {
		this.jnjsxmlxmc = jnjsxmlxmc;
	}
	/**
	 * @return Returns the jzqsnd.
	 */
	public String getJzqsnd() {
		return jzqsnd;
	}
	/**
	 * @param jzqsnd The jzqsnd to set.
	 */
	public void setJzqsnd(String jzqsnd) {
		this.jzqsnd = jzqsnd;
	}
	/**
	 * @return Returns the jzzznd.
	 */
	public String getJzzznd() {
		return jzzznd;
	}
	/**
	 * @param jzzznd The jzzznd to set.
	 */
	public void setJzzznd(String jzzznd) {
		this.jzzznd = jzzznd;
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
	 * @return Returns the qtzl.
	 */
	public String getQtzl() {
		return qtzl;
	}
	/**
	 * @param qtzl The qtzl to set.
	 */
	public void setQtzl(String qtzl) {
		this.qtzl = qtzl;
	}
	/**
	 * @return Returns the sfyhsqksm.
	 */
	public String getSfyhsqksm() {
		return sfyhsqksm;
	}
	/**
	 * @param sfyhsqksm The sfyhsqksm to set.
	 */
	public void setSfyhsqksm(String sfyhsqksm) {
		this.sfyhsqksm = sfyhsqksm;
	}
	/**
	 * @return Returns the sfyhsqksmmc.
	 */
	public String getSfyhsqksmmc() {
		return sfyhsqksmmc;
	}
	/**
	 * @param sfyhsqksmmc The sfyhsqksmmc to set.
	 */
	public void setSfyhsqksmmc(String sfyhsqksmmc) {
		this.sfyhsqksmmc = sfyhsqksmmc;
	}
	/**
	 * @return Returns the wh.
	 */
	public String getWh() {
		return wh;
	}
	/**
	 * @param wh The wh to set.
	 */
	public void setWh(String wh) {
		this.wh = wh;
	}
	/**
	 * @return Returns the wjmc.
	 */
	public String getWjmc() {
		return wjmc;
	}
	/**
	 * @param wjmc The wjmc to set.
	 */
	public void setWjmc(String wjmc) {
		this.wjmc = wjmc;
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
	/**
	 * @return Returns the zcba.
	 */
	public String getZcba() {
		return zcba;
	}
	/**
	 * @param zcba The zcba to set.
	 */
	public void setZcba(String zcba) {
		this.zcba = zcba;
	}
	/**
	 * @return Returns the zlmc.
	 */
	public String getZlmc() {
		return zlmc;
	}
	/**
	 * @param zlmc The zlmc to set.
	 */
	public void setZlmc(String zlmc) {
		this.zlmc = zlmc;
	}
    /*
     * 序号
     */
    private String xh;


    /*
     * 项目类型名称jnjsxmlxmc
     */
    private String jnjsxmlxmc;

    /*
     * 项目类型代码jnjsxmlxdm
     */
    private String jnjsxmlxdm;

    /*
     * 文件名称
     */
    private String wjmc;

    /*
     * 文号
     */
    private String wh;

    /*
     * 取得第一笔收入的相关证明资料名称
     */
    private String zlmc;

    /*
     * 取得第一笔生产经营收入的时间
     */
    private String dybsrnd;

    /*
     * 项目所得核算情况声明 0:是,1:否
     */
    private String sfyhsqksm = "0";
    /*
     * 项目所得核算情况声明 0:是,1:否
     */
    private String sfyhsqksmmc = "是";

    /*
     * 主管税务机关要求报送的其他资料
     */
    private String qtzl;

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
    private String jzqsnd;

    /*
     * 减征终止年度
     */
    private String jzzznd;

    /*
     * 再次备案
     */
    private String zcba="0";

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
		xmlstr += "<wnqysdsjmba>";
		xmlstr += XMLBuildUtil.appendStringElement("hc", hc);
		xmlstr += XMLBuildUtil.appendStringElement("xh", xh);
		xmlstr += XMLBuildUtil.appendStringElement("jnjsxmlxdm", jnjsxmlxdm);
		xmlstr += XMLBuildUtil.appendStringElement("jnjsxmlxmc", jnjsxmlxmc);
//		xmlstr += XMLBuildUtil.appendStringElement("wjmc", wjmc);
//		xmlstr += XMLBuildUtil.appendStringElement("wh", wh);
		xmlstr += XMLBuildUtil.appendStringElement("zlmc", zlmc);
		xmlstr += XMLBuildUtil.appendStringElement("dybsrnd", dybsrnd);
		xmlstr += XMLBuildUtil.appendStringElement("jzqsnd", jzqsnd);
		xmlstr += XMLBuildUtil.appendStringElement("jzzznd", jzzznd);
		xmlstr += XMLBuildUtil.appendStringElement("mzqsnd", mzqsnd);
		xmlstr += XMLBuildUtil.appendStringElement("mzzznd", mzzznd);
		xmlstr += XMLBuildUtil.appendStringElement("qtzl", qtzl);
		xmlstr += XMLBuildUtil.appendStringElement("zcba", zcba);
		xmlstr += XMLBuildUtil.appendStringElement("sfyhsqksm", sfyhsqksm);
		xmlstr += XMLBuildUtil.appendStringElement("sfyhsqksmmc", sfyhsqksmmc);
		xmlstr += "</wnqysdsjmba>";

	return xmlstr;
	}

	/* (non-Javadoc)
	 * @see com.syax.common.xml.XMLVOInterface#toXMLChilds()
	 */
	public String toXMLChilds() {
		// TODO Auto-generated method stub
		return null;
	}   
}
