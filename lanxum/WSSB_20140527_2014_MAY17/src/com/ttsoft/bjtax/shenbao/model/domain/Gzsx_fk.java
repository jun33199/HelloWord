package com.ttsoft.bjtax.shenbao.model.domain;

import java.sql.Timestamp;

import com.ekernel.db.or.ORObject;

public class Gzsx_fk implements ORObject{
	Timestamp ydsj;
	String jsjdm;
	String gzsx_id;
	String fknr;
	String nsrmc;
	public Gzsx_fk() {
		
	}
	public Timestamp getYdsj() {
		return ydsj;
	}
	public void setYdsj(Timestamp ydsj) {
		this.ydsj = ydsj;
	}
	public String getJsjdm() {
		return jsjdm;
	}
	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}
	public String getGzsx_id() {
		return gzsx_id;
	}
	public void setGzsx_id(String gzsx_id) {
		this.gzsx_id = gzsx_id;
	}
	public String getNsrmc() {
		return nsrmc;
	}
	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}
	public String getFknr() {
		return fknr;
	}
	public void setFknr(String fknr) {
		this.fknr = fknr;
	}

}
