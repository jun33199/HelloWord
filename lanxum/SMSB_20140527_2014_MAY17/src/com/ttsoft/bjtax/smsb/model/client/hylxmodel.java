package com.ttsoft.bjtax.smsb.model.client;

import java.io.Serializable;

public class hylxmodel implements Serializable{
	 /**
     * ��ҵ���
     */
    public String hylxid;

    /**
     * ��ҵ����
     */

    public  String hylxmc;
    public hylxmodel(){
    	
    }
	public String getHylxid() {
		return hylxid;
	}
	public void setHylxid(String hylxid) {
		this.hylxid = hylxid;
	}
	public String getHylxmc() {
		return hylxmc;
	}
	public void setHylxmc(String hylxmc) {
		this.hylxmc = hylxmc;
	}
	

}
