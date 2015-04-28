package com.ttsoft.bjtax.smsb.model.client;

import java.io.Serializable;

public class nsrtogzsx implements Serializable{
    /**
     * 计算机代码
     */
    private String jsjdm;

    /**
     * 纳税人名称
     */

    private  String nsrmc;

    /**
     * 告知事项标题
     */

    private String gzsxnrbt;

    /**
     * 纳税人阅读时间
     */

    private  String ydsj;

    /**
     * 纳税人反馈链接
     */

    private  String fknr ;
    /**
     * 告知事项内容链接
     */

    private  String gzsxnr ;
    /**
     * 告知事项id
     */

    private  String gzsx_id ;
    public  nsrtogzsx(){
    	
    }

	public String getJsjdm() {
		return jsjdm;
	}

	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}

	public String getNsrmc() {
		return nsrmc;
	}

	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}

	public String getGzsxnrbt() {
		return gzsxnrbt;
	}

	public void setGzsxnrbt(String gzsxnrbt) {
		this.gzsxnrbt = gzsxnrbt;
	}

	public String getYdsj() {
		return ydsj;
	}

	public void setYdsj(String ydsj) {
		this.ydsj = ydsj;
	}

	public String getFknr() {
		return fknr;
	}

	public void setFknr(String fknr) {
		this.fknr = fknr;
	}

	public String getGzsxnr() {
		return gzsxnr;
	}

	public void setGzsxnr(String gzsxnr) {
		this.gzsxnr = gzsxnr;
	}

	public String getGzsx_id() {
		return gzsx_id;
	}

	public void setGzsx_id(String gzsx_id) {
		this.gzsx_id = gzsx_id;
	}

	

}
