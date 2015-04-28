package com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx03.web;



import com.ttsoft.bjtax.smsb.qysdsjmsbagl.jmsbajl.web.QysdsJmsbajlMainForm;

public class Basx03Form extends QysdsJmsbajlMainForm {
	
	
	//1、序号
	private String xh;
	//2、处理标示
	private String clbs = "0";
	//4、支付给残疾职工工资
	private String zfgz;
	//5、加计扣除额
	private String jjkce;
	
	public String getClbs() {
		return clbs;
	}
	public void setClbs(String clbs) {
		this.clbs = clbs;
	}

	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getZfgz() {
		return zfgz;
	}
	public void setZfgz(String zfgz) {
		this.zfgz = zfgz;
	}
	public String getJjkce() {
		return jjkce;
	}
	public void setJjkce(String jjkce) {
		this.jjkce = jjkce;
	}
	
	
	
}