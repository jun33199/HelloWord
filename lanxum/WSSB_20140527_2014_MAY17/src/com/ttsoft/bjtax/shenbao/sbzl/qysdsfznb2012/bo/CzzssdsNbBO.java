package com.ttsoft.bjtax.shenbao.sbzl.qysdsfznb2012.bo;

import java.io.*;
import java.sql.*;
import java.util.*;

public class CzzssdsNbBO implements Serializable {

	private String jsjdm = "";

	private String nsrmc = "";

	private String nsrsbh="";

	private String swjgzzjgdm = "";

	private String jmzg = "";

	private String ybjmsl = "";

	private String qyzslx = "";

	private String cyl = "";

	private String xzqy = "";

	private String dezsse = "";

	private String fsdm = "";

	private String jd = "";

	private String nd = "";

	private Timestamp sbrq;

	private Timestamp skssjsrq;

	private Timestamp skssksrq;

	// 申报数据
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
	private String zczbje;
	private String zcze;
	
	/**
     * A表中纳税方法与总分机构信息
     */
    private HashMap nsfs_fsjg = new HashMap();
	private HashMap sbsj = new HashMap();

	/**
	 * 
	 */
	private String queryFlag;
	/**
	 * @return Returns the sbsj.
	 */
	public HashMap getSbsj() {
		return sbsj;
	}

	/**
	 * @param sbsj
	 *            The sbsj to set.
	 */
	public void setSbsj(HashMap sbsj) {
		this.sbsj = sbsj;
		System.out.println("========" + sbsj);
		if (sbsj != null && sbsj.size() > 0) {
			//this.sbrqshow = sbsj.get("sbrqshow").toString();
			this.nsfs = sbsj.get("nsfs").toString();
			this.zfjg = sbsj.get("zfjg").toString();
			this.yysr = sbsj.get("yysr").toString();
			this.yycb = sbsj.get("yycb").toString();
			this.lrze = sbsj.get("lrze").toString();
			this.tdjsynssde = sbsj.get("tdjsynssde").toString();
			this.bzsr = sbsj.get("bzsr").toString();
			this.mssr = sbsj.get("mssr").toString();
			this.mbyqndks = sbsj.get("mbyqndks").toString();
			this.sjlre = sbsj.get("sjlre").toString();
			this.sl = sbsj.get("sl").toString();
			this.ynsdse = sbsj.get("ynsdse").toString();
			this.jmsdse = sbsj.get("jmsdse").toString();
            this.sjyyjsdse = sbsj.get("sjyyjsdse").toString();
            this.tdywyjsdse = sbsj.get("tdywyjsdse").toString();
			this.ybtsdse = sbsj.get("ybtsdse").toString();
			this.yqnddjsdse = sbsj.get("yqnddjsdse").toString();
			this.bqsjybtsdse = sbsj.get("bqsjybtsdse").toString();
			this.zjgyftsdse = sbsj.get("zjgyftsdse").toString();
			this.czjzfpsdse = sbsj.get("czjzfpsdse").toString();
			this.fzjgyftsdse = sbsj.get("fzjgyftsdse").toString();
			this.zjgdlscjybmyftsdse = sbsj.get("zjgdlscjybmyftsdse").toString();
            this.zjgycxfzjgyftsdse = sbsj.get("zjgycxfzjgyftsdse").toString();
			this.fpbl = sbsj.get("fpbl").toString();
			this.fpsdse = sbsj.get("fpsdse").toString();
			this.zczbje=sbsj.get("zczbje").toString();
			this.zcze=sbsj.get("zcze").toString();
		}
	}

	/**
	 * @return Returns the cyl.
	 */
	public String getCyl() {
		return cyl;
	}

	/**
	 * @param cyl The cyl to set.
	 */
	public void setCyl(String cyl) {
		this.cyl = cyl;
	}

	/**
	 * @return Returns the dezsse.
	 */
	public String getDezsse() {
		return dezsse;
	}

	/**
	 * @param dezsse The dezsse to set.
	 */
	public void setDezsse(String dezsse) {
		this.dezsse = dezsse;
	}

	/**
	 * @return Returns the fsdm.
	 */
	public String getFsdm() {
		return fsdm;
	}

