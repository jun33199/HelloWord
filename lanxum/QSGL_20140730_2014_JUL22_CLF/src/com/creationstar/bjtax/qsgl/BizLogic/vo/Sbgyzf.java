package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 申报主表与已购公有住房上市情况表的关联表值对象
 */
public class Sbgyzf implements Serializable {

    /**
     * 申报表号
     */
    public java.lang.String sbbh;

    /**
     * 已购公有住房权属证书号
     */
    public java.lang.String yggyzfqszsh;

    /**
     * 录入人
     */
    public java.lang.String lrr;

    /**
     * 创建人
     */
    public java.lang.String cjr;

    /**
     * 创建日期
     */
    public java.sql.Timestamp cjrq;

    /**
     * 录入日期
     */
    public java.sql.Timestamp lrrq;

    /**
     * 本次抵扣额
     */
    public java.math.BigDecimal bcdke;

    /**
     * 获得申报表号
     */
    public String getSbbh() {
        return sbbh;
    }

    /**
     * 获得已购公有住房权属证书号
     */
    public String getYggyzfqszsh() {
        return yggyzfqszsh;
    }

    /**
     * 获得录入人
     */
    public String getLrr() {
        return lrr;
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
     * 获得录入日期
     */
    public Timestamp getLrrq() {
        return lrrq;
    }

    public BigDecimal getBcdke() {
        return bcdke;
    }

    /**
     * 赋值申报表号
     * @param sbbh 申报表号
     */
    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    /**
     * 赋值已购公有住房权属证书号
     * @param yggyzfqszsh 已购公有住房权属证书号
     */
    public void setYggyzfqszsh(String yggyzfqszsh) {
        this.yggyzfqszsh = yggyzfqszsh;
    }

    /**
     * 赋值录入人
     * @param lrr 录入人
     */
    public void setLrr(String lrr) {
        this.lrr = lrr;
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
     * 赋值录入日期
     * @param lrrq 录入日期
     */
    public void setLrrq(Timestamp lrrq) {
        this.lrrq = lrrq;
    }

    public void setBcdke(BigDecimal bcdke) {
        this.bcdke = bcdke;
    }


}
