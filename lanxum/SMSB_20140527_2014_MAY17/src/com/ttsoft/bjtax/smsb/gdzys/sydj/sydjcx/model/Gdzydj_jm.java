package com.ttsoft.bjtax.smsb.gdzys.sydj.sydjcx.model;

import java.io.Serializable;

public class Gdzydj_jm implements Serializable
{

	private static final long serialVersionUID = 788330204088951070L;

	//减免税类别代码
	private String jmslb;
	
	//减免面积
	private String jmmj;
	
	//减免税额
	private String jmse;
	
	/*-----------------------------------------------------------*/
	
	public String getJmslb() {
		return jmslb;
	}

	public void setJmslb(String jmslb) {
		this.jmslb = jmslb;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	
}
