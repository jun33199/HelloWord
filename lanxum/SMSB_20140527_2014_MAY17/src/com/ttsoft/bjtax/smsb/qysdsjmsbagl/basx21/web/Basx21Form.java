package com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx21.web;

import com.ttsoft.bjtax.smsb.qysdsjmsbagl.jmsbajl.web.QysdsJmsbajlMainForm;

public class Basx21Form extends QysdsJmsbajlMainForm 
{
	//处理标识
	private String clbs = "0";
	
	//免增起始年度
	private String mzqsnd;
	
	//免增终止年度
	private String mzzznd = "2015";
	
	//序号
	private String xh;

/*------------------------------------------------------------------------------------------------------------------------------*/
	
	public String getClbs() {
		return clbs;
	}

	

	public String getMzzznd() {
		return mzzznd;
	}



	public void setMzzznd(String mzzznd) {
		this.mzzznd = mzzznd;
	}



	public String getMzqsnd() {
		return mzqsnd;
	}



	public void setMzqsnd(String mzqsnd) {
		this.mzqsnd = mzqsnd;
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
	
	
}
