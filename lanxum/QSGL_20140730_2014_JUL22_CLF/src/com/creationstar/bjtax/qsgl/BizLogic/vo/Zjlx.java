package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 证件类型代码表值对象
 */
public class Zjlx implements Serializable {

    /**
     * 证件类型代码
     */
    public java.lang.String zjlxdm;

    /**
     * 证件类型名称
     */
    public java.lang.String zjlxmc;

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
     * 获得证件类型代码
     */
    public String getZjlxdm() {
        return zjlxdm;
    }

    /**
     * 获得证件类型名称
     */
    public String getZjlxmc() {
        return zjlxmc;
    }

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

    /**
     * 赋值证件类型代码
     * @param zjlxdm 证件类型代码
     */
    public void setZjlxdm(String zjlxdm) {
        this.zjlxdm = zjlxdm;
    }

    /**
     * 赋值证件类型名称
     * @param zjlxmc 证件类型名称
     */
    public void setZjlxmc(String zjlxmc) {
        this.zjlxmc = zjlxmc;
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


}
