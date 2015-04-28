package com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.xmlvo;

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
//	private String zjgycxfzjgyftsdse; 新版本不存在此字段
	private String fpbl;
	private String fpsdse;

	private String jmzynssde;//减征、免征应纳税所得额
	private String xwqyjmsdse ;// 其中：符合条件的小型微利企业减免所得税额
	
	//税款所属年度等于开业登记日期Y 否N
	private String sfxkh;
	//获取税款所属期所在年度上一年度征收方式
	private String syndZsfsdm;
	//获取上一年度核定征收年报主表行6数据
	private String syndZbh6;
	//获取上一年度汇算清缴主表行25数据
	private String syndZbh25;
	//获取上一年度汇算清缴附表5行45、46、47的校验结果
	private String syndFb5jyjg;
	
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
//        xmlstr += XMLBuildUtil.appendStringElement("zjgycxfzjgyftsdse", zjgycxfzjgyftsdse);
		xmlstr += XMLBuildUtil.appendStringElement("fpbl", fpbl);
		xmlstr += XMLBuildUtil.appendStringElement("fpsdse", fpsdse);
		xmlstr += XMLBuildUtil.appendStringElement("jmzynssde", jmzynssde);
		xmlstr += XMLBuildUtil.appendStringElement("xwqyjmsdse", xwqyjmsdse);
		
		xmlstr += XMLBuildUtil.appendStringElement("sfxkh", sfxkh);
		xmlstr += XMLBuildUtil.appendStringElement("syndzsfsdm", syndZsfsdm);
		xmlstr += XMLBuildUtil.appendStringElement("syndzbh6", syndZbh6);
		xmlstr += XMLBuildUtil.appendStringElement("syndzbh25", syndZbh25);
		xmlstr += XMLBuildUtil.appendStringElement("syndfb5jyjg", syndFb5jyjg);
		
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

	public String getJmzynssde() {
		return jmzynssde;
	}

	public void setJmzynssde(String jmzynssde) {
		this.jmzynssde = jmzynssde;
	}

	public String getXwqyjmsdse() {
		return xwqyjmsdse;
	}

	public void setXwqyjmsdse(String xwqyjmsdse) {
		this.xwqyjmsdse = xwqyjmsdse;
	}

	public String getSfxkh() {
		return sfxkh;
	}

	public void setSfxkh(String sfxkh) {
		this.sfxkh = sfxkh;
	}

	public String getSyndZsfsdm() {
		return syndZsfsdm;
	}

	public void setSyndZsfsdm(String syndZsfsdm) {
		this.syndZsfsdm = syndZsfsdm;
	}

	public String getSyndZbh6() {
		return syndZbh6;
	}

	public void setSyndZbh6(String syndZbh6) {
		this.syndZbh6 = syndZbh6;
	}

	public String getSyndZbh25() {
		return syndZbh25;
	}

	public void setSyndZbh25(String syndZbh25) {
		this.syndZbh25 = syndZbh25;
	}

	public String getSyndFb5jyjg() {
		return syndFb5jyjg;
	}

	public void setSyndFb5jyjg(String syndFb5jyjg) {
		this.syndFb5jyjg = syndFb5jyjg;
	}

}
