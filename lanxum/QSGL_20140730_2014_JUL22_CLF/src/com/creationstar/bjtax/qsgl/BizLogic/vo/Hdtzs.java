package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 契税核定通知书值对象
 */
public class Hdtzs implements Serializable {

    /**
     * 核定通知书号
     */
    public java.lang.String hdtzsh;

    /**
     * 申报表号
     */
    public java.lang.String sbbh;

    /**
     * 申请人
     */
    public java.lang.String sqr;

    /**
     * 商品房项目名称
     */
    public java.lang.String spfxmmc;

    /**
     * 坐落地址
     */
    public java.lang.String zldi;

    /**
     * 成交价格
     */
    public java.math.BigDecimal cjjg;

    /**
     * 计税依据
     */
    public java.math.BigDecimal jsyj;

    /**
     * 计征契税
     */
    public java.math.BigDecimal jzqs;

    /**
     * 扣除出售已购公有住房收入项
     */
    public java.math.BigDecimal kcqyzfx;

    /**
     * 实际应征契税
     */
    public java.math.BigDecimal sjyz;

    /**
     * 打印时间
     */
    public java.sql.Timestamp dysj;

    /**
     * 经办人
     */
    public java.lang.String jbr;

    /**
     * 联系电话
     */
    public java.lang.String lxdh;

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
     * 不征标识
     */
    public java.lang.String bzbs;

    /**
     * 年度字别
     */
    public String ndzb;

    /**
     * 文书简称
     */
    public String wsjc;

    /**
     * 流水号
     */
    public BigDecimal lsh;

    /**
     * 防伪号码
     */
    public String fwhm;

    /**
     * 获得核定通知书号
     */
    public String getHdtzsh() {
        return hdtzsh;
    }

    /**
     * 获得申报表号
     */
    public String getSbbh() {
        return sbbh;
    }

    /**
     * 获得申请人
     */
    public String getSqr() {
        return sqr;
    }

    /**
     * 获得商品房项目名称
     */
    public String getSpfxmmc() {
        return spfxmmc;
    }

    /**
     * 获得坐落地址
     */
    public String getZldi() {
        return zldi;
    }

    /**
     * 获得成交价格
     */
    public BigDecimal getCjjg() {
        return cjjg;
    }

    /**
     * 获得计税依据
     */
    public BigDecimal getJsyj() {
        return jsyj;
    }

    /**
     * 获得计征契税
     */
    public BigDecimal getJzqs() {
        return jzqs;
    }

    /**
     * 获得扣除出售已购公有住房收入项
     */
    public BigDecimal getKcqyzfx() {
        return kcqyzfx;
    }

    /**
     * 获得实际应征契税
     */
    public BigDecimal getSjyz() {
        return sjyz;
    }

    /**
     * 获得打印时间
     */
    public Timestamp getDysj() {
        return dysj;
    }

    /**
     * 获得经办人
     */
    public String getJbr() {
        return jbr;
    }

    /**
     * 获得联系电话
     */
    public String getLxdh() {
        return lxdh;
    }

    /**
     * 获得录入人
     */
    public String getLrr() {
        return lrr;
    }

    /**
     * 获得录入日期
     */
    public Timestamp getLrrq() {
        return lrrq;
    }

    /**
     * 获得创建人
     */
    public String getCjr() {
        return cjr;
    }

    /**
     * 获得创建日期
     */
    public Timestamp getCjrq() {
        return cjrq;
    }

    /**
     * 获得不征标识
     */
    public String getBzbs() {
        return bzbs;
    }

    public BigDecimal getLsh() {
        return lsh;
    }

    public String getNdzb() {
        return ndzb;
    }

    public String getWsjc() {
        return wsjc;
    }

    /**
     * 赋值核定通知书号
     * @param hdtzsh 核定通知书号
     */
    public void setHdtzsh(String hdtzsh) {
        this.hdtzsh = hdtzsh;
    }

    /**
     * 赋值申报表号
     * @param sbbh 申报表号
     */
    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    /**
     * 赋值申请人
     * @param sqr 申请人
     */
    public void setSqr(String sqr) {
        this.sqr = sqr;
    }

    /**
     * 赋值商品房项目名称
     * @param spfxmmc 商品房项目名称
     */
    public void setSpfxmmc(String spfxmmc) {
        this.spfxmmc = spfxmmc;
    }

    /**
     * 赋值坐落地址
     * @param zldi 坐落地址
     */
    public void setZldi(String zldi) {
        this.zldi = zldi;
    }

    /**
     * 赋值成交价格
     * @param cjjg 成交价格
     */
    public void setCjjg(BigDecimal cjjg) {
        this.cjjg = cjjg;
    }

    /**
     * 赋值计税依据
     * @param jsyj 计税依据
     */
    public void setJsyj(BigDecimal jsyj) {
        this.jsyj = jsyj;
    }

    /**
     * 赋值计征契税
     * @param jzqs 计征契税
     */
    public void setJzqs(BigDecimal jzqs) {
        this.jzqs = jzqs;
    }

    /**
     * 赋值扣除出售已购公有住房收入项
     * @param kcqyzfx 扣除出售已购公有住房收入项
     */
    public void setKcqyzfx(BigDecimal kcqyzfx) {
        this.kcqyzfx = kcqyzfx;
    }

    /**
     * 赋值实际应征契税
     * @param sjyz 实际应征契税
     */
    public void setSjyz(BigDecimal sjyz) {
        this.sjyz = sjyz;
    }

    /**
     * 赋值打印时间
     * @param dysj 打印时间
     */
    public void setDysj(Timestamp dysj) {
        this.dysj = dysj;
    }

    /**
     * 赋值经办人
     * @param jbr 经办人
     */
    public void setJbr(String jbr) {
        this.jbr = jbr;
    }

    /**
     * 赋值联系电话
     * @param lxdh 联系电话
     */
    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    /**
     * 赋值录入人
     * @param lrr 录入人
     */
    public void setLrr(String lrr) {
        this.lrr = lrr;
    }

    /**
     * 赋值录入日期
     * @param lrrq 录入日期
     */
    public void setLrrq(Timestamp lrrq) {
        this.lrrq = lrrq;
    }

    /**
     * 赋值创建人
     * @param cjr 创建人
     */
    public void setCjr(String cjr) {
        this.cjr = cjr;
    }

    /**
     * 赋值创建日期
     * @param cjrq 创建日期
     */
    public void setCjrq(Timestamp cjrq) {
        this.cjrq = cjrq;
    }

    /**
     * 赋值不征标识
     * @param bzbs 不征标识
     */
    public void setBzbs(String bzbs) {
        this.bzbs = bzbs;
    }

    public void setLsh(BigDecimal lsh) {
        this.lsh = lsh;
    }

    public void setNdzb(String ndzb) {
        this.ndzb = ndzb;
    }

    public void setWsjc(String wsjc) {
        this.wsjc = wsjc;
    }

    /**
     * 获取 减免税额 = 记税依据 - 实际应征。审核时用
     */
    public BigDecimal getJmse() {
        return this.jsyj.subtract(this.sjyz);
    }

    public String getFwhm() {
        return fwhm;
    }

    public void setFwhm(String fwhm) {
        this.fwhm = fwhm;
    }

}
