package com.ttsoft.bjtax.smsb.lwpk.model;

import java.io.Serializable;

public class PKFSModel implements Serializable {
	
	
	//���ش���
	private String qxdm;
	//���д���
	private String yhdm;
	//�ܱ���
	private int zbs;
	//���
	private String nd;
	//�¶�
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
