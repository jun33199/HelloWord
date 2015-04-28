package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 即时办理基础信息-公有住房值对象
 */
public class Jsblgyzf implements Serializable {
    /**
     * 相关申报主表表号
     */
    public String sbbh;

    /**
     * 已购公有住房权属证书号
     */
    public java.lang.String yggyzfqszsh;

    /**
     * 座落地址
     */
    public java.lang.String zldz;

    /**
     * 出售合同（契约）签订时间
     */
    public java.sql.Timestamp qdsj;

    /**
     * 建筑面积
     */
    public java.math.BigDecimal jzmj;

    /**
     * 成交价格
     */
    public java.math.BigDecimal cjjg;

    /**
     * 本次抵扣额
     */
    public java.math.BigDecimal bcdke;

    /**
     * 剩余额
     */
    public java.math.BigDecimal sye;

    /**
     * 状态标识
     */
    public java.lang.String ztbs;

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
     * 年度
     */
    public java.lang.String nd;

    /**
     * 备注
     */
    public java.lang.String bz;

    /**
     * 文书分局简称
     */
    public java.lang.String wsjc;

    /**
     * 征收点简称
     */
    public java.lang.String zsdjc;

    /**
     * 序号
     */
    public java.lang.String xh;

    /**
     * 剩余额用完标志
     */
    public String syeywbz;
    
    /**
     * 房屋权属证书号 add by zhangyj 20090219
     */
    public java.lang.String fwqszsh;    

    /**
     * 获得已购公有住房权属证书号
     */
    public String getYggyzfqszsh() {
        return yggyzfqszsh;
    }

    /**
     * 获得座落地址
     */
    public String getZldz() {
        return zldz;
    }

    /**
     * 获得出售合同（契约）签订时间
     */
    public Timestamp getQdsj() {
        return qdsj;
    }

    /**
     * 获得建筑面积
     */
    public BigDecimal getJzmj() {
        return jzmj;
    }

    /**
     * 获得成交价格
     */
    public BigDecimal getCjjg() {
        return cjjg;
    }

    /**
     * 获得本次抵扣额
     */
    public BigDecimal getBcdke() {
        return bcdke;
    }

    /**
     * 获得剩余额
     */
    public BigDecimal getSye() {
        return sye;
    }

    /**
     * 获得状态标识
     */
    public String getZtbs() {
        return ztbs;
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
     * 获得年度
     */
    public String getNd() {
        return nd;
    }

    /**
     * 获得备注
     */
    public String getBz() {
        return bz;
    }

    public String getSbbh() {
        return sbbh;
    }

    public String getZsdjc() {
        return zsdjc;
    }

    public String getXh() {
        return xh;
    }

    public String getWsjc() {
        return wsjc;
    }

    public String getSyeywbz() {
        return syeywbz;
    }

    /**
     * 获得房屋权属证书号
     */
    public String getFwqszsh()
    {
        return fwqszsh;
    }    
    
    /**
     * 赋值已购公有住房权属证书号
     * @param yggyzfqszsh 已购公有住房权属证书号
     */
    public void setYggyzfqszsh(String yggyzfqszsh) {
        this.yggyzfqszsh = yggyzfqszsh;
    }

    /**
     * 赋值座落地址
     * @param zldz 座落地址
     */
    public void setZldz(String zldz) {
        this.zldz = zldz;
    }

    /**
     * 赋值出售合同（契约）签订时间
     * @param qdsj 出售合同（契约）签订时间
     */
    public void setQdsj(Timestamp qdsj) {
        this.qdsj = qdsj;
    }

    /**
     * 赋值建筑面积
     * @param jzmj 建筑面积
     */
    public void setJzmj(BigDecimal jzmj) {
        this.jzmj = jzmj;
    }

    /**
     * 赋值成交价格
     * @param cjjg 成交价格
     */
    public void setCjjg(BigDecimal cjjg) {
        this.cjjg = cjjg;
    }

    /**
     * 赋值本次抵扣额
     * @param bcdke 本次抵扣额
     */
    public void setBcdke(BigDecimal bcdke) {
        this.bcdke = bcdke;
    }

    /**
     * 赋值剩余额
     * @param sye 剩余额
     */
    public void setSye(BigDecimal sye) {
        this.sye = sye;
    }

    /**
     * 赋值状态标识
     * @param ztbs 状态标识
     */
    public void setZtbs(String ztbs) {
        this.ztbs = ztbs;
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
     * 赋值年度
     * @param nd 年度
     */
    public void setNd(String nd) {
        this.nd = nd;
    }

    /**
     * 赋值备注
     * @param bz 备注
     */
    public void setBz(String bz) {
        this.bz = bz;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public void setZsdjc(String zsdjc) {
        this.zsdjc = zsdjc;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public void setWsjc(String wsjc) {
        this.wsjc = wsjc;
    }

    public void setSyeywbz(String syeywbz) {
        this.syeywbz = syeywbz;
    }

    /**
     * 赋值房屋权属证书号
     * @param fwqszsh 房屋权属证书号
     */
    public void setFwqszsh(String fwqszsh)
    {
        this.fwqszsh = fwqszsh;
    }

}