	/**
	 * @param fsdm The fsdm to set.
	 */
	public void setFsdm(String fsdm) {
		this.fsdm = fsdm;
	}

	/**
	 * @return Returns the jd.
	 */
	public String getJd() {
		return jd;
	}

	/**
	 * @param jd The jd to set.
	 */
	public void setJd(String jd) {
		this.jd = jd;
	}

	/**
	 * @return Returns the jmzg.
	 */
	public String getJmzg() {
		return jmzg;
	}

	/**
	 * @param jmzg The jmzg to set.
	 */
	public void setJmzg(String jmzg) {
		this.jmzg = jmzg;
	}

	/**
	 * @return Returns the jsjdm.
	 */
	public String getJsjdm() {
		return jsjdm;
	}

	/**
	 * @param jsjdm The jsjdm to set.
	 */
	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}

	/**
	 * @return Returns the nd.
	 */
	public String getNd() {
		return nd;
	}

	/**
	 * @param nd The nd to set.
	 */
	public void setNd(String nd) {
		this.nd = nd;
	}

	/**
	 * @return Returns the nsrmc.
	 */
	public String getNsrmc() {
		return nsrmc;
	}

	/**
	 * @param nsrmc The nsrmc to set.
	 */
	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}

	/**
	 * @return Returns the nsrsbh.
	 */
	public String getNsrsbh() {
		return nsrsbh;
	}

	/**
	 * @param nsrsbh The nsrsbh to set.
	 */
	public void setNsrsbh(String nsrsbh) {
		this.nsrsbh = nsrsbh;
	}

	/**
	 * @return Returns the qyzslx.
	 */
	public String getQyzslx() {
		return qyzslx;
	}

	/**
	 * @param qyzslx The qyzslx to set.
	 */
	public void setQyzslx(String qyzslx) {
		this.qyzslx = qyzslx;
	}

	/**
	 * @return Returns the sbrq.
	 */
	public Timestamp getSbrq() {
		return sbrq;
	}

	/**
	 * @param sbrq The sbrq to set.
	 */
	public void setSbrq(Timestamp sbrq) {
		this.sbrq = sbrq;
	}

	/**
	 * @return Returns the skssjsrq.
	 */
	public Timestamp getSkssjsrq() {
		return skssjsrq;
	}

	/**
	 * @param skssjsrq The skssjsrq to set.
	 */
	public void setSkssjsrq(Timestamp skssjsrq) {
		this.skssjsrq = skssjsrq;
	}

	/**
	 * @return Returns the skssksrq.
	 */
	public Timestamp getSkssksrq() {
		return skssksrq;
	}

	/**
	 * @param skssksrq The skssksrq to set.
	 */
	public void setSkssksrq(Timestamp skssksrq) {
		this.skssksrq = skssksrq;
	}

	/**
	 * @return Returns the swjgzzjgdm.
	 */
	public String getSwjgzzjgdm() {
		return swjgzzjgdm;
	}

	/**
	 * @param swjgzzjgdm The swjgzzjgdm to set.
	 */
	public void setSwjgzzjgdm(String swjgzzjgdm) {
		this.swjgzzjgdm = swjgzzjgdm;
	}

	/**
	 * @return Returns the xzqy.
	 */
	public String getXzqy() {
		return xzqy;
	}

	/**
	 * @param xzqy The xzqy to set.
	 */
	public void setXzqy(String xzqy) {
		this.xzqy = xzqy;
	}

	/**
	 * @return Returns the ybjmsl.
	 */
	public String getYbjmsl() {
		return ybjmsl;
	}

	public void setYbjmsl(String ybjmsl) {
		this.ybjmsl = ybjmsl;
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

	public HashMap getNsfs_fsjg() {
		return nsfs_fsjg;
	}

	public void setNsfs_fsjg(HashMap nsfs_fsjg) {
		this.nsfs_fsjg = nsfs_fsjg;
	}

	public String getZczbje() {
		return zczbje;
	}

	public void setZczbje(String zczbje) {
		this.zczbje = zczbje;
	}

	public String getZcze() {
		return zcze;
	}

	public void setZcze(String zcze) {
		this.zcze = zcze;
	}

	public String getQueryFlag() {
		return queryFlag;
	}

	public void setQueryFlag(String queryFlag) {
		this.queryFlag = queryFlag;
	}

	
}
