package com.ttsoft.bjtax.shenbao.model.client;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 委托代征、代扣、代售、监督代售单位认定情况
 * @author lsc-tujb
 * 2014.03.28
 */
public class WtdwInfor implements Serializable
{

	public WtdwInfor()
    {
    }
	
	String jsjdm;  //计算机代码
	
	String szsmdm; //税种税目
	
	String sklxbs; //税款类型标识
	
	public String getJsjdm() {
		return jsjdm;
	}
	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}
	public String getSzsmdm() {
		return szsmdm;
	}
	public void setSzsmdm(String szsmdm) {
		this.szsmdm = szsmdm;
	}
	public String getSklxbs() {
		return sklxbs;
	}
	public void setSklxbs(String sklxbs) {
		this.sklxbs = sklxbs;
	}
	
}
