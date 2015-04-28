package com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl;

import java.io.Serializable;

/**
 * 卖方申报信息表主表VO
 * @author tutu
 * 2013-05-10(1)
 */
public class MfsbxxzbSeller implements Serializable {

	/**
     * 申报表号
     */
    public java.lang.String sbbh;
    
    /**
     * 合同编号
     */
    public java.lang.String htbh;
    
    /**
     * 纳税人名称
     */
    public java.lang.String nsrmc;
    
    /**
     * 证件类型代码
     */
    public java.lang.String zjlxdm;
    
    /**
     * 证件号码
     */
    public java.lang.String zjhm;
    
    /**
     * 计算机代码
     */
    public java.lang.String jsjdm;
    
    /**
     * 登记注册类型代码
     */
    public java.lang.String djzclxdm;
    
    /**
     * 国家标准行业代码
     */
    public java.lang.String gjbzhydm;
    
    /**
     * 隶属关系代码
     */
    public java.lang.String lsgxdm;
    
    /**
     * 税务机关组织机构代码
     */
    public java.lang.String swjgzzjgdm;
    
    
    /**
     * 税款限缴日期
     */
    public java.sql.Timestamp skxjrq;
    
    /**
     * 合计金额
     */
    public java.math.BigDecimal sjhjje;
    
    /**
     * 创建人
     */
    public java.lang.String cjr;
    
    /**
     * 创建日期
     */
    public java.sql.Timestamp cjrq;
    
    /**
     * 录入人
     */
    public java.lang.String lrr;
    
    /**
     * 录入日期
     */
    public java.sql.Timestamp lrrq;
    
    /**
     * 证件类型名称
     */
    public java.lang.String zjlxmc;
    
    /**
     * 运行标记
     */
    public java.lang.String run_bz;  
    
    /**
     * 契税申报表号
     */
    public java.lang.String qs_sbbh;  
    
    /**
     * 应纳金额合计
     */
    public java.math.BigDecimal ynhjje;
    
    /**
     * 减免金额合计
     */
    public java.math.BigDecimal jmhjje;
    
    /**
     * 记账标志
     */
    public java.lang.String jzbz;
    
    
	public java.math.BigDecimal getYnhjje() {
		return ynhjje;
	}

	public void setYnhjje(java.math.BigDecimal ynhjje) {
		this.ynhjje = ynhjje;
	}

	public java.math.BigDecimal getJmhjje() {
		return jmhjje;
	}

	public void setJmhjje(java.math.BigDecimal jmhjje) {
		this.jmhjje = jmhjje;
	}

	public java.lang.String getJzbz() {
		return jzbz;
	}

	public void setJzbz(java.lang.String jzbz) {
		this.jzbz = jzbz;
	}

	public java.lang.String getSbbh() {
		return sbbh;
	}

	public void setSbbh(java.lang.String sbbh) {
		this.sbbh = sbbh;
	}

	public java.lang.String getHtbh() {
		return htbh;
	}

	public void setHtbh(java.lang.String htbh) {
		this.htbh = htbh;
	}

	public java.lang.String getNsrmc() {
		return nsrmc;
	}

	public void setNsrmc(java.lang.String nsrmc) {
		this.nsrmc = nsrmc;
	}

	public java.lang.String getZjlxdm() {
		return zjlxdm;
	}

	public void setZjlxdm(java.lang.String zjlxdm) {
		this.zjlxdm = zjlxdm;
	}

	public java.lang.String getZjhm() {
		return zjhm;
	}

	public void setZjhm(java.lang.String zjhm) {
		this.zjhm = zjhm;
	}

	public java.lang.String getJsjdm() {
		return jsjdm;
	}

	public void setJsjdm(java.lang.String jsjdm) {
		this.jsjdm = jsjdm;
	}

	public java.lang.String getDjzclxdm() {
		return djzclxdm;
	}

	public void setDjzclxdm(java.lang.String djzclxdm) {
		this.djzclxdm = djzclxdm;
	}

	public java.lang.String getGjbzhydm() {
		return gjbzhydm;
	}

	public void setGjbzhydm(java.lang.String gjbzhydm) {
		this.gjbzhydm = gjbzhydm;
	}

	public java.lang.String getLsgxdm() {
		return lsgxdm;
	}

	public void setLsgxdm(java.lang.String lsgxdm) {
		this.lsgxdm = lsgxdm;
	}

	public java.lang.String getSwjgzzjgdm() {
		return swjgzzjgdm;
	}

	public void setSwjgzzjgdm(java.lang.String swjgzzjgdm) {
		this.swjgzzjgdm = swjgzzjgdm;
	}

	public java.sql.Timestamp getSkxjrq() {
		return skxjrq;
	}

	public void setSkxjrq(java.sql.Timestamp skxjrq) {
		this.skxjrq = skxjrq;
	}

	public java.math.BigDecimal getSjhjje() {
		return sjhjje;
	}

	public void setSjhjje(java.math.BigDecimal sjhjje) {
		this.sjhjje = sjhjje;
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

	public java.lang.String getZjlxmc() {
		return zjlxmc;
	}

	public void setZjlxmc(java.lang.String zjlxmc) {
		this.zjlxmc = zjlxmc;
	}

	public java.lang.String getRun_bz() {
		return run_bz;
	}

	public void setRun_bz(java.lang.String run_bz) {
		this.run_bz = run_bz;
	}

	public java.lang.String getQs_sbbh() {
		return qs_sbbh;
	}

	public void setQs_sbbh(java.lang.String qs_sbbh) {
		this.qs_sbbh = qs_sbbh;
	}
    
    
}
