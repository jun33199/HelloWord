package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 政策维护值对象
 */
public class Zcwh implements Serializable {

    /**
     * 指标代码
     */
    public java.lang.String zbdm;

    /**
     * 指标名称
     */
    public java.lang.String zbmc;

    /**
     * 指标值
     */
    public java.lang.String zbz;

    /**
     * 生效起始日期
     */
    public java.sql.Timestamp sxqsrq;

    /**
     * 生效截止日期
     */
    public java.sql.Timestamp sxjzrq;

    /**
     * 备注
     */
    public java.lang.String bz;

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
     * 获得指标代码
     */
    public String getZbdm() {
        return zbdm;
    }

    /**
     * 获得指标名称
     */
    public String getZbmc() {
        return zbmc;
    }

    /**
     * 获得指标值
     */
    public String getZbz() {
        return zbz;
    }

    /**
     * 获得生效起始日期
     */
    public Timestamp getSxqsrq() {
        return sxqsrq;
    }

    /**
     * 获得生效截止日期
     */
    public Timestamp getSxjzrq() {
        return sxjzrq;
    }

    /**
     * 获得备注
     */
    public String getBz() {
        return bz;
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
     * 赋值指标代码
     * @param zbdm 指标代码
     */
    public void setZbdm(String zbdm) {
        this.zbdm = zbdm;
    }

    /**
     * 赋值指标名称
     * @param zbmc 指标名称
     */
    public void setZbmc(String zbmc) {
        this.zbmc = zbmc;
    }

    /**
     * 赋值指标值
     * @param zbz 指标值
     */
    public void setZbz(String zbz) {
        this.zbz = zbz;
    }

    /**
     * 赋值生效起始日期
     * @param sxqsrq 生效起始日期
     */
    public void setSxqsrq(Timestamp sxqsrq) {
        this.sxqsrq = sxqsrq;
    }

    /**
     * 赋值生效截止日期
     * @param sxjzrq 生效截止日期
     */
    public void setSxjzrq(Timestamp sxjzrq) {
        this.sxjzrq = sxjzrq;
    }

    /**
     * 赋值备注
     * @param bz 备注
     */
    public void setBz(String bz) {
        this.bz = bz;
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
