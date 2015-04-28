package com.ttsoft.bjtax.smsb.model.client;

import java.io.Serializable;

public class djlxmodel implements Serializable{
	 /**
     * 登记类型编号
     */
    public String djlxid;

    /**
     * 登记类型名称
     */

    public  String djlxmc;
    public djlxmodel(){
    	
    }
	public String getDjlxid() {
		return djlxid;
	}
	public void setDjlxid(String djlxid) {
		this.djlxid = djlxid;
	}
	public String getDjlxmc() {
		return djlxmc;
	}
	public void setDjlxmc(String djlxmc) {
		this.djlxmc = djlxmc;
	}

}
