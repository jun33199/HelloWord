package com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.bo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashMap;

/**
 *用于ejb交互的数据对象
 *
 */
public class HdzssdsBO implements Serializable {

	private String jsjdm = "";

	private String nsrmc = "";

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

	private String sbrqshow = "";

	private HashMap sbsj = new HashMap();

	private String srze_bqs = "";

	private String srze_ljs = "";

	private String yssdl_bqs = "";

	private String yssdl_ljs = "";

	private String ynssde_bqs = "";

	private String ynssde_ljs = "";

	private String sysl_bqs = "";

	private String sysl_ljs = "";

	private String yjsdse_bqs = "";

	private String yjsdse_ljs = "";

	private String sjyyjdsdse_bqs = "";

	private String sjyyjdsdse_ljs = "";

	private String ybdsdse_bqs = "";

	private String ybdsdse_ljs = "";

	private String lrrdm = "";

	private String zcze_ljs = "";
	private String zczbje = "";

	public String getLrrdm() {
		return lrrdm;
	}

	public void setLrrdm(String lrrdm) {
		this.lrrdm = lrrdm;
	}

	public String getCyl() {
		return cyl;
	}

	public void setCyl(String cyl) {
		this.cyl = cyl;
	}

	public String getDezsse() {
		return dezsse;
	}

	public void setDezsse(String dezsse) {
		this.dezsse = dezsse;
	}

	public String getFsdm() {
		return fsdm;
	}

	public void setFsdm(String fsdm) {
		this.fsdm = fsdm;
	}

	public String getJd() {
		return jd;
	}

	public void setJd(String jd) {
		this.jd = jd;
	}

	public String getJmzg() {
		return jmzg;
	}

	public void setJmzg(String jmzg) {
		this.jmzg = jmzg;
	}

