package com.ttsoft.bjtax.smsb.grsds.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Tzf implements Serializable {
	private static final long serialVersionUID = 1L;
	private String jsjdm;
	private String zjlxdm;
	private String zjhm;
	private String tzzxm;
	private String gj;
	private String nsrsbh;
	private String cjr;
	private Date cjsj;
	private double fpbl;
	private List zjlxList;
	private String txzt;

	public String getJsjdm() {
		return jsjdm;
	}

	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}

	public String getZjlxdm() {
		return zjlxdm;
	}

	public void setZjlxdm(String zjlxdm) {
		this.zjlxdm = zjlxdm;
	}

	public String getZjhm() {
		return zjhm;
	}

	public void setZjhm(String zjhm) {
		this.zjhm = zjhm;
	}

	public String getTzzxm() {
		return tzzxm;
	}

	public void setTzzxm(String tzzxm) {
		this.tzzxm = tzzxm;
	}

	public String getGj() {
		return gj;
	}

	public void setGj(String gj) {
		this.gj = gj;
	}

	public String getNsrsbh() {
		return nsrsbh;
	}

	public void setNsrsbh(String nsrsbh) {
		this.nsrsbh = nsrsbh;
	}

	public String getCjr() {
		return cjr;
	}

	public void setCjr(String cjr) {
		this.cjr = cjr;
	}

	public Date getCjsj() {
		return cjsj;
	}

	public void setCjsj(Date cjsj) {
		this.cjsj = cjsj;
	}

	public double getFpbl() {
		return fpbl;
	}

	public void setFpbl(double fpbl) {
		this.fpbl = fpbl;
	}

	public List getZjlxList() {
		return zjlxList;
	}

	public void setZjlxList(List zjlxList) {
		this.zjlxList = zjlxList;
	}

	public String getTxzt() {
		return txzt;
	}

	public void setTxzt(String txzt) {
		this.txzt = txzt;
	}

}
