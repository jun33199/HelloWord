package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 土地级次代码表值对象
 */
public class Tdjc implements Serializable {

    /**
     * 土地级次代码
     */
    private java.lang.String tdjcdm;

    /**
     * 土地级次名称
     */
    private java.lang.String tdjcmc;

    /**
     * 最后更新时间
     */
    public java.sql.Timestamp zhgxsj;

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
     * 处理方式
     */
    public java.lang.String clfs;

    /**
     * 获得最后更新时间
     */
    public Timestamp getZhgxsj() {
        return zhgxsj;
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
     * 获得处理方式
     */
    public String getClfs() {
        return clfs;
    }

    public String getTdjcdm() {

        return tdjcdm;
    }

    public String getTdjcmc() {

        return tdjcmc;
    }


    /**
     * 赋值最后更新时间
     * @param zhgxsj 最后更新时间
     */
    public void setZhgxsj(Timestamp zhgxsj) {
        this.zhgxsj = zhgxsj;
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

    /**
     * 赋值处理方式
     * @param clfs 处理方式
     */
    public void setClfs(String clfs) {
        this.clfs = clfs;
    }

    public void setTdjcdm(String tdjcdm) {

        this.tdjcdm = tdjcdm;
    }

    public void setTdjcmc(String tdjcmc) {

        this.tdjcmc = tdjcmc;
    }


}
