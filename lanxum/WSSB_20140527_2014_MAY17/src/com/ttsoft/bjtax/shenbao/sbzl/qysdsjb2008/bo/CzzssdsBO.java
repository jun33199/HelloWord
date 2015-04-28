package com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2008.bo;

import java.io.*;
import java.sql.*;
import java.util.*;

public class CzzssdsBO implements Serializable {

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

	///

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

	private HashMap sbsj = new HashMap();// Éê±¨Êý¾Ý

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
			this.sl = sbsj.get("sl").toString();
			this.ynsdse = sbsj.get("ynsdse").toString();
			this.jmsdse = sbsj.get("jmsdse").toString();
			this.sjyjsdse = sbsj.get("sjyjsdse").toString();
			this.ybtsdse = sbsj.get("ybtsdse").toString();
			this.zjgftsdse = sbsj.get("zjgftsdse").toString();
			this.zyczjzsdse = sbsj.get("zyczjzsdse").toString();
			this.fzjgftsdse = sbsj.get("fzjgftsdse").toString();
            this.jzjnfzjgsdse = sbsj.get("jzjnfzjgsdse").toString();
			this.fpbl = sbsj.get("fpbl").toString();
			this.fpsdse = sbsj.get("fpsdse").toString();
		}
	}

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

	/**
	 * @param zyczjzsdse
	 *            The zyczjzsdse to set.
	 */
	public void setZyczjzsdse(String zyczjzsdse) {
		this.zyczjzsdse = zyczjzsdse;
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

    public String getJzjnfzjgsdse()
    {
        return jzjnfzjgsdse;
    }

    /**
	 * @param ybjmsl The ybjmsl to set.
	 */
	public void setYbjmsl(String ybjmsl) {
		this.ybjmsl = ybjmsl;
	}

    public void setJzjnfzjgsdse(String jzjnfzjgsdse)
    {
        this.jzjnfzjgsdse = jzjnfzjgsdse;
    }

}
