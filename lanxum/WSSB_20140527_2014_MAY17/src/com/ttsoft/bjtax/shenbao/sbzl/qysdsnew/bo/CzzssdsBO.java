package com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.bo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashMap;

/**
 *����ejb���������ݶ���
 *
 */
public class CzzssdsBO implements Serializable {


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

	private String lrze = "";

	private String nstzzje = "";

	private String nstzjse = "";

	private String mbyqndks = "";

	private String ynssde = "";

	private String sysl = "";

	private String ynsdse = "";

	private String jmsdse = "";

	private String hznscyqyjdyjbl = "";

	private String sjyyjdsdse = "";

	private String ybdsdse = "";

	private String lrrdm = "";

	

	/**
	 * @param sbsj
	 */
	public void setSbsj(HashMap sbsj) {
		this.sbsj = sbsj;
		System.out.println("========"+sbsj);
		if (sbsj != null&&sbsj.size()>0) {
			this.lrze = sbsj.get("lrze").toString();
			this.nstzzje = sbsj.get("nstzzje").toString();
			this.nstzjse = sbsj.get("nstzjse").toString();
			this.mbyqndks = sbsj.get("mbyqndks").toString();
			this.ynssde = sbsj.get("ynssde").toString();
			this.sysl = sbsj.get("sysl").toString();
			this.ynsdse = sbsj.get("ynsdse").toString();
			this.jmsdse = sbsj.get("jmsdse").toString();
			this.hznscyqyjdyjbl = sbsj.get("hznscyqyjdyjbl").toString();
			this.sjyyjdsdse = sbsj.get("sjyyjdsdse").toString();
			this.ybdsdse = sbsj.get("ybdsdse").toString();
		}
	}



	/**
	 * @return cyl
	 */
	public String getCyl() {
		return cyl;
	}



	/**
	 * @param cyl Ҫ���õ� cyl
	 */
	public void setCyl(String cyl) {
		this.cyl = cyl;
	}



	/**
	 * @return dezsse
	 */
	public String getDezsse() {
		return dezsse;
	}



	/**
	 * @param dezsse Ҫ���õ� dezsse
	 */
	public void setDezsse(String dezsse) {
		this.dezsse = dezsse;
	}



	/**
	 * @return fsdm
	 */
	public String getFsdm() {
		return fsdm;
	}



	/**
	 * @param fsdm Ҫ���õ� fsdm
	 */
	public void setFsdm(String fsdm) {
		this.fsdm = fsdm;
	}



	/**
	 * @return hznscyqyjdyjbl
	 */
	public String getHznscyqyjdyjbl() {
		return hznscyqyjdyjbl;
	}



	/**
	 * @param hznscyqyjdyjbl Ҫ���õ� hznscyqyjdyjbl
	 */
	public void setHznscyqyjdyjbl(String hznscyqyjdyjbl) {
		this.hznscyqyjdyjbl = hznscyqyjdyjbl;
	}



	/**
	 * @return jd
	 */
	public String getJd() {
		return jd;
	}



	/**
	 * @param jd Ҫ���õ� jd
	 */
	public void setJd(String jd) {
		this.jd = jd;
	}



	/**
	 * @return jmsdse
	 */
	public String getJmsdse() {
		return jmsdse;
	}



	/**
	 * @param jmsdse Ҫ���õ� jmsdse
	 */
	public void setJmsdse(String jmsdse) {
		this.jmsdse = jmsdse;
	}



	/**
	 * @return jmzg
	 */
	public String getJmzg() {
		return jmzg;
	}



	/**
	 * @param jmzg Ҫ���õ� jmzg
	 */
	public void setJmzg(String jmzg) {
		this.jmzg = jmzg;
	}



	/**
	 * @return jsjdm
	 */
	public String getJsjdm() {
		return jsjdm;
	}



