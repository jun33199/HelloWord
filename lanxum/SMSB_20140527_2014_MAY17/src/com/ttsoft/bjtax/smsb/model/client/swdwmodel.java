package com.ttsoft.bjtax.smsb.model.client;

import java.io.Serializable;

public class swdwmodel implements Serializable{
	 /**
     * ˰��λ���
     */
    public String swdwid;

    /**
     * ˰��λ����
     */

    public  String swdwmc;
    public swdwmodel(){
    	
    }
	public String getSwdwid() {
		return swdwid;
	}
	public void setSwdwid(String swdwid) {
		this.swdwid = swdwid;
	}
	public String getSwdwmc() {
		return swdwmc;
	}
	public void setSwdwmc(String swdwmc) {
		this.swdwmc = swdwmc;
	}
	
	
}
	