package com.ttsoft.bjtax.shenbao.gzsxfk.web;

import java.sql.Timestamp;

import com.ttsoft.framework.form.BaseForm;

public class GzsxfkForm extends BaseForm {
	Timestamp ydsj;
	String jsjdm;
	String gzsx_id;
	Timestamp fksj;
	String ydbs;
	String fkbs;
	String fknr;
	String nsrmc;
	public String getNsrmc() {
		return nsrmc;
	}
	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}
	public GzsxfkForm() {
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
	public Timestamp getFksj() {
		return fksj;
	}
	public void setFksj(Timestamp fksj) {
		this.fksj = fksj;
	}
	public String getYdbs() {
		return ydbs;
	}
	public void setYdbs(String ydbs) {
		this.ydbs = ydbs;
	}
	public String getFkbs() {
		return fkbs;
	}
	public void setFkbs(String fkbs) {
		this.fkbs = fkbs;
	}
	public String getFknr() {
		return fknr;
	}
	public void setFknr(String fknr) {
		this.fknr = fknr;
	}
	
	

}
