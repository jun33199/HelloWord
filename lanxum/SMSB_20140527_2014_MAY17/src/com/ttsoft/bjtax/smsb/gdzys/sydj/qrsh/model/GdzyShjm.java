package com.ttsoft.bjtax.smsb.gdzys.sydj.qrsh.model;

import java.io.Serializable;

public class GdzyShjm implements Serializable
{

	private static final long serialVersionUID = 788330204088951070L;

	//����˰������
	private String jmslb;
	
	//�������
	private String jmmj;
	
	//����˰��
	private String jmse;
	
	/*-----------------------------------------------------------*/
	

	public String getJmslb() {
		return jmslb;
	}

	public void setJmslb(String jmslb) {
		this.jmslb = jmslb;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
