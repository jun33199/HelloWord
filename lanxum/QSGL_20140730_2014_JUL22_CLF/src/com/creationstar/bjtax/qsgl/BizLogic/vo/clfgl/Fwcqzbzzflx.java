package com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl;

import java.io.Serializable;

/**
 * 房屋产权证标注住房类型代码表
 */
public class Fwcqzbzzflx implements Serializable {
	
	/**
     * 房屋产权证标注住房类型代码
     */
    private java.lang.String fwcqzbzzflxdm;

    /**
     * 房屋产权证标注住房类型名称
     */
    private java.lang.String fwcqzbzzflxmc;

    /**
     * 录入人
     */
    public java.lang.String lrr;

    /**
     * 录入日期
     */
    public java.sql.Timestamp lrrq;

    /**
     * 注销标识
     */
    public java.lang.String zxbs;

    /**
     * 创建人
     */
    public java.lang.String cjr;

    /**
     * 创建日期
     */
    public java.sql.Timestamp cjrq;

	public java.lang.String getFwcqzbzzflxdm() {
		return fwcqzbzzflxdm;
	}

	public void setFwcqzbzzflxdm(java.lang.String fwcqzbzzflxdm) {
		this.fwcqzbzzflxdm = fwcqzbzzflxdm;
	}

	public java.lang.String getFwcqzbzzflxmc() {
		return fwcqzbzzflxmc;
	}

	public void setFwcqzbzzflxmc(java.lang.String fwcqzbzzflxmc) {
		this.fwcqzbzzflxmc = fwcqzbzzflxmc;
	}

	public java.lang.String getLrr() {
		return lrr;
	}

	public void setLrr(java.lang.String lrr) {
		this.lrr = lrr;
	}

	public java.sql.Timestamp getLrrq() {
		return lrrq;
	}

	public void setLrrq(java.sql.Timestamp lrrq) {
		this.lrrq = lrrq;
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

	public java.sql.Timestamp getCjrq() {
		return cjrq;
	}

	public void setCjrq(java.sql.Timestamp cjrq) {
		this.cjrq = cjrq;
	}

}
