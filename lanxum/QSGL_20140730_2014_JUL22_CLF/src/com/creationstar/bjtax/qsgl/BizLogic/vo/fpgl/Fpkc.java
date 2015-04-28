package com.creationstar.bjtax.qsgl.BizLogic.vo.fpgl;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 发票库存明细表
 * @author tutu
 * 2013-05-07(1)
 */
public class Fpkc implements Serializable {
	
	/**
     * 发票库房代码
     */
    public java.lang.String fpkfdm;
    
    /**
     * 发票种类代码
     */
    public java.lang.String fpzldm;
    
    /**
     * 结存时间
     */
    public java.sql.Timestamp jcsj;
    
    /**
     * 起始号码
     */
    public java.lang.String qshm;
    
    /**
     * 截止号码
     */
    public java.lang.String jzhm;
    
    /**
     * 数量
     */
    public java.math.BigDecimal sl;
    
    /**
     * 合计数量
     */
    public java.math.BigDecimal hjsl;
    
    /**
     * 税务机关组织机构代码
     */
    public java.lang.String swjgzzjgdm;
    
    /**
     * 录入人
     */
    public java.lang.String lrr;
    
    /**
     * 录入日期
     */
    public java.sql.Timestamp lrrq;

    /**
     * 创建人
     */
    public java.lang.String cjr;

    /**
     * 创建日期
     */
    public java.sql.Timestamp cjrq;
    
    /**
     * 备注
     */
    public java.lang.String bz;
    
    /**
     * 入库标示（标明号码是录入新增库存）
     */
    public java.lang.String rkbs;
    
    

	public java.lang.String getRkbs() {
		return rkbs;
	}

	public void setRkbs(java.lang.String rkbs) {
		this.rkbs = rkbs;
	}

	public java.lang.String getBz() {
		return bz;
	}

	public void setBz(java.lang.String bz) {
		this.bz = bz;
	}

	public java.lang.String getFpkfdm() {
		return fpkfdm;
	}

	public void setFpkfdm(java.lang.String fpkfdm) {
		this.fpkfdm = fpkfdm;
	}

	public java.lang.String getFpzldm() {
		return fpzldm;
	}

	public void setFpzldm(java.lang.String fpzldm) {
		this.fpzldm = fpzldm;
	}

	public java.sql.Timestamp getJcsj() {
		return jcsj;
	}

	public void setJcsj(java.sql.Timestamp jcsj) {
		this.jcsj = jcsj;
	}

	public java.lang.String getQshm() {
		return qshm;
	}

	public void setQshm(java.lang.String qshm) {
		this.qshm = qshm;
	}

	public java.lang.String getJzhm() {
		return jzhm;
	}

	public void setJzhm(java.lang.String jzhm) {
		this.jzhm = jzhm;
	}

	public java.lang.String getSwjgzzjgdm() {
		return swjgzzjgdm;
	}

	public java.math.BigDecimal getSl() {
		return sl;
	}

	public void setSl(java.math.BigDecimal sl) {
		this.sl = sl;
	}

	public void setSwjgzzjgdm(java.lang.String swjgzzjgdm) {
		this.swjgzzjgdm = swjgzzjgdm;
	}

	public java.sql.Timestamp getLrrq() {
		return lrrq;
	}

	public void setLrrq(java.sql.Timestamp lrrq) {
		this.lrrq = lrrq;
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

	public java.lang.String getLrr() {
		return lrr;
	}

	public void setLrr(java.lang.String lrr) {
		this.lrr = lrr;
	}

	public java.math.BigDecimal getHjsl() {
		return hjsl;
	}

	public void setHjsl(java.math.BigDecimal hjsl) {
		this.hjsl = hjsl;
	}

}
