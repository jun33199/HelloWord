package com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2012.xmlvo;

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
	private String tdjsynssde;
	private String bzsr;
	private String mssr;
	private String mbyqndks;
	private String sjlre;
	private String sl;
	private String ynsdse;
	private String jmsdse;
    private String sjyyjsdse;
	private String tdywyjsdse;
	private String ybtsdse;
	private String yqnddjsdse;
	private String bqsjybtsdse;
	private String zjgyftsdse;
	private String czjzfpsdse;
	private String fzjgyftsdse;
	private String zjgdlscjybmyftsdse;
	private String zjgycxfzjgyftsdse;
	private String fpbl;
	private String fpsdse;

	

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
		xmlstr += XMLBuildUtil.appendStringElement("tdjsynssde", tdjsynssde);
		xmlstr += XMLBuildUtil.appendStringElement("bzsr", bzsr);
		xmlstr += XMLBuildUtil.appendStringElement("mssr", mssr);
		xmlstr += XMLBuildUtil.appendStringElement("mbyqndks", mbyqndks);
		xmlstr += XMLBuildUtil.appendStringElement("sjlre", sjlre);
		xmlstr += XMLBuildUtil.appendStringElement("sl", sl);
		xmlstr += XMLBuildUtil.appendStringElement("ynsdse", ynsdse);
		xmlstr += XMLBuildUtil.appendStringElement("jmsdse", jmsdse);
        xmlstr += XMLBuildUtil.appendStringElement("sjyyjsdse", sjyyjsdse);
        xmlstr += XMLBuildUtil.appendStringElement("tdywyjsdse", tdywyjsdse);
		xmlstr += XMLBuildUtil.appendStringElement("ybtsdse", ybtsdse);
		xmlstr += XMLBuildUtil.appendStringElement("yqnddjsdse", yqnddjsdse);
		xmlstr += XMLBuildUtil.appendStringElement("bqsjybtsdse", bqsjybtsdse);
		xmlstr += XMLBuildUtil.appendStringElement("zjgyftsdse", zjgyftsdse);
		xmlstr += XMLBuildUtil.appendStringElement("czjzfpsdse", czjzfpsdse);
		xmlstr += XMLBuildUtil.appendStringElement("fzjgyftsdse", fzjgyftsdse);
		xmlstr += XMLBuildUtil.appendStringElement("zjgdlscjybmyftsdse", zjgdlscjybmyftsdse);
        xmlstr += XMLBuildUtil.appendStringElement("zjgycxfzjgyftsdse", zjgycxfzjgyftsdse);
		xmlstr += XMLBuildUtil.appendStringElement("fpbl", fpbl);
		xmlstr += XMLBuildUtil.appendStringElement("fpsdse", fpsdse);
		return xmlstr;
	}

	public String getSbrqshow() {
		return sbrqshow;
	}

	public void setSbrqshow(String sbrqshow) {
		this.sbrqshow = sbrqshow;
	}

	public String getNsfs() {
		return nsfs;
	}

	public void setNsfs(String nsfs) {
		this.nsfs = nsfs;
	}

	public String getZfjg() {
		return zfjg;
	}

	public void setZfjg(String zfjg) {
		this.zfjg = zfjg;
	}

	public String getYysr() {
		return yysr;
	}

	public void setYysr(String yysr) {
		this.yysr = yysr;
	}

	public String getYycb() {
		return yycb;
	}

	public void setYycb(String yycb) {
		this.yycb = yycb;
	}

	public String getLrze() {
		return lrze;
	}

	public void setLrze(String lrze) {
		this.lrze = lrze;
	}

	public String getTdjsynssde() {
		return tdjsynssde;
	}

	public void setTdjsynssde(String tdjsynssde) {
		this.tdjsynssde = tdjsynssde;
	}

	public String getBzsr() {
		return bzsr;
	}

	public void setBzsr(String bzsr) {
		this.bzsr = bzsr;
	}

	public String getMssr() {
		return mssr;
	}

	public void setMssr(String mssr) {
		this.mssr = mssr;
	}

	public String getMbyqndks() {
		return mbyqndks;
	}

	public void setMbyqndks(String mbyqndks) {
		this.mbyqndks = mbyqndks;
	}

	public String getSjlre() {
		return sjlre;
	}

	public void setSjlre(String sjlre) {
		this.sjlre = sjlre;
	}

	public String getSl() {
		return sl;
	}

	public void setSl(String sl) {
		this.sl = sl;
	}

	public String getYnsdse() {
		return ynsdse;
	}

	public void setYnsdse(String ynsdse) {
		this.ynsdse = ynsdse;
	}

	public String getJmsdse() {
		return jmsdse;
	}

	public void setJmsdse(String jmsdse) {
		this.jmsdse = jmsdse;
	}

	public String getSjyyjsdse() {
		return sjyyjsdse;
	}

	public void setSjyyjsdse(String sjyyjsdse) {
		this.sjyyjsdse = sjyyjsdse;
	}

	public String getTdywyjsdse() {
		return tdywyjsdse;
	}

	public void setTdywyjsdse(String tdywyjsdse) {
		this.tdywyjsdse = tdywyjsdse;
	}

	public String getYbtsdse() {
		return ybtsdse;
	}

	public void setYbtsdse(String ybtsdse) {
		this.ybtsdse = ybtsdse;
	}

	public String getYqnddjsdse() {
		return yqnddjsdse;
	}

	public void setYqnddjsdse(String yqnddjsdse) {
		this.yqnddjsdse = yqnddjsdse;
	}

	public String getBqsjybtsdse() {
		return bqsjybtsdse;
	}

	public void setBqsjybtsdse(String bqsjybtsdse) {
		this.bqsjybtsdse = bqsjybtsdse;
	}

	public String getZjgyftsdse() {
		return zjgyftsdse;
	}

	public void setZjgyftsdse(String zjgyftsdse) {
		this.zjgyftsdse = zjgyftsdse;
	}

	public String getCzjzfpsdse() {
		return czjzfpsdse;
	}

	public void setCzjzfpsdse(String czjzfpsdse) {
		this.czjzfpsdse = czjzfpsdse;
	}

	public String getFzjgyftsdse() {
		return fzjgyftsdse;
	}

	public void setFzjgyftsdse(String fzjgyftsdse) {
		this.fzjgyftsdse = fzjgyftsdse;
	}

	public String getZjgdlscjybmyftsdse() {
		return zjgdlscjybmyftsdse;
	}

	public void setZjgdlscjybmyftsdse(String zjgdlscjybmyftsdse) {
		this.zjgdlscjybmyftsdse = zjgdlscjybmyftsdse;
	}

	public String getZjgycxfzjgyftsdse() {
		return zjgycxfzjgyftsdse;
	}

	public void setZjgycxfzjgyftsdse(String zjgycxfzjgyftsdse) {
		this.zjgycxfzjgyftsdse = zjgycxfzjgyftsdse;
	}

	public String getFpbl() {
		return fpbl;
	}

	public void setFpbl(String fpbl) {
		this.fpbl = fpbl;
	}

	public String getFpsdse() {
		return fpsdse;
	}

	public void setFpsdse(String fpsdse) {
		this.fpsdse = fpsdse;
	}

}
