package com.creationstar.bjtax.qsgl.model.bo.clfgl;

import java.io.Serializable;

public class ClfqxwhItem implements Serializable
{
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
