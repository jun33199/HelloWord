package com.ttsoft.bjtax.shenbao.fangtu.total;

public class FwZiyongTotal implements java.io.Serializable {
	private double fcyz = 0.0;
	private double swjggz = 0.0;
	private double qzmsyz = 0.0;
	private double qzysyz = 0.0;
	private double nynse = 0.0;
	
	
	public FwZiyongTotal() {
		super();
	}

	public FwZiyongTotal(double fcyz, double swjggz, double qzmsyz, double qzysyz, double nynse) {
		super();
		this.fcyz = fcyz;
		this.swjggz = swjggz;
		this.qzmsyz = qzmsyz;
		this.qzysyz = qzysyz;
		this.nynse = nynse;
	}
	
	public double getFcyz() {
		return fcyz;
	}
	public void setFcyz(double fcyz) {
		this.fcyz = fcyz;
	}
	public double getNynse() {
		return nynse;
	}
	public void setNynse(double nynse) {
		this.nynse = nynse;
	}
	public double getQzmsyz() {
		return qzmsyz;
	}
	public void setQzmsyz(double qzmsyz) {
		this.qzmsyz = qzmsyz;
	}
	public double getQzysyz() {
		return qzysyz;
	}
	public void setQzysyz(double qzysyz) {
		this.qzysyz = qzysyz;
	}
	public double getSwjggz() {
		return swjggz;
	}
	public void setSwjggz(double swjggz) {
		this.swjggz = swjggz;
	}
	
	
}
