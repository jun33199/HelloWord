/*
 * Created on 2010-2-1
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.syax.bjtax.shenbao.model.dm;

import java.io.Serializable;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Zlqddm implements Serializable{
	/**
	 * 资料清单
	 */
	private String zlqdmc;

	/**
	 * 是否可以删除
	 */
	private String sfkysc;

	/**
	 * 是否审核通过
	 */
	private String sfshtg;

	/**
	 * 资料清单代码
	 */
	private String zlqddm;


	/**
	 * 减免备案事项代码
	 */
	private String jmbasxdm;

	/**
	 * 适用年度  数据库默认为2012
	 */
	private String synd;
	/**
	 * @return Returns the jmbasxdm.
	 */
	public String getJmbasxdm() {
		return jmbasxdm;
	}
	/**
	 * @param jmbasxdm The jmbasxdm to set.
	 */
	public void setJmbasxdm(String jmbasxdm) {
		this.jmbasxdm = jmbasxdm;
	}
	/**
	 * @return Returns the sfkysc.
	 */
	public String getSfkysc() {
		return sfkysc;
	}
	/**
	 * @param sfkysc The sfkysc to set.
	 */
	public void setSfkysc(String sfkysc) {
		this.sfkysc = sfkysc;
	}
	/**
	 * @return Returns the sfshtg.
	 */
	public String getSfshtg() {
		return sfshtg;
	}
	/**
	 * @param sfshtg The sfshtg to set.
	 */
	public void setSfshtg(String sfshtg) {
		this.sfshtg = sfshtg;
	}
	/**
	 * @return Returns the zlqddm.
	 */
	public String getZlqddm() {
		return zlqddm;
	}
	/**
	 * @param zlqddm The zlqddm to set.
	 */
	public void setZlqddm(String zlqddm) {
		this.zlqddm = zlqddm;
	}
	/**
	 * @return Returns the zlqdmc.
	 */
	public String getZlqdmc() {
		return zlqdmc;
	}
	/**
	 * @param zlqdmc The zlqdmc to set.
	 */
	public void setZlqdmc(String zlqdmc) {
		this.zlqdmc = zlqdmc;
	}
	public String getSynd() {
		return synd;
	}
	public void setSynd(String synd) {
		this.synd = synd;
	}
	
}
