package com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsba.web;

import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.base.QyqssdsBaseForm;

public class QyqssdsBaForm extends QyqssdsBaseForm{
	/**
	 * 纳税人计算机代码 String
	 */
	private String jsjdm;

	/**
	 * 纳税人名称 String
	 */
	private String nsrmc;
	/**
	 * 纳税人识别号
	 */
	private String nsrsbh;
	/**
	 * 所属经济类型-登记注册类型代码 String
	 */
	private String ssjjlxdm = "";

	/**
	 * 所属经济类型-登记注册类型名称 String
	 */
	private String ssjjlxmc = "";
	
	/**
	 * 所属行业代码
	 */
	private String sshydm = "";

	/**
	 * 所属行业名称
	 */
	private String sshymc = "";
	/**
	 * 系统级别 String
	 */
	
	private String xtjb;
	
	/**
	 * 报表描述符 String
	 */
	
	private String bbmsf;
	/**
	 * 是否执行了查询动作， 防止查询一次后在进行修改没有进行查询也可以保存
	 * 所以做一标志 ，防止输入的计算机代码不对 并且还可以保证时时取到税源标识
	 * 1 代表查询  
	 */
	private String isQuery;
	/**
	 * 申报类型 0：网上，1：上门
	 */
	private String sqlx;
	private String czlx;//操作类型
	private String saveSuccess;
	public String getSqlx() {
		return sqlx;
	}
	public void setSqlx(String sqlx) {
		this.sqlx = sqlx;
	}
	public String getJsjdm() {
		return jsjdm;
	}
	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}
	public String getNsrmc() {
		return nsrmc;
	}
	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}
	public String getNsrsbh() {
		return nsrsbh;
	}
	public void setNsrsbh(String nsrsbh) {
		this.nsrsbh = nsrsbh;
	}
	public String getSsjjlxdm() {
		return ssjjlxdm;
	}
	public void setSsjjlxdm(String ssjjlxdm) {
		this.ssjjlxdm = ssjjlxdm;
	}
	public String getSsjjlxmc() {
		return ssjjlxmc;
	}
	public void setSsjjlxmc(String ssjjlxmc) {
		this.ssjjlxmc = ssjjlxmc;
	}
	public String getIsQuery() {
		return isQuery;
	}
	public void setIsQuery(String isQuery) {
		this.isQuery = isQuery;
	}
	public String getSshydm() {
		return sshydm;
	}
	public void setSshydm(String sshydm) {
		this.sshydm = sshydm;
	}
	public String getSshymc() {
		return sshymc;
	}
	public void setSshymc(String sshymc) {
		this.sshymc = sshymc;
	}
	public String getXtjb() {
		return xtjb;
	}
	public void setXtjb(String xtjb) {
		this.xtjb = xtjb;
	}
	public String getBbmsf() {
		return bbmsf;
	}
	public void setBbmsf(String bbmsf) {
		this.bbmsf = bbmsf;
	}
	public String getCzlx() {
		return czlx;
	}
	public void setCzlx(String czlx) {
		this.czlx = czlx;
	}
	public String getSaveSuccess() {
		return saveSuccess;
	}
	public void setSaveSuccess(String saveSuccess) {
		this.saveSuccess = saveSuccess;
	}

	
}
