package com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx19.web;

import java.util.List;

import com.ttsoft.bjtax.smsb.qysdsjmsbagl.jmsbajl.web.QysdsJmsbajlMainForm;

public class Basx19Form extends QysdsJmsbajlMainForm {


	/*
	 * 序号
	 */
	private String xh;
	/*
	 * 文化事业单位类型代码
	 */
	private String whsylxdm;
	/*
	 * 文化事业单位类型
	 */
	private String whsylx;
	/*
	 * 文化事业单位列表
	 */
	private List whsylxList;
	/*
	 * 文化事业单位名称
	 */
	private String whsylxmc;

	/*
	 * 减免税额
	 */
	private String jmse;
	
	
	
	private String clbs="0";



	public String getClbs() {
		return clbs;
	}



	public void setClbs(String clbs) {
		this.clbs = clbs;
	}



	public String getJmse() {
		return jmse;
	}



	public void setJmse(String jmse) {
		this.jmse = jmse;
	}



	public String getWhsylx() {
		return whsylx;
	}



	public void setWhsylx(String whsylx) {
		this.whsylx = whsylx;
	}



	public String getWhsylxdm() {
		return whsylxdm;
	}



	public void setWhsylxdm(String whsylxdm) {
		this.whsylxdm = whsylxdm;
	}



	public List getWhsylxList() {
		return whsylxList;
	}



	public void setWhsylxList(List whsylxList) {
		this.whsylxList = whsylxList;
	}



	public String getWhsylxmc() {
		return whsylxmc;
	}



	public void setWhsylxmc(String whsylxmc) {
		this.whsylxmc = whsylxmc;
	}



	public String getXh() {
		return xh;
	}



	public void setXh(String xh) {
		this.xh = xh;
	}

	

	

	
	
	
}