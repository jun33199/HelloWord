package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 预算级次代码表值对象
 */
public class Ysjc implements Serializable {

    /**
     * 预算级次代码
     */
    public java.lang.String ysjcdm;

    /**
     * 预算级次名称
     */
    public java.lang.String ysjcmc;

    /**
     * 录入人
     */
    public java.lang.String lrr;

    /**
     * 录入日期
     */
    public java.sql.Timestamp lrrq;

    /**
     * 注销标识
     */
    public java.lang.String zxbs;

    /**
     * 获得预算级次代码
     */
    public String getYsjcdm() {
        return ysjcdm;
    }

    /**
     * 获得预算级次名称
     */
    public String getYsjcmc() {
        return ysjcmc;
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
     * 获得注销标识
     */
    public String getZxbs() {
        return zxbs;
    }

    /**
     * 赋值预算级次代码
     * @param ysjcdm 预算级次代码
     */
    public void setYsjcdm(String ysjcdm) {
        this.ysjcdm = ysjcdm;
    }

    /**
     * 赋值预算级次名称
     * @param ysjcmc 预算级次名称
     */
    public void setYsjcmc(String ysjcmc) {
        this.ysjcmc = ysjcmc;
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
     * 赋值注销标识
     * @param zxbs 注销标识
     */
    public void setZxbs(String zxbs) {
        this.zxbs = zxbs;
    }


}
