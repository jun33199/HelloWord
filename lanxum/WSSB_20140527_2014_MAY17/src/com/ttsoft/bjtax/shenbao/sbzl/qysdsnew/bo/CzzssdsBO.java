package com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.bo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashMap;

/**
 *用于ejb交互的数据对象
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
	 * @param cyl 要设置的 cyl
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
	 * @param dezsse 要设置的 dezsse
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
	 * @param fsdm 要设置的 fsdm
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
	 * @param hznscyqyjdyjbl 要设置的 hznscyqyjdyjbl
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
	 * @param jd 要设置的 jd
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
	 * @param jmsdse 要设置的 jmsdse
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
	 * @param jmzg 要设置的 jmzg
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
	 * @param jsjdm 要设置的 jsjdm
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
	 * @param lrrdm 要设置的 lrrdm
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
	 * @param lrze 要设置的 lrze
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
	 * @param mbyqndks 要设置的 mbyqndks
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
	 * @param nd 要设置的 nd
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
	 * @param nsrmc 要设置的 nsrmc
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
	 * @param nstzjse 要设置的 nstzjse
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
	 * @param nstzzje 要设置的 nstzzje
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
	 * @param qyzslx 要设置的 qyzslx
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
	 * @param sbrq 要设置的 sbrq
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
	 * @param sbrqshow 要设置的 sbrqshow
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
	 * @param sjyyjdsdse 要设置的 sjyyjdsdse
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
	 * @param skssjsrq 要设置的 skssjsrq
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
	 * @param skssksrq 要设置的 skssksrq
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
	 * @param swjgzzjgdm 要设置的 swjgzzjgdm
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
	 * @param sysl 要设置的 sysl
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
	 * @param xzqy 要设置的 xzqy
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
	 * @param ybdsdse 要设置的 ybdsdse
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
	 * @param ybjmsl 要设置的 ybjmsl
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
	 * @param ynsdse 要设置的 ynsdse
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
	 * @param ynssde 要设置的 ynssde
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
