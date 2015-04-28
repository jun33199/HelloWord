package com.ttsoft.bjtax.smsb.jccswh.vo;

import java.io.Serializable;

public class JmxzVo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String jmxzdm;	//减免性质代码
	private String jmxzmc;	//减免性质名称
	private String zxbs;	//注销标识
	private String lrr;	//录入人
	private String lrrq;	//录入日期
	private String xsbs;	//显示标识（用于总局减免税代码维护，0：不显示；1：显示）
	public String getJmxzdm() {
		return jmxzdm;
	}
	public void setJmxzdm(String jmxzdm) {
		this.jmxzdm = jmxzdm;
	}
	public String getJmxzmc() {
		return jmxzmc;
	}
	public void setJmxzmc(String jmxzmc) {
		this.jmxzmc = jmxzmc;
	}
	public String getZxbs() {
		return zxbs;
	}
	public void setZxbs(String zxbs) {
		this.zxbs = zxbs;
	}
	public String getLrr() {
		return lrr;
	}
	public void setLrr(String lrr) {
		this.lrr = lrr;
	}
	public String getLrrq() {
		return lrrq;
	}
	public void setLrrq(String lrrq) {
		this.lrrq = lrrq;
	}
	public String getXsbs() {
		return xsbs;
	}
	public void setXsbs(String xsbs) {
		this.xsbs = xsbs;
	}
	
	
	
	
	
	

}
