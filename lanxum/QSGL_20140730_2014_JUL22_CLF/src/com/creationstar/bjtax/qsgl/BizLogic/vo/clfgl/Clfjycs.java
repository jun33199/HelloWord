package com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl;

import java.io.Serializable;

/**
 * 存量房交易参数表VO
 * @author tutu
 * 2013-10-10(1)
 */
public class Clfjycs implements Serializable {

	/**
     * 参数类型
     */
    public java.lang.String cslx;
    /**
     * 对应值
     */
    public java.lang.String dyz;
    /**
     * 注销标示
     */
    public java.lang.String zxbs;
    /**
     * 创建人
     */
    public java.lang.String cjr;
    /**
     * 录入人
     */
    public java.lang.String lrr;
    
    /**
     * 创建日期
     */
    public java.sql.Timestamp cjrq;
    
    /**
     * 录入日期
     */
    public java.sql.Timestamp lrrq;
    
    /**
     * 参数类型描述
     */
    public java.lang.String cslxms;
    
    /**
     * 税务机关组织结构代码
     */
    public java.lang.String  swjgzzjgdm;

	public java.lang.String getCslx() {
		return cslx;
	}

	public void setCslx(java.lang.String cslx) {
		this.cslx = cslx;
	}

	public java.lang.String getDyz() {
		return dyz;
	}

	public void setDyz(java.lang.String dyz) {
		this.dyz = dyz;
	}

	public java.lang.String getZxbs() {
		return zxbs;
	}

	public void setZxbs(java.lang.String zxbs) {
		this.zxbs = zxbs;
	}

	public java.lang.String getCjr() {
		return cjr;
	}

	public void setCjr(java.lang.String cjr) {
		this.cjr = cjr;
	}

	public java.lang.String getLrr() {
		return lrr;
	}

	public void setLrr(java.lang.String lrr) {
		this.lrr = lrr;
	}

	public java.sql.Timestamp getCjrq() {
		return cjrq;
	}

	public void setCjrq(java.sql.Timestamp cjrq) {
		this.cjrq = cjrq;
	}

	public java.sql.Timestamp getLrrq() {
		return lrrq;
	}

	public void setLrrq(java.sql.Timestamp lrrq) {
		this.lrrq = lrrq;
	}

	public java.lang.String getCslxms() {
		return cslxms;
	}

	public void setCslxms(java.lang.String cslxms) {
		this.cslxms = cslxms;
	}

	public java.lang.String getSwjgzzjgdm() {
		return swjgzzjgdm;
	}

	public void setSwjgzzjgdm(java.lang.String swjgzzjgdm) {
		this.swjgzzjgdm = swjgzzjgdm;
	}
}
