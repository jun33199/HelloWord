package com.ttsoft.bjtax.shenbao.fangtu.total;

public class TdZiyongTotal implements java.io.Serializable {
	private double tdmj = 0.0;
	private double qzmsmj = 0.0;
	private double qzysmj = 0.0;
	private double mpfmse = 0.0;
	private double nynse = 0.0;
	
	
	public TdZiyongTotal() {
		super();
	}
	public TdZiyongTotal(double tdmj, double qzmsmj, double qzysmj, double mpfmse, double nynse) {
		super();
		this.tdmj = tdmj;
		this.qzmsmj = qzmsmj;
		this.qzysmj = qzysmj;
		this.mpfmse = mpfmse;
		this.nynse = nynse;
	}
	public double getMpfmse() {
		return mpfmse;
	}
	public void setMpfmse(double mpfmse) {
		this.mpfmse = mpfmse;
	}
	public double getNynse() {
		return nynse;
	}
	public void setNynse(double nynse) {
		this.nynse = nynse;
	}
	public double getQzmsmj() {
		return qzmsmj;
	}
	public void setQzmsmj(double qzmsmj) {
		this.qzmsmj = qzmsmj;
	}
	public double getQzysmj() {
		return qzysmj;
	}
	public void setQzysmj(double qzysmj) {
		this.qzysmj = qzysmj;
	}
	public double getTdmj() {
		return tdmj;
	}
	public void setTdmj(double tdmj) {
		this.tdmj = tdmj;
	}
	
	

}
