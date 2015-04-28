package com.creationstar.bjtax.qsgl.BizLogic.vo.fpgl;

import java.io.Serializable;

/**
 * 发票种类代码表
 * @author tutu
 * 2013-05-07(1)
 */
public class Fpzl implements Serializable{

	/**
     * 发票种类代码
     */
    public java.lang.String fpzldm;
    
    /**
     * 发票种类名称
     */
    public java.lang.String fpzlmc;
    
    /**
     * 主销标示
     */
    public java.lang.String zxbs;
    
    /**
     * 发票号码长度
     */
    public java.lang.String fphmcd;
    
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

	public java.lang.String getFpzldm() {
		return fpzldm;
	}

	public void setFpzldm(java.lang.String fpzldm) {
		this.fpzldm = fpzldm;
	}

	public java.lang.String getFpzlmc() {
		return fpzlmc;
	}

	public void setFpzlmc(java.lang.String fpzlmc) {
		this.fpzlmc = fpzlmc;
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

	public java.lang.String getFphmcd() {
		return fphmcd;
	}

	public void setFphmcd(java.lang.String fphmcd) {
		this.fphmcd = fphmcd;
	}
	
}
