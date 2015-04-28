package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 纳税人类型代码值对象
 */
public class Qsdwxz implements Serializable {

    /**
     * 契税单位性质代码
     */
    public java.lang.String nsrlxdm;

    /**
     * 契税单位性质名称
     */
    public java.lang.String nsrlxmc;

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
     * 获得契税单位性质代码
     */
    public String getNsrlxdm() {
        return nsrlxdm;
    }

    /**
     * 获得契税单位性质名称
     */
    public String getNsrlxmc() {
        return nsrlxmc;
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
     * 赋值契税单位性质代码
     * @param nsrlxdm 契税单位性质代码
     */
    public void setNsrlxdm(String nsrlxdm) {
        this.nsrlxdm = nsrlxdm;
    }

    /**
     * 赋值契税单位性质名称
     * @param nsrlxmc 契税单位性质名称
     */
    public void setNsrlxmc(String nsrlxmc) {
        this.nsrlxmc = nsrlxmc;
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
