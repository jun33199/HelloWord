package com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx09.web;


import com.ttsoft.bjtax.smsb.qysdsjmsbagl.jmsbajl.web.QysdsJmsbajlMainForm;

public class Basx09Form extends QysdsJmsbajlMainForm { 

	
	
	/*
	 * 序号
	 */
	private String xh;
	/*
	 * 证书类型
	 */
	private String zslx;
	/*
	 * 证书类型代码
	 */
	private String zslxdm;
	/*
	 * 获利年度
	 */
	private String hlnd;
	
	/*
	 * 证书编号
	 */
	private String zsbh;
	/*
	 * 证书有效期起
	 */
	private String zsyxqq;
	/*
	 * 证书有效期止
	 */
	private String zsyxqz;
	
	/*
	 * 免征起始年度
	 */
	private String mzqsnd;
	/*
	 * 免征终止年度
	 */
	private String mzzznd ;
	
	/*
	 * 减征起始年度
	 */
	private String jzqsnd ;
	/*
	 * 减征终止年度
	 */
	private String jzzznd ;
	
	
	
	
	
	private String flag="0";
	private String clbs="0";

	public String getClbs() {
		return clbs;
	}

	public void setClbs(String clbs) {
		this.clbs = clbs;
	}

	public String getHlnd() {
		return hlnd;
	}

	public void setHlnd(String hlnd) {
		this.hlnd = hlnd;
	}

	public String getJzqsnd() {
		return jzqsnd;
	}

	public void setJzqsnd(String jzqsnd) {
		this.jzqsnd = jzqsnd;
	}

	public String getJzzznd() {
		return jzzznd;
	}

	public void setJzzznd(String jzzznd) {
		this.jzzznd = jzzznd;
	}

	public String getMzqsnd() {
		return mzqsnd;
	}

	public void setMzqsnd(String mzqsnd) {
		this.mzqsnd = mzqsnd;
	}

	public String getMzzznd() {
		return mzzznd;
	}

	public void setMzzznd(String mzzznd) {
		this.mzzznd = mzzznd;
	}



	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getZsbh() {
		return zsbh;
	}

	public void setZsbh(String zsbh) {
		this.zsbh = zsbh;
	}

	
	

	public String getZslx() {
		return zslx;
	}

	public void setZslx(String zslx) {
		this.zslx = zslx;
	}

	public String getZsyxqq() {
		return zsyxqq;
	}

	public void setZsyxqq(String zsyxqq) {
		this.zsyxqq = zsyxqq;
	}

	public String getZsyxqz() {
		return zsyxqz;
	}

	public void setZsyxqz(String zsyxqz) {
		this.zsyxqz = zsyxqz;
	}

	

	public String getZslxdm() {
		return zslxdm;
	}

	public void setZslxdm(String zslxdm) {
		this.zslxdm = zslxdm;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}