package com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx20.web;

import java.util.List;

import com.ttsoft.bjtax.smsb.qysdsjmsbagl.jmsbajl.web.QysdsJmsbajlMainForm;

public class Basx20Form extends QysdsJmsbajlMainForm {

	/*
	 * 序号
	 */
	private String xh;
	/*
	 * 动漫企业类型
	 */
	private String dmlx;
	/*
	 * 动漫企业列表
	 */
	private List dmlxList;
	
	/*
	 * 动漫企业类型代码
	 */
	private String dmlxdm;
	/*
	 *  动漫企业类型名称
	 */
	private String dmlxmc;
	/*
	 * 证书编号
	 */
	private String zsbh;
	
	/*
	 * 证书起始日期
	 */
	private String zsqsrq;
	
	/*
	 * 证书终止日期
	 */
	private String zszzrq;
	/*
	 * 获利年度
	 */
	private String hlnd ;
	/*
	 * 免征起始年度
	 */
	private String mzqsnd;
	/*
	 * 免征终止年度
	 */
	private String mzzznd;
	/*
	 * 减征起始年度
	 */
	private String jzqsnd;
	/*
	 * 减征终止年度
	 */
	private String jzzznd;
	/*
	 * 减免税额
	 */
	private String jmse;
	
	private String clbs="0";

	public String getClbs() {
		return clbs;
	}

	public String getDmlx() {
		return dmlx;
	}

	public void setDmlx(String dmlx) {
		this.dmlx = dmlx;
	}

	public String getDmlxdm() {
		return dmlxdm;
	}

	public void setDmlxdm(String dmlxdm) {
		this.dmlxdm = dmlxdm;
	}

	public String getDmlxmc() {
		return dmlxmc;
	}

	public void setDmlxmc(String dmlxmc) {
		this.dmlxmc = dmlxmc;
	}

	public String getHlnd() {
		return hlnd;
	}

	public void setHlnd(String hlnd) {
		this.hlnd = hlnd;
	}

	public String getJmse() {
		return jmse;
	}

	public void setJmse(String jmse) {
		this.jmse = jmse;
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

	public String getZsqsrq() {
		return zsqsrq;
	}

	public void setZsqsrq(String zsqsrq) {
		this.zsqsrq = zsqsrq;
	}

	public String getZszzrq() {
		return zszzrq;
	}

	public void setZszzrq(String zszzrq) {
		this.zszzrq = zszzrq;
	}

	public void setClbs(String clbs) {
		this.clbs = clbs;
	}

	
	
	
	
	

	public List getDmlxList() {
		return dmlxList;
	}

	public void setDmlxList(List dmlxList) {
		this.dmlxList = dmlxList;
	}

	
	

	
}