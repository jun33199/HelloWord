package com.ttsoft.bjtax.smsb.model.client;

import java.io.Serializable;

public class yhdmmodel implements Serializable {
	/**
	 * 银行代码
	 */
	public String yhdm;
	/**
	 * 银行名称
	 */
	public String yhmc;
	public String getYhdm() {
		return yhdm;
	}
	public void setYhdm(String yhdm) {
		this.yhdm = yhdm;
	}
	public String getYhmc() {
		return yhmc;
	}
	public void setYhmc(String yhmc) {
		this.yhmc = yhmc;
	}
	
	
}
