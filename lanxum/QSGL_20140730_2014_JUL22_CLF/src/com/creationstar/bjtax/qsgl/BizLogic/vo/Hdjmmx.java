package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 核定减免明细表值对象
 */
public class Hdjmmx implements Serializable {

    /**
     * 政策编号
     */
    public java.lang.String zcbh;

    /**
     * 核定通知书号
     */
    public java.lang.String hdtzsh;

    /**
     * 减免金额
     */
    public java.math.BigDecimal jmje;

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
     * 年度
     */
    public java.lang.String nd;

    /**
     * 获得政策编号
     */
    public String getZcbh() {
        return zcbh;
    }

    /**
     * 获得核定通知书号
     */
    public String getHdtzsh() {
        return hdtzsh;
    }

    /**
     * 获得减免金额
     */
    public BigDecimal getJmje() {
        return jmje;
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
     * 获得备注
     */
    public String getBz() {
        return bz;
    }

    /**
     * 获得年度
     */
    public String getNd() {
        return nd;
    }

    /**
     * 赋值政策编号
     * @param zcbh 政策编号
     */
    public void setZcbh(String zcbh) {
        this.zcbh = zcbh;
    }

    /**
     * 赋值核定通知书号
     * @param hdtzsh 核定通知书号
     */
    public void setHdtzsh(String hdtzsh) {
        this.hdtzsh = hdtzsh;
    }

    /**
     * 赋值减免金额
     * @param jmje 减免金额
     */
    public void setJmje(BigDecimal jmje) {
        this.jmje = jmje;
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
     * 赋值备注
     * @param bz 备注
     */
    public void setBz(String bz) {
        this.bz = bz;
    }

    /**
     * 赋值年度
     * @param nd 年度
     */
    public void setNd(String nd) {
        this.nd = nd;
    }


}
