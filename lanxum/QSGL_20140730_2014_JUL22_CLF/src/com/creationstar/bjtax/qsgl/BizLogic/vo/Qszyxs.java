package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 土地房屋权属转移形式代码表值对象
 */
public class Qszyxs implements Serializable {

    /**
     * 土地房屋权属转移形式代码
     */
    public java.lang.String qszyxsdm;

    /**
     * 土地房屋权属转移形式名称
     */
    public java.lang.String qszyxsmc;

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
     * 获得土地房屋权属转移形式代码
     */
    public String getQszyxsdm() {
        return qszyxsdm;
    }

    /**
     * 获得土地房屋权属转移形式名称
     */
    public String getQszyxsmc() {
        return qszyxsmc;
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
     * 赋值土地房屋权属转移形式代码
     * @param qszyxsdm 土地房屋权属转移形式代码
     */
    public void setQszyxsdm(String qszyxsdm) {
        this.qszyxsdm = qszyxsdm;
    }

    /**
     * 赋值土地房屋权属转移形式名称
     * @param qszyxsmc 土地房屋权属转移形式名称
     */
    public void setQszyxsmc(String qszyxsmc) {
        this.qszyxsmc = qszyxsmc;
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
