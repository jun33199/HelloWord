package com.ttsoft.bjtax.smsb.model.client;

import java.io.Serializable;

public class djlxmodel implements Serializable{
	 /**
     * �Ǽ����ͱ��
     */
    public String djlxid;

    /**
     * �Ǽ���������
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
