package com.ttsoft.bjtax.smsb.model.client;

import java.io.Serializable;

public class nsrztmodel implements Serializable{
	 /**
     * ״̬���
     */
    public String nsrztid;

    /**
     * ״̬����
     */

    public  String nsrztmc;
    public nsrztmodel(){
    	
    }
	public String getNsrztid() {
		return nsrztid;
	}
	public void setNsrztid(String nsrztid) {
		this.nsrztid = nsrztid;
	}
	public String getNsrztmc() {
		return nsrztmc;
	}
	public void setNsrztmc(String nsrztmc) {
		this.nsrztmc = nsrztmc;
	}
	
}
	