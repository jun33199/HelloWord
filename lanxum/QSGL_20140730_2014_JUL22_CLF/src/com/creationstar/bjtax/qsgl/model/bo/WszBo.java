package com.creationstar.bjtax.qsgl.model.bo;

import java.io.Serializable;

public class WszBo implements Serializable {
    /**
     * 完税证号
     */
    public String wszh;

    /**
     * 年度字别
     */
    public String ndzb;

    /**
     * 计算机代码
     */
    public String jsjdm;

    /**
     * 汇总起始日期
     */
    public String hzqsrq;

    /**
     * 汇总截止日期
     */
    public String hzjzrq;

    /**
     * 缴税方式
     */
    public String jsfs;

    /**
     * 缴税方式名称
     */
    public String jsfsmc;

    /**
     * 汇总方式
     */
    public String hzfs;

    /**
     * 汇总方式名称
     */
    public String hzfsmc;


    /**
     * 缴款凭证号
     */
    public String jkpzh;

    public String drpch;

    public String getDrpch() {
        return drpch;
    }

    public void setDrpch(String drpch) {
        this.drpch = drpch;
    }

    public String getJsjdm() {
        return jsjdm;
    }

    public String getNdzb() {
        return ndzb;
    }

    public String getWszh() {
        return wszh;
    }

    public String getHzfs() {
        return hzfs;
    }

    public String getHzjzrq() {
        return hzjzrq;
    }

    public String getHzqsrq() {
        return hzqsrq;
    }

    public String getJsfs() {
        return jsfs;
    }

    public String getJkpzh() {
        return jkpzh;
    }

    public String getHzfsmc() {
        return hzfsmc;
    }

    public String getJsfsmc() {
        return jsfsmc;
    }

    public void setJsjdm(String jsjdm) {
        this.jsjdm = jsjdm;
    }

    public void setNdzb(String ndzb) {
        this.ndzb = ndzb;
    }

    public void setWszh(String wszh) {
        this.wszh = wszh;
    }

    public void setHzfs(String hzfs) {
        this.hzfs = hzfs;
    }

    public void setHzjzrq(String hzjzrq) {
        this.hzjzrq = hzjzrq;
    }

    public void setHzqsrq(String hzqsrq) {
        this.hzqsrq = hzqsrq;
    }

    public void setJsfs(String jsfs) {
        this.jsfs = jsfs;
    }

    public void setJkpzh(String jkpzh) {
        this.jkpzh = jkpzh;
    }

    public void setHzfsmc(String hzfsmc) {
        this.hzfsmc = hzfsmc;
    }

    public void setJsfsmc(String jsfsmc) {
        this.jsfsmc = jsfsmc;
    }

}
