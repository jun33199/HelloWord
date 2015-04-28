package com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl;

import java.io.Serializable;

/**
 * 合同与凭证对照关系表
 * @author tutu
 * 2013-05-15(1)
 */
public class Htypzdzgxb implements Serializable {
	
	/**
     * 序号
     */
    public java.lang.String xh;
    
    /**
     * 合同编号
     */
    public java.lang.String htbh;
    
    /**
     * 买卖方标志（1：标示卖方，0表示卖方）
     */
    public java.lang.String mmfbz;
    
    /**
     * 凭证分类代码（01：完税证、02：缴款书：11：发票）
     */
    public java.lang.String pzfldm;
    
    /**
     * 凭证种类代码
     */
    public java.lang.String pizzldm;
    
    /**
     * 凭证号码
     */
    public java.lang.String pzhm;
    
    /**
     * 凭证年度字别
     */
    public java.lang.String pzndzb;
    
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
     * 申报编号
     */
    public java.lang.String sbbh;
    
    /**
     * 税务机关组织机构代码
     */
    public java.lang.String swjgzzjgdm;
    

	public java.lang.String getSwjgzzjgdm() {
		return swjgzzjgdm;
	}

	public void setSwjgzzjgdm(java.lang.String swjgzzjgdm) {
		this.swjgzzjgdm = swjgzzjgdm;
	}

	public java.lang.String getSbbh() {
		return sbbh;
	}

	public void setSbbh(java.lang.String sbbh) {
		this.sbbh = sbbh;
	}

	public java.lang.String getXh() {
		return xh;
	}

	public void setXh(java.lang.String xh) {
		this.xh = xh;
	}

	public java.lang.String getHtbh() {
		return htbh;
	}

	public void setHtbh(java.lang.String htbh) {
		this.htbh = htbh;
	}

	public java.lang.String getMmfbz() {
		return mmfbz;
	}

	public void setMmfbz(java.lang.String mmfbz) {
		this.mmfbz = mmfbz;
	}

	public java.lang.String getPzfldm() {
		return pzfldm;
	}

	public void setPzfldm(java.lang.String pzfldm) {
		this.pzfldm = pzfldm;
	}

	public java.lang.String getPizzldm() {
		return pizzldm;
	}

	public void setPizzldm(java.lang.String pizzldm) {
		this.pizzldm = pizzldm;
	}

	public java.lang.String getPzhm() {
		return pzhm;
	}

	public void setPzhm(java.lang.String pzhm) {
		this.pzhm = pzhm;
	}

	public java.lang.String getPzndzb() {
		return pzndzb;
	}

	public void setPzndzb(java.lang.String pzndzb) {
		this.pzndzb = pzndzb;
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
    
    

}
