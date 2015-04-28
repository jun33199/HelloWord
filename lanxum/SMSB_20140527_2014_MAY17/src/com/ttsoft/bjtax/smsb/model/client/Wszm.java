package com.ttsoft.bjtax.smsb.model.client;

import java.io.Serializable;

public class Wszm implements Serializable 
{
	/**
     * 凭证来源代码
     */
    private java.lang.String pzlydm;
    
    /**
     * 票证种类代码
     */
    private java.lang.String pzzldm;
    
    /**
     * 完税证明完税证号
     */
    private java.lang.String wszh;
    
    /**
     * 年度字别
     */
    private java.lang.String ndzb;
    
    /**
     * 纳税人识别号
     */
    private java.lang.String nsrsbh;
    
    /**
     * 纳税人名称
     */
    private java.lang.String nsrmc;
    
    /**
     * 税务机关组织机构代码
     */
    private java.lang.String swjgzzjgdm;
    
    /**
     * 开具税务机关组织机构代码
     */
    private java.lang.String kjswjgzzjgdm;
    
    /**
     * 开具税务机关组织机构名称
     */
    private java.lang.String kjswjgzzjgmc;
    
    /**
     * 填发日期
     */
    private java.lang.String tfrq;
    
    /**
     * 合计金额
     */
    private java.math.BigDecimal hjje;
    
    /**
     * 合计金额（大写）
     */
    private java.math.BigDecimal hjjedx;
    
    /**
     * 创建人
     */
    private java.lang.String cjr;
    
    /**
     * 创建日期
     */
    private java.sql.Timestamp cjrq;
    
    /**
     * 录入人
     */
    private java.lang.String lrr;
    
    /**
     * 录入日期
     */
    private java.sql.Timestamp lrrq;
    
    /**
     * 区县代码
     */
    private java.lang.String qxdm;
    
    /**
     * 所属税务机关组织机构代码
     */
    private java.lang.String ssswjgzzjgdm;
    
    /**
     * 所属税务机关组织机构名称
     */
    private java.lang.String ssswjgzzjgmc;
    
    /**
     * 原凭证号税票号码
     */
    private java.lang.String ysphm;
    
    /**
     * 原凭证号
     */
    private java.lang.String ypzh;
    
    /**
     * 原票证种类代码
     */
    private java.lang.String ypzzldm;
    
    /**
     * 原完税证明完税证号
     */
    private java.lang.String ywszh;
    
    /**
     * 原年度字别
     */
    private java.lang.String yndzb;
    
    /**
     * 业务号码(投保确认码||车辆编号 交易流水号)
     */
    private java.lang.String ywhm;
    
    /**
     * 打印标志
     */
    private java.lang.String dybz;
    
    /**
     * 打印次数
     */
    private java.lang.String dycs;
    
    /**
     * 随机码
     */
    private java.lang.String sjm;
    
    /**
     * 有效标志
     */
    private java.lang.String yxbz;
    
    /**
     * 开具来源代码(0税收完税证明管理 1申报换开 2保单换开)
     */
    private java.lang.String kjlydm;
    
    /**
     * 备注
     */
    private java.lang.String bz;
    
    /**
     * 税种代码
     */
    private java.lang.String szdm;
    
    /**
     * 税目代码
     */
    private java.lang.String szsmdm;
    
    /**
     * 预算科目代码
     */
    private java.lang.String yskmdm;
    
    /**
     * 税款所属开始日期
     */
    private java.lang.String skssksrq;
    
    /**
     * 税款所属结束日期
     */
    private java.lang.String skssjsrq;
    
    /**
     * 申报日期
     */
    private java.sql.Timestamp sbrq;
    
    
    /**
     * 原凭证入库日期
     */
    private java.lang.String ypzrkrq;
    
    /**
     * 原缴款凭证号
     */
    private java.lang.String yjkpzh;
    
    
    /**
     * 银行退票标志
     */
    private java.lang.String yhtpbz;
    
    
    
	public java.lang.String getYhtpbz() {
		return yhtpbz;
	}

	public void setYhtpbz(java.lang.String yhtpbz) {
		this.yhtpbz = yhtpbz;
	}

	public String getPzlydm() {
		return pzlydm;
	}

	public void setPzlydm(String pzlydm) {
		this.pzlydm = pzlydm;
	}

	public String getPzzldm() {
		return pzzldm;
	}

	public void setPzzldm(String pzzldm) {
		this.pzzldm = pzzldm;
	}

	public String getWszh() {
		return wszh;
	}

	public void setWszh(String wszh) {
		this.wszh = wszh;
	}

	public String getNdzb() {
		return ndzb;
	}

	public void setNdzb(String ndzb) {
		this.ndzb = ndzb;
	}

	public String getNsrsbh() {
		return nsrsbh;
	}

	public void setNsrsbh(String nsrsbh) {
		this.nsrsbh = nsrsbh;
	}

