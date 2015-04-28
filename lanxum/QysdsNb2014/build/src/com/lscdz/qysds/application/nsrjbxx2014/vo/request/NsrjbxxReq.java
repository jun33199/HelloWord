package com.lscdz.qysds.application.nsrjbxx2014.vo.request;

import java.io.Serializable;

public class NsrjbxxReq implements Serializable{
	private static final long serialVersionUID = 1L;
	//计算机代码
	private String jsjdm;
	//税款年度
	private String nd;
	private String startTime;
	private String endTime;
	private String smwsbz;//上门（0），网上（1）标志

	public String getSmwsbz() {
		return smwsbz;
	}
	public void setSmwsbz(String smwsbz) {
		this.smwsbz = smwsbz;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getJsjdm() {
		return jsjdm;
	}
	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}
	public String getNd() {
		return nd;
	}
	public void setNd(String nd) {
		this.nd = nd;
	}
	

}
