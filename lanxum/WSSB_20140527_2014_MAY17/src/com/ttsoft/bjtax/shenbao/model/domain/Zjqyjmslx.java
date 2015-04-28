package com.ttsoft.bjtax.shenbao.model.domain;

import java.sql.Timestamp;

import com.ekernel.db.or.ORObject;

/**
 * <p>Title: 北京地税核心征管系统－－网上申报--总局企业减免税类型</p>
 * <p>Description: </p>
 * @author  lsc-tujb
 * @version 1.1
 */

public class Zjqyjmslx implements ORObject
{

	public Zjqyjmslx() 
	{
		
	}
	
	String jmslxdm;
	String wh;
	String jmslxmc;
	String sz;
	String lrr;
	Timestamp lrrq;
	String jmszcqsnd;
	String szdm;
	public String getJmslxdm() {
		return jmslxdm;
	}
	public void setJmslxdm(String jmslxdm) {
		this.jmslxdm = jmslxdm;
	}
	public String getWh() {
		return wh;
	}
	public void setWh(String wh) {
		this.wh = wh;
	}
	public String getJmslxmc() {
		return jmslxmc;
	}
	public void setJmslxmc(String jmslxmc) {
		this.jmslxmc = jmslxmc;
	}
	public String getSz() {
		return sz;
	}
	public void setSz(String sz) {
		this.sz = sz;
	}
	public String getLrr() {
		return lrr;
	}
	public void setLrr(String lrr) {
		this.lrr = lrr;
	}
	public Timestamp getLrrq() {
		return lrrq;
	}
	public void setLrrq(Timestamp lrrq) {
		this.lrrq = lrrq;
	}
	public String getJmszcqsnd() {
		return jmszcqsnd;
	}
	public void setJmszcqsnd(String jmszcqsnd) {
		this.jmszcqsnd = jmszcqsnd;
	}
	public String getSzdm() {
		return szdm;
	}
	public void setSzdm(String szdm) {
		this.szdm = szdm;
	}
	
}