	/**
	 * @param jsjdm Ҫ���õ� jsjdm
	 */
	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}



	/**
	 * @return lrrdm
	 */
	public String getLrrdm() {
		return lrrdm;
	}



	/**
	 * @param lrrdm Ҫ���õ� lrrdm
	 */
	public void setLrrdm(String lrrdm) {
		this.lrrdm = lrrdm;
	}



	/**
	 * @return lrze
	 */
	public String getLrze() {
		return lrze;
	}



	/**
	 * @param lrze Ҫ���õ� lrze
	 */
	public void setLrze(String lrze) {
		this.lrze = lrze;
	}



	/**
	 * @return mbyqndks
	 */
	public String getMbyqndks() {
		return mbyqndks;
	}



	/**
	 * @param mbyqndks Ҫ���õ� mbyqndks
	 */
	public void setMbyqndks(String mbyqndks) {
		this.mbyqndks = mbyqndks;
	}



	/**
	 * @return nd
	 */
	public String getNd() {
		return nd;
	}



	/**
	 * @param nd Ҫ���õ� nd
	 */
	public void setNd(String nd) {
		this.nd = nd;
	}



	/**
	 * @return nsrmc
	 */
	public String getNsrmc() {
		return nsrmc;
	}



	/**
	 * @param nsrmc Ҫ���õ� nsrmc
	 */
	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}



	/**
	 * @return nstzjse
	 */
	public String getNstzjse() {
		return nstzjse;
	}



	/**
	 * @param nstzjse Ҫ���õ� nstzjse
	 */
	public void setNstzjse(String nstzjse) {
		this.nstzjse = nstzjse;
	}



	/**
	 * @return nstzzje
	 */
	public String getNstzzje() {
		return nstzzje;
	}



	/**
	 * @param nstzzje Ҫ���õ� nstzzje
	 */
	public void setNstzzje(String nstzzje) {
		this.nstzzje = nstzzje;
	}



	/**
	 * @return qyzslx
	 */
	public String getQyzslx() {
		return qyzslx;
	}



	/**
	 * @param qyzslx Ҫ���õ� qyzslx
	 */
	public void setQyzslx(String qyzslx) {
		this.qyzslx = qyzslx;
	}



	/**
	 * @return sbrq
	 */
	public Timestamp getSbrq() {
		return sbrq;
	}



	/**
	 * @param sbrq Ҫ���õ� sbrq
	 */
	public void setSbrq(Timestamp sbrq) {
		this.sbrq = sbrq;
	}



	/**
	 * @return sbrqshow
	 */
	public String getSbrqshow() {
		return sbrqshow;
	}



	/**
	 * @param sbrqshow Ҫ���õ� sbrqshow
	 */
	public void setSbrqshow(String sbrqshow) {
		this.sbrqshow = sbrqshow;
	}



	/**
	 * @return sjyyjdsdse
	 */
	public String getSjyyjdsdse() {
		return sjyyjdsdse;
	}



	/**
	 * @param sjyyjdsdse Ҫ���õ� sjyyjdsdse
	 */
	public void setSjyyjdsdse(String sjyyjdsdse) {
		this.sjyyjdsdse = sjyyjdsdse;
	}



	/**
	 * @return skssjsrq
	 */
	public Timestamp getSkssjsrq() {
		return skssjsrq;
	}



	/**
	 * @param skssjsrq Ҫ���õ� skssjsrq
	 */
	public void setSkssjsrq(Timestamp skssjsrq) {
		this.skssjsrq = skssjsrq;
	}



	/**
	 * @return skssksrq
	 */
	public Timestamp getSkssksrq() {
		return skssksrq;
	}



	/**
	 * @param skssksrq Ҫ���õ� skssksrq
	 */
	public void setSkssksrq(Timestamp skssksrq) {
		this.skssksrq = skssksrq;
	}



	/**
	 * @return swjgzzjgdm
	 */
	public String getSwjgzzjgdm() {
		return swjgzzjgdm;
	}



	/**
	 * @param swjgzzjgdm Ҫ���õ� swjgzzjgdm
	 */
	public void setSwjgzzjgdm(String swjgzzjgdm) {
		this.swjgzzjgdm = swjgzzjgdm;
	}



	/**
	 * @return sysl
	 */
	public String getSysl() {
		return sysl;
	}



	/**
	 * @param sysl Ҫ���õ� sysl
	 */
	public void setSysl(String sysl) {
		this.sysl = sysl;
	}



	/**
	 * @return xzqy
	 */
	public String getXzqy() {
		return xzqy;
	}



	/**
	 * @param xzqy Ҫ���õ� xzqy
	 */
	public void setXzqy(String xzqy) {
		this.xzqy = xzqy;
	}



	/**
	 * @return ybdsdse
	 */
	public String getYbdsdse() {
		return ybdsdse;
	}



	/**
	 * @param ybdsdse Ҫ���õ� ybdsdse
	 */
	public void setYbdsdse(String ybdsdse) {
		this.ybdsdse = ybdsdse;
	}



	/**
	 * @return ybjmsl
	 */
	public String getYbjmsl() {
		return ybjmsl;
	}



	/**
	 * @param ybjmsl Ҫ���õ� ybjmsl
	 */
	public void setYbjmsl(String ybjmsl) {
		this.ybjmsl = ybjmsl;
	}



	/**
	 * @return ynsdse
	 */
	public String getYnsdse() {
		return ynsdse;
	}



	/**
	 * @param ynsdse Ҫ���õ� ynsdse
	 */
	public void setYnsdse(String ynsdse) {
		this.ynsdse = ynsdse;
	}



	/**
	 * @return ynssde
	 */
	public String getYnssde() {
		return ynssde;
	}



	/**
	 * @param ynssde Ҫ���õ� ynssde
	 */
	public void setYnssde(String ynssde) {
		this.ynssde = ynssde;
	}



	/**
	 * @return sbsj
	 */
	public HashMap getSbsj() {
		return sbsj;
	}

}
