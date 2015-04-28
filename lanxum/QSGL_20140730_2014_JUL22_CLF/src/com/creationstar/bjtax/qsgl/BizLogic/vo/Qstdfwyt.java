package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *  契税土地房屋用途值对象
 */
public class Qstdfwyt implements Serializable {

    /**
     * 契税土地房屋用途代码
     */
    public java.lang.String qstdfwytdm;

    /**
     * 契税土地房屋用途名称
     */
    public java.lang.String qstdfwytmc;

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
     * 获得契税土地房屋用途代码
     */
    public String getQstdfwytdm() {
        return qstdfwytdm;
    }

    /**
     * 获得契税土地房屋用途名称
     */
    public String getQstdfwytmc() {
        return qstdfwytmc;
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
     * 赋值契税土地房屋用途代码
     * @param qstdfwytdm 契税土地房屋用途代码
     */
    public void setQstdfwytdm(String qstdfwytdm) {
        this.qstdfwytdm = qstdfwytdm;
    }

    /**
     * 赋值契税土地房屋用途名称
     * @param qstdfwytmc 契税土地房屋用途名称
     */
    public void setQstdfwytmc(String qstdfwytmc) {
        this.qstdfwytmc = qstdfwytmc;
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
