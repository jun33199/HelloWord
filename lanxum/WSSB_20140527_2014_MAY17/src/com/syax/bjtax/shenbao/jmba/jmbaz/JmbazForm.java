/*
 * Created on 2010-1-1
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.syax.bjtax.shenbao.jmba.jmbaz;

import java.io.Serializable;

/**
 * @author MI_Viewer
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JmbazForm implements Serializable{
	public static final String FORM_ATTRIBUTE_NAME="jmbazForm";
	public static final String BASQBH_ATTRIBUTE_NAME="basqbh";
	public static final String BASQWSH_ATTRIBUTE_NAME="basqwsh";
	public static final String ZQ_ATTRIBUTE_NAME="CHECKZQ";
	public static final String JMBAMXBO_ATTRIBUTE_NAME="jmbamxbo";
	public static final String MESSAGE_ATTRIBUTE_NAME="hintmessage";
	
	/**
	 * 备案申请文书号
	 */
	private String basqwsh;

	/**
	 *	备案年度
	 */
	private String band;

	/**
	 * 减免备案事项代码
	 */
	private String jmbasxdm;

	/**
	 * 减免备案事项代码
	 */
	private String jmbasxdmadd;


	/**
	 * 状态代码
	 */
	private String ztdm;

	/**
	 * 征期判断，0在征期，1不在
	 */
	private String checkZq;

	/**
	 * @return Returns the band.
	 */
	public String getBand() {
		return band;
	}
	/**
	 * @param band The band to set.
	 */
	public void setBand(String band) {
		this.band = band;
	}
	/**
	 * @return Returns the basqwsh.
	 */
	public String getBasqwsh() {
		return basqwsh;
	}
	/**
	 * @param basqwsh The basqwsh to set.
	 */
	public void setBasqwsh(String basqwsh) {
		this.basqwsh = basqwsh;
	}
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
	 * @return Returns the ztdm.
	 */
	public String getZtdm() {
		return ztdm;
	}
	/**
	 * @param ztdm The ztdm to set.
	 */
	public void setZtdm(String ztdm) {
		this.ztdm = ztdm;
	}
	/**
	 * @return Returns the checkZq.
	 */
	public String getCheckZq() {
		return checkZq;
	}
	/**
	 * @param checkZq The checkZq to set.
	 */
	public void setCheckZq(String checkZq) {
		this.checkZq = checkZq;
	}
	/**
	 * @return Returns the jmbasxdmadd.
	 */
	public String getJmbasxdmadd() {
		return jmbasxdmadd;
	}
	/**
	 * @param jmbasxdmadd The jmbasxdmadd to set.
	 */
	public void setJmbasxdmadd(String jmbasxdmadd) {
		this.jmbasxdmadd = jmbasxdmadd;
	}
}
