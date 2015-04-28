package com.creationstar.bjtax.qsgl.BizLogic.vo.fpgl;

import java.io.Serializable;

/**
 * 发票库房代码表
 * @author tutu
 * 2013-05-07(1)
 */
public class Fpkf implements Serializable{

	/**
     * 发票库房代码
     */
    public java.lang.String fpkfdm;
    
    /**
     * 发票库房名称
     */
    public java.lang.String fpkfmc;
    
    /**
     * 机器编号
     */
    public java.lang.String jqbh;
    
    /**
     * 计算机代码
     */
    public java.lang.String jsjdm;
    
    /**
     * 主销标示
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

	public java.lang.String getFpkfdm() {
		return fpkfdm;
	}

	public void setFpkfdm(java.lang.String fpkfdm) {
		this.fpkfdm = fpkfdm;
	}

	public java.lang.String getFpkfmc() {
		return fpkfmc;
	}

	public void setFpkfmc(java.lang.String fpkfmc) {
		this.fpkfmc = fpkfmc;
	}

	public java.lang.String getJqbh() {
		return jqbh;
	}

	public void setJqbh(java.lang.String jqbh) {
		this.jqbh = jqbh;
	}

	public java.lang.String getJsjdm() {
		return jsjdm;
	}

	public void setJsjdm(java.lang.String jsjdm) {
		this.jsjdm = jsjdm;
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
}
