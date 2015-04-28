package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 国籍代码表值对象
 */
public class Gjdq implements Serializable {

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
     * 国籍或地区代码
     */
    public java.lang.String gjdqdm;

    /**
     * 国籍或地区名称
     */
    public java.lang.String gjdqmc;

    /**
     * 显示顺序
     */
    public java.lang.String xssx;

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
     * 获得国籍或地区代码
     */
    public String getGjdqdm() {
        return gjdqdm;
    }

    /**
     * 获得国籍或地区名称
     */
    public String getGjdqmc() {
        return gjdqmc;
    }

    /**
     * 获得显示顺序
     */
    public String getXssx() {
        return xssx;
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
     * 赋值国籍或地区代码
     * @param gjdqdm 国籍或地区代码
     */
    public void setGjdqdm(String gjdqdm) {
        this.gjdqdm = gjdqdm;
    }

    /**
     * 赋值国籍或地区名称
     * @param gjdqmc 国籍或地区名称
     */
    public void setGjdqmc(String gjdqmc) {
        this.gjdqmc = gjdqmc;
    }

    /**
     * 赋值显示顺序
     * @param xssx 显示顺序
     */
    public void setXssx(String xssx) {
        this.xssx = xssx;
    }


}
