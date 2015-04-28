package com.ttsoft.bjtax.smsb.model.client;

import java.io.Serializable;

public class scjxmodel implements Serializable{
	 /**
     * ½ÖÏç±àºÅ
     */
    public String jxid;

    /**
     * ½ÖÏçÃû³Æ
     */

    public  String jxmc;
    public scjxmodel(){
    	
    }
	public String getJxid() {
		return jxid;
	}
	public void setJxid(String jxid) {
		this.jxid = jxid;
	}
	public String getJxmc() {
		return jxmc;
	}
	public void setJxmc(String jxmc) {
		this.jxmc = jxmc;
	}
	
	
}
