package com.lscdz.qysds.application.qysdsjb2014.czzsjb.sbb.vo;

import java.io.Serializable;

public class DmVo implements Serializable{
	private String dm; //����
	private String mc; //����
	private String level;//����
	
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getDm() {
		return dm;
	}
	public void setDm(String dm) {
		this.dm = dm;
	}
	public String getMc() {
		return mc;
	}
	public void setMc(String mc) {
		this.mc = mc;
	}
}
