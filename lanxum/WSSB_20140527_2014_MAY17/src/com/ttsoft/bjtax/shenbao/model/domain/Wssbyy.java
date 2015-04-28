package com.ttsoft.bjtax.shenbao.model.domain;

import java.sql.Timestamp;

import com.ekernel.db.or.ORObject;

/**
 * <p>Title: 北京地税核心征管系统－－网上申报--无税申报原因</p>
 * <p>Description: </p>
 * @author  lsc-tujb
 * @version 1.1
 */

public class Wssbyy implements ORObject
{

	public Wssbyy() 
	{
		
	}
	
	String wssbyydm;
	String wssbyymc;
	String zchbsdm;
	String zxbs;
	String lrr;
	Timestamp lrrq;
	
	public String getWssbyydm() {
		return wssbyydm;
	}
	public void setWssbyydm(String wssbyydm) {
		this.wssbyydm = wssbyydm;
	}
	public String getWssbyymc() {
		return wssbyymc;
	}
	public void setWssbyymc(String wssbyymc) {
		this.wssbyymc = wssbyymc;
	}
	public String getZchbsdm() {
		return zchbsdm;
	}
	public void setZchbsdm(String zchbsdm) {
		this.zchbsdm = zchbsdm;
	}
	public String getZxbs() {
		return zxbs;
	}
	public void setZxbs(String zxbs) {
		this.zxbs = zxbs;
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
	
}
