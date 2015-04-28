package com.ttsoft.bjtax.smsb.lwpk.model;

import java.io.Serializable;

/**
 *查询基本数据model
 *kanght 
 * 201307
 */
public class PKJBSJModel implements Serializable {

	//纳税人名称
	private String nsrmc;
	//责任人--法定代表人
	private String zrr;
	//固定电话
	private String gddh;
	//移动电话
	private String yddh;
	//注册地址
	private String zcdz;
	//经营地址
	private String jydz;
	public String getNsrmc() {
		return nsrmc;
	}
	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}
	public String getZrr() {
		return zrr;
	}
	public void setZrr(String zrr) {
		this.zrr = zrr;
	}
	public String getGddh() {
		return gddh;
	}
	public void setGddh(String gddh) {
		this.gddh = gddh;
	}
	public String getYddh() {
		return yddh;
	}
	public void setYddh(String yddh) {
		this.yddh = yddh;
	}
	public String getZcdz() {
		return zcdz;
	}
	public void setZcdz(String zcdz) {
		this.zcdz = zcdz;
	}
	public String getJydz() {
		return jydz;
	}
	public void setJydz(String jydz) {
		this.jydz = jydz;
	}
	
	
}
