/*
 * Created on 2009-12-21
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
	package com.syax.bjtax.shenbao.model.dm;

import java.io.Serializable;

/**
 * @author guzx
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JMBASXDM implements Serializable{
	   /** 减免备案事项代码
	    * 
	    * @pdOid 57e39b04-2c98-45d1-b8c6-437a83986e42 */
	   public java.lang.String JMBASXDM;
	   /** 减免备案事项名称
	    * 
	    * @pdOid 06bbb3ec-2724-499f-b857-d9d7c0703397 */
	   public java.lang.String JMBASXMC;

	public static void main(String[] args) {
	}
	/**
	 * @return Returns the jMBASXDM.
	 */
	public java.lang.String getJMBASXDM() {
		return JMBASXDM;
	}
	/**
	 * @param jmbasxdm The jMBASXDM to set.
	 */
	public void setJMBASXDM(java.lang.String jmbasxdm) {
		JMBASXDM = jmbasxdm;
	}
	/**
	 * @return Returns the jMBASXMC.
	 */
	public java.lang.String getJMBASXMC() {
		return JMBASXMC;
	}
	/**
	 * @param jmbasxmc The jMBASXMC to set.
	 */
	public void setJMBASXMC(java.lang.String jmbasxmc) {
		JMBASXMC = jmbasxmc;
	}
}
