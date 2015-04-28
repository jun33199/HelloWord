package com.ttsoft.bjtax.smsb.gdzys.sydj.sybg.model;

import java.io.Serializable;

public class GdzysSybg_sbmx implements Serializable
{
	//占地用途
	private String zdyt;
	
	//适用税额
	private String syse;
	
	//计税面积
	private String jsmj;
	
	//计征税额
	private String jzse;
		
	//减免面积
	private String jmmj;
	
	//减免税额
	private String jmse;
	
	//应税面积
	private String ysmj;
	
	//应纳税额
	private String ynse;
	
	/*--------------------------------------------------*/

	public String getZdyt() {
		return zdyt;
	}

	public void setZdyt(String zdyt) {
		this.zdyt = zdyt;
	}

	public String getSyse() {
		return syse;
	}

	public void setSyse(String syse) {
		this.syse = syse;
	}

	public String getJsmj() {
		return jsmj;
	}

	public void setJsmj(String jsmj) {
		this.jsmj = jsmj;
	}

	public String getJzse() {
		return jzse;
	}

	public void setJzse(String jzse) {
		this.jzse = jzse;
	}

	public String getJmmj() {
		return jmmj;
	}

	public void setJmmj(String jmmj) {
		this.jmmj = jmmj;
	}

	public String getJmse() {
		return jmse;
	}

	public void setJmse(String jmse) {
		this.jmse = jmse;
	}

	public String getYsmj() {
		return ysmj;
	}

	public void setYsmj(String ysmj) {
		this.ysmj = ysmj;
	}

	public String getYnse() {
		return ynse;
	}

	public void setYnse(String ynse) {
		this.ynse = ynse;
	}

	
	
}
