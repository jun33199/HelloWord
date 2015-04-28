package com.ttsoft.bjtax.smsb.lwpk.model;

import java.io.Serializable;

public class PKFSModel implements Serializable {
	
	
	//区县代码
	private String qxdm;
	//银行代码
	private String yhdm;
	//总笔数
	private int zbs;
	//年度
	private String nd;
	//月度
	private String yd;
	
	public String getNd() {
		return nd;
	}
	public void setNd(String nd) {
		this.nd = nd;
	}
	public String getYd() {
		return yd;
	}
	public void setYd(String yd) {
		this.yd = yd;
	}
	public String getQxdm() {
		return qxdm;
	}
	public void setQxdm(String qxdm) {
		this.qxdm = qxdm;
	}
	public String getYhdm() {
		return yhdm;
	}
	public void setYhdm(String yhdm) {
		this.yhdm = yhdm;
	}
	public int getZbs() {
		return zbs;
	}
	public void setZbs(int zbs) {
		this.zbs = zbs;
	}
	
	
	
	

}
