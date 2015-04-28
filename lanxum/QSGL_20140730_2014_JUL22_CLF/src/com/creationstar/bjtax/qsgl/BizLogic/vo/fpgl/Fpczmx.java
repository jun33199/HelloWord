package com.creationstar.bjtax.qsgl.BizLogic.vo.fpgl;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 发票开票明细表
 * @author tutu
 * 2013-05-07(1)
 *
 */
public class Fpczmx implements Serializable {
	
	/**
     * 发票种类代码
     */
    public java.lang.String fpzldm;
    
    /**
     * 发票号码
     */
    public java.lang.String fphm;
    
    /**
     * 发票库房代码
     */
    public java.lang.String fpkfdm;
    
    /**
     * 开票日期
     */
    public java.sql.Timestamp kprq;
    
    /**
     * 品目（项目）
     */
    public java.lang.String pm;
    
    /**
     * 单价
     */
    public java.math.BigDecimal dj;
    
    /**
     * 数量
     */
    public java.math.BigDecimal sl;
    
    /**
     * 金额
     */
    public java.math.BigDecimal je;
    
    /**
     * 创建人
     */
    public java.lang.String cjr;
    
    /**
     * 创建时间
     */
    public java.sql.Timestamp cjrq;
    
    /**
     * 录入人
     */
    public java.lang.String lrr;

    /**
     * 录入时间
     */
    public java.sql.Timestamp lrrq;
    
    /**
     * 税务机关组织机构代码
     */
    public java.lang.String swjgzzjgdm;
    
    /**
     * 备注
     */
    public java.lang.String bz;

	public java.lang.String getFpzldm() {
		return fpzldm;
	}

	public void setFpzldm(java.lang.String fpzldm) {
		this.fpzldm = fpzldm;
	}

	public java.lang.String getFphm() {
		return fphm;
	}

	public void setFphm(java.lang.String fphm) {
		this.fphm = fphm;
	}

	public java.lang.String getFpkfdm() {
		return fpkfdm;
	}

	public void setFpkfdm(java.lang.String fpkfdm) {
		this.fpkfdm = fpkfdm;
	}

	public java.sql.Timestamp getKprq() {
		return kprq;
	}

	public void setKprq(java.sql.Timestamp kprq) {
		this.kprq = kprq;
	}

	public java.lang.String getPm() {
		return pm;
	}

	public void setPm(java.lang.String pm) {
		this.pm = pm;
	}

	public java.math.BigDecimal getDj() {
		return dj;
	}

	public void setDj(java.math.BigDecimal dj) {
		this.dj = dj;
	}

	public java.math.BigDecimal getSl() {
		return sl;
	}

	public void setSl(java.math.BigDecimal sl) {
		this.sl = sl;
	}

	public java.math.BigDecimal getJe() {
		return je;
	}

	public void setJe(java.math.BigDecimal je) {
		this.je = je;
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

	public java.sql.Timestamp getLrrq() {
		return lrrq;
	}

	public void setLrrq(java.sql.Timestamp lrrq) {
		this.lrrq = lrrq;
	}

	public java.lang.String getSwjgzzjgdm() {
		return swjgzzjgdm;
	}

	public void setSwjgzzjgdm(java.lang.String swjgzzjgdm) {
		this.swjgzzjgdm = swjgzzjgdm;
	}

	public java.lang.String getBz() {
		return bz;
	}

	public void setBz(java.lang.String bz) {
		this.bz = bz;
	}

}
