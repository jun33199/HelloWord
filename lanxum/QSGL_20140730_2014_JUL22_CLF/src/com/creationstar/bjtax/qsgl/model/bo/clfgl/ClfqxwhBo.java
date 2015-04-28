package com.creationstar.bjtax.qsgl.model.bo.clfgl;

import java.io.Serializable;
import java.util.ArrayList;

public class ClfqxwhBo implements Serializable{
	
	//新增人员
	private String allNewAddInfo;
	
	/**
     * 参数类型
     */
    private String cslx;
    
    /**
     * 对应值
     */
    private String dyz;
    
    /**
     * 注销标示
     */
    private String zxbs;
    
    /**
     * 参数类型描述
     */
    private String cslxms;
	
	
	/**
     * 录入人
     */
    private String lrr;

    /**
     * 录入日期
     */
    private String lrrq;
    
    /**
	 * 存量房交易参数表
	 */
    public ArrayList jycsList= new ArrayList();

	public String getLrr() {
		return lrr;
	}

	public void setLrr(String lrr) {
		this.lrr = lrr;
	}

	public String getLrrq() {
		return lrrq;
	}

	public void setLrrq(String lrrq) {
		this.lrrq = lrrq;
	}

	public ArrayList getJycsList() {
		return jycsList;
	}

	public void setJycsList(ArrayList jycsList) {
		this.jycsList = jycsList;
	}

	public String getAllNewAddInfo() {
		return allNewAddInfo;
	}

	public void setAllNewAddInfo(String allNewAddInfo) {
		this.allNewAddInfo = allNewAddInfo;
	}

	public String getCslx() {
		return cslx;
	}

	public void setCslx(String cslx) {
		this.cslx = cslx;
	}

	public String getDyz() {
		return dyz;
	}

	public void setDyz(String dyz) {
		this.dyz = dyz;
	}

	public String getZxbs() {
		return zxbs;
	}

	public void setZxbs(String zxbs) {
		this.zxbs = zxbs;
	}

	public String getCslxms() {
		return cslxms;
	}

	public void setCslxms(String cslxms) {
		this.cslxms = cslxms;
	}

}
