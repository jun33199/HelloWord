package com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2008.xmlvo;

import java.util.*;

import com.syax.common.xml.*;
import com.syax.common.xml.util.*;

public class CzzsSbsjVO implements XMLVOInterface {

	private String sbrqshow;

	private String nsfs;

	private String zfjg;

	private String yysr;

	private String yycb;

	private String lrze;

	private String sl;

	private String ynsdse;

	private String jmsdse;

	private String sjyjsdse;

	private String ybtsdse;

	private String zjgftsdse;

	private String zyczjzsdse;

	private String fzjgftsdse;

    private String jzjnfzjgsdse;

	private String fpbl;

	private String fpsdse;

	/**
	 * @return Returns the fpbl.
	 */
	public String getFpbl() {
		return fpbl;
	}

	/**
	 * @param fpbl
	 *            The fpbl to set.
	 */
	public void setFpbl(String fpbl) {
		this.fpbl = fpbl;
	}

	/**
	 * @return Returns the fpsdse.
	 */
	public String getFpsdse() {
		return fpsdse;
	}

	/**
	 * @param fpsdse
	 *            The fpsdse to set.
	 */
	public void setFpsdse(String fpsdse) {
		this.fpsdse = fpsdse;
	}

	/**
	 * @return Returns the fzjgftsdse.
	 */
	public String getFzjgftsdse() {
		return fzjgftsdse;
	}

	/**
	 * @param fzjgftsdse
	 *            The fzjgftsdse to set.
	 */
	public void setFzjgftsdse(String fzjgftsdse) {
		this.fzjgftsdse = fzjgftsdse;
	}

	/**
	 * @return Returns the jmsdse.
	 */
	public String getJmsdse() {
		return jmsdse;
	}

	/**
	 * @param jmsdse
	 *            The jmsdse to set.
	 */
	public void setJmsdse(String jmsdse) {
		this.jmsdse = jmsdse;
	}

	/**
	 * @return Returns the lrze.
	 */
	public String getLrze() {
		return lrze;
	}

	/**
	 * @param lrze
	 *            The lrze to set.
	 */
	public void setLrze(String lrze) {
		this.lrze = lrze;
	}

	/**
	 * @return Returns the nsfs.
	 */
	public String getNsfs() {
		return nsfs;
	}

	/**
	 * @param nsfs
	 *            The nsfs to set.
	 */
	public void setNsfs(String nsfs) {
		this.nsfs = nsfs;
	}

	/**
	 * @return Returns the sbrqshow.
	 */
	public String getSbrqshow() {
		return sbrqshow;
	}

	/**
	 * @param sbrqshow
	 *            The sbrqshow to set.
	 */
	public void setSbrqshow(String sbrqshow) {
		this.sbrqshow = sbrqshow;
	}

	/**
	 * @return Returns the sjyjsdse.
	 */
	public String getSjyjsdse() {
		return sjyjsdse;
	}

	/**
	 * @param sjyjsdse
	 *            The sjyjsdse to set.
	 */
	public void setSjyjsdse(String sjyjsdse) {
		this.sjyjsdse = sjyjsdse;
	}

	/**
	 * @return Returns the sl.
	 */
	public String getSl() {
		return sl;
	}

	/**
	 * @param sl
	 *            The sl to set.
	 */
	public void setSl(String sl) {
		this.sl = sl;
	}

	/**
	 * @return Returns the ybtsdse.
	 */
	public String getYbtsdse() {
		return ybtsdse;
	}

	/**
	 * @param ybtsdse
	 *            The ybtsdse to set.
	 */
	public void setYbtsdse(String ybtsdse) {
		this.ybtsdse = ybtsdse;
	}

	/**
	 * @return Returns the ynsdse.
	 */
	public String getYnsdse() {
		return ynsdse;
	}

	/**
	 * @param ynsdse
	 *            The ynsdse to set.
	 */
	public void setYnsdse(String ynsdse) {
		this.ynsdse = ynsdse;
	}

	/**
	 * @return Returns the yycb.
	 */
	public String getYycb() {
		return yycb;
	}

	/**
	 * @param yycb
	 *            The yycb to set.
	 */
	public void setYycb(String yycb) {
		this.yycb = yycb;
	}

	/**
	 * @return Returns the yysr.
	 */
	public String getYysr() {
		return yysr;
	}

	/**
	 * @param yysr
	 *            The yysr to set.
	 */
	public void setYysr(String yysr) {
		this.yysr = yysr;
	}

	/**
	 * @return Returns the zfjg.
	 */
	public String getZfjg() {
		return zfjg;
	}

	/**
	 * @param zfjg
	 *            The zfjg to set.
	 */
	public void setZfjg(String zfjg) {
		this.zfjg = zfjg;
	}

	/**
	 * @return Returns the zjgftsdse.
	 */
	public String getZjgftsdse() {
		return zjgftsdse;
	}

	/**
	 * @param zjgftsdse
	 *            The zjgftsdse to set.
	 */
	public void setZjgftsdse(String zjgftsdse) {
		this.zjgftsdse = zjgftsdse;
	}

	/**
	 * @return Returns the zyczjzsdse.
	 */
	public String getZyczjzsdse() {
		return zyczjzsdse;
	}

    public String getJzjnfzjgsdse()
    {
        return jzjnfzjgsdse;
    }

    /**
	 * @param zyczjzsdse
	 *            The zyczjzsdse to set.
	 */
	public void setZyczjzsdse(String zyczjzsdse) {
		this.zyczjzsdse = zyczjzsdse;
	}

    public void setJzjnfzjgsdse(String jzjnfzjgsdse)
    {
        this.jzjnfzjgsdse = jzjnfzjgsdse;
    }

    public CzzsSbsjVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Map getListTypeMap() {
		return null;
	}

	public String toXML() {
		String xmlstr = "<sbsj>";

		xmlstr += toXMLChilds();
		xmlstr += "</sbsj>";
		return xmlstr;
	}

	public String toXMLChilds() {
		String xmlstr = "";

		xmlstr += XMLBuildUtil.appendStringElement("sbrqshow", sbrqshow);
		xmlstr += XMLBuildUtil.appendStringElement("nsfs", nsfs);
		xmlstr += XMLBuildUtil.appendStringElement("zfjg", zfjg);
		xmlstr += XMLBuildUtil.appendStringElement("yysr", yysr);
		xmlstr += XMLBuildUtil.appendStringElement("yycb", yycb);
		xmlstr += XMLBuildUtil.appendStringElement("lrze", lrze);
		xmlstr += XMLBuildUtil.appendStringElement("sl", sl);
		xmlstr += XMLBuildUtil.appendStringElement("ynsdse", ynsdse);
		xmlstr += XMLBuildUtil.appendStringElement("jmsdse", jmsdse);
		xmlstr += XMLBuildUtil.appendStringElement("sjyjsdse", sjyjsdse);
		xmlstr += XMLBuildUtil.appendStringElement("ybtsdse", ybtsdse);
		xmlstr += XMLBuildUtil.appendStringElement("zjgftsdse", zjgftsdse);
		xmlstr += XMLBuildUtil.appendStringElement("zyczjzsdse", zyczjzsdse);
		xmlstr += XMLBuildUtil.appendStringElement("fzjgftsdse", fzjgftsdse);
        xmlstr += XMLBuildUtil.appendStringElement("jzjnfzjgsdse", jzjnfzjgsdse);
		xmlstr += XMLBuildUtil.appendStringElement("fpbl", fpbl);
		xmlstr += XMLBuildUtil.appendStringElement("fpsdse", fpsdse);
		return xmlstr;
	}

}