	public String getNsrmc() {
		return nsrmc;
	}

	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}

	public String getSwjgzzjgdm() {
		return swjgzzjgdm;
	}

	public void setSwjgzzjgdm(String swjgzzjgdm) {
		this.swjgzzjgdm = swjgzzjgdm;
	}

	public String getKjswjgzzjgdm() {
		return kjswjgzzjgdm;
	}

	public void setKjswjgzzjgdm(String kjswjgzzjgdm) {
		this.kjswjgzzjgdm = kjswjgzzjgdm;
	}

	public java.lang.String getTfrq() {
		return tfrq;
	}

	public void setTfrq(java.lang.String tfrq) {
		this.tfrq = tfrq;
	}

	public java.math.BigDecimal getHjje() {
		return hjje;
	}

	public void setHjje(java.math.BigDecimal hjje) {
		this.hjje = hjje;
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

	public String getCjr() {
		return cjr;
	}

	public void setCjr(String cjr) {
		this.cjr = cjr;
	}

	public String getLrr() {
		return lrr;
	}

	public void setLrr(String lrr) {
		this.lrr = lrr;
	}

	public String getQxdm() {
		return qxdm;
	}

	public void setQxdm(String qxdm) {
		this.qxdm = qxdm;
	}

	public String getYpzh() {
		return ypzh;
	}

	public void setYpzh(String ypzh) {
		this.ypzh = ypzh;
	}

	public String getYpzzldm() {
		return ypzzldm;
	}

	public void setYpzzldm(String ypzzldm) {
		this.ypzzldm = ypzzldm;
	}

	public String getYwszh() {
		return ywszh;
	}

	public void setYwszh(String ywszh) {
		this.ywszh = ywszh;
	}

	public String getYndzb() {
		return yndzb;
	}

	public void setYndzb(String yndzb) {
		this.yndzb = yndzb;
	}

	public String getYwhm() {
		return ywhm;
	}

	public void setYwhm(String ywhm) {
		this.ywhm = ywhm;
	}

	public String getDybz() {
		return dybz;
	}

	public void setDybz(String dybz) {
		this.dybz = dybz;
	}

	public String getDycs() {
		return dycs;
	}

	public void setDycs(String dycs) {
		this.dycs = dycs;
	}

	public String getYxbz() {
		return yxbz;
	}

	public void setYxbz(String yxbz) {
		this.yxbz = yxbz;
	}

	public String getKjlydm() {
		return kjlydm;
	}

	public void setKjlydm(String kjlydm) {
		this.kjlydm = kjlydm;
	}

	public java.lang.String getBz() {
		return bz;
	}

	public void setBz(java.lang.String bz) {
		this.bz = bz;
	}

	public java.lang.String getYjkpzh() {
		return yjkpzh;
	}

	public void setYjkpzh(java.lang.String yjkpzh) {
		this.yjkpzh = yjkpzh;
	}

	public java.lang.String getSzdm() {
		return szdm;
	}

	public void setSzdm(java.lang.String szdm) {
		this.szdm = szdm;
	}

	public java.lang.String getSzsmdm() {
		return szsmdm;
	}

	public void setSzsmdm(java.lang.String szsmdm) {
		this.szsmdm = szsmdm;
	}

	public java.lang.String getYskmdm() {
		return yskmdm;
	}

	public void setYskmdm(java.lang.String yskmdm) {
		this.yskmdm = yskmdm;
	}

	public java.lang.String getSkssksrq() {
		return skssksrq;
	}

	public void setSkssksrq(java.lang.String skssksrq) {
		this.skssksrq = skssksrq;
	}

	public java.lang.String getSkssjsrq() {
		return skssjsrq;
	}

	public void setSkssjsrq(java.lang.String skssjsrq) {
		this.skssjsrq = skssjsrq;
	}

	public java.sql.Timestamp getSbrq() {
		return sbrq;
	}

	public void setSbrq(java.sql.Timestamp sbrq) {
		this.sbrq = sbrq;
	}

	public java.lang.String getYpzrkrq() {
		return ypzrkrq;
	}

	public void setYpzrkrq(java.lang.String ypzrkrq) {
		this.ypzrkrq = ypzrkrq;
	}

	public java.lang.String getKjswjgzzjgmc() {
		return kjswjgzzjgmc;
	}

	public void setKjswjgzzjgmc(java.lang.String kjswjgzzjgmc) {
		this.kjswjgzzjgmc = kjswjgzzjgmc;
	}

	public java.math.BigDecimal getHjjedx() {
		return hjjedx;
	}

	public void setHjjedx(java.math.BigDecimal hjjedx) {
		this.hjjedx = hjjedx;
	}

	public java.lang.String getSsswjgzzjgdm() {
		return ssswjgzzjgdm;
	}

	public void setSsswjgzzjgdm(java.lang.String ssswjgzzjgdm) {
		this.ssswjgzzjgdm = ssswjgzzjgdm;
	}

	public java.lang.String getSsswjgzzjgmc() {
		return ssswjgzzjgmc;
	}

	public void setSsswjgzzjgmc(java.lang.String ssswjgzzjgmc) {
		this.ssswjgzzjgmc = ssswjgzzjgmc;
	}

	public java.lang.String getSjm() {
		return sjm;
	}

	public void setSjm(java.lang.String sjm) {
		this.sjm = sjm;
	}

	public java.lang.String getYsphm() {
		return ysphm;
	}

	public void setYsphm(java.lang.String ysphm) {
		this.ysphm = ysphm;
	}
    
}