	public String getJsjdm() {
		return jsjdm;
	}

	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}

	public String getNd() {
		return nd;
	}

	public void setNd(String nd) {
		this.nd = nd;
	}

	public String getNsrmc() {
		return nsrmc;
	}

	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}

	public String getQyzslx() {
		return qyzslx;
	}

	public void setQyzslx(String qyzslx) {
		this.qyzslx = qyzslx;
	}

	public Timestamp getSbrq() {
		return sbrq;
	}

	public void setSbrq(Timestamp sbrq) {
		this.sbrq = sbrq;
	}

	public String getSbrqshow() {
		return sbrqshow;
	}

	public void setSbrqshow(String sbrqshow) {
		this.sbrqshow = sbrqshow;
	}

	public String getSjyyjdsdse_bqs() {
		return sjyyjdsdse_bqs;
	}

	public void setSjyyjdsdse_bqs(String sjyyjdsdse_bqs) {
		this.sjyyjdsdse_bqs = sjyyjdsdse_bqs;
	}

	public String getSjyyjdsdse_ljs() {
		return sjyyjdsdse_ljs;
	}

	public void setSjyyjdsdse_ljs(String sjyyjdsdse_ljs) {
		this.sjyyjdsdse_ljs = sjyyjdsdse_ljs;
	}

	public Timestamp getSkssjsrq() {
		return skssjsrq;
	}

	public void setSkssjsrq(Timestamp skssjsrq) {
		this.skssjsrq = skssjsrq;
	}

	public Timestamp getSkssksrq() {
		return skssksrq;
	}

	public void setSkssksrq(Timestamp skssksrq) {
		this.skssksrq = skssksrq;
	}

	public String getSrze_bqs() {
		return srze_bqs;
	}

	public void setSrze_bqs(String srze_bqs) {
		this.srze_bqs = srze_bqs;
	}

	public String getSrze_ljs() {
		return srze_ljs;
	}

	public void setSrze_ljs(String srze_ljs) {
		this.srze_ljs = srze_ljs;
	}

	public String getSwjgzzjgdm() {
		return swjgzzjgdm;
	}

	public void setSwjgzzjgdm(String swjgzzjgdm) {
		this.swjgzzjgdm = swjgzzjgdm;
	}

	public String getSysl_bqs() {
		return sysl_bqs;
	}

	public void setSysl_bqs(String sysl_bqs) {
		this.sysl_bqs = sysl_bqs;
	}

	public String getSysl_ljs() {
		return sysl_ljs;
	}

	public void setSysl_ljs(String sysl_ljs) {
		this.sysl_ljs = sysl_ljs;
	}

	public String getXzqy() {
		return xzqy;
	}

	public void setXzqy(String xzqy) {
		this.xzqy = xzqy;
	}

	public String getYbdsdse_bqs() {
		return ybdsdse_bqs;
	}

	public void setYbdsdse_bqs(String ybdsdse_bqs) {
		this.ybdsdse_bqs = ybdsdse_bqs;
	}

	public String getYbdsdse_ljs() {
		return ybdsdse_ljs;
	}

	public void setYbdsdse_ljs(String ybdsdse_ljs) {
		this.ybdsdse_ljs = ybdsdse_ljs;
	}

	public String getYbjmsl() {
		return ybjmsl;
	}

	public void setYbjmsl(String ybjmsl) {
		this.ybjmsl = ybjmsl;
	}

	public String getYjsdse_bqs() {
		return yjsdse_bqs;
	}

	public void setYjsdse_bqs(String yjsdse_bqs) {
		this.yjsdse_bqs = yjsdse_bqs;
	}

	public String getYjsdse_ljs() {
		return yjsdse_ljs;
	}

	public void setYjsdse_ljs(String yjsdse_ljs) {
		this.yjsdse_ljs = yjsdse_ljs;
	}

	public String getYnssde_bqs() {
		return ynssde_bqs;
	}

	public void setYnssde_bqs(String ynssde_bqs) {
		this.ynssde_bqs = ynssde_bqs;
	}

	public String getYnssde_ljs() {
		return ynssde_ljs;
	}

	public void setYnssde_ljs(String ynssde_ljs) {
		this.ynssde_ljs = ynssde_ljs;
	}

	public String getYssdl_bqs() {
		return yssdl_bqs;
	}

	public void setYssdl_bqs(String yssdl_bqs) {
		this.yssdl_bqs = yssdl_bqs;
	}

	public String getYssdl_ljs() {
		return yssdl_ljs;
	}

	public void setYssdl_ljs(String yssdl_ljs) {
		this.yssdl_ljs = yssdl_ljs;
	}

	public HashMap getSbsj() {
		return sbsj;
	}

	public void setSbsj(HashMap sbsj) {
		this.sbsj = sbsj;
		System.out.println("========"+sbsj);
		if (sbsj != null&&sbsj.size()>0) {
			//this.srze_bqs = sbsj.get("srze_bqs").toString();
			this.srze_ljs = sbsj.get("srze_ljs").toString();
			//this.yssdl_bqs = sbsj.get("yssdl_bqs").toString();
			this.yssdl_ljs = sbsj.get("yssdl_ljs").toString();
			this.ynssde_bqs = sbsj.get("ynssde_bqs").toString();
			//this.ynssde_ljs = sbsj.get("ynssde_ljs").toString();
			//this.sysl_bqs = sbsj.get("sysl_bqs").toString();
			this.sysl_ljs = sbsj.get("sysl_ljs").toString();
			//this.yjsdse_bqs = sbsj.get("yjsdse_bqs").toString();
			this.yjsdse_ljs = sbsj.get("yjsdse_ljs").toString();
			this.sjyyjdsdse_bqs = sbsj.get("sjyyjdsdse_bqs").toString();
			this.sjyyjdsdse_ljs = sbsj.get("sjyyjdsdse_ljs").toString();
			//this.ybdsdse_bqs = sbsj.get("ybdsdse_bqs").toString();
			this.ybdsdse_ljs = sbsj.get("ybdsdse_ljs").toString();
			this.zcze_ljs = sbsj.get("zcze_ljs").toString();
			this.zczbje =  sbsj.get("zczbje").toString();
		}
	}

	/**
	 * @return Returns the zcze_ljs.
	 */
	public String getZcze_ljs() {
		return zcze_ljs;
	}
	/**
	 * @param zcze_ljs The zcze_ljs to set.
	 */
	public void setZcze_ljs(String zcze_ljs) {
		this.zcze_ljs = zcze_ljs;
	}
	/**
	 * @return Returns the zczbje.
	 */
	public String getZczbje() {
		return zczbje;
	}
	/**
	 * @param zczbje The zczbje to set.
	 */
	public void setZczbje(String zczbje) {
		this.zczbje = zczbje;
	}
}
