package com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.xmlvo;

import java.io.Serializable;

public class DmVo implements Serializable{
	private String dm; //代码
	private String mc; //名称
	private String level;//级次
	
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
