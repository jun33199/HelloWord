package com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx05.web;

import java.util.List;

import com.ttsoft.bjtax.smsb.qysdsjmsbagl.jmsbajl.web.QysdsJmsbajlMainForm;

public class Basx05Form extends QysdsJmsbajlMainForm {
	
	/*
	 * 序号
	 */
	private String xh;
	/*
	 * 免征企业所得税列表
	 */
	private List mzqysdsList;
	/*
	 * 免征企业所得税
	 */
	private String mzqysds;
	/*
	 * 免征企业所得税名称
	 */
	private String mzqysdsmc;
	/*
	 * 免征企业所得税代码
	 */
	private String mzqysdsdm;
	/*
	 * 免征所得额 	
	 */
	private String mzsde;
	/*
	 * 减免征收企业所得税列表
	 */
	private List jbzsqysdsList;
	/*
	 * 减免征收企业所得税
	 */
	private String jbzsqysds;
	/*
	 * 减半征收企业所得税名称
	 */
	private String jbzsqysdsmc;
	/*
	 * 减半征收企业所得税代码
	 */
	private String jbzsqysdsdm;
	/*
	 * 减征所得额
	 */
	private String jzsde;
	/*
	 * 处理标示
	 */
	private String clbs = "0";
	/*
	 * 免征或减半 0:免征；1：减半;
	 */
	private String mzhjb = "0";
	
	public String getClbs() {
		return clbs;
	}
	public void setClbs(String clbs) {
		this.clbs = clbs;
	}
	public String getJbzsqysdsdm() {
		return jbzsqysdsdm;
	}
	public void setJbzsqysdsdm(String jbzsqysdsdm) {
		this.jbzsqysdsdm = jbzsqysdsdm;
	}
	public List getJbzsqysdsList() {
		return jbzsqysdsList;
	}
	public void setJbzsqysdsList(List jbzsqysdsList) {
		this.jbzsqysdsList = jbzsqysdsList;
	}
	public String getJbzsqysdsmc() {
		return jbzsqysdsmc;
	}
	public void setJbzsqysdsmc(String jbzsqysdsmc) {
		this.jbzsqysdsmc = jbzsqysdsmc;
	}
	public String getJzsde() {
		return jzsde;
	}
	public void setJzsde(String jzsde) {
		this.jzsde = jzsde;
	}
	public String getMzqysdsdm() {
		return mzqysdsdm;
	}
	public void setMzqysdsdm(String mzqysdsdm) {
		this.mzqysdsdm = mzqysdsdm;
	}
	public List getMzqysdsList() {
		return mzqysdsList;
	}
	public void setMzqysdsList(List mzqysdsList) {
		this.mzqysdsList = mzqysdsList;
	}
	public String getMzqysdsmc() {
		return mzqysdsmc;
	}
	public void setMzqysdsmc(String mzqysdsmc) {
		this.mzqysdsmc = mzqysdsmc;
	}
	public String getMzsde() {
		return mzsde;
	}
	public void setMzsde(String mzsde) {
		this.mzsde = mzsde;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getJbzsqysds() {
		return jbzsqysds;
	}
	public void setJbzsqysds(String jbzsqysds) {
		this.jbzsqysds = jbzsqysds;
	}
	public String getMzqysds() {
		return mzqysds;
	}
	public void setMzqysds(String mzqysds) {
		this.mzqysds = mzqysds;
	}
	public String getMzhjb() {
		return mzhjb;
	}
	public void setMzhjb(String mzhjb) {
		this.mzhjb = mzhjb;
	}
	
}