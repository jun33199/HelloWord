package com.ttsoft.bjtax.shenbao.fangtu.total;

public class FwChuzuTotal implements java.io.Serializable {
	private double nzjsr = 0.0;
	private double nynse = 0.0;
	
	
	public FwChuzuTotal() {
		super();
	}


	public FwChuzuTotal(double nzjsr, double nynse) {
		super();
		this.nzjsr = nzjsr;
		this.nynse = nynse;
	}


	public double getNynse() {
		return nynse;
	}


	public void setNynse(double nynse) {
		this.nynse = nynse;
	}


	public double getNzjsr() {
		return nzjsr;
	}


	public void setNzjsr(double nzjsr) {
		this.nzjsr = nzjsr;
	}

	
	

}
