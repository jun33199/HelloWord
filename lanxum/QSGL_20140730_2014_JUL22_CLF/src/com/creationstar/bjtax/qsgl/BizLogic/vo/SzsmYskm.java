package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 税种税目与预算科目对照表值对象
 */
public class SzsmYskm implements Serializable {

    /**
     * 税种税目代码
     */
    public java.lang.String szsmdm;

    /**
     * 预算科目代码
     */
    public java.lang.String yskmdm;

    /**
     * 默认预算科目代码
     */
    public java.lang.String mryskmdm;

    /**
     * 录入人
     */
    public java.lang.String lrr;

    /**
     * 录入日期
     */
    public java.sql.Timestamp lrrq;

    /**
     * 获得税种税目代码
     */
    public String getSzsmdm() {
        return szsmdm;
    }

    /**
     * 获得预算科目代码
     */
    public String getYskmdm() {
        return yskmdm;
    }

    /**
     * 获得默认预算科目代码
     */
    public String getMryskmdm() {
        return mryskmdm;
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
     * 赋值税种税目代码
     * @param szsmdm 税种税目代码
     */
    public void setSzsmdm(String szsmdm) {
        this.szsmdm = szsmdm;
    }

    /**
     * 赋值预算科目代码
     * @param yskmdm 预算科目代码
     */
    public void setYskmdm(String yskmdm) {
        this.yskmdm = yskmdm;
    }

    /**
     * 赋值默认预算科目代码
     * @param mryskmdm 默认预算科目代码
     */
    public void setMryskmdm(String mryskmdm) {
        this.mryskmdm = mryskmdm;
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


}
