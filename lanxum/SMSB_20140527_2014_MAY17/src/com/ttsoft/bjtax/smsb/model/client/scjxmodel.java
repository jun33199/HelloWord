package com.ttsoft.bjtax.smsb.model.client;

import java.io.Serializable;

public class scjxmodel implements Serializable{
	 /**
     * ������
     */
    public String jxid;

    /**
     * ��������
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
